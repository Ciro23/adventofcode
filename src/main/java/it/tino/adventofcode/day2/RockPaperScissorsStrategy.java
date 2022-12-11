package it.tino.adventofcode.day2;

import it.tino.adventofcode.FileLinesReader;

import java.util.*;

public class RockPaperScissorsStrategy {

    public static void main(String[] args) {
        FileLinesReader fileLinesReader = new FileLinesReader();
        RockPaperScissorsStrategy rockPaperScissorsStrategy = new RockPaperScissorsStrategy();

        List<String> lines = fileLinesReader.getFileLines("rock-paper-scissors-strategy.txt");
        int totalScore = rockPaperScissorsStrategy.findScoreAccordingToStrategyGuide(lines);

        System.out.println(totalScore);
    }

    private static class Shape implements Comparable<Shape> {

        String id;

        /**
         * Each shape allows to obtain a score
         * only for using them
         */
        int score;

        Set<String> beatable;

        public Shape(String character) {
            setId(character);
            setScore();

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

        public void setScore() {
            Map<String, Integer> scorePerShape = new HashMap<>();
            scorePerShape.put("A", 1);
            scorePerShape.put("B", 2);
            scorePerShape.put("C", 3);

            this.score = scorePerShape.get(id);
        }

        public int getScore() {
            return score;
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

    public int findScoreAccordingToStrategyGuide(List<String> lines) {
        int scoreForShape = 0;
        int scoreForMatchOutcome = 0;
        for (String line : lines) {
            String[] x = line.split(" ");

            Shape shapeSelectedByOpponent = new Shape(x[0]);
            Shape shapeToRespondWith = new Shape(x[1]);

            scoreForShape += shapeToRespondWith.getScore();

            int matchOutcome = shapeToRespondWith.compareTo(shapeSelectedByOpponent);
            if (matchOutcome > 0) {
                scoreForMatchOutcome += 6;
            } else if (matchOutcome == 0) {
                scoreForMatchOutcome += 3;
            }
        }
        return scoreForShape + scoreForMatchOutcome;
    }
}
