package it.tino.adventofcode.year2023.day3;

import it.tino.adventofcode.ResourcesFileReader;
import it.tino.adventofcode.StringUtils;
import it.tino.adventofcode.year2023.day3.component.Line;

import java.util.*;

public class NumbersAdjacentToSymbols {

    public static void main(String[] args) {
        ResourcesFileReader fileReader = new ResourcesFileReader();
        NumbersAdjacentToSymbols numbersAdjacentToSymbols = new NumbersAdjacentToSymbols();

        List<String> fileLines = fileReader.getFileLines("2023/day-3-numbers-adjacent-to-symbols.txt");
        Collection<Integer> numbersAdjacentToSymbols1 = numbersAdjacentToSymbols.findNumbersAdjacentToSymbols(fileLines);

        int sum = numbersAdjacentToSymbols1.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("The sum of all numbers adjacent to symbols is: " + sum);
    }

    /**
     * <br>
     * Given this example:
     * <pre>
     * 467..114..
     * ...*......
     * ..35..633.
     * ......#...
     * 617*......
     * .....+.58.
     * ..592.....
     * ......755.
     * ...$.*....
     * .664.598..
     * </pre>
     * ony two numbers are not part numbers because they are not adjacent
     * to a symbol: 114 (top right) and 58 (middle right). Every other
     * number is adjacent to a symbol and so is a part number.
     *
     * @param lines A line can contain symbols, dots and numbers.
     * @return Numbers adjacent to symbols, even diagonally.
     * Dots "." are not considered symbols.
     */
    private Collection<Integer> findNumbersAdjacentToSymbols(List<String> lines) {
        Collection<Integer> numbersAdjacentToSymbols = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            Line previousLine = null;
            if (i > 0) {
                previousLine = new Line(lines.get(i - 1));
            }

            String currentLineContent = lines.get(i);
            Line currentLine = new Line(currentLineContent);

            Line nextLine = null;
            if (i < lines.size() - 1) {
                nextLine = new Line(lines.get(i + 1));
            }

            Collection<Integer> numbers = findNumbersAdjacentToSymbols(previousLine, currentLine, nextLine);
            numbersAdjacentToSymbols.addAll(numbers);
        }

        return numbersAdjacentToSymbols;
    }

    private Collection<Integer> findNumbersAdjacentToSymbols(
        Line topLine,
        Line centralLine,
        Line bottomLine
    ) {
        String topLineContent = "";
        if (topLine != null) {
            topLineContent = topLine.content();
            System.out.println(topLine.content());
        }

        System.out.println(centralLine.content());

        String bottomLineContent = "";
        if (bottomLine != null) {
            bottomLineContent = bottomLine.content();
            System.out.println(bottomLine.content());
        }

        Collection<Integer> numbersAdjacentToSymbols = new ArrayList<>();

        // Iterating over all whole numbers, e.g. 15, 21, 34.
        for (List<Integer> numberIndexes : centralLine.getIndexesOfNumbers()) {
            Collection<String> adjacentChars = new ArrayList<>();

            // Iterating over single digits, e.g. 1, 5, 2, 1, 3, 4.
            for (Integer numberIndex : numberIndexes) {
                // Left check.
                List<String> charsToTheLeft = findAdjacentCharacters(
                        numberIndex,
                        -1,
                        topLineContent,
                        centralLine.content(),
                        bottomLineContent
                );
                adjacentChars.addAll(charsToTheLeft);
                System.out.println("chars to left: " + charsToTheLeft);

                // Central check.
                List<String> charsToTopAndBottom = findAdjacentCharacters(
                        numberIndex,
                        0,
                        topLineContent,
                        centralLine.content(),
                        bottomLineContent
                );
                adjacentChars.addAll(charsToTopAndBottom);

                // Right check.
                List<String> charsToTheRight = findAdjacentCharacters(
                        numberIndex,
                        1,
                        topLineContent,
                        centralLine.content(),
                        bottomLineContent
                );
                adjacentChars.addAll(charsToTheRight);

                int numberOfAdjacentSymbols = getSymbolsFromStrings(adjacentChars).size();

                System.out.println("Current number: " + centralLine.getNumberFromIndexes(numberIndexes));
                System.out.println("Adjacent chars: " + adjacentChars);
                System.out.println("Adjacent symbols: " + getSymbolsFromStrings(adjacentChars));

                try {
                    var x = centralLine.content().substring(numberIndex, numberIndex + 1);
                    System.out.println("Current digit: " + x);
                } catch (StringIndexOutOfBoundsException e) {

                }
                if (numberOfAdjacentSymbols > 0) {
                    Integer number = centralLine.getNumberFromIndexes(numberIndexes);
                    System.out.println("Adjacent num: " + number);
                    numbersAdjacentToSymbols.add(number);
                    System.out.println("_________");
                    break;
                }
                System.out.println("_________");
            }
        }

        System.out.println();
        System.out.println();

        return numbersAdjacentToSymbols;
    }
    
    private List<String> findAdjacentCharacters(
        int characterIndex,
        int offset,
        String topLine,
        String centralLine,
        String bottomLine
    ) {
        String charAtTopLeft = "";
        String charAtLeft = "";
        String charAtBottomLeft = "";

        if (topLine != null && characterIndex + offset > 0 && topLine.length() > characterIndex + offset) {
            charAtTopLeft = String.valueOf(topLine.charAt(characterIndex + offset));
        }

        if (characterIndex + offset > 0 && centralLine.length() > characterIndex + offset) {
            charAtLeft = String.valueOf(centralLine.charAt(characterIndex + offset));
        }

        if (bottomLine != null && characterIndex + offset > 0 && bottomLine.length() > characterIndex + offset) {
            charAtBottomLeft = String.valueOf(bottomLine.charAt(characterIndex + offset));
        }

        return Arrays.asList(charAtTopLeft, charAtLeft, charAtBottomLeft);
    }

    private Collection<String> getSymbolsFromStrings(Collection<String> strings) {
        Collection<String> symbols = new ArrayList<>();
        for (String string : strings) {
            Collection<String> symbolsFromString = getSymbolsFromString(string);
            symbols.addAll(
                    symbolsFromString
                            .stream()
                            .filter(s -> !s.isEmpty() && !s.equals("."))
                            .toList()
            );
        }

        return symbols;
    }

    private Collection<String> getSymbolsFromString(String string) {
        // Replace the numbers with spaces, leaving the signs and spaces...
        String signString = string.replaceAll("[0-9]+", " ");

        // ...then get a list that contains the signs without spaces
        return Arrays.asList(signString.trim().split(" +"));
    }
}
