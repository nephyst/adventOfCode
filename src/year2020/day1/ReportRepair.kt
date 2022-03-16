package year2020.day1

import java.io.File
import kotlin.streams.asStream

class ReportRepair {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input = File("./src/year2020/day1/input").useLines {
                it.asStream().mapToInt { s -> Integer.parseInt(s) }.sorted().toArray()
            }

            var ai = 0
            var bi = input.lastIndex

            var a = input[ai]
            var b = input[bi]

            var t = (a + b)
            while (t != 2020 || ai == bi) {
                println("$a + $b = $t")
                if (t > 2020) {
                    bi--
                    b = input[bi]
                } else {
                    ai++
                    a = input[ai]
                }
                t = (a + b)
            }

            println("$a + $b = $t $a * $b = ${a * b}")
        }
    }
}