package it.tino.adventofcode.day1;

import it.tino.adventofcode.ResourcesFileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a document with all the calories carried
 * by each elf:
 * 1. Find the Elf carrying the most Calories.
 * How many total Calories is that Elf carrying?
 * <br>
 * 2. Find the top three Elves carrying the most Calories.
 * How many Calories are those Elves carrying in total?
 */
public class CaloriesCarried {

    public static void main(String[] args) {
        CaloriesCarried caloriesCarried = new CaloriesCarried();
        ResourcesFileReader fileReader = new ResourcesFileReader();

        List<String> lines = fileReader.getFileLines("calories-carried-by-elves.txt");
        List<Integer> caloriesCarriedByElves = caloriesCarried.getCaloriesCarriedByElves(lines);

        List<Integer> maximumCaloriesCarried = caloriesCarried.getMaxOfList(caloriesCarriedByElves, 3);
        System.out.println("The elves carrying the most calories got respectively " + maximumCaloriesCarried);

        int caloriesSum = maximumCaloriesCarried
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.printf("The total sum is: " + caloriesSum);
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

    private List<Integer> getMaxOfList(List<Integer> list, int quantity) {
        Collections.sort(list);

        List<Integer> maxNOfList = new ArrayList<>();
        int lastElementIndex = list.size() - 1;

        for (int i = lastElementIndex; i >= 0; i--) {
            // Only a specified number of elements shall be considered.
            if (quantity + i == lastElementIndex) {
                break;
            }

            maxNOfList.add(list.get(i));
        }

        return maxNOfList;
    }
}
