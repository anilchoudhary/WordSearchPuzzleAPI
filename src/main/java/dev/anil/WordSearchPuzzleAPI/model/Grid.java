package dev.anil.WordSearchPuzzleAPI.model;

import dev.anil.WordSearchPuzzleAPI.util.Direction;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Data
public class Grid {

    private int gridSize;
    private char[][] contents;

    public Grid(int gridSize) {
        this.gridSize = gridSize;
        this.contents = new char[gridSize][gridSize];
    }


}
