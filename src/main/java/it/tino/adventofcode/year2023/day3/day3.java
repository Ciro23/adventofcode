package it.tino.adventofcode.year2023.day3;

import it.tino.adventofcode.ResourcesFileReader;
import it.tino.adventofcode.year2023.day3.component.NumbersAdjacentToSymbols;

import java.util.*;

public class day3 {

    public static void main(String[] args) {
        ResourcesFileReader fileReader = new ResourcesFileReader();

        List<String> fileLines = fileReader.getFileLines("2023/day-3-numbers-adjacent-to-symbols.txt");
        NumbersAdjacentToSymbols numbers = new NumbersAdjacentToSymbols(fileLines);

        List<Integer> numbersAdjacentToSymbols = numbers.findNumbersAdjacentToSymbols(NumbersAdjacentToSymbols.symbols);
        int sum = numbersAdjacentToSymbols
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("The sum of all numbers adjacent to symbols is: " + sum);
    }
}
