package it.tino.adventofcode.day2.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents a shape from the game rock-paper-scissors.
 */
public class Shape implements Comparable<Shape>  {

    String id;

    /**
     * Each shape allows to obtain a score
     * just for using them.
     */
    int score;

    Set<String> beatable;

    public Shape(String character) {
        setId(character);
        initializeScore();

        switch (id) {
            case "A" -> setBeatable(Set.of("C"));
            case "B" -> setBeatable(Set.of("A"));
            case "C" -> setBeatable(Set.of("B"));
        }
    }

    public void setId(String character) {
        character = character.toUpperCase();
        Map<String, String> alternativeIds = new HashMap<>();
        alternativeIds.put("X", "A");
        alternativeIds.put("Y", "B");
        alternativeIds.put("Z", "C");

        id = alternativeIds.getOrDefault(character, character);
    }

    public void setBeatable(Set<String> beatable) {
        this.beatable = beatable;
    }

    public int getScore() {
        return score;
    }

    private void initializeScore() {
        Map<String, Integer> scorePerShape = new HashMap<>();
        scorePerShape.put("A", 1);
        scorePerShape.put("B", 2);
        scorePerShape.put("C", 3);

        this.score = scorePerShape.get(id);
    }

    @Override
    public int compareTo(Shape that) {
        if (this.id.equals(that.id)) {
            return 0;
        }

        if (this.beatable.contains(that.id)) {
            return 1;
        }

        return -1;
    }
}
