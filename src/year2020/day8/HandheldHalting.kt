package year2020.day8

import java.io.File
import kotlin.streams.asStream

class HandheldHalting {

    data class Instruction(val instruction: String, val argument: Int, var done: Boolean = false)

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val input = File("./src/year2020/day8/input").useLines {
                val regex = Regex("(\\w{3}) ([\\+\\-])(\\d+)")
                it.map { line ->
                    val (instruction, sign, value) = regex.find(line)!!.destructured
                    var argument = value.toInt()
                    if (sign == "-") argument *= -1
                    Instruction(instruction, argument)
                }.toList()
            }

            var i = 0;
            var acc = 0
            var ins = input[i]
            while (!ins.done) {
                when (ins.instruction) {
                    "acc" -> {
                        println("$i | ${ins.instruction} ${ins.argument} | acc $acc -> ${acc + ins.argument}")
                        acc += ins.argument
                        i++
                    }
                    "jmp" -> {
                        println("$i | ${ins.instruction} ${ins.argument} |   i $i -> ${i + ins.argument}")
                        i += ins.argument
                    }
                    "nop" -> {
                        println("$i | ${ins.instruction} ${ins.argument} |   i $i -> ${i + 1}")
                        i++
                    }
                }
                ins.done = true
                ins = input[i]
            }
        }
    }
}