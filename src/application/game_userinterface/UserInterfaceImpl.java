package application.game_userinterface;

import java.util.HashMap;



import application.game_userinterface.IUserInterfaceContract.EventListener;
import application.gamedomain.Coordinate;
import application.gamedomain.SudokuGame;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class UserInterfaceImpl implements IUserInterfaceContract.View, EventHandler<KeyEvent>{
	private final Stage stage;
	private final Group root;

	private HashMap<Coordinate, SudokuTextField> textFieldCoordinate;
	private IUserInterfaceContract.EventListener listener;
	
	private static final double WINDOW_X = 668;
	private static final double WINDOW_Y = 732;
	private static final double BOARD_PADDING = 50;
	private static final double BOARD_X_AND_Y = 576;
	
	private static final Color WINDOW_BACKGROUND_COLOR = Color.rgb(0, 150, 136);
	private static final Color BOARD_BACKGROUND_COLOR = Color.rgb(224, 242, 241);
	private static final String SUDOKU = "Sudoku";
	
	
	
	public UserInterfaceImpl(Stage stage) {
		this.stage = stage;
		this.root = new Group();
		this.textFieldCoordinate = new HashMap<>();
		initializeUserInterface();
	}

	private void initializeUserInterface() {
		// TODO Auto-generated method stub
		drawBackground(root);
		drawTitle(root);
		drawSudokuBoard(root);
		drawTextFields(root);
		drawGridLines(root);
		stage.show();
		
		
	}

	private void drawGridLines(Group root2) {
		int xAndY = 114;
		int index = 0;
		while(index < 8) {
			int thickness;
			if(index == 2 || index == 5) {
				thickness = 3;
			}	else {
				thickness = 2;
			}
			
			Rectangle verticleLine = getLine(xAndY + 64 * index, BOARD_PADDING, BOARD_X_AND_Y, thickness);
			Rectangle horizontalLine = getLine(BOARD_PADDING, xAndY + 64 * index, thickness, BOARD_X_AND_Y);
			
			root.getChildren().addAll(verticleLine, horizontalLine);
		}
		
		
	}

	private Rectangle getLine(double x, double y, double h, double w) {
		Rectangle line = new Rectangle();
		line.setX(x);
		line.setY(y);
		line.setHeight(h);
		line.setWidth(w);
		
		line.setFill(Color.BLACK);
		return line;
	}

	private void drawTextFields(Group root2) {
		// TODO Auto-generated method stub
		
	}

	private void drawSudokuBoard(Group root2) {
		// TODO Auto-generated method stub
		
	}

	private void drawTitle(Group root2) {
		// TODO Auto-generated method stub
		
	}

	private void drawBackground(Group root2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handle(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setListener(EventListener listener) {
		// TODO Auto-generated method stub
		this.listener = listener;
	}

	@Override
	public void updateSquare(int x, int y, int input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBoard(SudokuGame game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showDialog(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showError(String error) {
		// TODO Auto-generated method stub
		
	}
	
}
