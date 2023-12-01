package it.tino.adventofcode.year2022.day2.component;

import java.util.Collection;

/**
 * A "strategy" is what you think you should
 * do based on a list of instructions.
 */
public interface Strategy {

    /**
     * A strategy guide is handed to you with a collection
     * of matches of rock-paper-scissors, and you want to calculate
     * what your total score would be by following the strategy.
     *
     * @return The calculated score.
     */
    int findScore(Collection<Match> matches);
}
