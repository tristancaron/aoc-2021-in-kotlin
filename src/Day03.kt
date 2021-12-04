fun transpose(lines: List<String>): List<String> {
    val result = lines.first().map { StringBuilder() }

    lines.forEach { line ->
        line.forEachIndexed { i, char ->
            result[i].append(char)
        }
    }

    return result.map { it.toString() }
}

fun getDominantChar(line: String) =
    if (line.count { it == '0' } > line.count() / 2)
        '0'
    else
        '1'

tailrec fun extractValue(lines: List<String>, shouldDominate: Boolean, index: Int = 0): Int {
    val dominantChar = lines.let(::transpose)[index].let(::getDominantChar)
    val remainingLines = lines.filter {
        if (shouldDominate) it[index] == dominantChar else it[index] != dominantChar
    }

    return if (remainingLines.count() > 1) {
        extractValue(remainingLines, shouldDominate, index + 1)
    } else {
        remainingLines.first().toInt(2)
    }
}

fun main() {
    fun part1(input: List<String>) =
        input
            .let(::transpose)
            .map(::getDominantChar)
            .let {
                val gamma = it.joinToString("").toInt(2)
                val epsilon = it.map { if (it == '0') '1' else '0' }.joinToString("").toInt(2)
                gamma * epsilon
            }

    fun part2(input: List<String>) = extractValue(input, true) * extractValue(input, false)

    readInput("Day03_test").also {
        it.toList().run(::part1).also { check(it == 198) }
        it.toList().run(::part2).also { check(it == 230) }
    }

    readInput("Day03").also {
        it.toList().run(::part1).also(::println)
        it.toList().run(::part2).also(::println)
    }
}
