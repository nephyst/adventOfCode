package year2020.day7

import java.io.File

class HandyHaversacksPt2 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            // SourceBag contains TargetBag
            // map of SourceBag to List<Pair<TargetBag, Count>
            val bagMap: MutableMap<String, MutableList<Pair<String, Int>>> = mutableMapOf()

            File("./src/year2020/day7/input").useLines { lines ->
                lines.forEach { line ->
                    val split = line.removeSuffix(".").split(" contain ")
                    if (split[1] != "no other bags") {
                        val sourceBag = split[0].removeSuffix(" bags")
                        val targetBags = split[1].split(", ").map { it.removeSuffix("s").removeSuffix(" bag") }
                        targetBags.forEach { targetBag: String ->
                            val splitBag = targetBag.split(" ", limit = 2)
                            bagMap.getOrPut(sourceBag) { mutableListOf() }.add(Pair(splitBag[1], splitBag[0].toInt()))
                        }
                    }
                }
            }

            val bagList: MutableList<Pair<String, Int>> = mutableListOf(Pair("shiny gold", 1))
            val usedBagMap: MutableMap<String, Int> = mutableMapOf()
            while (bagList.isNotEmpty()) {
                val bag = bagList.removeAt(0)
                println("Removing $bag;")

                val name = bag.first
                val count = bag.second
                usedBagMap.merge(name, count, Int::plus)

                bagMap[name]?.forEach { newBag ->
                    val newerBag = Pair(newBag.first, newBag.second * count)
                    bagList.add(newerBag)
                    println("Adding $newerBag | ${newBag.second} * $count $name")
                }
            }

            println("Total Bags: ${usedBagMap.map { it.value }.sum() - 1}")
        }

    }
}