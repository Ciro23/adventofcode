package it.tino.adventofcode;

import it.tino.adventofcode.year2023.day1.component.Numbers;

import java.util.LinkedList;
import java.util.Optional;

public class StringUtils {

    /**
     * The first and last number extracted from a string
     * are concatenated, e.g. "321" => 31, "one65" => 15.
     * @see #getNumbersInsideString(String, boolean)
     */
    public static int getFirstAndLastNumbersConcatenated(String string, boolean onlyDigits) {
        LinkedList<Integer> numbersInString = getNumbersInsideString(string, onlyDigits);
        return concatenateNumbers(
                numbersInString.getFirst(),
                numbersInString.getLast()
        );
    }

    public static int concatenateNumbers(int first, int second) {
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
    public static LinkedList<Integer> getNumbersInsideString(String string, boolean onlyDigits) {
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
