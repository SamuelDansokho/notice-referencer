package vauvert.noticeme.referencer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReferencerApplication

fun main(args: Array<String>) {
	runApplication<ReferencerApplication>(*args)
}
