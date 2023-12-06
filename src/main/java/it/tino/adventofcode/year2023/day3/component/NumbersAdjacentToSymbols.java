package it.tino.adventofcode.year2023.day3.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class NumbersAdjacentToSymbols {

    private final Matrix matrix;
    private final List<String> engineSchematic;

    public static final Collection<String> symbols = Arrays.asList(
            "#",
            "*",
            "+",
            "-",
            "=",
            "%",
            "$",
            "&",
            "/",
            "@"
    );

    public NumbersAdjacentToSymbols(List<String> engineSchematic) {
        this.engineSchematic = engineSchematic;

        int matrixWidth = engineSchematic.size();
        int matrixHeight = engineSchematic.isEmpty() ? 0 : engineSchematic.get(0).length();
        matrix = new Matrix(matrixWidth, matrixHeight);
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
     * @param legalSymbols A collection of what is considered a symbol.
     * @return Numbers adjacent to symbols, even diagonally.
     */
    public List<Integer> findNumbersAdjacentToSymbols(Collection<String> legalSymbols) {
        List<Integer> numbers = new ArrayList<>();
        List<Coordinate> numberCoordinates = findNumberCoordinatesAdjacentToSymbols(
                engineSchematic,
                legalSymbols
        );

        StringBuilder currentNumberBuff = new StringBuilder();
        for (int i = 0; i < engineSchematic.size(); i++) {
            String line = engineSchematic.get(i);

            for (int j = 0; j < line.length(); j++) {
                Coordinate currentCoordinate = new Coordinate(j, i);
                if (numberCoordinates.contains(currentCoordinate)) {
                    char digit = line.charAt(j);
                    currentNumberBuff.append(digit);
                } else if (!currentNumberBuff.isEmpty()) {
                    int currentNumber = Integer.parseInt(currentNumberBuff.toString());
                    numbers.add(currentNumber);
                    currentNumberBuff = new StringBuilder();
                }
            }

            if (!currentNumberBuff.isEmpty()) {
                int currentNumber = Integer.parseInt(currentNumberBuff.toString());
                numbers.add(currentNumber);
                currentNumberBuff = new StringBuilder();
            }
        }

        return numbers;
    }

    private List<Coordinate> findNumberCoordinatesAdjacentToSymbols(
        List<String> strings,
        Collection<String> legalSymbols
    ) {
        List<Coordinate> symbolCoordinates = findSymbolCoordinates(strings, legalSymbols);
        List<Coordinate> numbersAdjacentToSymbols = new ArrayList<>();

        for (int i = 0; i < symbolCoordinates.size(); i++) {
            Coordinate coordinate = symbolCoordinates.get(i);
            int x = coordinate.x();
            int y = coordinate.y();

            String currentRow = engineSchematic.get(y);
            char currentChar = currentRow.charAt(x);
            if (Character.isDigit(currentChar) && currentChar != '.') {
                numbersAdjacentToSymbols.add(coordinate);
            }

            List<Coordinate> adjacentCoordinates = matrix.findAdjacentCoordinates(coordinate);
            for (Coordinate adjacentCoordinate : adjacentCoordinates) {
                String adjacentRow = engineSchematic.get(adjacentCoordinate.y());
                char adjacentChar = adjacentRow.charAt(adjacentCoordinate.x());

                if (!numbersAdjacentToSymbols.contains(adjacentCoordinate) && Character.isDigit(adjacentChar) && adjacentChar != '.') {
                    symbolCoordinates.add(adjacentCoordinate);
                }
            }
        }

        return numbersAdjacentToSymbols;
    }
    
    private List<Coordinate> findSymbolCoordinates(
        List<String> strings,
        Collection<String> legalSymbols
    ) {
        List<Coordinate> allSymbolIndexes = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            String string = strings.get(i);
            List<Integer> symbolIndexes = findSymbolIndexes(string, legalSymbols);

            for (Integer symbolIndex : symbolIndexes) {
                Coordinate coordinate = new Coordinate(symbolIndex, i);
                allSymbolIndexes.add(coordinate);
            }
        }

        return allSymbolIndexes;
    }

    private List<Integer> findSymbolIndexes(String string, Collection<String> legalSymbols) {
        List<Integer> symbolIndexes = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            String charAt = String.valueOf(string.charAt(i));
            if (legalSymbols.contains(charAt)) {
                symbolIndexes.add(i);
            }
        }
        return symbolIndexes;
    }
}
