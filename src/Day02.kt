fun main(){
    fun isNumOfMarblesExceeded(marble: String): Boolean {
        val number = marble.trim().split(" ")[0]
        val color = marble.trim().split(" ")[1]
        //println("number " + number + " color " + color)
        var isRedExceeded = false
        var isBlueExceeded = false
        var isGreenExceeded = false
        when(color.trim()) {
            "blue" -> isBlueExceeded = number.toInt() > 14
            "red" -> isRedExceeded = number.toInt() > 12
            else -> isGreenExceeded = number.toInt() > 13
        }
        return isRedExceeded || isBlueExceeded || isGreenExceeded
    }

    fun isMiniGamePossible(game: String): Boolean {
        val marbles = game.split(",")
        var isPossible = true
        for(i in marbles.indices){
            if(isNumOfMarblesExceeded(marbles[i])) {
                isPossible = false
                break
            }
        }
        return isPossible
    }

    fun isGamePossible(games: List<String>): Boolean {
        var isPossible = true;
        for(i in games.indices){
            if(!isMiniGamePossible(games[i])) {
                isPossible = false
                break
            }
        }
        return isPossible
    }

    fun part1(input: List<String>): Int {
        var total = 0
        input.forEach { game ->
            //val game = "Game 1000: 7 blue, 6 green, 3 red; 3 red, 5 green, 1 blue; 1 red, 5 green, 8 blue; 3 red, 1 green, 5 blue"
            val colonIndex = game.indexOf(":")
            val firstSpaceIndex = game.indexOf(" ")
            val gameNumber = game.substring(firstSpaceIndex + 1, colonIndex).toInt()
            val gameInfo = game.substring(colonIndex + 1)
            val games = gameInfo.split(";")
            //println(gameNumber)
            //games.forEach { minigame ->
            if (isGamePossible(games)) {
                //println(gameNumber)
                total += gameNumber
            }
            //}
        }
        return total
    }

    fun fewestNumberOfCubes(games: List<String>): Triple<Int,Int,Int> {
        var fewestNumOfBlue = 0
        var fewestNumOfGreen = 0
        var fewestNumOfRed = 0
        for(i in games.indices){
            games[i].split(",").forEach{ marble ->
                val number = marble.trim().split(" ")[0]
                val color = marble.trim().split(" ")[1]
                if(color.trim() == "green"){
                    if(number.toInt() > fewestNumOfGreen) {
                        fewestNumOfGreen = number.toInt()
                    }
                }
                if(color == "red") {
                    if(number.toInt() > fewestNumOfRed) {
                        fewestNumOfRed = number.toInt()
                    }
                }
                if(color == "blue") {
                    if(number.toInt() > fewestNumOfBlue) {
                        fewestNumOfBlue = number.toInt()
                    }
                }
            }
        }
        return Triple(fewestNumOfRed, fewestNumOfBlue, fewestNumOfGreen)
    }

    fun part2(input: List<String>): Int {
        var total = 0
        input.forEach { game ->
            val colonIndex = game.indexOf(":")
            val gameInfo = game.substring(colonIndex + 1)
            val games = gameInfo.split(";")
            val triple = fewestNumberOfCubes(games)
            total += triple.first * triple.second * triple.third
        }
        return total
    }

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}