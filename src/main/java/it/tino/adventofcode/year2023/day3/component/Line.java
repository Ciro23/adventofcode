package it.tino.adventofcode.year2023.day3.component;

import it.tino.adventofcode.StringUtils;

import java.util.ArrayList;
import java.util.List;

public record Line(String content) {

    public List<List<Integer>> getIndexesOfNumbers() {
        return getIndexesOfNumberFromString(content);
    }

    public Integer getNumberFromIndexes(List<Integer> indexes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index : indexes) {
            char charAt = content.charAt(index);
            stringBuilder.append(charAt);
        }

        return Integer.parseInt(stringBuilder.toString());
    }

    public List<Integer> getNumbersFromIndexes(List<List<Integer>> indexesOfAllNumbers) {
        List<Integer> numbers = new ArrayList<>();
        for (List<Integer> indexes : indexesOfAllNumbers) {
            numbers.add(getNumberFromIndexes(indexes));
        }
        return numbers;
    }

    private List<List<Integer>> getIndexesOfNumberFromString(String string) {
        List<List<Integer>> indexesPerLine = new ArrayList<>();
        int currentIndex = 0;
        boolean shouldIncrementIndex = false;

        for (int i = 0; i < string.length(); i++) {
            char charAt = string.charAt(i);

            if (Character.isDigit(charAt)) {
                if (indexesPerLine.size() <= currentIndex) {
                    indexesPerLine.add(new ArrayList<>());
                }

                indexesPerLine.get(currentIndex).add(i);
                shouldIncrementIndex = true;
            } else if (shouldIncrementIndex) {
                currentIndex++;
                shouldIncrementIndex = false;
            }
        }
        return indexesPerLine;
    }
}
