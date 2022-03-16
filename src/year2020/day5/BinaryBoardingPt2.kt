package year2020.day5

import java.io.File

class BinaryBoardingPt2 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val input = File("./src/year2020/day5/input").useLines {
                it.map { line ->
                    Pair(line, line.map { c -> c == 'B' || c == 'R' }.toList())
                }.toList()
            }

            val seats: MutableList<Int> = mutableListOf()
            for (ticket in input) {
                val row = binaryToDecimal(ticket.second.take(7))
                val col = binaryToDecimal(ticket.second.takeLast(3))
                val seat = row * 8 + col
                seats.add(seat)
                println("${ticket.first}: row $row, column $col, seat ID $seat.")
            }
            val sortedSeats = seats.sorted()
            var last: Int? = null
            for (seat in sortedSeats) {
                if (last != null) {
                    if (seat - last == 2) {
                        println("Your seat is ${seat-1}")
                    }
                }
                last = seat
            }
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