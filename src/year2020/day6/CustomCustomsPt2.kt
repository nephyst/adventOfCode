package year2020.day6

import java.io.File

class CustomCustomsPt2 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val answerSets: MutableList<Set<Char>> = mutableListOf()

            File("./src/year2020/day6/input").useLines { lines ->
                var currentAnswers: Set<Char>? = null
                lines.forEach { line ->
                    if (line == "") {
                        answerSets.add(checkNotNull(currentAnswers))
                        currentAnswers = null
                    } else {
                        if (currentAnswers == null) {
                            currentAnswers = line.toSet()
                        } else {
                            currentAnswers = currentAnswers?.intersect(line.toSet())
                        }
                    }
                }
                answerSets.add(checkNotNull(currentAnswers))
            }

            println("Total yes answers: ${answerSets.map{ it.count() }.sum()}")
        }
    }
}