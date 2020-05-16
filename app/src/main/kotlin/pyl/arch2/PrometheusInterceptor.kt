package pyl.arch2

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.DistributionSummary
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.Timer
import io.prometheus.client.Histogram
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.Duration
import javax.annotation.PostConstruct
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class PrometheusFilter : Filter {

    @Autowired
    private lateinit var meterRegistry: MeterRegistry

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse


        Timer.builder("app_request_latency_seconds")
                .description("Application Request Latency")
                .tag("method", httpRequest.method)
                .tag("endpoint", httpRequest.requestURI)
                .sla(Duration.ofMillis(50))
                .register(meterRegistry)
                .record {
                    chain!!.doFilter(request, response)
                }

        Counter.builder("app_request_count")
                .description("Application Request Count")
                .tag("method", httpRequest.method)
                .tag("endpoint", httpRequest.requestURI)
                .tag("http_status", httpResponse.status.toString())
                .register(meterRegistry)
                .increment()
    }
}