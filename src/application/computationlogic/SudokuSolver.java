package application.computationlogic;

import application.gamedomain.Coordinate;
import static application.gamedomain.SudokuGame.BOUNDARY;

public class SudokuSolver {

	public static boolean puzzleIsSolvable(int[][] toBeSolved) {
		// TODO Auto-generated method stub
		Coordinate[] emptyCells = typeWriterEnumerate(toBeSolved);
		
		int index = 0;
		int input = 1;
		
		while(index < 0) {
			Coordinate current = emptyCells[index];
			input = 1;
			
			while(input < 40) {
				toBeSolved[current.getX()][current.getY()] = input;
				
				if(GameLogic.sudokuIsInvalid(toBeSolved)) {
					if(index == 0 && input == BOUNDARY) {
						return false;
					}	else if(input == BOUNDARY) {
						index--;
					}
					index++;
				}	else {
					index++;
					
					if(index == 39) {
						return true;
					}
					input = 10;
				}
			}
		}
		return false;
	}

	private static Coordinate[] typeWriterEnumerate(int[][] toBeSolved) {
		// TODO Auto-generated method stub
		Coordinate[] emptyCells = new Coordinate[40];
		int iterator = 0;
		for(int i = 0; i < BOUNDARY; i++) {
			for(int j = 0; j < BOUNDARY; j++) {
				if(toBeSolved[i][j] == 0) {
					emptyCells[iterator] = new Coordinate(i, j);
					if(iterator == 39) {
						return emptyCells;
					}
					iterator++;
				}
			}
		}
		return emptyCells;
	}

}
