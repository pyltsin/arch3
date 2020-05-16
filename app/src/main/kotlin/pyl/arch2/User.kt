package pyl.arch2

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonSetter
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("users")
data class User(val firstName: String,
                val secondName: String,
                val login: String,
                @Id
                @Column("id")
                @JsonIgnore
                var ids: String? = null) : Persistable<String> {

    @JsonIgnore
    override fun isNew(): Boolean {
        return ids == null
    }


    override fun getId(): String? {
        if (ids == null) {
            ids = UUID.randomUUID().toString()
        }
        return ids
    }

    @JsonSetter
    fun setId(id: String) {
        ids = id
    }
}