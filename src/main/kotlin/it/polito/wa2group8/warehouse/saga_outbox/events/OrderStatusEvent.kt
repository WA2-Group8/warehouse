package it.polito.wa2group8.warehouse.saga_outbox.events

enum class OrderStatusEvent
{
    STARTED,
    ACCEPTED,
    REJECTED,
    COMPENSATING,
    COMPENSATED
}
