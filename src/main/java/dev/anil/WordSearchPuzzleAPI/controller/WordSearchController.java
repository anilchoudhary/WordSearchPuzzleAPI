package dev.anil.WordSearchPuzzleAPI.controller;

import dev.anil.WordSearchPuzzleAPI.service.WordSearchService;
import dev.anil.WordSearchPuzzleAPI.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class WordSearchController {

    @Autowired
    private WordSearchService wordSearchService;

    @GetMapping("/getGrid")
    public ResponseEntity<String > createWordGrid(@RequestParam("gridSize") int gridSize, @RequestParam("words") List<String> words){
        char[][] grid = wordSearchService.generateGrid(gridSize, words);
        StringBuilder gridToString = new StringBuilder();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                gridToString.append(grid[i][j]).append(" ");
            }
            gridToString.append("\r\n");
        }
        return new ResponseEntity<String>(gridToString.toString(), HttpStatus.OK);
    }

}
