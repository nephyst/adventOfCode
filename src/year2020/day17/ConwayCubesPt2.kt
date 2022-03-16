package year2020.day17

import java.io.File

class ConwayCubes {

    data class Point(val x: Int, val y: Int, val z: Int, val w: Int)

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            // x, y, z, w
            // line, character, 0, 0
            var grid: MutableSet<Point> = mutableSetOf()
            File("./src/year2020/day17/input").useLines {
                var x = 0;
                it.forEach { line ->
                    var y = 0;
                    line.forEach { char ->
                        if (char == '#') {
                            grid.add(Point(x, y, 0, 0))
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
            for (w in min.w..max.w) {
                for (z in min.z..max.z) {
                    println("z=$z, w=$w")
                    for (x in min.x..max.x) {
                        for (y in min.y..max.y) {
                            if (grid.contains(Point(x, y, z, w))) {
                                print("#")
                            } else {
                                print(".")
                            }
                        }
                        println()
                    }
                }
            }
        }

        private fun iterate(grid: Set<Point>): MutableSet<Point> {
            val minMax: Pair<Point, Point> = getMinMax(grid);
            val min = minMax.first
            val max = minMax.second

            val newGrid: MutableSet<Point> = mutableSetOf()
            for (w in (min.w - 1)..(max.w + 1)) {
                for (z in (min.z - 1)..(max.z + 1)) {
                    for (x in (min.x - 1)..(max.x + 1)) {
                        for (y in (min.y - 1)..(max.y + 1)) {
                            if (testPoint(grid, x, y, z, w)) {
                                newGrid.add(Point(x, y, z, w))
                            }
                        }
                    }
                }
            }

            return newGrid
        }

        private fun getMinMax(grid: Set<Point>): Pair<Point, Point> {
            val first = grid.first();
            var min = Point(first.x, first.y, first.z, first.w)
            var max = Point(first.x, first.y, first.z, first.w)

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
                if (p.w < min.w) {
                    min = min.copy(w = p.w)
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
                if (p.w > max.w) {
                    max = max.copy(w = p.w)
                }
            }
            return Pair(min, max)
        }

        private fun testPoint(grid: Set<Point>, x: Int, y: Int, z: Int, w: Int): Boolean {
            var count = 0
            var inactive = 0
            for (dw in -1..1) {
                for (dz in -1..1) {
                    for (dx in -1..1) {
                        for (dy in -1..1) {

                            if (dx != 0 || dy != 0 || dz != 0 || dw != 0) {
                                if (grid.contains(Point(x + dx, y + dy, z + dz, w + dw))) {
                                    count++
                                } else {
                                    inactive++
                                }
                            }

                        }
                    }
                }
            }

            val isActive = grid.contains(Point(x, y, z, w))
            val result = (isActive && count == 2) || count == 3
            //println("Result: $result | Previous: $isActive | $count / $inactive ($x, $y, $z, $w)")
            return result
        }

    }
}
