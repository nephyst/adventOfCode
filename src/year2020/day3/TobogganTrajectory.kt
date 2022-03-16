package year2020.day3

import java.io.File
import kotlin.streams.asStream
import kotlin.streams.toList

class TobogganTrajectory {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val input: List<List<Boolean>> = File("./src/year2020/day3/input").useLines {
                it.asStream().map { s ->
                    s.chars().asLongStream().mapToObj { c ->
                        c == '#'.toLong()
                    }.toList()
                }.toList()
            }

            val lineWidth = input[0].size
            var row = 0
            var column = 0

            var treeCount = 0
            while (row < input.size - 1) {
                column = (column + 3) % lineWidth
                row++
                if (input[row][column]) {
                    treeCount++
                }
                printRow(input[row], column)
            }
            print("TreeCount: $treeCount")
        }

        private fun printRow(row: List<Boolean>, column: Int) {
            for (i in row.indices) {
                if (i == column) {
                    if (row[i]) print("X") else print("O")
                } else {
                    if (row[i]) print("#") else print(".")
                }
            }
            println("")
        }

    }
}