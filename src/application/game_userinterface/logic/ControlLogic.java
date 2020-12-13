package application.game_userinterface.logic;

import java.io.IOException;

import application.computationlogic.GameLogic;
import application.game_userinterface.IUserInterfaceContract;
import application.game_userinterface.IUserInterfaceContract.View;
import application.gameconstants.GameState;
import application.gameconstants.Messages;
import application.gamedomain.IStorage;
import application.gamedomain.SudokuGame;

public class ControlLogic implements IUserInterfaceContract.EventListener{

	private IStorage storage;
	private IUserInterfaceContract.View view;
	
	
	
	public ControlLogic(IStorage storage, View view) {
		super();
		this.storage = storage;
		this.view = view;
	}

	@Override
	public void onSudokuInput(int x, int y, int input) {
		// TODO Auto-generated method stub
		try {
			SudokuGame gameData = storage.getGameData();
			int[][] newGridState = gameData.getCopyOfBoardState();
			newGridState[x][y] = input;
			
			gameData = new SudokuGame(GameLogic.checkForCompletion(newGridState), newGridState);
			storage.updateGameData(gameData);
			view.updateSquare(x, y, input);
			
			if(gameData.getGamestate() == GameState.COMPLETE) {
				view.showDialog(Messages.GAME_COMPLETE);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			view.showError(Messages.ERROR);
		}
		
	}

	@Override
	public void onDialogClick() {
		// TODO Auto-generated method stub
		try {
			storage.updateGameData(GameLogic.getNewGame());
			view.updateBoard(storage.getGameData());
		}	catch(IOException e) {
			e.printStackTrace();
			view.showError(Messages.ERROR);
		}
	}

}
