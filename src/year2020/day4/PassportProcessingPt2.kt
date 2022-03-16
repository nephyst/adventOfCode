package year2020.day4

import java.io.File

class PassportProcessingPt2 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val input = File("./src/year2020/day4/input").useLines { it.toList() }

            var document: MutableMap<String, String> = mutableMapOf()
            val documents: MutableList<Map<String, String>> = mutableListOf(document)

            for (line in input) {
                if (line == "") {
                    document = mutableMapOf()
                    documents.add(document)
                } else {
                    document.putAll(line.split(" ").associate {
                        val (key, value) = it.split(":")
                        key to value
                    })
                }
            }

            println("${documents.filter { isValid(it) }.count()} valid documents")

        }

        private val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid") //cid
        private fun isValid(doc: Map<String, String>): Boolean {

            if (!doc.keys.containsAll(requiredFields)) {
                println("Missing ${requiredFields.subtract(doc.keys)} | $doc")
                return false
            }

//            byr (Birth Year) - four digits; at least 1920 and at most 2002.
            if (!minMax(doc, "byr", doc.getValue("byr").toInt(), 1920, 2002)) {
                return false
            }

//            iyr (Issue Year) - four digits; at least 2010 and at most 2020.
            if (!minMax(doc, "iyr", doc.getValue("iyr").toInt(), 2010, 2020)) {
                return false
            }

//            eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
            if (!minMax(doc, "eyr", doc.getValue("eyr").toInt(), 2020, 2030)) {
                return false
            }

//            hgt (Height) - a number followed by either cm or in:
//            If cm, the number must be at least 150 and at most 193.
//            If in, the number must be at least 59 and at most 76.
            doc.getValue("hgt").let {
                if (it.length > 2) {
                    val unit = it.takeLast(2)
                    val value = it.take(it.length - 2).toInt()
                    when (unit) {
                        "cm" -> {
                            if (!minMax(doc, "hgt", value, 150, 193, it)) {
                                return false
                            }
                        }
                        "in" -> {
                            if (!minMax(doc, "hgt", value, 59, 76, it)) {
                                return false
                            }
                        }
                        else -> {
                            println("hgt unit invalid : $unit | $doc")
                            return false
                        }
                    }
                } else {
                    println("bad hgt: $it | $doc")
                    return false
                }
            }


//            hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
            doc.getValue("hcl").let {
                val regex = Regex("#[0-9a-f]{6}")
                if (it.length != 7 || !it.matches(regex)) {
                    println("bad hcl: $it | $doc")
                    return false
                }
            }


//            ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
            doc.getValue("ecl").let {
                val eyeColors = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
                if (!eyeColors.contains(it)) {
                    println("bad ecl: $it | $doc")
                    return false
                }
            }

//            pid (Passport ID) - a nine-digit number, including leading zeroes.
            doc.getValue("pid").let {
                val regex = Regex("[0-9]{9}")
                if (it.length != 9 || !it.matches(regex)) {
                    println("bad pid: $it | $doc")
                    return false
                }
            }

//            cid (Country ID) - ignored, missing or not.

            println("Valid | {${requiredFields.joinToString(", ") { "$it=${doc[it]}" }}}")
            return true

        }

        private fun minMax(
            doc: Map<String, String>,
            key: String,
            value: Int,
            min: Int,
            max: Int,
            valueRaw: String? = null
        ): Boolean {
            if (value < min) {
                println("$key < $min : ${valueRaw?:value} | $doc")
                return false
            }
            if (value > max) {
                println("$key > $max : ${valueRaw?:value} | $doc")
                return false
            }
            return true
        }
    }
}
