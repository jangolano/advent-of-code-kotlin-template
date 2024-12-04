
data class CharacterLocation(val char: Char, val x: Int, val y: Int)

val expectedChars = listOf('M', 'A', 'S')

fun search(currentLocation: CharacterLocation, searchFunction: ( currentX:Int, currentY:Int, index:Int)->CharacterLocation?): Boolean {
    if (currentLocation.char != 'X') return false
    val currentX = currentLocation.x
    val currentY = currentLocation.y

    for (i in expectedChars.indices) {
        val newCharLocation = searchFunction( currentX, currentY, i)
        if (newCharLocation?.char != expectedChars[i]) {
            return false
        }
    }
    return true
}

fun searchUp(currentLocation: CharacterLocation, characterLocations: List<CharacterLocation>): Boolean {
    return search(
        currentLocation
    ) { currentX, currentY, index -> characterLocations.firstOrNull { it.x == currentX && it.y == currentY - (index + 1) } }
}
fun searchDown(currentLocation: CharacterLocation, characterLocations: List<CharacterLocation>): Boolean {
    return search(
        currentLocation
    ) { currentX, currentY, index -> characterLocations.firstOrNull { it.x == currentX && it.y == currentY + (index + 1) } }
}
fun searchRight(currentLocation: CharacterLocation, characterLocations: List<CharacterLocation>): Boolean {
    return search(
        currentLocation
    ) { currentX, currentY, index -> characterLocations.firstOrNull { it.x == currentX + (index+1) && it.y == currentY  } }
}
fun searchLeft(currentLocation: CharacterLocation, characterLocations: List<CharacterLocation>): Boolean {
    return search(
        currentLocation
    ) { currentX, currentY, index -> characterLocations.firstOrNull { it.x == currentX - (index+1) && it.y == currentY  } }
}

fun searchUpRight(currentLocation: CharacterLocation, characterLocations: List<CharacterLocation>): Boolean {
    return search(
        currentLocation
    ) { currentX, currentY, index -> characterLocations.firstOrNull { it.x == currentX +(index+1) && it.y ==currentY + (index + 1) } }
}
fun searchDownRight(currentLocation: CharacterLocation, characterLocations: List<CharacterLocation>): Boolean {
    return search(
        currentLocation
    ) { currentX, currentY, index -> characterLocations.firstOrNull { it.x == currentX +(index+1) && it.y == currentY - (index + 1) } }
}
fun searchUpLeft(currentLocation: CharacterLocation, characterLocations: List<CharacterLocation>): Boolean {
    return search(
        currentLocation
    ) { currentX, currentY, index -> characterLocations.firstOrNull { it.x == currentX -(index+1) && it.y == currentY + (index + 1)  } }
}
fun searchDownLeft(currentLocation: CharacterLocation, characterLocations: List<CharacterLocation>): Boolean {
    return search(
        currentLocation
    ) { currentX, currentY, index -> characterLocations.firstOrNull { it.x == currentX - (index + 1) && it.y == currentY - (index + 1) } }
}

fun main() {
    val input = readInput("Day04")
    val charLocations = mutableListOf<CharacterLocation>()

    fun part1(): Int {
        input.forEachIndexed { index, byte ->
            byte.forEachIndexed { i, char ->
                charLocations.add(CharacterLocation(char, i, index))
            }
        }

        val searchFunctions = listOf(
            ::searchUp,
            ::searchDown,
            ::searchRight,
            ::searchLeft,
            ::searchUpRight,
            ::searchDownRight,
            ::searchUpLeft,
            ::searchDownLeft
        )


        return charLocations.sumOf { location ->
            searchFunctions.count { search -> search(location, charLocations) }
        }
    }

    fun part2(): Int {
        var currentCount = 0

        charLocations.forEach { byte ->
            if (byte.char == 'A') {
                val topRight = charLocations.firstOrNull { it.x == byte.x + 1 && it.y == byte.y + 1 }?.char
                val bottomRight = charLocations.firstOrNull { it.x == byte.x + 1 && it.y == byte.y - 1 }?.char
                val topLeft = charLocations.firstOrNull { it.x == byte.x - 1 && it.y == byte.y + 1 }?.char
                val bottomLeft = charLocations.firstOrNull { it.x == byte.x - 1 && it.y == byte.y - 1 }?.char

                val pairs = listOf(
                    topRight to bottomLeft,
                    topLeft to bottomRight
                )

                val validConditions = listOf(
                    listOf('M' to 'S', 'S' to 'M'),
                    listOf('M' to 'S', 'M' to 'S'),
                    listOf('S' to 'M', 'S' to 'M'),
                    listOf('S' to 'M', 'M' to 'S')
                )

                if (validConditions.any { it == pairs }) {
                    currentCount++
                }
            }
        }

        return currentCount
    }



    println(part1())
    println(part2())
}

