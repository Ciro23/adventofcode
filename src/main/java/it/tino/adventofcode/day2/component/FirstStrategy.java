package it.tino.adventofcode.day2.component;

import java.util.Collection;

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
            Shape shapeToRespondWith = new Shape(match.secondCharacter());

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
