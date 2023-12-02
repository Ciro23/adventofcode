package it.tino.adventofcode.year2023.day1.component;

import java.util.Optional;

/**
 * Represents numbers from 1 to 9.
 */
public enum Numbers {
    // Zero is not handled because the problem description
    // doesn't mention it in the valid "digits".
    ONE(1, "one"),
    TWO(2, "two"),
    THREE(3, "three"),
    FOUR(4, "four"),
    FIVE(5, "five"),
    SIX(6, "six"),
    SEVEN(7, "seven"),
    EIGHT(8, "eight"),
    NINE(9, "nine");

    private final int number;
    private final String name;

    Numbers(int number, String name) {
        this.number = number;
        this.name = name;
    }

    /**
     * Translate an english written number to its corresponding digit.
     * This method is case-insensitive.
     * @param name The name in english of the number. Any character to
     *             the left is not considered.
     *             E.g. "six" => 6, "xxxseven" => 7, "xxonexx" => empty.
     * @return The optional containing the digit corresponding to
     * the given name, or an empty optional if none is found.
     */
    public static Optional<Integer> getDigitFromName(String name) {
        for (Numbers number : Numbers.values()) {
            if (name.toLowerCase().endsWith(number.name.toLowerCase())) {
                return Optional.of(number.number);
            }
        }
        return Optional.empty();
    }
}
