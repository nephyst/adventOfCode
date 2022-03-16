package year2020.day5

import java.io.File

class BinaryBoarding {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val input = File("./src/year2020/day5/input").useLines {
                it.map { line ->
                    Pair(line, line.map { c -> c == 'B' || c == 'R' }.toList())
                }.toList()
            }

            var maxSeat = 0;
            for (ticket in input) {
                val row = binaryToDecimal(ticket.second.take(7))
                val col = binaryToDecimal(ticket.second.takeLast(3))
                val seat = row * 8 + col
                if (seat > maxSeat) maxSeat = seat
                println("${ticket.first}: row $row, column $col, seat ID $seat.")
            }

            println("Max seat: $maxSeat")
        }

        fun binaryToDecimal(bits: List<Boolean>): Int {
            var result = 0
            for (b in bits) {
                result = result shl 1
                if (b) result++
            }
            return result
        }

    }
}