package pyl.arch2

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "not allowed")
class NotAllowedException : RuntimeException("not allowed") {

}