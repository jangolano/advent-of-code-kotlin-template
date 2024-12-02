val input = readInput("Day02")

fun getIntegerInputs(): List<List<Int>> {
    val inputs = input.map {
        it.split(" ")
    }
    val integerInputs = inputs.map { it ->
        it.map { it.toInt() }
    }
    return integerInputs
}


fun getUnsafeInputs(): List<List<Int>> {
    val integerInputs = getIntegerInputs()
    val safeInputs = integerInputs.map {
        if (!isSafe(it, ::safeInputCount)) {
            emptyList()
        } else {
            it
        }
    }.filter { it.isNotEmpty() }
    val unsafeInputs = integerInputs.minus(safeInputs.toSet())
    return unsafeInputs
}
fun main() {

    fun part1():Int {
        val integerInputs =  getIntegerInputs()
        return integerInputs.map { isSafe(it, ::safeInputCount) }.count { it }
    }

    fun part2():Int{
        val unsafeInputs = getUnsafeInputs()
        return unsafeInputs.map {
            canBeSafe(it)
        }.count { it }
    }

   println(part1())
   println(part2())
}


fun canBeSafe(integerList: List<Int>):Boolean{
    var updatedList = integerList.toMutableList()
    integerList.forEachIndexed { index, _ ->
        updatedList.removeAt(index)
        if(isSafe(updatedList, ::safeInputCount)){
            return true
        }
        updatedList =integerList.toMutableList()
    }
    return false
}
fun isSafe(integerList: List<Int>, processor: (assendingList: List<Int>)->Boolean):Boolean{
    val assendingList = integerList.sorted()
    val descendingList = integerList.sortedDescending()
    if(integerList==assendingList || integerList==descendingList){
        return processor(assendingList)
    }
    return false
}



private fun safeInputCount(assendingList: List<Int>) :Boolean{
    var previous = assendingList[0]
    assendingList.forEachIndexed { index, it ->
        if (index != 0) {
            val isSafe = (it - previous >= 1) && (it - previous <= 3)
            if (!isSafe) {
                return false
            }
            previous = it
        }
    }
    return true
}
