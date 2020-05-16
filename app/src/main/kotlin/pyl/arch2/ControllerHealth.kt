package pyl.arch2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ControllerHealth {

    @GetMapping("/health")
    fun healthCheck(): Response {
        return Response("OK")
    }

    @GetMapping("/health2")
    fun healthCheck2(): Response {
        return Response("OK")
    }

    data class Response(val status:String)
}