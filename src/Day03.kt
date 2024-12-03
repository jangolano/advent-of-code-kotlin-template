import java.math.BigInteger

data class MatchRange(val matchString:String, val range: IntRange)
fun main() {

    val input = readInput("Day03").joinToString()

    fun part1(): BigInteger {
        val commands =  findAcceptableCommands(input)
        val multipliedValues = commands.map {
            multiplyNumbers(it)
        }
        return multipliedValues.sumOf { it }

    }

    fun part2(): BigInteger {
        val commands =  findAcceptableCommandsWithRanges(input).sortedBy { it.range.first }
        val onCommands = findOnCommands(input).sortedBy { it.range.first }
        val offCommands = findOffCommands(input).sortedBy { it.range.first }
        val totalInstructions = (commands + onCommands + offCommands).sortedBy { it.range.first }
        var on = true
        val totals = mutableListOf<BigInteger>()
        totalInstructions.forEach {
            if(it.matchString=="don't()"){
                on = false
            }else if(it.matchString=="do()"){
                on = true
            }else{
                if (on){
                    totals.add(multiplyNumbers(it.matchString))
                }
            }
        }
        return totals.sumOf { it }
    }

    println(part1())
    println(part2())
}

fun multiplyNumbers(commandString: String): BigInteger {
    val matcher = "\\d+".toRegex()
    val operands = matcher.findAll(commandString).map { it.value }.toList()
    return BigInteger(operands[0])*BigInteger(operands[1])
}
fun findAcceptableCommands(commandString: String): List<String> {
    val matcher = "mul\\([0-9]*,[0-9]*\\)".toRegex()
    return matcher.findAll(commandString).map { it.value }.toList()
}

fun findAcceptableCommandsWithRanges(commandString: String): List<MatchRange> {
    val matcher = "mul\\([0-9]*,[0-9]*\\)".toRegex()
    return matcher.findAll(commandString).map { MatchRange(it.value, it.range) }.toList()
}

fun findOnCommands(commandString: String): List<MatchRange>{
    val matcher = "do\\(\\)".toRegex()
    return matcher.findAll(commandString).map { MatchRange(it.value, it.range) }.toList()
}

fun findOffCommands(commandString: String): List<MatchRange> {
    val matcher = "don't\\(\\)".toRegex()
    return matcher.findAll(commandString).map { MatchRange(it.value, it.range) }.toList()
}
