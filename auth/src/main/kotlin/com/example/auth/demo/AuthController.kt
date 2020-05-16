package com.example.auth.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class AuthController {
    @Autowired
    private lateinit var inMemoryUserDetailsManager: InMemoryUserDetailsManager
    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @PostMapping("/register")
    fun register(@RequestBody user: CustomUser) {
        inMemoryUserDetailsManager.createUser(User(user.login, user.password, arrayListOf()))
    }

    @GetMapping("/login")
    fun login(): String {
        return "you are login"
    }

    @PostMapping("/login2")
    fun login(@RequestBody user: CustomUser, request: HttpServletRequest): String {
        val token = UsernamePasswordAuthenticationToken(user.login, user.password)
        val authenticate = authenticationManager.authenticate(token)
        val context = SecurityContextHolder.getContext()
        context.authentication=authenticate
        val session = request.getSession(true)
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, context)
        return "you are login"
    }


    @GetMapping("/auth")
    fun auth(response: HttpServletResponse) {
        response.addHeader("Login", SecurityContextHolder.getContext().authentication.name)
    }

    data class CustomUser(val login: String, val password: String)
}