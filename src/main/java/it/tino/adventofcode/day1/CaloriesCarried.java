package it.tino.adventofcode.day1;

import it.tino.adventofcode.FileLinesReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CaloriesCarried {

    public static void main(String[] args) {
        CaloriesCarried caloriesCarried = new CaloriesCarried();
        FileLinesReader fileLinesReader = new FileLinesReader();

        List<String> lines = fileLinesReader.getFileLines("calories-carried-by-elves.txt");
        List<Integer> caloriesCarriedByElves = caloriesCarried.getCaloriesCarriedByElves(lines);

        Integer maximumCaloriesCarried = Collections.max(caloriesCarriedByElves);
        System.out.println("The elf carrying the most calories got " + maximumCaloriesCarried);
    }

    /**
     * Each elf writes the calories for all the food
     * their carrying in a document, and separates
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
}
