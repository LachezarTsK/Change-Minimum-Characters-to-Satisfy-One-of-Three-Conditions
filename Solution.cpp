
#include <span>
#include <array>
#include <limits>
#include <string>
#include <algorithm>
#include <string_view>
using namespace std;

class Solution {

    const static int ALPHABET_SIZE = 26;

public:
    int minCharacters(string first, string second) {
        array<int, ALPHABET_SIZE>  frequencyFirst = countFrequency(first);
        array<int, ALPHABET_SIZE>  frequencySecond = countFrequency(second);

        int minChanges = numeric_limits<int>::max();

        minChanges = min(minChanges,
                minChangesToMakeAllLettersInBothStringsEqual(frequencyFirst, first.length(), frequencySecond, second.length()));

        minChanges = min(minChanges,
                minChangesToMakeAllLettersInOneStringSmaller(frequencyFirst, frequencySecond));

        minChanges = min(minChanges,
                minChangesToMakeAllLettersInOneStringSmaller(frequencySecond, frequencyFirst));

        return minChanges;
    }

private:
    static array<int, ALPHABET_SIZE> countFrequency(string_view input) {
        array<int, ALPHABET_SIZE> frequency{};

        for (const auto& letter : input) {
            ++frequency[letter - 'a'];
        }
        return frequency;
    }

    static int minChangesToMakeAllLettersInBothStringsEqual(span<const int> frequencyFirst, int lengthFirst, span<const int> frequencySecond, int lengthSecond) {
        int minChanges = numeric_limits<int>::max();

        for (char letter = 'a'; letter <= 'z'; ++letter) {
            int changesFirst = lengthFirst - frequencyFirst[letter - 'a'];
            int changesSecond = lengthSecond - frequencySecond[letter - 'a'];
            minChanges = min(minChanges, changesFirst + changesSecond);
        }
        return minChanges;
    }


    static int minChangesToMakeAllLettersInOneStringSmaller(span<const int> frequencyToMakeSmaller, span<const int> frequencyToMakeLarger) {
        int minChanges = numeric_limits<int>::max();

        for (char letter = 'a'; letter < 'z'; ++letter) {
            int changesToMakeSmaller = 0;
            for (char current = (char)(letter + 1); current <= 'z'; ++current) {
                changesToMakeSmaller += frequencyToMakeSmaller[current - 'a'];
            }

            int changesToMakeLarger = 0;
            for (char current = 'a'; current <= letter; ++current) {
                changesToMakeLarger += frequencyToMakeLarger[current - 'a'];
            }
            minChanges = min(minChanges, changesToMakeSmaller + changesToMakeLarger);
        }
        return minChanges;
    }
};
