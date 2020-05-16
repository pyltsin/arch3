package pyl.arch2

class LoginContext {
    companion object {
        private val login: ThreadLocal<String> = ThreadLocal()

        fun set(login: String) {
            this.login.set(login)
        }

        fun get(): String {
            return login.get()
        }
    }
}