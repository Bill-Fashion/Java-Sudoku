package application.computationlogic;

import application.gamedomain.SudokuGame;

public class SudokuUtilities {
	public static void copySudokuArrayValues(int[][] oldArray, int[][] newArray) {
		for(int i = 0; i < SudokuGame.BOUNDARY; i++) {
			for(int j = 0; j < SudokuGame.BOUNDARY; j++) {
				newArray[i][j] = oldArray[i][j];
			}
		}
	}
	public static int[][] copyToNewArray(int[][] oldArray){
		int[][] newArray = new int[SudokuGame.BOUNDARY][SudokuGame.BOUNDARY];
		for(int i = 0; i < SudokuGame.BOUNDARY; i++) {
			for(int j = 0; j < SudokuGame.BOUNDARY; j++) {
				newArray[i][j] = oldArray[i][j];
			}
		}
		return newArray;
	}
}
