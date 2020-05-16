package pyl.arch2

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
@Repository
interface UserRepo : CrudRepository<User, String> {
    @Query("select * from Users s where s.login = :login")
    fun findUserByLogin(@Param("login") login: String): Optional<User>
}