package br.com.sample.application.vo

import java.time.Instant

class ErrorResponse(val timestamp: Instant, val status: Int, val error: String, val message: String)