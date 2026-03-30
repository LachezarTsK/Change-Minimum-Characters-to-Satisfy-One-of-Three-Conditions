
package main
import "math"

const ALPHABET_SIZE = 26

func minCharacters(first string, second string) int {
    var frequencyFirst []int = countFrequency(first)
    var frequencySecond []int = countFrequency(second)

    var minChanges = math.MaxInt

    minChanges = min(minChanges,
        minChangesToMakeAllLettersInBothStringsEqual(frequencyFirst, len(first), frequencySecond, len(second)))

    minChanges = min(minChanges,
        minChangesToMakeAllLettersInOneStringSmaller(frequencyFirst, frequencySecond))

    minChanges = min(minChanges,
        minChangesToMakeAllLettersInOneStringSmaller(frequencySecond, frequencyFirst))

    return minChanges
}

func countFrequency(input string) []int {
    frequency := make([]int, ALPHABET_SIZE)

    for _, letter := range input {
        frequency[letter - 'a']++
    }
    return frequency
}

func minChangesToMakeAllLettersInBothStringsEqual(frequencyFirst []int, lengthFirst int, frequencySecond []int, lengthSecond int) int {
    var minChanges = math.MaxInt

    for letter := 'a'; letter <= 'z'; letter++ {
        changesFirst := lengthFirst - frequencyFirst[letter - 'a']
        changesSecond := lengthSecond - frequencySecond[letter - 'a']
        minChanges = min(minChanges, changesFirst + changesSecond)
    }
    return minChanges
}

func minChangesToMakeAllLettersInOneStringSmaller(frequencyToMakeSmaller []int, frequencyToMakeLarger []int) int {
    minChanges := math.MaxInt

    for letter := 'a'; letter < 'z'; letter++ {
        var changesToMakeSmaller = 0
        for current := (letter + 1); current <= 'z'; current++ {
            changesToMakeSmaller += frequencyToMakeSmaller[current - 'a']
        }

        var changesToMakeLarger = 0
        for current := 'a'; current <= letter; current++ {
            changesToMakeLarger += frequencyToMakeLarger[current - 'a']
        }
        minChanges = min(minChanges, changesToMakeSmaller + changesToMakeLarger)
    }
    return minChanges
}
