package it.tino.adventofcode.year2023.day3.component;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class ThreeLinesGroup {

    private final String topLine;
    private final String middleLine;
    private final String bottomLine;

    public ThreeLinesGroup(String topLine, String middleLine, String bottomLine) {
        this.topLine = topLine;
        this.middleLine = middleLine;
        this.bottomLine = bottomLine;
    }

    /**
     * Finds characters adjacent to a specific index, even
     * diagonally.
     * @return E.g. Given index = 0, offset = 1 and these three lines:
     * <br>
     * ".1........."<br>
     * "*2........."<br>
     * ".3........."<br>
     * the result is [1, 2, 3]
     */
    public List<String> findAdjacentCharacters(int characterIndex, int horizontalOffset) {
        String charAtTopLeft = "";
        String charAtLeft = "";
        String charAtBottomLeft = "";

        if (topLine != null && characterIndex + horizontalOffset > 0 && topLine.length() > characterIndex + horizontalOffset) {
            charAtTopLeft = String.valueOf(topLine.charAt(characterIndex + horizontalOffset));
        }

        if (characterIndex + horizontalOffset > 0 && middleLine.length() > characterIndex + horizontalOffset) {
            charAtLeft = String.valueOf(middleLine.charAt(characterIndex + horizontalOffset));
        }

        if (bottomLine != null && characterIndex + horizontalOffset > 0 && bottomLine.length() > characterIndex + horizontalOffset) {
            charAtBottomLeft = String.valueOf(bottomLine.charAt(characterIndex + horizontalOffset));
        }

        return Arrays.asList(charAtTopLeft, charAtLeft, charAtBottomLeft);
    }
}
