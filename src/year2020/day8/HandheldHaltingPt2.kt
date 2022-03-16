package year2020.day8

import java.io.File
import kotlin.streams.asStream

class HandheldHaltingPt2 {

    data class Instruction(var instruction: String, val argument: Int, var done: Boolean = false)

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

            var changedLine = 0
            while (changedLine < input.size) {
                val instruction = input[changedLine]

                swapInstruction(instruction)

                if (instruction.instruction != "acc") {
                    val result = testProgram(input)
                    if (result == null) {
                        //println("$changedLine null")
                    } else {
                        println("$changedLine $result")
                    }
                } else {
                    //println("$changedLine ignored | acc")
                }

                swapInstruction(instruction)
                input.forEach { it.done = false }

                changedLine++
            }
        }

        private fun swapInstruction(instruction: Instruction) {
            if (instruction.instruction == "jmp") {
                instruction.instruction = "nop"
            } else if (instruction.instruction == "nop") {
                instruction.instruction = "jmp"
            }
        }

        private fun testProgram(input: List<Instruction>): Int? {
            var i = 0
            var acc = 0
            var ins = input[0]
            while (!ins.done) {
                when (ins.instruction) {
                    "acc" -> {
                        //println("$i | ${ins.instruction} ${ins.argument} | acc $acc -> ${acc + ins.argument}")
                        acc += ins.argument
                        i++
                    }
                    "jmp" -> {
                        //println("$i | ${ins.instruction} ${ins.argument} |   i $i -> ${i + ins.argument}")
                        i += ins.argument
                    }
                    "nop" -> {
                        //println("$i | ${ins.instruction} ${ins.argument} |   i $i -> ${i + 1}")
                        i++
                    }
                }
                ins.done = true
                if (i >= input.size) {
                    return acc;
                }
                ins = input[i]
            }
            return null;
        }

    }
}