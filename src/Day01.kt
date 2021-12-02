fun main() {
    fun part1(input: Sequence<String>) =
        input
            .map(String::toInt)
            .drop(1)
            .fold(0 to 0) { (sum, prev), current -> (if (current > prev) sum + 1 else sum) to current }
            .first

    fun part2(input: Sequence<String>) =
        input
            .map(String::toInt)
            .windowed(3)
            .map { it.sum() }
            .drop(1)
            .fold(0 to 0) { (sum, prev), current -> (if (current > prev) sum + 1 else sum) to current }
            .first

    readInput("Day01_test").also {
        it.run(::part1).also { check(it == 7) }
        it.run(::part2).also { check(it == 5) }
    }

    readInput("Day01").also {
        it.run(::part1).also(::println)
        it.run(::part2).also(::println)
    }
}
