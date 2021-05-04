package org.adaschool.TaskPlanner.model

import org.adaschool.TaskPlanner.controller.dto.UserDto
import org.adaschool.TaskPlanner.utils.RoleEnum
import org.springframework.security.crypto.bcrypt.BCrypt
import javax.annotation.security.DeclareRoles

data class User(
    val oid:Long,
    val id:String,
    val name:String,
    val passwordHash:String,
    val email:String,
    val profilePictureUrl:String,
    val roles: List<RoleEnum>


)
{
    constructor(oid: Long,userDto: UserDto):
            this(
                oid,
                userDto.id,
                userDto.name,
                BCrypt.hashpw("password1", BCrypt.gensalt()),
                userDto.email,
                userDto.profilePictureUrl,
                listOf(RoleEnum.ADMIN))

}