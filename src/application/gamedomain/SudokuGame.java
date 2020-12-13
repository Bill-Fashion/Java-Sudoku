package application.gamedomain;

import java.io.Serializable;

import application.computationlogic.SudokuUtilities;
import application.gameconstants.GameState;

public class SudokuGame implements Serializable{
	private final GameState gamestate;
	private final int[][] boardState;
	
	public static final int BOUNDARY = 9;
	
	public SudokuGame(GameState gamestate, int[][] boardState) {
		super();
		this.gamestate = gamestate;
		this.boardState = boardState;
	}

	public GameState getGamestate() {
		return gamestate;
	}

	public int[][] getCopyOfBoardState() {
		return SudokuUtilities.copyToNewArray(boardState);
	}
	
	

}
