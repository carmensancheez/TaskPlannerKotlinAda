package org.adaschool.TaskPlanner.controller.dto

data class UserDto(
    val id:String,
    val name:String,
    val password:String,
    val email:String,
    val profilePictureUrl:String
)