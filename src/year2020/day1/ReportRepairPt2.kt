package year2020.day1

import java.io.File
import kotlin.streams.asStream

class ReportRepairPt2 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input =
                File("./src/year2020/day1/input").useLines { it.asStream().mapToInt { s -> Integer.parseInt(s) }.toArray() }

            a@ for (a in input) {
                for (b in input) {
                    for (c in input) {
                        if (a == b || a == c || b == c) continue

                        val t = a + b + c
                        println("$a + $b + $c = $t")
                        if (t == 2020) {
                            println("$a * $b * $c = ${a * b * c}")
                            break@a
                        }
                    }
                }
            }

        }
    }
}