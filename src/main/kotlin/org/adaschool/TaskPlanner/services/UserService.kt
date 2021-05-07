package org.adaschool.TaskPlanner.services

import org.adaschool.TaskPlanner.controller.dto.UserDto
import org.adaschool.TaskPlanner.data.document.User

interface UserService {

    fun save(userDto: UserDto):User

    fun update(userId: String,userDto: UserDto):User

    fun findUserById(userId: String):User?

    fun findByEmail(email: String):User?

    fun all():List<User>

    fun delete(userId: String)
}