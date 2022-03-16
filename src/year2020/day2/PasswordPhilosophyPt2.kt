package year2020.day2

import java.io.File

class PasswordPhilosophyPt2 {

    data class Password(val password: String, val policy: Char, val i: Int, val j: Int)

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val regex = Regex("(\\d{1,2})-(\\d{1,2}) ([a-z]): ([a-z]*)")

            val input = File("./src/year2020/day2/inputTest").useLines {
                it.map { s ->
                    val match = regex.find(s)
                    val (i, j, policy, password) = match!!.destructured
                    return@map Password(password, policy.first(), Integer.parseInt(i) - 1, Integer.parseInt(j) - 1)
                }.toList()
            }
            println(input.size)
            val count = input.stream().filter { password -> matches(password) }.count()
            println("$count matching passwords")
        }

        private fun matches(password: Password): Boolean {
            var pw = password.password
            val matches = (pw[password.i] == password.policy).xor(pw[password.j] == password.policy)
            val m = if (matches) "Y" else " "
            println("[$m] ${password.password} : Policy ${password.policy} ${password.i}-${password.j} : Actual ${pw[password.i]}-${pw[password.j]}")
            return matches
        }

    }
}