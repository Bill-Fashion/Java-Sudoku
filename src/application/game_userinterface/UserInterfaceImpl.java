package application.game_userinterface;

import java.util.HashMap;

import application.game_userinterface.IUserInterfaceContract.EventListener;
import application.gameconstants.GameState;
import application.gamedomain.Coordinate;
import application.gamedomain.SudokuGame;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserInterfaceImpl implements IUserInterfaceContract.View, EventHandler<KeyEvent> {
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
		while (index < 8) {
			int thickness;
			if (index == 2 || index == 5) {
				thickness = 3;
			} else {
				thickness = 2;
			}

			Rectangle verticleLine = getLine(xAndY + 64 * index, BOARD_PADDING, BOARD_X_AND_Y, thickness);
			Rectangle horizontalLine = getLine(BOARD_PADDING, xAndY + 64 * index, thickness, BOARD_X_AND_Y);

			root.getChildren().addAll(verticleLine, horizontalLine);

			index++;
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
		final int xOrigin = 50;
		final int yOrigin = 50;

		final int xAndYDelta = 64;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int x = xOrigin + i * xAndYDelta;
				int y = yOrigin + j * xAndYDelta;

				SudokuTextField tile = new SudokuTextField(i, j);
				styleSudokuTile(tile, x, y);

				tile.setOnKeyPressed(this);
				textFieldCoordinate.put(new Coordinate(i, j), tile);
				root.getChildren().add(tile);
			}
		}
	}

	private void styleSudokuTile(SudokuTextField tile, double x, double y) {
		// TODO Auto-generated method stub
		Font numberFont = new Font(32);

		tile.setFont(numberFont);
		tile.setAlignment(Pos.CENTER);

		tile.setLayoutX(x);
		tile.setLayoutY(y);
		tile.setPrefHeight(64);
		tile.setPrefWidth(64);
		tile.setBackground(Background.EMPTY);
	}

	private void drawSudokuBoard(Group root2) {
		// TODO Auto-generated method stub
		Rectangle boardBackGround = new Rectangle();

		boardBackGround.setX(BOARD_PADDING);
		boardBackGround.setY(BOARD_PADDING);

		boardBackGround.setHeight(BOARD_X_AND_Y);
		boardBackGround.setWidth(BOARD_X_AND_Y);

		boardBackGround.setFill(BOARD_BACKGROUND_COLOR);

		root.getChildren().addAll(boardBackGround);
	}

	private void drawTitle(Group root2) {
		// TODO Auto-generated method stub
		Text title = new Text(235, 690, SUDOKU);
		title.setFill(Color.WHITE);
		Font titleFont = new Font(43);
		title.setFont(titleFont);
		root.getChildren().add(title);
	}

	private void drawBackground(Group root2) {
		// TODO Auto-generated method stub
		Scene scene = new Scene(root, WINDOW_X, WINDOW_Y);
		scene.setFill(WINDOW_BACKGROUND_COLOR);
		stage.setScene(scene);
	}

	@Override
	public void handle(KeyEvent event) {
		// TODO Auto-generated method stub
		if(event.getEventType() == KeyEvent.KEY_PRESSED) {
			if(event.getText().matches("[0-9]")) {
				int value = Integer.parseInt(event.getText());
				handleInput(value, event.getSource());
				
			}	else if(event.getCode() == KeyCode.BACK_SPACE) {
				handleInput(0, event.getSource());
			}	else {
				((TextField) event.getSource()).setText("");
			}
			event.consume();
		}
	}

	private void handleInput(int i, Object source) {
		// TODO Auto-generated method stub
		listener.onSudokuInput(((SudokuTextField) source).getX(), ((SudokuTextField) source).getY(), i);
	}

	@Override
	public void setListener(EventListener listener) {
		// TODO Auto-generated method stub
		this.listener = listener;
	}

	@Override
	public void updateSquare(int x, int y, int input) {
		// TODO Auto-generated method stub
		SudokuTextField tile = textFieldCoordinate.get(new Coordinate(x, y));
		String value = Integer.toString(input);
		
		if(value.equals("0")) value = "";
		tile.textProperty().setValue(value);
		
	}

	@Override
	public void updateBoard(SudokuGame game) {
		// TODO Auto-generated method stub
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				TextField tile = textFieldCoordinate.get(new Coordinate(i, j));
				
				String value = Integer.toString(game.getCopyOfBoardState()[i][j]);
				if(value.equals("0")) value = "";
				
				tile.setText(value);
				if(game.getGamestate() == GameState.NEW) {
					if(value.equals("")) {
						tile.setStyle("-fx-opacity: 1;");
						tile.setDisable(false);
					}	else {
						tile.setStyle("-fx-opacity: 0.8;");
						tile.setDisable(true);
					}
				}
			}
		}
	}

	@Override
	public void showDialog(String message) {
		// TODO Auto-generated method stub
		Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.OK);
		dialog.showAndWait();
		
		if(dialog.getResult() == ButtonType.OK) listener.onDialogClick();
	}

	@Override
	public void showError(String error) {
		// TODO Auto-generated method stub
		Alert dialog = new Alert(Alert.AlertType.ERROR, error, ButtonType.OK);
		dialog.showAndWait();
	}

}
