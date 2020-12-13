package application.buildlogic;

import java.io.IOException;

import application.computationlogic.GameLogic;
import application.game_userinterface.IUserInterfaceContract;
import application.game_userinterface.logic.ControlLogic;
import application.gamedomain.IStorage;
import application.gamedomain.SudokuGame;
import application.localdatabase.LocalStorageImpl;

public class SudokuBuildLogic {

	public static void build(IUserInterfaceContract.View uiImpl) throws IOException{
		// TODO Auto-generated method stub
		SudokuGame initialState;
		IStorage storage = new LocalStorageImpl();
		
		try {
			initialState = storage.getGameData();
		} catch(IOException e) {
			initialState = GameLogic.getNewGame();
			storage.updateGameData(initialState);
		}
		
		IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, uiImpl);
		
		uiImpl.setListener(uiLogic);
		uiImpl.updateBoard(initialState);
	}

}
