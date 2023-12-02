package it.tino.adventofcode.year2023.day2.component;

import java.util.Optional;

public enum Color {
    RED("red"),
    GREEN("green"),
    BLUE("blue");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public static Optional<Color> fromName(String name) {
        for (Color value : Color.values()) {
            if (value.name.equals(name)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}
