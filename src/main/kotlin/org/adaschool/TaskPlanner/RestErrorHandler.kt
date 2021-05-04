package org.adaschool.TaskPlanner

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.multipart.support.MissingServletRequestPartException

@RestControllerAdvice
class RestErrorHandler {

    @ExceptionHandler(HttpMessageNotReadableException::class)
    private fun handleHTTPMessageNotReadable(e: HttpMessageNotReadableException): ResponseEntity<String> {
        return ResponseEntity(e.cause?.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MissingServletRequestPartException::class)
    private fun handleMissingServletRequestPart(e: MissingServletRequestPartException): ResponseEntity<String> {
        return ResponseEntity(e.cause?.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(RuntimeException::class)
    private fun handleRuntimeException(e: Exception) {
        throw e
    }
}