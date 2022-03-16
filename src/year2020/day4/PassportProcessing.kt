package year2020.day4

import java.io.File

class PassportProcessing {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid") //cid

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

            var validDocuments = 0
            for (doc in documents) {
                if (doc.keys.containsAll(requiredFields)) {
                    validDocuments++
                    println("Valid: ${doc.keys}")
                } else {
                    println("Missing Fields: ${requiredFields.subtract(doc.keys)} | ${doc.keys}")
                }
            }
            println("$validDocuments valid documents")
        }

    }
}