fun main() {
    fun part1(input: List<String>): Int {
        var total = 0
//        input.forEach { line ->
//            val lastDigit = line[line.indexOfLast { it.isDigit() }]
//            val firstDigit = line[line.indexOfFirst { it.isDigit() }]
//            total += ((firstDigit - '0') * 10 + (lastDigit - '0'))
//        }
        return total
    }
    val digits = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    fun toNumericalValue(line: String, index: Int): Int {
        return if(index < 0) 0 else when(line.substring(index, index + 2)) {
            "one" -> 1
            "two" -> 2
            "thr" -> 3
            "fou" -> 4
            "fiv" -> 5
            "six" -> 6
            "sev" -> 7
            "eig" -> 8
            "nin" -> 9
            else -> 0
        }
    }
    fun part2(input: List<String>): Int {
        var total = 0
        input.forEach { line ->
            val firstStringDigitIndex = line.indexOfAny(digits)
            val lastStringDigitIndex = line.lastIndexOfAny(digits)
            val lastDigitIndex = line.indexOfLast { it.isDigit() }
            val firstDigitIndex = line.indexOfFirst { it.isDigit() }
            val firstDigitNumeric = line[firstDigitIndex].digitToInt()
            val lastDigitNumeric = line[lastDigitIndex].digitToInt()
            val firstStringDigitNumeric = toNumericalValue(line, firstStringDigitIndex)
            val lastStringDigitNumeric = toNumericalValue(line, lastStringDigitIndex)

            val firstDigit = if(firstStringDigitIndex > -1){
                if(firstDigitIndex <= firstStringDigitIndex) firstDigitNumeric else firstStringDigitNumeric
            } else {
                firstDigitNumeric
            }
            val lastDigit = if(lastStringDigitIndex > -1) {
                if(lastDigitIndex <= lastStringDigitIndex) lastStringDigitNumeric else lastDigitNumeric
            } else {
                lastDigitNumeric
            }
            total += (firstDigit * 10 + lastDigit)
        }
        return total
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
