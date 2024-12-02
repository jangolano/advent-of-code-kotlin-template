fun main() {
    val (col1, col2) = parseIntegerList()
    fun part1(): Int {
        return col1.mapIndexed{ index, int ->
            val result = int - col2[index]
            if (result < 0)
                result*-1
            else
                result
        }.sum()
    }

    fun part2(): Int {
        return col1.sumOf { int ->
            int * col2.count { int2 -> int2 == int }
        }
    }

   println(part1())
   println(part2())
}

private fun parseIntegerList(): Pair<List<Int>, List<Int>> {
    val input = readInput("Day01")
    val column1 = mutableListOf<String>()
    val column2 = mutableListOf<String>()
    input.forEach { line ->
        val items = line.split("   ")
        column1.add(items[0])
        column2.add(items[1])
    }
    val col1 = column1.map { it.toInt() }.sorted()
    val col2 = column2.map { it.toInt() }.sorted()
    return Pair(col1, col2)
}
