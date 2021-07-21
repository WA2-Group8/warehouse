package it.polito.wa2group8.warehouse.repositories

import it.polito.wa2group8.warehouse.domain.Comment
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.LockModeType

@Repository
interface CommentRepository : CrudRepository<Comment, Long>
{
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    override fun findAll(): MutableIterable<Comment>

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    override fun findById(id: Long): Optional<Comment>

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    override fun <S : Comment?> save(entity: S): S
}
