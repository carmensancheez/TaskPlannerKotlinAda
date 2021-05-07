package org.adaschool.TaskPlanner.data.document

import org.adaschool.TaskPlanner.controller.dto.UserDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.crypto.bcrypt.BCrypt

@Document
class User(
    @Id
    var id: String?,
    var name: String,
    var email: String,
    var profilePictureUrl: String,
    var passwordHash: String,
    val roles: List<RoleEnum>
)  {

    fun update(userDto: UserDto) {
        name = userDto.name
        email = userDto.email
        passwordHash =  BCrypt.hashpw(userDto.password, BCrypt.gensalt())
    }

    constructor(userDto: UserDto) : this(
        null,
        userDto.name,
        userDto.email,
        userDto.profilePictureUrl,
        BCrypt.hashpw(userDto.password, BCrypt.gensalt()),
        listOf(RoleEnum.USER),
    )

}