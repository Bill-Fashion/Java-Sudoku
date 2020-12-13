package application.computationlogic;

import static application.gamedomain.SudokuGame.BOUNDARY;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import application.gamedomain.Coordinate;

public class GameGenerator {
	public static int[][] getNewGameGrid() {
		return unsolveGame(getSolvedGame());
	}

	private static int[][] unsolveGame(int[][] solvedGame) {
		// TODO Auto-generated method stub
		Random rd = new Random(System.currentTimeMillis());
		
		boolean solvable = false;
		int[][] solvableArray = new int[BOUNDARY][BOUNDARY];
		
		while(solvable == false) {
			SudokuUtilities.copySudokuArrayValues(solvedGame, solvableArray);
			
			int index = 0;
			
			while(index < 40) {
				int x = rd.nextInt(BOUNDARY);
				int y = rd.nextInt(BOUNDARY);
				
				if(solvableArray[x][y] != 0) {
					solvableArray[x][y] = 0;
					index++;
				}
			}
			int[][] toBeSolved = new int[BOUNDARY][BOUNDARY];
			SudokuUtilities.copySudokuArrayValues(solvableArray, toBeSolved);
			
			solvable = SudokuSolver.puzzleIsSolvable(toBeSolved);
		}
		return solvableArray;
	}

	private static int[][] getSolvedGame() {
		Random rd = new Random(System.currentTimeMillis());
		int[][] newGrid = new int[BOUNDARY][BOUNDARY];
		for(int i = 1; i <= BOUNDARY; i++) {
			int allocations = 0;
			int interrupt = 0;
			
			List<Coordinate> allocTracker = new ArrayList<>();
			
			int attempts = 0;
			
			while(allocations < BOUNDARY) {
				if(interrupt > 200) {
					allocTracker.forEach(coord -> {newGrid[coord.getX()][coord.getY()] = 0;});
					allocations = 0;
					interrupt = 0;
					allocTracker.clear();
					attempts++;
					
					if(attempts > 500) {
						clearArray(newGrid);
						i = 1;
						attempts = 0;
					}
				}
			}
		}
		return null;
	}

	private static void clearArray(int[][] newGrid) {
		// TODO Auto-generated method stub
		for(int i = 0; i < BOUNDARY; i++) {
			for(int j = 0; j < BOUNDARY; j++) {
				newGrid[i][j] = 0;
			}
		}
	}
	
	
}
