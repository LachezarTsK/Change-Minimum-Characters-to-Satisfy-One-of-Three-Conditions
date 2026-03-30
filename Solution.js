
/**
 * @param {string} first
 * @param {string} second
 * @return {number}
 */
var minCharacters = function (first, second) {
    const frequencyFirst = countFrequency(first);
    const frequencySecond = countFrequency(second);

    let minChanges = Number.MAX_SAFE_INTEGER;

    minChanges = Math.min(minChanges,
            minChangesToMakeAllLettersInBothStringsEqual(frequencyFirst, first.length, frequencySecond, second.length));

    minChanges = Math.min(minChanges,
            minChangesToMakeAllLettersInOneStringSmaller(frequencyFirst, frequencySecond));

    minChanges = Math.min(minChanges,
            minChangesToMakeAllLettersInOneStringSmaller(frequencySecond, frequencyFirst));

    return minChanges;
};

/**
 * @param {string} input
 * @return {number[]}
 */
function countFrequency(input) {
    const frequency = new Array(Util.ALPHABET_SIZE).fill(0);

    for (let i = 0; i < input.length; ++i) {
        ++frequency[input.codePointAt(i) - Util.ASCII_SMALL_CASE_A];
    }
    return frequency;
}

/**
 * @param {number[]} frequencyFirst
 * @param {number} lengthFirst
 * @param {number[]} frequencySecond
 * @param {number} lengthSecond
 * @return {number}
 */
function minChangesToMakeAllLettersInBothStringsEqual(frequencyFirst, lengthFirst, frequencySecond, lengthSecond) {
    let minChanges = Number.MAX_SAFE_INTEGER;

    for (let letter = Util.ASCII_SMALL_CASE_A; letter <= Util.ASCII_SMALL_CASE_Z; ++letter) {
        const changesFirst = lengthFirst - frequencyFirst[letter - Util.ASCII_SMALL_CASE_A];
        const changesSecond = lengthSecond - frequencySecond[letter - Util.ASCII_SMALL_CASE_A];
        minChanges = Math.min(minChanges, changesFirst + changesSecond);
    }
    return minChanges;
}

/**
 * @param {number[]} frequencyToMakeSmaller
 * @param {number[]} frequencyToMakeLarger
 * @return {number}
 */
function minChangesToMakeAllLettersInOneStringSmaller(frequencyToMakeSmaller, frequencyToMakeLarger) {
    let minChanges = Number.MAX_SAFE_INTEGER;

    for (let letter = Util.ASCII_SMALL_CASE_A; letter < Util.ASCII_SMALL_CASE_Z; ++letter) {
        let changesToMakeSmaller = 0;
        for (let current = (letter + 1); current <= Util.ASCII_SMALL_CASE_Z; ++current) {
            changesToMakeSmaller += frequencyToMakeSmaller[current - Util.ASCII_SMALL_CASE_A];
        }

        let changesToMakeLarger = 0;
        for (let current = Util.ASCII_SMALL_CASE_A; current <= letter; ++current) {
            changesToMakeLarger += frequencyToMakeLarger[current - Util.ASCII_SMALL_CASE_A];
        }
        minChanges = Math.min(minChanges, changesToMakeSmaller + changesToMakeLarger);
    }
    return minChanges;
}

class Util {
    static ALPHABET_SIZE = 26;
    static ASCII_SMALL_CASE_A = 97;
    static ASCII_SMALL_CASE_Z = 122;
}
