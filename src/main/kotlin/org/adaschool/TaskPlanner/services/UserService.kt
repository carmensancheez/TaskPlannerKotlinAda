package org.adaschool.TaskPlanner.services

import org.adaschool.TaskPlanner.controller.dto.UserDto
import org.adaschool.TaskPlanner.model.User

interface UserService {


    //CRUD Operations

    //Create
    fun save(userDto: UserDto):User

    //Update
    fun update(userId: String,userDto: UserDto):User

    //Read specific reserve
    fun findUserById(userId: String):User?

    fun findByEmail(email: String):User?

    //Read all reserves
    fun all():List<User>

    //Delete
    fun delete(userId:String):Boolean
}