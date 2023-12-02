package it.tino.adventofcode.year2023.day1;

import it.tino.adventofcode.ResourcesFileReader;
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
        CalibrationValues calibrationValues = new CalibrationValues();

        List<String> fileLines = fileReader.getFileLines("2023/day-1-calibration-values.txt");

        int sumWithOnlyDigits = 0;
        int sumWithDigitsAndLetters = 0;

        for (String fileLine : fileLines) {
            sumWithOnlyDigits += calibrationValues.getFirstAndLastNumbersConcatenated(fileLine, true);
            sumWithDigitsAndLetters += calibrationValues.getFirstAndLastNumbersConcatenated(fileLine, false);
        }

        System.out.println("The sum of all calibrated values using only digits is: " + sumWithOnlyDigits);
        System.out.println("The sum of all calibrated values using digits and letters is: " + sumWithDigitsAndLetters);
    }

    /**
     * The first and last number extracted from a string
     * are concatenated, e.g. "321" => 31, "one65" => 15.
     * @see #getNumbersInsideString(String, boolean)
     */
    private int getFirstAndLastNumbersConcatenated(String string, boolean onlyDigits) {
        LinkedList<Integer> numbersInString = getNumbersInsideString(string, onlyDigits);
        return concatenateNumbers(
                numbersInString.getFirst(),
                numbersInString.getLast()
        );
    }

    private int concatenateNumbers(int first, int second) {
        String concatenatedNumbers = first + "" + second;
        return Integer.parseInt(concatenatedNumbers);
    }

    /**
     * Extracts all numbers inside a string.
     * @param string E.g. "2oneight3"
     * @param onlyDigits If true, only digits are extracted from
     *                   the string, otherwise "one", "two", "three",
     *                   "four", "five", "six", "seven", "eight"
     *                   and "nine" also count as valid "digits"
     * @return E.g. "2oneight3" => [2, 3] with only digits, otherwise [2, 1, 8, 3].
     * "3twone57" => [3, 5, 7] with only digits, otherwise [3, 2, 1, 5, 7].
     */
    private LinkedList<Integer> getNumbersInsideString(String string, boolean onlyDigits) {
        LinkedList<Integer> numbers = new LinkedList<>();

        // Contains all the letters read in the string
        // until the next integer shows up.
        StringBuilder numberWordBuffer = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            String charAt = String.valueOf(string.charAt(i));
            numberWordBuffer.append(charAt);
            
            try {
                int number = Integer.parseInt(charAt);
                numbers.add(number);
                numberWordBuffer = new StringBuilder();
            } catch (NumberFormatException e) {
                if (onlyDigits) {
                    continue;
                }
                
                Optional<Integer> writtenNumber = Numbers.getDigitFromName(numberWordBuffer.toString());
                writtenNumber.ifPresent(numbers::add);
            }
        }

        return numbers;
    }
}
