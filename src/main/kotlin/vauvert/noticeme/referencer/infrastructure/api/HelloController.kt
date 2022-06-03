package vauvert.noticeme.referencer.infrastructure.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/hello")
class HelloController {

    @GetMapping
    fun sayHello() : ResponseEntity<Any> = ResponseEntity.ok("HELLO")
}