package it.polito.wa2group8.warehouse.repositories

import it.polito.wa2group8.warehouse.domain.Comment
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : CrudRepository<Comment, Long>
