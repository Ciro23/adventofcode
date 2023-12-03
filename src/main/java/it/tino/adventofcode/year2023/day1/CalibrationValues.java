package it.tino.adventofcode.year2023.day1;

import it.tino.adventofcode.ResourcesFileReader;
import it.tino.adventofcode.StringUtils;
import it.tino.adventofcode.year2023.day1.component.Numbers;

import java.util.*;

/**
 * Given a document with calibration values,
 * first and last numbers inside each line needs
 * to be extracted and summed.
 * <br>
 * 1. Find the sum of all first and last values considering only
 * digits, e.g. 1, 2, 3, 4, 5, 6, 7, 8, 9.
 * <br>
 * 2. Find the sum of all first and last values considering both
 * digits and letters, e.g "one", "two", "three", "four", "five",
 * "six", "seven", "eight", "nine"
 * <br>
 * <a href="https://adventofcode.com/2023/day/1">Problem description for more details</a>
 */
public class CalibrationValues {

    public static void main(String[] args) {
        ResourcesFileReader fileReader = new ResourcesFileReader();
        StringUtils stringUtils = new StringUtils();

        List<String> fileLines = fileReader.getFileLines("2023/day-1-calibration-values.txt");

        int sumWithOnlyDigits = 0;
        int sumWithDigitsAndLetters = 0;

        for (String fileLine : fileLines) {
            sumWithOnlyDigits += stringUtils.getFirstAndLastNumbersConcatenated(fileLine, true);
            sumWithDigitsAndLetters += stringUtils.getFirstAndLastNumbersConcatenated(fileLine, false);
        }

        System.out.println("The sum of all calibrated values using only digits is: " + sumWithOnlyDigits);
        System.out.println("The sum of all calibrated values using digits and letters is: " + sumWithDigitsAndLetters);
    }
}
