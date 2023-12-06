package it.tino.adventofcode.year2023.day3.component;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

    private final int width;
    private final int height;

    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public List<Coordinate> findAdjacentCoordinates(Coordinate coordinate) {
        int x = coordinate.x();
        int y = coordinate.y();

        List<Coordinate> adjacentCoordinates = new ArrayList<>();
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                if (x == 0 && y == 0) {
                    continue;
                }

                if (isInBound(j, i)) {
                    Coordinate adjacentCoordinate = new Coordinate(j, i);
                    adjacentCoordinates.add(adjacentCoordinate);
                }
            }
        }
        return adjacentCoordinates;
    }

    public boolean isInBound(int x, int y) {
        return (x >= 0 && x < width) && (y >= 0 && y < height);
    }
}
