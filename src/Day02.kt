data class Coordinate(val x: Int = 0, val y: Int = 0, val aim: Int = 0)

fun executeCommandPart1(direction: String, value: Int, currentCoordinate: Coordinate) =
    with(currentCoordinate) {
        when (direction) {
            "down" -> copy(y = y + value)
            "up" -> copy(y = y - value)
            "forward" -> copy(x = x + value)
            else -> throw IllegalArgumentException("Unknown direction: $direction")
        }
    }

fun executeCommandPart2(direction: String, value: Int, currentCoordinate: Coordinate) =
    with(currentCoordinate) {
        when (direction) {
            "down" -> copy(aim = aim + value)
            "up" -> copy(aim = aim - value)
            "forward" -> copy(
                x = x + value,
                y = y + aim * value
            )
            else -> throw IllegalArgumentException("Unknown direction: $direction")
        }
    }

fun main() {
    fun part1(input: Sequence<String>) =
        input
            .map { it.split(' ') }
            .fold(Coordinate()) { currentCoordinate, (direction, value) ->
                executeCommandPart1(direction, value.toInt(), currentCoordinate)
            }
            .let {
                it.x * it.y
            }

    fun part2(input: Sequence<String>) =
        input
            .map { it.split(' ') }
            .fold(Coordinate()) { currentCoordinate, (direction, value) ->
                executeCommandPart2(direction, value.toInt(), currentCoordinate)
            }
            .let {
                it.x * it.y
            }

    readInput("Day02_test").also {
        it.run(::part1).also { check(it == 150) }
        it.run(::part2).also { check(it == 900) }
    }

    readInput("Day02").also {
        it.run(::part1).also(::println)
        it.run(::part2).also(::println)
    }
}
