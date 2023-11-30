package it.tino.adventofcode.day2.component;

import java.util.Collection;

/**
 * You finally get told what the second character really
 * means: it says how the round needs to end.
 * X means you need to lose, Y means you need to draw,
 * Z means you need to win.
 */
public class SecondStrategy implements Strategy {

    @Override
    public int findScore(Collection<Match> matches) {
        int scoreForShape = 0;
        int scoreForMatchOutcome = 0;

        for (Match match : matches) {
            Shape shapeSelectedByOpponent = new Shape(match.firstCharacter());
            String expectedMatchOutcome = match.secondCharacter();

            Shape shapeToRespondWith;
            if (expectedMatchOutcome.equals("X")) {
                shapeToRespondWith = shapeSelectedByOpponent.getBeatable();
            } else if (expectedMatchOutcome.equals("Y")) {
                shapeToRespondWith = shapeSelectedByOpponent;
            } else {
                shapeToRespondWith = shapeSelectedByOpponent.getCounters()
                        .stream()
                        .findAny()
                        .orElseThrow();
            }

            scoreForShape += shapeToRespondWith.getScore();
            scoreForMatchOutcome += shapeToRespondWith.calculateMatchScore(shapeSelectedByOpponent);
        }

        return scoreForShape + scoreForMatchOutcome;
    }
}
