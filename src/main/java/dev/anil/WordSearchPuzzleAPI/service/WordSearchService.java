package dev.anil.WordSearchPuzzleAPI.service;

import dev.anil.WordSearchPuzzleAPI.model.Coordinate;
import dev.anil.WordSearchPuzzleAPI.model.Grid;
import dev.anil.WordSearchPuzzleAPI.util.Direction;
import dev.anil.WordSearchPuzzleAPI.util.Utility;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static dev.anil.WordSearchPuzzleAPI.util.Utility.randomFillGrid;

@Service
public class WordSearchService {
    public char[][] generateGrid(int gridSize, List<String> words) {
        Grid grid = new Grid(gridSize);
        List<Coordinate> coordinates = new ArrayList<>();
        char[][] contents =  grid.getContents();

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                coordinates.add(new Coordinate(i, j));
                contents[i][j] = '_';
            }
        }

        for (String word : words) {
            Collections.shuffle(coordinates);
            for (Coordinate coordinate : coordinates) {
                int x = coordinate.getX();
                int y = coordinate.getY();
                Direction selectedDirection = Utility.getDirectionForFit(contents, word, coordinate);
                if (selectedDirection != null) {
                    switch (selectedDirection) {
                        case HORIZONTAL:
                            for (char c : word.toCharArray()) {
                                contents[x][y++] = c;
                            }
                            break;
                        case VERTICAL:
                            for (char c : word.toCharArray()) {
                                contents[x++][y] = c;
                            }
                            break;
                        case DIAGONAL:
                            for (char c : word.toCharArray()) {
                                contents[x++][y++] = c;
                            }
                            break;
                        case HORIZONTAL_INVERSE:
                            for (char c : word.toCharArray()) {
                                contents[x][y--] = c;
                            }
                            break;
                        case VERTICAL_INVERSE:
                            for (char c : word.toCharArray()) {
                                contents[x--][y] = c;
                            }
                            break;
                        case DIAGONAL_INVERSE:
                            for (char c : word.toCharArray()) {
                                contents[x--][y--] = c;
                            }
                            break;
                    }
                    break;
                }
            }
        }
        randomFillGrid(contents);
        return contents;
    }
}
