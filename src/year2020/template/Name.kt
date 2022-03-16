package year2020.day2

import java.io.File
import kotlin.streams.asStream

class Name {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val input = File("./src/year2020/day1/input").useLines {
                it.asStream().mapToInt { s -> Integer.parseInt(s) }.toArray()
            }

        }

    }
}