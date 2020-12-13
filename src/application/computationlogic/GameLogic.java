package application.computationlogic;

import static application.gamedomain.SudokuGame.BOUNDARY;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.gameconstants.GameState;
import application.gameconstants.Rows;
import application.gamedomain.SudokuGame;

public class GameLogic {
	public static SudokuGame getNewGame() {
		return new SudokuGame(GameState.NEW, GameGenerator.getNewGameGrid());
	}
	
	public static GameState checkForCompletion(int[][] grid) {
		if(sudokuIsInvalid(grid)) return GameState.ACTIVE;
		if(tilesAreNotFilled(grid)) return GameState.ACTIVE;
		return GameState.COMPLETE;
	}

	public static boolean sudokuIsInvalid(int[][] grid) {
		if(rowAreInvalid(grid))	return true;
		if(colAreInvalid(grid))	return true;
		if(squaresAreInvalid(grid))	return true;
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean rowAreInvalid(int[][] grid) {
		// TODO Auto-generated method stub
		for(int j = 0; j < BOUNDARY; j++) {
			List<Integer> row = new ArrayList<>();
			for(int i = 0; i < BOUNDARY; i++) {
				row.add(grid[i][j]);
			}
			if(collectionHasRepeat(row)) return true;
		}
		return false;
	}

	private static boolean colAreInvalid(int[][] grid) {
		// TODO Auto-generated method stub
		for(int i = 0; i < BOUNDARY; i++) {
			List<Integer> row = new ArrayList<>();
			for(int j = 0; j < BOUNDARY; j++) {
				row.add(grid[i][j]);
			}
			if(collectionHasRepeat(row)) return true;
		}
		return false;
	}

	private static boolean squaresAreInvalid(int[][] grid) {
		// TODO Auto-generated method stub
		if(rowOfSquaresIsInvalid(Rows.TOP, grid))	return true;
		if(rowOfSquaresIsInvalid(Rows.MID, grid))	return true;
		if(rowOfSquaresIsInvalid(Rows.BOT, grid))	return true;
		return false;
	}

	private static boolean rowOfSquaresIsInvalid(Rows value, int[][] grid) {
		// TODO Auto-generated method stub
		switch(value) {
			case TOP:
				if(squaresIsInvalid(0, 0, grid)) return true;
				if(squaresIsInvalid(0, 3, grid)) return true;
				if(squaresIsInvalid(0, 6, grid)) return true;
				return false;
			case MID:
				if(squaresIsInvalid(3, 0, grid)) return true;
				if(squaresIsInvalid(3, 3, grid)) return true;
				if(squaresIsInvalid(3, 6, grid)) return true;
				return false;
			case BOT:
				if(squaresIsInvalid(6, 0, grid)) return true;
				if(squaresIsInvalid(6, 3, grid)) return true;
				if(squaresIsInvalid(6, 6, grid)) return true;
				return false;
			default:
				return false;
		}
		
	}

	private static boolean squaresIsInvalid(int i, int j, int[][] grid) {
		// TODO Auto-generated method stub
		int iEnd = i + 3;
		int jEnd = j + 3;
		
		List<Integer> square = new ArrayList<>();
		while(j < jEnd) {
			while(i < iEnd) {
				square.add(grid[i][j]);
				i++;
			}
			i-=3;
			j++;
		}
		if(collectionHasRepeat(square)) return true;
		return false;
	}

	private static boolean collectionHasRepeat(List<Integer> square) {
		// TODO Auto-generated method stub
		for(int index = 1; index <= BOUNDARY; index++) {
			if(Collections.frequency(square, index) > 1) return true;
		}
		return false;
	}

	private static boolean tilesAreNotFilled(int[][] grid) {
		// TODO Auto-generated method stub
		for(int i = 0; i < BOUNDARY; i++) {
			for(int j = 0; j < BOUNDARY; j++) {
				if(grid[i][j] == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
}
