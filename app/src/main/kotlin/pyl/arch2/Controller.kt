package pyl.arch2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class Controller {

    @Autowired
    private lateinit var userRepo: UserRepo

    @PostMapping
    fun create(@RequestBody user: User): User {
        check(user.login)
        userRepo.save(user)
        return user
    }

    @GetMapping("/{login}")
    fun find(@PathVariable(value = "login") login: String): User {
        check(login)
        return userRepo.findUserByLogin(login).orElseThrow { IllegalArgumentException() }
    }

    @GetMapping
    fun findAll(): MutableIterable<User> {
        return userRepo.findAll()
    }

    @DeleteMapping("/{login}")
    fun delete(@PathVariable(value = "login") login: String) {
        check(login)
        return userRepo.deleteById(login)
    }

    @PutMapping
    fun update(@RequestBody user: User): User {
        check(user.login)
        return userRepo.save(user)
    }

    fun check(login: String) {
        if (LoginContext.get() != login) {
            throw NotAllowedException()
        }
    }
}