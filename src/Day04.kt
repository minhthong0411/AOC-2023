
import java.util.*
import kotlin.math.log2

fun main(){
    fun part1(input: List<String>): HashMap<String, Int> {
        var total = 0
        val hashMap = hashMapOf<String, Int>()
        input.forEach { card ->
            var subTotal = 0
            val winningNumbers = card.substring(card.indexOf(':') + 1, card.indexOf('|')).trim().split(" ").filter { it.isNotEmpty() }
            val yourNumbers = card.substring(card.indexOf('|') + 1).trim().split(" ").filter { it.isNotEmpty() }
            var isFirstOne = true
            yourNumbers.forEach { number ->
                if (winningNumbers.contains(number)) {
                    if(isFirstOne) {
                        isFirstOne = false
                        subTotal = 1
                    } else {
                        subTotal = subTotal shl 1
                    }
                    //println("SUBTOTAL $subTotal $number")
                }
            }
            //println(subTotal)

            if(subTotal > 0) hashMap[card.substring(4, card.indexOf(':')).trim()] = log2(subTotal.toFloat()).toInt() + 1
            total += subTotal
            //println(total)
        }
        //println(hashMap)
        return hashMap
    }

    fun part2(input: List<String>): Int {
        val hashMap = part1(input)
        val queue: Queue<String> = LinkedList()
        var total = 0
        input.forEach { card ->
//            val winningNumbers = card.substring(card.indexOf(':') + 1, card.indexOf('|')).trim().split(" ").filter { it.isNotEmpty() }
//            val yourNumbers = card.substring(card.indexOf('|') + 1).trim().split(" ").filter { it.isNotEmpty() }
            val cardNum = card.substring(4, card.indexOf(':')).trim()
            if(queue.isEmpty()) {
                queue.add(cardNum)
                total += 1
            }
            while(queue.isNotEmpty()){
                //println(queue)
                val numOfExtraTicket = hashMap[queue.peek()]
                if(numOfExtraTicket != null){
                    for(i in 1 .. numOfExtraTicket){
                        queue.add((queue.peek().trim().toInt() + i).toString())
                        total += 1
                    }
                }
                queue.poll()
                //println(queue.poll() + numOfExtraTicket)
            }
            //println("SUB TOTAL $total")
        }
        //println(total)
        return total
    }

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}