package it.tino.adventofcode.day2.component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * At the beginning you get told the first character
 * is the opponent choice, but you don't know what
 * the second one represents. You assume it's what
 * you should pick, and that probably X is rock,
 * Y is paper, Z is scissors.
 */
public class FirstStrategy implements Strategy {

    @Override
    public int findScore(Collection<Match> matches) {
        int scoreForShape = 0;
        int scoreForMatchOutcome = 0;

        for (Match match : matches) {
            Shape shapeSelectedByOpponent = new Shape(match.firstCharacter());

            String shapeToRespondWithId = match.secondCharacter();
            Map<String, Shape> alternativeIds = new HashMap<>();
            alternativeIds.put("X", Shape.ROCK);
            alternativeIds.put("Y", Shape.PAPER);
            alternativeIds.put("Z", Shape.SCISSORS);
            Shape shapeToRespondWith = alternativeIds.get(shapeToRespondWithId);

            scoreForShape += shapeToRespondWith.getScore();
            scoreForMatchOutcome += shapeToRespondWith.calculateMatchScore(shapeSelectedByOpponent);
        }

        return scoreForShape + scoreForMatchOutcome;
    }
}
