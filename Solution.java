
public class Solution {

    private static final int ALPHABET_SIZE = 26;

    public int minCharacters(String first, String second) {
        int[] frequencyFirst = countFrequency(first.toCharArray());
        int[] frequencySecond = countFrequency(second.toCharArray());

        int minChanges = Integer.MAX_VALUE;

        minChanges = Math.min(minChanges,
                minChangesToMakeAllLettersInBothStringsEqual(frequencyFirst, first.length(), frequencySecond, second.length()));

        minChanges = Math.min(minChanges,
                minChangesToMakeAllLettersInOneStringSmaller(frequencyFirst, frequencySecond));

        minChanges = Math.min(minChanges,
                minChangesToMakeAllLettersInOneStringSmaller(frequencySecond, frequencyFirst));

        return minChanges;
    }

    private static int[] countFrequency(char[] input) {
        int[] frequency = new int[ALPHABET_SIZE];

        for (char letter : input) {
            ++frequency[letter - 'a'];
        }
        return frequency;
    }

    private static int minChangesToMakeAllLettersInBothStringsEqual(int[] frequencyFirst, int lengthFirst, int[] frequencySecond, int lengthSecond) {
        int minChanges = Integer.MAX_VALUE;

        for (char letter = 'a'; letter <= 'z'; ++letter) {
            int changesFirst = lengthFirst - frequencyFirst[letter - 'a'];
            int changesSecond = lengthSecond - frequencySecond[letter - 'a'];
            minChanges = Math.min(minChanges, changesFirst + changesSecond);
        }
        return minChanges;
    }

    private static int minChangesToMakeAllLettersInOneStringSmaller(int[] frequencyToMakeSmaller, int[] frequencyToMakeLarger) {
        int minChanges = Integer.MAX_VALUE;

        for (char letter = 'a'; letter < 'z'; ++letter) {
            int changesToMakeSmaller = 0;
            for (char current = (char) (letter + 1); current <= 'z'; ++current) {
                changesToMakeSmaller += frequencyToMakeSmaller[current - 'a'];
            }

            int changesToMakeLarger = 0;
            for (char current = 'a'; current <= letter; ++current) {
                changesToMakeLarger += frequencyToMakeLarger[current - 'a'];
            }
            minChanges = Math.min(minChanges, changesToMakeSmaller + changesToMakeLarger);
        }
        return minChanges;
    }
}
