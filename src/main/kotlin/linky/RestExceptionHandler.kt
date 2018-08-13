package linky

import linky.infra.ValidationFailed
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(ValidationFailed::class)
    fun handleValidation(ex: ValidationFailed): ResponseEntity<String> {
        return ResponseEntity<String>(ex.message, HttpStatus.BAD_REQUEST)
    }
}