package pyl.arch2

import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Component
class ContextFilter : Filter {
    override fun doFilter(request: ServletRequest, response: ServletResponse?, chain: FilterChain) {
        val header = (request as HttpServletRequest).getHeader("login")
        LoginContext.set(header)
        chain.doFilter(request, response)
    }
}