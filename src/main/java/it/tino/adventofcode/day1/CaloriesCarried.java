package it.tino.adventofcode.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CaloriesCarried {

    public static void main(String[] args) {
        CaloriesCarried caloriesCarried = new CaloriesCarried();
        List<String> lines = caloriesCarried.getFileLines("calories-carried-by-elves.txt");
        List<Integer> caloriesCarriedByElves = caloriesCarried.getCaloriesCarriedByElves(lines);

        Integer maximumCaloriesCarried = Collections.max(caloriesCarriedByElves);
        System.out.println("The elf carrying the most calories got " + maximumCaloriesCarried);
    }

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

    public List<String> getFileLines(String filename) {
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(filename);

        if (inputStream == null) {
            return Collections.emptyList();
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        LinkedList<String> lines = new LinkedList<>();
        try {
            while (bufferedReader.ready()) {
                lines.addLast(bufferedReader.readLine());
            }
        } catch (IOException e) {
            return Collections.emptyList();
        }

        return lines;
    }
}
