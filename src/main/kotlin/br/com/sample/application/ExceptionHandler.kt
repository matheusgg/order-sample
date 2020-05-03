package br.com.sample.application

import br.com.sample.application.vo.ErrorResponse
import br.com.sample.domain.exception.IllegalOrderChangeException
import br.com.sample.domain.exception.OrderNotFoundException
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.Instant.now

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(IllegalOrderChangeException::class)
    fun handleIllegalOrderChangeException(exception: IllegalOrderChangeException) =
        ErrorResponse(now(), BAD_REQUEST.value(), BAD_REQUEST.reasonPhrase, exception.message)

    @ExceptionHandler(OrderNotFoundException::class)
    fun handleOrderNotFoundException(exception: OrderNotFoundException) =
        ErrorResponse(now(), NOT_FOUND.value(), NOT_FOUND.reasonPhrase, exception.message)
}