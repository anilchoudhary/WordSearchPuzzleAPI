package dev.anil.WordSearchPuzzleAPI.util;

import dev.anil.WordSearchPuzzleAPI.model.Coordinate;
import dev.anil.WordSearchPuzzleAPI.model.Grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Utility {
    public static void displayGrid(char[][] contents) {
        int gridSize = contents[0].length;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                System.out.print(contents[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void randomFillGrid(char[][] contents) {
        int gridSize = contents[0].length;
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (contents[i][j] == '_') {
                    int randomIndex = ThreadLocalRandom.current().nextInt(0, letters.length());
                    contents[i][j] = letters.charAt(randomIndex);
                }
            }
        }
    }

    public static Direction getDirectionForFit(char[][] contents, String word, Coordinate coordinate) {
        List<Direction> directions = Arrays.asList(Direction.values());
        Collections.shuffle(directions);
        for (Direction direction : directions) {
            if (doesFit(contents, word, coordinate, direction)) {
                return direction;
            }
        }
        return null;
    }

    public static boolean doesFit(char[][] contents, String word, Coordinate coordinate, Direction direction) {
        int gridSize = contents[0].length;
        int wordLength = word.length();
        int x = coordinate.getX();
        int y = coordinate.getY();
        switch (direction) {
            case HORIZONTAL:
                if (y + wordLength > gridSize) return false;
                for (int i = 0; i < wordLength; i++) {
                    char letter = contents[x][y + i];
                    if (letter != '_' && letter != word.charAt(i)) return false;
                }
                break;
            case VERTICAL:
                if (x + wordLength > gridSize) return false;
                for (int i = 0; i < wordLength; i++) {
                    char letter = contents[x + i][y];
                    if (letter != '_' && letter != word.charAt(i)) return false;
                }
                break;
            case DIAGONAL:
                if (x + wordLength > gridSize || y + wordLength > gridSize) return false;
                for (int i = 0; i < wordLength; i++) {
                    char letter = contents[x + i][y + i];
                    if (letter != '_' && letter != word.charAt(i)) return false;
                }
                break;
            case HORIZONTAL_INVERSE:
                if (y < wordLength) return false;
                for (int i = 0; i < wordLength; i++) {
                    char letter = contents[x][y - i];
                    if (letter != '_' && letter != word.charAt(i)) return false;
                }
                break;
            case VERTICAL_INVERSE:
                if (x < wordLength) return false;
                for (int i = 0; i < wordLength; i++) {
                    char letter = contents[x - i][y];
                    if (letter != '_' && letter != word.charAt(i)) return false;
                }
                break;
            case DIAGONAL_INVERSE:
                if (x < wordLength || y < wordLength) return false;
                for (int i = 0; i < wordLength; i++) {
                    char letter = contents[x - i][y - i];
                    if (letter != '_' && letter != word.charAt(i)) return false;
                }
                break;
        }
        return true;
    }

}
