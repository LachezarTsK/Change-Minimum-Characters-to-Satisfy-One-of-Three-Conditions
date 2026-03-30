
import kotlin.math.min

class Solution {

    private companion object {
        const val ALPHABET_SIZE = 26
    }

    fun minCharacters(first: String, second: String): Int {
        val frequencyFirst: IntArray = countFrequency(first)
        val frequencySecond: IntArray = countFrequency(second)

        var minChanges = Int.MAX_VALUE

        minChanges = min(minChanges,
            minChangesToMakeAllLettersInBothStringsEqual(frequencyFirst, first.length, frequencySecond, second.length))

        minChanges = min(minChanges,
            minChangesToMakeAllLettersInOneStringSmaller(frequencyFirst, frequencySecond))

        minChanges = min(minChanges,
            minChangesToMakeAllLettersInOneStringSmaller(frequencySecond, frequencyFirst))

        return minChanges
    }

    private fun countFrequency(input: String): IntArray {
        val frequency = IntArray(ALPHABET_SIZE)

        for (letter in input) {
            ++frequency[letter - 'a']
        }
        return frequency
    }

    private fun minChangesToMakeAllLettersInBothStringsEqual(frequencyFirst: IntArray, lengthFirst: Int, frequencySecond: IntArray, lengthSecond: Int): Int {
        var minChanges = Int.MAX_VALUE

        for (letter in 'a'..'z') {
            val changesFirst = lengthFirst - frequencyFirst[letter - 'a']
            val changesSecond = lengthSecond - frequencySecond[letter - 'a']
            minChanges = min(minChanges, changesFirst + changesSecond)
        }
        return minChanges
    }

    private fun minChangesToMakeAllLettersInOneStringSmaller(frequencyToMakeSmaller: IntArray, frequencyToMakeLarger: IntArray): Int {
        var minChanges = Int.MAX_VALUE

        for (letter in 'a'..<'z') {
            var changesToMakeSmaller = 0
            for (current in (letter + 1)..'z') {
                changesToMakeSmaller += frequencyToMakeSmaller[current - 'a']
            }

            var changesToMakeLarger = 0
            for (current in 'a'..letter) {
                changesToMakeLarger += frequencyToMakeLarger[current - 'a']
            }
            minChanges = min(minChanges, changesToMakeSmaller + changesToMakeLarger)
        }
        return minChanges
    }
}
