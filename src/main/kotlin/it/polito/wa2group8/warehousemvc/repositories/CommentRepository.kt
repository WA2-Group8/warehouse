package it.polito.wa2group8.warehousemvc.repositories

import it.polito.wa2group8.warehousemvc.domain.Comment
import org.springframework.data.repository.CrudRepository

interface CommentRepository : CrudRepository<Comment, Long> {
}