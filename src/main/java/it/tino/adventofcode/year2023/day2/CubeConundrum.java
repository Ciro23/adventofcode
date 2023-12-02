package it.tino.adventofcode.year2023.day2;

import it.tino.adventofcode.ResourcesFileReader;
import it.tino.adventofcode.year2023.day2.component.Color;
import it.tino.adventofcode.year2023.day2.component.Cube;
import it.tino.adventofcode.year2023.day2.component.Game;
import it.tino.adventofcode.year2023.day2.component.Subset;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2023/day/2">Problem description for more details</a>
 */
public class CubeConundrum {

    public static void main(String[] args) {
        ResourcesFileReader fileReader = new ResourcesFileReader();
        CubeConundrum cubeConundrum = new CubeConundrum();

        Map<Color, Integer> numberOfCubesAvailableByColor = new HashMap<>();
        numberOfCubesAvailableByColor.put(Color.RED, 12);
        numberOfCubesAvailableByColor.put(Color.GREEN, 13);
        numberOfCubesAvailableByColor.put(Color.BLUE, 14);

        List<String> fileLines = fileReader.getFileLines("2023/day-2-cube-games.txt");
        Collection<Game> games = cubeConundrum.parseGamesFromStrings(fileLines);
        int sumOfIdOfPossibleGames = cubeConundrum.sumPossibleGameIds(games, numberOfCubesAvailableByColor);

        System.out.println("Sum of possible game ids: " + sumOfIdOfPossibleGames);
    }

    private int sumPossibleGameIds(
        Collection<Game> games,
        Map<Color, Integer> availableCubeQuantities
    ) {
        int sumOfPossibleGameIds = 0;
        for (Game game : games) {
            boolean gamePossible = isGamePossible(game, availableCubeQuantities);
            if (gamePossible) {
                sumOfPossibleGameIds += game.id();
            }
        }
        return sumOfPossibleGameIds;
    }

    private boolean isGamePossible(
        Game game,
        Map<Color, Integer> availableCubeQuantities
    ) {
        for (Subset subset : game.subsets()) {
            for (Cube cube : subset.cubes()) {
                int availableCubeQuantity = availableCubeQuantities.get(cube.color());
                if (cube.quantity() > availableCubeQuantity) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * @see #parseGameFromString(String) 
     */
    private Collection<Game> parseGamesFromStrings(Collection<String> gameStrings) {
        List<Game> games = new ArrayList<>();
        for (String gameString : gameStrings) {
            Game game = parseGameFromString(gameString);
            games.add(game);
        }
        return games;
    }

    /**
     * @param gameString E.g. "Game 1: 2 red, 1 blue; 3 green"
     */
    private Game parseGameFromString(String gameString) {
        List<String> gameInfoAndSubsets = Arrays.asList(gameString.split(": "));
        if (gameInfoAndSubsets.size() < 2) {
            throw new IllegalArgumentException("Game string argument is not formatted correctly. Your value:" +
                    " '" + gameString + "', correct example: '3 blue'");
        }

        // "Game 1" => 1, "Game 8" => 8
        int gameId = Integer.parseInt(gameInfoAndSubsets.get(0).split(" ")[1]);

        String[] subsetsString = gameInfoAndSubsets.get(1).split("; ");
        List<Subset> subsets = parseSubsetsFromStrings(Arrays.asList(subsetsString));

        return new Game(gameId, subsets);
    }

    /**
     * @see #parseSubsetFromString(String)  
     */
    private List<Subset> parseSubsetsFromStrings(Collection<String> strings) {
        List<Subset> subsets = new ArrayList<>();
        for (String string : strings) {
            Subset subset = parseSubsetFromString(string);
            subsets.add(subset);
        }
        return subsets;
    }

    /**
     * @param string E.g. "2 red, 1 blue"
     */
    private Subset parseSubsetFromString(String string) {
        String[] subsetsString = string.split(", ");
        List<Cube> cubes = new ArrayList<>();

        for (String subsetString : subsetsString) {
            Cube cube = parseCubeFromString(subsetString);
            cubes.add(cube);
        }

        return new Subset(cubes);
    }

    /**
     * @param cubeString E.g. "3 blue"
     */
    private Cube parseCubeFromString(String cubeString) {
        String[] quantityAndColor = cubeString.split(" ");
        int quantity = Integer.parseInt(quantityAndColor[0]);
        String colorName = quantityAndColor[1].toLowerCase();
        Optional<Color> color = Color.fromName(colorName);

        if (color.isEmpty()) {
            throw new IllegalArgumentException("Cube string argument is not formatted correctly. Your value:" +
                    " '" + cubeString + "', correct example: '3 blue'");
        }

        return new Cube(color.get(), quantity);
    }
}
