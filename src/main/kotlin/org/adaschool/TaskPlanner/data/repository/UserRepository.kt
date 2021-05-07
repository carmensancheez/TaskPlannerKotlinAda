package org.adaschool.TaskPlanner.data.repository

import org.adaschool.TaskPlanner.data.document.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository: MongoRepository<User, String> {

    fun findUserByEmail(email: String): User?

}