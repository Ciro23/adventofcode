package it.tino.adventofcode.day2;

import it.tino.adventofcode.ResourcesFileReader;
import it.tino.adventofcode.day2.component.FirstStrategy;
import it.tino.adventofcode.day2.component.Match;
import it.tino.adventofcode.day2.component.Strategy;

import java.util.*;

public class RockPaperScissorsStrategy {

    public static void main(String[] args) {
        ResourcesFileReader fileReader = new ResourcesFileReader();
        RockPaperScissorsStrategy rockPaperScissorsStrategy = new RockPaperScissorsStrategy();

        List<String> lines = fileReader.getFileLines("rock-paper-scissors-strategy.txt");
        Collection<Match> matches = rockPaperScissorsStrategy.getMatchesFromFileLines(lines);

        Strategy firstStrategy = new FirstStrategy();
        int firstStrategyScore = firstStrategy.findScore(matches);

        System.out.println("The score calculated using the first strategy is: " + firstStrategyScore);
    }

    public Collection<Match> getMatchesFromFileLines(Collection<String> lines) {
        List<Match> matches = new ArrayList<>();
        for (String line : lines) {
            String[] split = line.split(" ");

            Match match = new Match(split[0], split[1]);
            matches.add(match);
        }

        return matches;
    }
}
