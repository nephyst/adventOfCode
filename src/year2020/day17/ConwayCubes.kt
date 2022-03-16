package year2020.day17

import java.io.File

class ConwayCubesP2 {

    data class Point(val x: Int, val y: Int, val z: Int)

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            // x, y, z
            // line, character, z
            var grid: MutableSet<Point> = mutableSetOf()
            File("./src/year2020/day17/input").useLines {
                var x = 0;
                it.forEach { line ->
                    var y = 0;
                    line.forEach { char ->
                        if (char == '#') {
                            grid.add(Point(x, y, 0))
                        }
                        y++
                    }
                    x++
                }
            }

            println("Init | size: ${grid.size}")
            printGrid(grid)
            repeat(6) {
                println()
                grid = iterate(grid)
                println("Cycle ${it + 1} | size: ${grid.size}")
                printGrid(grid)
            }

            println("${grid.size}")

        }//main

        private fun printGrid(grid: Set<Point>) {
            val minMax: Pair<Point, Point> = getMinMax(grid)
            val min = minMax.first
            val max = minMax.second

            println("(${min.x}, ${min.y}) - (${max.x}, ${max.y})")
            for (z in min.z..max.z) {
                println("Z: $z")
                for (x in min.x..max.x) {
                    for (y in min.y..max.y) {
                        if (grid.contains(Point(x, y, z))) {
                            print("#")
                        } else {
                            print(".")
                        }
                    }
                    println()
                }
            }

        }

        private fun iterate(grid: Set<Point>): MutableSet<Point> {

            val minMax: Pair<Point, Point> = getMinMax(grid);
            val min = minMax.first
            val max = minMax.second

            val newGrid: MutableSet<Point> = mutableSetOf()
            for (z in (min.z - 1)..(max.z + 1)) {
                for (x in (min.x - 1)..(max.x + 1)) {
                    for (y in (min.y - 1)..(max.y + 1)) {
                        if (testPoint(grid, x, y, z)) {
                            newGrid.add(Point(x, y, z))
                        }
                    }
                }
            }

            return newGrid
        }

        private fun getMinMax(grid: Set<Point>): Pair<Point, Point> {
            val first = grid.first();
            var min = Point(first.x, first.y, first.z)
            var max = Point(first.x, first.y, first.z)

            grid.forEach { p ->
                if (p.x < min.x) {
                    min = min.copy(x = p.x)
                }
                if (p.y < min.y) {
                    min = min.copy(y = p.y)
                }
                if (p.z < min.z) {
                    min = min.copy(z = p.z)
                }

                if (p.x > max.x) {
                    max = max.copy(x = p.x)
                }
                if (p.y > max.y) {
                    max = max.copy(y = p.y)
                }
                if (p.z > max.z) {
                    max = max.copy(z = p.z)
                }
            }
            return Pair(min, max)
        }

        private fun testPoint(grid: Set<Point>, x: Int, y: Int, z: Int): Boolean {
            var count = 0
            var inactive = 0
            for (dz in -1..1) {
                for (dx in -1..1) {
                    for (dy in -1..1) {

                        if (dx != 0 || dy != 0 || dz != 0) {
                            if (grid.contains(Point(x + dx, y + dy, z + dz))) {
                                count++
                            } else {
                                inactive++
                            }
                        }

                    }
                }
            }

            val isActive = grid.contains(Point(x, y, z))
            val result = (isActive && count == 2) || count == 3
            println("Result: $result | Previous: $isActive | $count / $inactive ($x, $y, $z)")
            return result
        }

    }
}

//##.#....
//...#...#