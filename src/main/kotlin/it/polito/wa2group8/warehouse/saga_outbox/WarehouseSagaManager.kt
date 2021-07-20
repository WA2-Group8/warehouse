package it.polito.wa2group8.warehouse.saga_outbox

/*
import io.debezium.config.Configuration
import io.debezium.data.Envelope
import io.debezium.embedded.Connect
import io.debezium.engine.DebeziumEngine
import io.debezium.engine.RecordChangeEvent
import io.debezium.engine.format.ChangeEventFormat
import it.polito.wa2group8.warehouse.domain.*
import it.polito.wa2group8.warehouse.repositories.ProductRepository
import it.polito.wa2group8.warehouse.repositories.ProductWarehouseRepository
import it.polito.wa2group8.warehouse.repositories.WalletOutboxRepository
import it.polito.wa2group8.warehouse.repositories.WarehouseRepository
import it.polito.wa2group8.warehouse.saga_outbox.events.*
import org.apache.kafka.connect.data.Struct
import org.apache.kafka.connect.source.SourceRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

/**
 * This class handle orders (warehouse side) according patterns Saga and Outbox
 */
@Transactional
@Component
class WarehouseSagaManager(
    private val productRepository: ProductRepository,
    private val warehouseRepository: WarehouseRepository,
    private val pwRepository: ProductWarehouseRepository,
    private val warehouseOutboxRepository: WalletOutboxRepository,
    private val kafkaTemplate: KafkaTemplate<String, String>,
    warehouseConnectorConfiguration: Configuration
)
{
    private val executor: Executor = Executors.newSingleThreadExecutor()
    //Init a Debezium engine
    private var debeziumEngine: DebeziumEngine<RecordChangeEvent<SourceRecord>> =
        DebeziumEngine.create(ChangeEventFormat.of(Connect::class.java))
            .using(warehouseConnectorConfiguration.asProperties())
            .notifying { sourceRecordRecordChangeEvent: RecordChangeEvent<SourceRecord> ->
                //Set the function to run every time the debezium engine detects a change
                this.handleOutboxChangeEvent(sourceRecordRecordChangeEvent)
            }
            .build()

    @PostConstruct
    private fun startDebeziumEngine() = executor.execute(debeziumEngine)

    @PreDestroy
    private fun stopDebeziumEngine() = debeziumEngine.close()

    /* --------------------------- SAGA MANAGER --------------------------- */

    fun rejectRequest(request: OrderEventRequest)
    {
        warehouseOutboxRepository.save(WarehouseOutbox.createRejectedWarehouseOutboxOutbox(request))
        println("[WAREHOUSE] REJECTED ${request.orderId}")
    }

    fun acceptRequest(request: OrderEventRequest, wallet: Wallet)
    {
        wallet.currentAmount -= request.amount.toBigDecimal()
        //walletRepository.updateAmount(wallet.currentAmount, wallet.getId()!!)
        walletRepository.save(wallet)
        walletOutboxRepository.save(WalletOutbox.createAcceptedWalletOutbox(request))
        println("[WAREHOUSE] ACCEPTED ${request.orderId}")
    }

    fun compensateRequest(request: OrderEventRequest, wallet: Wallet)
    {
        wallet.currentAmount += request.amount.toBigDecimal()
        //walletRepository.updateAmount(wallet.currentAmount, wallet.getId()!!)
        walletRepository.save(wallet)
        walletOutboxRepository.save(WalletOutbox.createCompensatedWalletOutbox(request))
        println("[WAREHOUSE] COMPENSATED ${request.orderId}")
    }

    fun handleMessageFromKafka(request: OrderEventRequest)
    {
        val furtherProcessing = this.checkIfAdditionalProcessingIsRequired(request) ?: return

        var bIsFirst: Boolean = true
        var totPrice: Double = 0.0
        var warehousesSet: HashSet<ProductWarehouse> = hashSetOf()
        for (purchasedProduct in request.productsList)
        {
            val p = productRepository.findByProductIdAndPrice(purchasedProduct.productId, purchasedProduct.price.toBigDecimal())
            if (p == null)
            {
                this.rejectRequest(request)
                return
            }
            totPrice += purchasedProduct.price
            val warehouses= pwRepository.findAllByProductIdAndQuantity(purchasedProduct.productId, purchasedProduct.quantity)
            if (warehouses.isEmpty())
            {
                this.rejectRequest(request)
                return
            }

            if (!bIsFirst)
            {
                warehousesSet.retainAll(warehouses)
                if (warehousesSet.isEmpty())
                {
                    this.rejectRequest(request)
                    return
                }
            }
            else
            {
                warehousesSet = warehouses.toHashSet()
                bIsFirst = false
            }
        }
        if (totPrice != request.amount)
        {
            this.rejectRequest(request)
            return
        }

        //If here, request requires further processing
        val customer = customerRepository.findByUsername(request.username)
        if (customer == null) //Customer doesn't have any wallet
        {
            if (request.status == OrderStatusEvent.COMPENSATING)
                throw RuntimeException("Logic error in wallet detected in handleMessageFromKafka")
            this.rejectRequest(request)
            return
        }
        val wallet = walletRepository.findFirstByCustomerAndCurrentAmountGreaterThanEqual(customer, request.amount.toBigDecimal())
        when
        {
            wallet == null -> rejectRequest(request)
            furtherProcessing == OrderStatusEvent.STARTED -> this.acceptRequest(request, wallet)
            else -> this.compensateRequest(request, wallet)
        }
    }

    /* --------------------------- DEBEZIUM LISTENER AND OUTBOX PATTERN --------------------------- */

    private fun checkIfAdditionalProcessingIsRequired(request: OrderEventRequest): OrderStatusEvent?
    {
        val outboxEntry = walletOutboxRepository.findByOrderId(request.orderId)
        if (outboxEntry != null)
        {
            //If here, wallet has already processed the request...
            if (request.status == OrderStatusEvent.STARTED)
            {
                //If here, request is asking me to start order processing...
                //However, if here, wallet has already processed the request so simply resend computed response
                this.produceMessage(TO_ORDER_TOPIC, outboxEntry.toOrderMsg)
                return null
            }
            if (request.status == OrderStatusEvent.REJECTED && outboxEntry.walletSagaStatus == OrderStatusEvent.REJECTED)
            {
                //If here, request is asking me to reject order processing...
                //However, if here, wallet has already processed the request so simply resend computed response
                this.produceMessage(TO_ORDER_TOPIC, outboxEntry.toOrderMsg)
                return null
            }
            if (request.status == OrderStatusEvent.COMPENSATING && outboxEntry.walletSagaStatus == OrderStatusEvent.COMPENSATED)
            {
                //If here, request is asking me to compensate previous order processing...
                //However, if here, wallet has already compensated the request so simply resend computed response
                this.produceMessage(TO_ORDER_TOPIC, outboxEntry.toOrderMsg)
                return null
            }
        }
        else if (request.status == OrderStatusEvent.REJECTED || request.status == OrderStatusEvent.COMPENSATING)
        {
            //If here, wallet doesn't processed the request yet.
            //Nonetheless, request is asking to reject the order, so simply reject order.
            this.rejectRequest(request)
            return null
        }

        return if (request.status == OrderStatusEvent.STARTED) OrderStatusEvent.STARTED else OrderStatusEvent.COMPENSATING
    }

    private fun handleOutboxChangeEvent(sourceRecordRecordChangeEvent: RecordChangeEvent<SourceRecord>)
    {
        val sourceRecord = sourceRecordRecordChangeEvent.record()
        val sourceRecordChangeValue: Struct = sourceRecord.value() as Struct
        val operation: Envelope.Operation = Envelope.Operation.forCode(sourceRecordChangeValue.get(Envelope.FieldName.OPERATION) as String)

        //Handling only Update and Insert operations
        if (operation === Envelope.Operation.CREATE || operation === Envelope.Operation.UPDATE)
        {
            //Get info about table AFTER the operation performed on it
            val structAfter: Struct = sourceRecordChangeValue.get(Envelope.FieldName.AFTER) as Struct

            //Get messages to send
            val toOrderMsgAfter = structAfter[RESPONSE_TO_ORDER_CLN] as String

            //Send messages by kafka
            this.produceMessage(TO_ORDER_TOPIC, toOrderMsgAfter)
        }
    }

    /* --------------------------- KAFKA MANAGER --------------------------- */

    private fun produceMessage(topic: String, message: String)
    {
        println("[WAREHOUSE] sending $topic-$message")
        kafkaTemplate.send(topic, message)
    }

    @KafkaListener(topics = [FROM_ORDER_TOPIC])
    fun consumeFromOrder(message: String)
    {
        val request = OrderEventRequest.createByString(message)
        println("[WAREHOUSE] received $request")
        this.handleMessageFromKafka(request)
    }
}

 */

