package it.tino.adventofcode.day1;

import it.tino.adventofcode.FileLinesReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CaloriesCarried {

    public static void main(String[] args) {
        CaloriesCarried caloriesCarried = new CaloriesCarried();
        FileLinesReader fileLinesReader = new FileLinesReader();

        List<String> lines = fileLinesReader.getFileLines("calories-carried-by-elves.txt");
        List<Integer> caloriesCarriedByElves = caloriesCarried.getCaloriesCarriedByElves(lines);

        List<Integer> maximumCaloriesCarried = caloriesCarried.getMaxOfList(caloriesCarriedByElves, 3);
        System.out.println("The elves carrying the most calories got " + maximumCaloriesCarried);
    }

    /**
     * Each elf writes the calories for all the food
     * they're carrying in a document, and separates
     * their own inventory from the previous Elf's inventory
     * (if any) by a blank line
     */
    public List<Integer> getCaloriesCarriedByElves(List<String> lines) {
        int currentElf = 0;

        List<Integer> caloriesCarriedByElves = new ArrayList<>();
        for (String line : lines) {
            if (line.isEmpty()) {
                currentElf++;
                continue;
            }

            Integer calories = Integer.valueOf(line);
            Integer caloriesAlreadyCarried = 0;
            try {
                caloriesAlreadyCarried = caloriesCarriedByElves.get(currentElf);
                caloriesCarriedByElves.set(currentElf, calories + caloriesAlreadyCarried);
            } catch (IndexOutOfBoundsException e) {
                caloriesCarriedByElves.add(currentElf, calories + caloriesAlreadyCarried);
            }
        }

        return caloriesCarriedByElves;
    }

    public List<Integer> getMaxOfList(List<Integer> list, int quantity) {
        LinkedList<Integer> higherValues = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            Integer max = Collections.max(list);
            higherValues.addLast(max);
            list.remove(max);
        }
        return higherValues;
    }
}
