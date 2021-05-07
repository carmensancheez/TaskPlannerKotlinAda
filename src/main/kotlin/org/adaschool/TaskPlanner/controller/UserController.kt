package org.adaschool.TaskPlanner.controller

import org.adaschool.TaskPlanner.controller.dto.UserDto
import org.adaschool.TaskPlanner.data.document.User
import org.adaschool.TaskPlanner.exceptions.UserNotFoundException
//import org.adaschool.TaskPlanner.exception.UserNotFoundException
import org.adaschool.TaskPlanner.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/users")
class UserController(@Autowired val usersService: UserService) {

    @PostMapping
    fun create(@RequestBody userDto: UserDto): User {
        return usersService.save(userDto)
    }

    @GetMapping
    fun getUsers(): List<User>? {
        return usersService.all()
    }

    @GetMapping("/{id}")
    fun findUserById(@PathVariable id: String): User? {
        return usersService.findUserById(id) ?: throw UserNotFoundException()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody userDto: UserDto): User? {
        return usersService.update(id, userDto)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) {
        usersService.delete(id)
    }

}