package year2020.day3

import java.io.File
import kotlin.streams.asStream
import kotlin.streams.toList

class TobogganTrajectoryPt2 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val r1d1 = countTrees(1, 1)
            val r3d1 = countTrees(3, 1)
            val r5d1 = countTrees(5, 1)
            val r7d1 = countTrees(7, 1)
            val r1d2 = countTrees(1, 2)

            println(r1d1 * r3d1 * r5d1 * r7d1 * r1d2)

        }

        fun countTrees(right: Int, down: Int): Long {
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

            var treeCount: Long = 0
            while (row < input.size - 1) {
                column = (column + right) % lineWidth
                row += down
                if (row >= input.size) break
                if (input[row][column]) {
                    treeCount++
                }
                printRow(input[row], column)
            }
            println("TreeCount: $treeCount")
            return treeCount
        }

        fun printRow(row: List<Boolean>, column: Int) {
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