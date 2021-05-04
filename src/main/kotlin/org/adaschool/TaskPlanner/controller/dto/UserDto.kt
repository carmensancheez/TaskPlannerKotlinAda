package org.adaschool.TaskPlanner.controller.dto

import org.adaschool.TaskPlanner.utils.RoleEnum

data class UserDto(
    val id:String,
    val name:String,
    val password:String,
    val email:String,
    val profilePictureUrl:String,
    val roles: List<RoleEnum>


)