package it.tino.adventofcode.year2022.day2.component;

import java.util.*;

/**
 * Represents a shape from the game rock-paper-scissors.
 */
public class Shape implements Comparable<Shape>  {

    public static final Shape ROCK = new Shape("A", "C", 1);
    public static final Shape PAPER = new Shape("B", "A", 2);
    public static final Shape SCISSORS = new Shape("C", "B", 3);

    public static final Collection<Shape> SHAPES = Arrays.asList(ROCK, PAPER, SCISSORS);

    private String id;

    /**
     * Each shape allows to obtain a score
     * just for using them.
     */
    private int score;
    private String beatableId;

    public Shape(String id) {
        for (Shape shape : SHAPES) {
            if (shape.getId().equals(id)) {
                copy(this, shape);
            }
        }
    }

    private Shape(String id, String beatableId, int pointForUsing) {
        this.id = id.toUpperCase();
        this.beatableId = beatableId;
        this.score = pointForUsing;
    }

    public int calculateMatchScore(Shape opponent) {
        int matchOutcome = compareTo(opponent);

        if (matchOutcome > 0) {
            return 6;
        }

        if (matchOutcome == 0) {
            return 3;
        }

        return 0;
    }

    public String getId() {
        return id;
    }

    public Shape getBeatable() {
        return new Shape(beatableId);
    }

    /**
     * Returns all counter shapes for this shape.
     * "Counter" means a shape which beats this shape.
     */
    public Collection<Shape> getCounters() {
        List<Shape> counters = new ArrayList<>();
        for (Shape shape : SHAPES) {
            if (shape.beatableId.equals(id)) {
                counters.add(shape);
            }
        }

        return counters;
    }

    public int getScore() {
        return score;
    }

    private void copy(Shape destination, Shape source) {
        destination.id = source.id;
        destination.score = source.score;
        destination.beatableId = source.beatableId;
    }

    @Override
    public int compareTo(Shape that) {
        if (this.id.equals(that.id)) {
            return 0;
        }

        if (this.beatableId.equals(that.id)) {
            return 1;
        }

        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shape shape = (Shape) o;

        return id.equals(shape.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
