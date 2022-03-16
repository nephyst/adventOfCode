package year2020.day6

import java.io.File
import kotlin.streams.asStream

class CustomCustoms {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val answerCounts: MutableList<Map<Char, Int>> = mutableListOf()

            File("./src/year2020/day6/inputTest").useLines { lines ->
                var currentCounts: Map<Char, Int> = mapOf()
                lines.forEach { line ->
                    currentCounts = if (line == "") {
                        answerCounts.add(currentCounts)
                        mutableMapOf()
                    } else {
                        val newCounts = line.toList().groupingBy { it }.eachCount().toMap()
                        (currentCounts.keys + newCounts.keys)
                            .associateWith { (currentCounts[it] ?: 0) + (newCounts[it] ?: 0) }
                    }
                }
                answerCounts.add(currentCounts)
            }

            println("Total yes answers: ${answerCounts.map{ it.keys.distinct().count() }.sum()}")
        }
    }
}