package it.polito.wa2group8.warehouse.repositories

import it.polito.wa2group8.warehouse.domain.Comment
import org.springframework.data.repository.CrudRepository

interface CommentRepository : CrudRepository<Comment, Long> {
}