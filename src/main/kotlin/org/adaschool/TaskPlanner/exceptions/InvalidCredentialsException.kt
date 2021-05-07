package org.adaschool.TaskPlanner.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Invalid Credentials")
class InvalidCredentialsException: RuntimeException()