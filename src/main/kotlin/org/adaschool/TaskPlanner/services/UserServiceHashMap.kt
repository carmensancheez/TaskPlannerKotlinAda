package org.adaschool.TaskPlanner.services

import org.adaschool.TaskPlanner.controller.dto.UserDto
import org.adaschool.TaskPlanner.data.document.User
import org.adaschool.TaskPlanner.data.document.RoleEnum
import org.springframework.security.crypto.bcrypt.BCrypt
import java.util.concurrent.atomic.AtomicLong

class UserServiceHashMap:UserService {

    private val users = HashMap<String,User>()

    private val nextOid = AtomicLong()

    init {
        createSampleUser()
    }

    private fun createSampleUser(){
        val id = nextOid.incrementAndGet().toString()
        val user = User(
            id,
            "David",
            "davidcab11@gmail.com",
            "https://www.imgur.com/kotlin-image",
            BCrypt.hashpw("passw0rd",BCrypt.gensalt()),
            listOf(RoleEnum.USER)
        )
        users[id] = user
    }


    override fun save(userDto: UserDto): User {
        val user = User( userDto)
        user.id = nextOid.incrementAndGet().toString()
        users[userDto.id] = user
        return user
    }

    override fun update(userId: String, userDto: UserDto): User {
        if (users.containsKey(userId)) {
            users[userId] = User( userDto)
            users[userId]!!.id = userId
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

    override fun delete(userId: String) {
        users.remove(userId) != null
    }
}