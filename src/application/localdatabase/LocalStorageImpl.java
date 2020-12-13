package application.localdatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import application.gamedomain.IStorage;
import application.gamedomain.SudokuGame;

public class LocalStorageImpl implements IStorage{
	private static File GAME_DATA = new File(System.getProperty("user.home"), "gamedata.txt");
	
	@Override
	public void updateGameData(SudokuGame game) throws IOException {
		// TODO Auto-generated method stub
		FileOutputStream fos = new FileOutputStream(GAME_DATA);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(game);
		oos.close();
		
	}

	@Override
	public SudokuGame getGameData() throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream(GAME_DATA);
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			SudokuGame gameState = (SudokuGame) ois.readObject();
			ois.close();
			return gameState;
		}	catch(ClassNotFoundException e) {
			throw new IOException("File not found");
		}
	}

}
