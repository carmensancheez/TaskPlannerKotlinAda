package org.adaschool.TaskPlanner.services

import org.adaschool.TaskPlanner.controller.dto.UserDto
import org.adaschool.TaskPlanner.model.User
import org.adaschool.TaskPlanner.utils.RoleEnum
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong

@Service
class UserServiceHashMap:UserService {

    private val users = HashMap<String,User>()

    private val nextOid = AtomicLong()


    override fun save(userDto: UserDto): User {
        val user = User(nextOid.incrementAndGet(),userDto)
        users[userDto.id] = user
        return user

    }

    override fun update(userId: String, userDto: UserDto): User {
        if (users.containsKey(userId)){
            val user = users[userId]
            users[userId] = User(user!!.oid,userDto)
        }
        return users[userId]!!

    }

    override fun findUserById(userId: String): User? {
        return if (users.containsKey(userId))
            users[userId]
        else
            null

    }

    override fun findByEmail(email: String): User? {
        return users.values.find { email == it.email }
    }

    override fun all(): List<User> {
        return users.values.toList()

    }

    override fun delete(userId: String): Boolean {
        return users.remove(userId) != null
    }

}