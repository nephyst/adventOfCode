package year2020.day7

import java.io.File
import kotlin.streams.asStream

class HandyHaversacks {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            // SourceBag contains TargetBag
            // map of TargetBag to List<SourceBag>
            val bagMap: MutableMap<String, MutableList<String>> = mutableMapOf()

            File("./src/year2020/day7/input").useLines { lines ->
                lines.forEach { line ->
                    val split = line.removeSuffix(".").split(" contain ")
                    if (split[1] != "no other bags") {
                        val sourceBag = split[0].removeSuffix(" bags")
                        val targetBags = split[1].split(", ").map { it.removeSuffix("s").removeSuffix(" bag").substringAfter(" ") }
                        targetBags.forEach { targetBag ->
                            bagMap.getOrPut(targetBag) { mutableListOf() }.add(sourceBag)
                        }
                    }
                }
            }

            val bagList : MutableList<String> = mutableListOf("shiny gold")
            val usedBagSet : MutableSet<String> = mutableSetOf()
            while (bagList.isNotEmpty()) {
                val bag = bagList.removeAt(0)
                usedBagSet.add(bag)
                val newBags = bagMap[bag]
                println("Removing $bag; Adding $newBags; ${usedBagSet.size}")
                bagList.addAll(bagMap[bag] ?: listOf())
            }
            usedBagSet.remove("shiny gold")
            println("BagCount: ${usedBagSet.size} | $usedBagSet")
        }

    }
}

//light red bags contain 1 bright white bag, 2 muted yellow bags.
//dark orange bags contain 3 bright white bags, 4 muted yellow bags.
//bright white bags contain 1 shiny gold bag.