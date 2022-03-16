package year2020.day2

import java.io.File

class PasswordPhilosophy {

    data class Password(val password: String, val policy: Int, val min: Int, val max: Int)

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val regex = Regex("(\\d{1,2})-(\\d{1,2}) ([a-z]): ([a-z]*)")

            val input = File("./src/year2020/day2/input").useLines {
                it.map { s ->
                    val match = regex.find(s)
                    val (min, max, policy, password) = match!!.destructured
                    return@map Password(password, policy.first().toInt(), Integer.parseInt(min), Integer.parseInt(max))
                }.toList()
            }
            println(input.size)
            val count = input.stream().filter { password -> matches(password) }.count()
            println("$count matching passwords")
        }

        private fun matches(password: Password): Boolean {
            val count = password.password.chars().filter { c -> c == password.policy }.count()
            val matches = password.min <= count && count <= password.max
            val m = if (matches) "Y" else " "
            println("[$m] ${password.password} : Actual $count : Policy ${password.min}-${password.max} : ${password.policy.toChar()}")
            return matches
        }
    }
}