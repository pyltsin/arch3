package com.example.auth.demo

import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .anonymous() //allow anonymous access
                .and()
                .authorizeRequests()
                .antMatchers("/register**").permitAll()
                .antMatchers("/login2**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(inMemoryUserDetailsManager())
    }

    @Bean
    fun inMemoryUserDetailsManager(): InMemoryUserDetailsManager {
        return InMemoryUserDetailsManager()

    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}