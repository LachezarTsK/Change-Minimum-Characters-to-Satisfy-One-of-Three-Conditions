
using System;

public class Solution
{
    private static readonly int ALPHABET_SIZE = 26;

    public int MinCharacters(string first, string second)
    {
        int[] frequencyFirst = CountFrequency(first);
        int[] frequencySecond = CountFrequency(second);

        int minChanges = int.MaxValue;

        minChanges = Math.Min(minChanges,
                MinChangesToMakeAllLettersInBothStringsEqual(frequencyFirst, first.Length, frequencySecond, second.Length));

        minChanges = Math.Min(minChanges,
                MinChangesToMakeAllLettersInOneStringSmaller(frequencyFirst, frequencySecond));

        minChanges = Math.Min(minChanges,
                MinChangesToMakeAllLettersInOneStringSmaller(frequencySecond, frequencyFirst));

        return minChanges;
    }

    private static int[] CountFrequency(string input)
    {
        int[] frequency = new int[ALPHABET_SIZE];

        foreach (char letter in input)
        {
            ++frequency[letter - 'a'];
        }
        return frequency;
    }

    private static int MinChangesToMakeAllLettersInBothStringsEqual(int[] frequencyFirst, int lengthFirst, int[] frequencySecond, int lengthSecond)
    {
        int minChanges = int.MaxValue;

        for (char letter = 'a'; letter <= 'z'; ++letter)
        {
            int changesFirst = lengthFirst - frequencyFirst[letter - 'a'];
            int changesSecond = lengthSecond - frequencySecond[letter - 'a'];
            minChanges = Math.Min(minChanges, changesFirst + changesSecond);
        }
        return minChanges;
    }

    private static int MinChangesToMakeAllLettersInOneStringSmaller(int[] frequencyToMakeSmaller, int[] frequencyToMakeLarger)
    {
        int minChanges = int.MaxValue;

        for (char letter = 'a'; letter < 'z'; ++letter)
        {
            int changesToMakeSmaller = 0;
            for (char current = (char)(letter + 1); current <= 'z'; ++current)
            {
                changesToMakeSmaller += frequencyToMakeSmaller[current - 'a'];
            }

            int changesToMakeLarger = 0;
            for (char current = 'a'; current <= letter; ++current)
            {
                changesToMakeLarger += frequencyToMakeLarger[current - 'a'];
            }
            minChanges = Math.Min(minChanges, changesToMakeSmaller + changesToMakeLarger);
        }
        return minChanges;
    }
}
