package candyCrushRivals;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
	private Candy[][] candyBoard;
	private int column = 10;
	private int row = 10;
	
	
	
/****************************************CREATES THE BOARD****************************************/
	
	
	
	// Fills a 10 X 10 board with candy
	// Ensures that no candy color is repeated three times
	public Board() {
		candyBoard = new Candy[row][column];
		// Fills the board with candy from the top left to the bottom right
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				// Checks the color of two candies to the left of the current candy and two candies to the top of the current candy
				// If both pairs are the same color, ensure that new candy is not one of the two colors
				if (i > 1 && candyBoard[i - 2][j].getColor().equals(candyBoard[i - 1][j].getColor()) &&
						j > 1 && candyBoard[i][j - 2].getColor().equals(candyBoard[i][j - 1].getColor())) {
					String notColor1 = candyBoard[i - 2][j].getColor();
					String notColor2 = candyBoard[i][j - 2].getColor();
					candyBoard[i][j] = new Candy();
					candyBoard[i][j].setNotColor(notColor1, notColor2);
				}
				// Checks the color of two candies to the top of the current candy
				// If two candies are the same color, ensure that the new candy is not that color
				else if (i > 1 && candyBoard[i - 2][j].getColor().equals(candyBoard[i - 1][j].getColor())) {
					String notColor = candyBoard[i - 2][j].getColor();
					candyBoard[i][j] = new Candy();
					candyBoard[i][j].setNotColor(notColor);
				}
				// Checks the color of two candies to the left of the current candy
				// If two candies are the same color, ensure that the new candy is not that color
				else if (j > 1 && candyBoard[i][j - 2].getColor().equals(candyBoard[i][j - 1].getColor())) {
					String notColor = candyBoard[i][j - 2].getColor();
					candyBoard[i][j] = new Candy();
					candyBoard[i][j].setNotColor(notColor);
				}
				// If the previous two candies from the left and top are not the same color,
				// simply create any random-colored candy
				else {
					candyBoard[i][j] = new Candy();
				}
			}
		}
	}
	
	// Fills a user-defined board with candy
	// Ensure that no candy color is repeated three times
	public Board(int row, int column) {
		candyBoard = new Candy [row][column];
		this.row = row;
		this.column = column;
		
		// Fills the board with candy from the top left to the bottom right
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				// Checks the color of two candies to the left of the current candy and two candies to the top of the current candy
				// If both pairs are the same color, ensure that new candy is not one of the two colors
				if (i > 1 && candyBoard[i - 2][j].getColor().equals(candyBoard[i - 1][j].getColor()) &&
						j > 1 && candyBoard[i][j - 2].getColor().equals(candyBoard[i][j - 1].getColor())) {
					String notColor1 = candyBoard[i - 2][j].getColor();
					String notColor2 = candyBoard[i][j - 2].getColor();
					candyBoard[i][j] = new Candy();
					candyBoard[i][j].setNotColor(notColor1, notColor2);
				}
				// Checks the color of two candies to the top of the current candy
				// If two candies are the same color, ensure that the new candy is not that color
				else if (i > 1 && candyBoard[i - 2][j].getColor().equals(candyBoard[i - 1][j].getColor())) {
					String notColor = candyBoard[i - 2][j].getColor();
					candyBoard[i][j] = new Candy();
					candyBoard[i][j].setNotColor(notColor);
				}
				// Checks the color of two candies to the left of the current candy
				// If two candies are the same color, ensure that the new candy is not that color
				else if (j > 1 && candyBoard[i][j - 2].getColor().equals(candyBoard[i][j - 1].getColor())) {
					String notColor = candyBoard[i][j - 2].getColor();
					candyBoard[i][j] = new Candy();
					candyBoard[i][j].setNotColor(notColor);
				}
				// If the previous two candies from the left and top are not the same color,
				// simply create any random-colored candy
				else {
					candyBoard[i][j] = new Candy();
				}
			}
		}
	}
	
	
	
/****************************************USED FOR DEBUGGING PURPOSES****************************************/
	
	
	
	// Used for testing or debugging
	// Allows a user to set the type of a particular candy in the board
	public void setType(int r, int c, String type) {
		candyBoard[r - 1][c - 1].setType(type);
	}
	
	// Used for testing or debugging
	// Allows a user to set the color of a particular candy in the board
	public void setColor(int r, int c, String color) {
		candyBoard[r - 1][c - 1].setColor(color);
	}
	
	
	
/****************************************CHECK IF SWAP IS VALID****************************************/
	
	
	
	// Checks whether the user inputs valid coordinates on the board that are one length apart
	private boolean isValidSwap(int[] coordinates) {
		int startRow = coordinates[0];
		int startColumn = coordinates[1];
		int endRow = coordinates[2];
		int endColumn = coordinates[3];
			
		// Checks whether the starting row, starting column, ending row, and ending column are valid coordinates on the board
		if (startRow < 0 || startRow > row - 1) {
			return false;
		}
		if (startColumn < 0 || startColumn > column - 1) {
			return false;
		}
		if (endRow < 0 || endRow > row - 1) {
			return false;
		}
		if (endColumn < 0 || endColumn > column - 1) {
			return false;
		}
			
		// Checks whether the starting coordinates and the ending coordinates are only one length apart
		if (Math.abs(startRow - endRow) == 1 && startColumn - endColumn == 0) {
			return true;
		}
		if (Math.abs(startColumn - endColumn) == 1 && startRow - endRow == 0) {
			return true;
		}
			
		// If the given rows and columns are valid coordinates on the board, but not one length apart, return false
		return false;
	}
	
		
	// Checks whether the user seeks to swap two special candies
	public boolean isSwapCombinationCandy(int[] coordinates) {
		int startRow = coordinates[0];
		int startColumn = coordinates[1];
		int endRow = coordinates[2];
		int endColumn = coordinates[3];
			
		if (!candyBoard[startRow][startColumn].getType().equals("regular") && !candyBoard[endRow][endColumn].getType().equals("regular")) {
			return true;
		}
			
		return false;
	}
		
	// Checks whether the user seeks to swap with a colored candy
	public boolean isSwapColorCandy(int[] coordinates) {
		int startRow = coordinates[0];
		int startColumn = coordinates[1];
		int endRow = coordinates[2];
		int endColumn = coordinates[3];
		
		if (candyBoard[startRow][startColumn].getType().equals("Color") || candyBoard[endRow][endColumn].getType().equals("Color")) {
			return true;
		}
		
		return false;
	}
	
	
	
/****************************************CHECKS IF A MOVE RESULTS IN A CRUSHABLE SET****************************************/
	
	
	
	// Checks if the user-provided coordinates result in a move that produces a crushable set of candy
	public boolean isCrushable(int[] coordinates) {
		if (isTopCrushable(coordinates) || isBottomCrushable(coordinates) ||
				isRightCrushable(coordinates) || isLeftCrushable(coordinates)) {
			return true;
		}
		else {
			return false;
		}		
	}
	
	// Checks if the user-provided coordinates result in a move that produces a crushable set of candy
	// When the user moves up
	public boolean isTopCrushable(int[] coordinates) {
		int startRow = coordinates[0];
		int startColumn = coordinates[1];
		int endRow = coordinates[2];
		int endColumn = coordinates[3];
		String startCandyColor = candyBoard[startRow][startColumn].getColor();
		String endCandyColor = candyBoard[endRow][endColumn].getColor();
		
		if (startRow - endRow == 1) {
			// Check top of start-candy
			if (endRow > 1 && candyBoard[endRow - 1][endColumn].getColor().equals(startCandyColor) &&
					candyBoard[endRow - 2][endColumn].getColor().equals(startCandyColor)) {
				return true;
			}
			// Check right of start-candy
			else if (candyBoard[endRow].length - endColumn > 2 && candyBoard[endRow][endColumn + 1].getColor().equals(startCandyColor) &&
					candyBoard[endRow][endColumn + 2].getColor().equals(startCandyColor)) {
				return true;
			}
			// Check middle of start-candy
			else if (candyBoard[endRow].length - endColumn > 1 && endColumn - 1 >= 0 &&
					candyBoard[endRow][endColumn + 1].getColor().equals(startCandyColor) &&
					candyBoard[endRow][endColumn - 1].getColor().equals(startCandyColor)) {
				return true;
			}
			// Check left of start-candy
			else if (endColumn - 2 >= 0 && candyBoard[endRow][endColumn - 1].getColor().equals(startCandyColor) &&
					candyBoard[endRow][endColumn - 2].getColor().equals(startCandyColor)) {
				return true;
			}
			// Check bottom of end-candy
			else if (candyBoard.length - startRow > 2 && candyBoard[startRow + 1][startColumn].getColor().equals(endCandyColor) &&
					candyBoard[startRow + 2][startColumn].getColor().equals(endCandyColor)) {
				return true;
			}
			// Check right of end-candy
			else if (candyBoard[startRow].length - startColumn > 2 && candyBoard[startRow][startColumn + 1].getColor().equals(endCandyColor) &&
					candyBoard[startRow][startColumn + 2].getColor().equals(endCandyColor)) {
				return true;
			}
			// Check middle of end-candy
			else if (candyBoard[startRow].length - startColumn > 1 && startColumn - 1 >= 0 &&
					candyBoard[startRow][startColumn + 1].getColor().equals(endCandyColor) &&
					candyBoard[startRow][startColumn - 1].getColor().equals(endCandyColor)) {
				return true;
			}
			// Check left of end-candy
			else if (startColumn - 2 >= 0 && candyBoard[startRow][startColumn - 1].getColor().equals(endCandyColor) &&
					candyBoard[startRow][startColumn - 2].getColor().equals(endCandyColor)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	// Checks if the user-provided coordinates result in a move that produces a crushable set of candy
	// When the user moves down
	public boolean isBottomCrushable(int[] coordinates) {
		int startRow = coordinates[0];
		int startColumn = coordinates[1];
		int endRow = coordinates[2];
		int endColumn = coordinates[3];
		String startCandyColor = candyBoard[startRow][startColumn].getColor();
		String endCandyColor = candyBoard[endRow][endColumn].getColor();
		
		if (endRow - startRow == 1) {
			// Check top of end-candy
			if (startRow > 1 && candyBoard[startRow - 1][startColumn].getColor().equals(endCandyColor) &&
					candyBoard[startRow - 2][startColumn].getColor().equals(endCandyColor)) {
				return true;
			}
			// Check right of end-candy
			else if (candyBoard[startRow].length - startColumn > 2 && candyBoard[startRow][startColumn + 1].getColor().equals(endCandyColor) &&
					candyBoard[startRow][startColumn + 2].getColor().equals(endCandyColor)) {
				return true;
			}
			// Check middle of end-candy
			else if (candyBoard[startRow].length - startColumn > 1 && startColumn - 1 >= 0 &&
					candyBoard[startRow][startColumn + 1].getColor().equals(endCandyColor) &&
					candyBoard[startRow][startColumn - 1].getColor().equals(endCandyColor)) {
				return true;
			}
			// Check left of end-candy
			else if (startColumn - 2 >= 0 && candyBoard[startRow][startColumn - 1].getColor().equals(endCandyColor) &&
					candyBoard[startRow][startColumn - 2].getColor().equals(endCandyColor)) {
				return true;
			}
			// Check bottom of start-candy
			else if (candyBoard.length - endRow > 2 && candyBoard[endRow + 1][endColumn].getColor().equals(startCandyColor) &&
					candyBoard[endRow + 2][endColumn].getColor().equals(startCandyColor)) {
				return true;
			}
			// Check right of start-candy
			else if (candyBoard[endRow].length - endColumn > 2 && candyBoard[endRow][endColumn + 1].getColor().equals(startCandyColor) &&
					candyBoard[endRow][endColumn + 2].getColor().equals(startCandyColor)) {
				return true;
			}
			// Check middle of start-candy
			else if (candyBoard[endRow].length - endColumn > 1 && endColumn - 1 >= 0 &&
					candyBoard[endRow][endColumn + 1].getColor().equals(startCandyColor) &&
					candyBoard[endRow][endColumn - 1].getColor().equals(startCandyColor)) {
				return true;
			}
			// Check left of start-candy
			else if (endColumn - 2 >= 0 && candyBoard[endRow][endColumn - 1].getColor().equals(startCandyColor) &&
					candyBoard[endRow][endColumn - 2].getColor().equals(startCandyColor)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	// Checks if the user-provided coordinates result in a move that produces a crushable set of candy
	// When the user moves right
	public boolean isRightCrushable(int[] coordinates) {
		int startRow = coordinates[0];
		int startColumn = coordinates[1];
		int endRow = coordinates[2];
		int endColumn = coordinates[3];
		String startCandyColor = candyBoard[startRow][startColumn].getColor();
		String endCandyColor = candyBoard[endRow][endColumn].getColor();
		
		if (endColumn - startColumn == 1) {
			// Check top of start-candy
			if (endRow > 1 && candyBoard[endRow - 1][endColumn].getColor().equals(startCandyColor) &&
					candyBoard[endRow - 2][endColumn].getColor().equals(startCandyColor)) {
				return true;
			}
			// Check right of start-candy
			else if (candyBoard[endRow].length - endColumn > 2 && candyBoard[endRow][endColumn + 1].getColor().equals(startCandyColor) &&
					candyBoard[endRow][endColumn + 2].getColor().equals(startCandyColor)) {
				return true;
			}
			// Check middle of start-candy
			else if (candyBoard.length - endRow > 1 && endRow - 1 >= 0 &&
					candyBoard[endRow + 1][endColumn].getColor().equals(startCandyColor) &&
					candyBoard[endRow - 1][endColumn].getColor().equals(startCandyColor)) {
				return true;
			}
			// Check left of end-candy
			else if (startColumn - 2 >= 0 && candyBoard[startRow][startColumn - 1].getColor().equals(endCandyColor) &&
					candyBoard[startRow][startColumn - 2].getColor().equals(endCandyColor)) {
				return true;
			}
			// Check bottom of start-candy
			else if (candyBoard.length - endRow > 2 && candyBoard[endRow + 1][endColumn].getColor().equals(startCandyColor) &&
					candyBoard[endRow + 2][endColumn].getColor().equals(startCandyColor)) {
				return true;
			}
			// Check top of end-candy
			else if (startRow > 1 && candyBoard[startRow - 1][startColumn].getColor().equals(endCandyColor) &&
					candyBoard[startRow - 2][startColumn].getColor().equals(endCandyColor)) {
				return true;
			}
			// Check middle of end-candy
			else if (candyBoard.length - startRow > 1 && startRow - 1 >= 0 &&
					candyBoard[startRow + 1][startColumn].getColor().equals(endCandyColor) &&
					candyBoard[startRow - 1][startColumn].getColor().equals(endCandyColor)) {
				return true;
			}
			// Check bottom of end-candy
			else if (candyBoard.length - startRow > 2 && candyBoard[startRow + 1][startColumn].getColor().equals(endCandyColor) &&
					candyBoard[startRow + 2][startColumn].getColor().equals(endCandyColor)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	// Checks if the user-provided coordinates result in a move that produces a crushable set of candy
	// When the user moves left
	public boolean isLeftCrushable(int[] coordinates) {
		int startRow = coordinates[0];
		int startColumn = coordinates[1];
		int endRow = coordinates[2];
		int endColumn = coordinates[3];
		String startCandyColor = candyBoard[startRow][startColumn].getColor();
		String endCandyColor = candyBoard[endRow][endColumn].getColor();
		
		if (startColumn - endColumn == 1) {
			// Check top of start-candy
			if (endRow > 1 && candyBoard[endRow - 1][endColumn].getColor().equals(startCandyColor) &&
					candyBoard[endRow - 2][endColumn].getColor().equals(startCandyColor)) {
				return true;
			}
			// Check right of end-candy
			else if (candyBoard[startRow].length - startColumn > 2 && candyBoard[startRow][startColumn + 1].getColor().equals(endCandyColor) &&
					candyBoard[startRow][startColumn + 2].getColor().equals(endCandyColor)) {
				return true;
			}
			// Check middle of start-candy
			else if (candyBoard.length - endRow > 1 && endRow - 1 >= 0 &&
					candyBoard[endRow + 1][endColumn].getColor().equals(startCandyColor) &&
					candyBoard[endRow - 1][endColumn].getColor().equals(startCandyColor)) {
				return true;
			}
			// Check left of start-candy
			else if (endColumn - 2 >= 0 && candyBoard[endRow][endColumn - 1].getColor().equals(startCandyColor) &&
					candyBoard[endRow][endColumn - 2].getColor().equals(startCandyColor)) {
				return true;
			}
			// Check bottom of start-candy
			else if (candyBoard.length - endRow > 2 && candyBoard[endRow + 1][endColumn].getColor().equals(startCandyColor) &&
					candyBoard[endRow + 2][endColumn].getColor().equals(startCandyColor)) {
				return true;
			}
			// Check top of end-candy
			else if (startRow > 1 && candyBoard[startRow - 1][startColumn].getColor().equals(endCandyColor) &&
					candyBoard[startRow - 2][startColumn].getColor().equals(endCandyColor)) {
				return true;
			}
			// Check middle of end-candy
			else if (candyBoard.length - startRow > 1 && startRow - 1 >= 0 &&
					candyBoard[startRow + 1][startColumn].getColor().equals(endCandyColor) &&
					candyBoard[startRow - 1][startColumn].getColor().equals(endCandyColor)) {
				return true;
			}
			// Check bottom of end-candy
			else if (candyBoard.length - startRow > 2 && candyBoard[startRow + 1][startColumn].getColor().equals(endCandyColor) &&
					candyBoard[startRow + 2][startColumn].getColor().equals(endCandyColor)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	
	
/****************************************SWAP CANDY****************************************/	
	
	
	
	// Swaps the position of two regular candies if the move is valid and results in a crushed set of candy
	public boolean swapRegularCandy(int[] coordinates) {
		int startRow = coordinates[0];
		int startColumn = coordinates[1];
		int endRow = coordinates[2];
		int endColumn = coordinates[3];
		
		// Swaps the two candies
		if (isValidSwap(coordinates) && isCrushable(coordinates)) {
			String startColor = candyBoard[startRow][startColumn].getColor();
			String endColor = candyBoard[endRow][endColumn].getColor();
			String startType = candyBoard[startRow][startColumn].getType();
			String endType = candyBoard[endRow][endColumn].getType();
			candyBoard[endRow][endColumn].setColor(startColor);
			candyBoard[startRow][startColumn].setColor(endColor);
			candyBoard[endRow][endColumn].setType(startType);
			candyBoard[startRow][startColumn].setType(endType);
			return true;
		}
		else {
			return false;
		}
	}
	
	// Swaps a combination if the move is valid and the two candies are special
	public boolean swapCombinationCandy(int[] coordinates) {
		if (isValidSwap(coordinates) && isSwapCombinationCandy(coordinates)) {
			crushCombinationCandy(coordinates);
			return true;
		}
		else {
			return false;
		}
	}
	
	// Swaps a colored candy and regular candy if the move is valid and one candy is colored
	public boolean swapColorCandy(int[] coordinates) {
		if (isValidSwap(coordinates) && isSwapColorCandy(coordinates)) {
			crushColorCandy(coordinates);
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
/****************************************CRUSH CANDY****************************************/
	
	
	
	// Crushes colored candy
	// If colored candy is used, will crush all candies of the paired candy's color
	public void crushColorCandy(int[] coordinates) {
		int startRow = coordinates[0];
		int startColumn = coordinates[1];
		int endRow = coordinates[2];
		int endColumn = coordinates[3];
		
		// If the starting candy's type is Color, crush all candies of the paired candy's color
		// Searches the entire board for paired candy's color and crushes it
		if (candyBoard[startRow][startColumn].getType().equals("Color")) {
			String color = candyBoard[endRow][endColumn].getColor();
			candyBoard[startRow][startColumn].setColor("*");
			candyBoard[startRow][startColumn].setType("regular");
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					if (candyBoard[i][j].getColor().equals(color)) {
						candyBoard[i][j].setColor("*");
					}
				}
			}
		}
		// If the ending candy's type is Color, crush all candies of the paired candy's color
		else if (candyBoard[endRow][endColumn].getType().equals("Color")) {
			String color = candyBoard[startRow][startColumn].getColor();
			candyBoard[endRow][endColumn].setColor("*");
			candyBoard[endRow][endColumn].setType("regular");

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					if (candyBoard[i][j].getColor().equals(color)) {
						candyBoard[i][j].setColor("*");
					}
				}
			}
		}
	}
	
	// Depending on the type of combination, crushes candy in a particular pattern
	public void crushCombinationCandy(int[] coordinates) {
		int startRow = coordinates[0];
		int startColumn = coordinates[1];
		int endRow = coordinates[2];
		int endColumn = coordinates[3];
		String startType = candyBoard[startRow][startColumn].getType();
		String endType = candyBoard[endRow][endColumn].getType();
		
		if (!startType.equals("regular") && !endType.equals("regular")) {
			if (startType.endsWith("Striped")) {
				crushStartWithStriped(coordinates);
			}
			else if (startType.equals("Wrapped")) {
				crushStartWithWrapped(coordinates);
			}
			else if (startType.equals("Color")) {
				crushStartWithColor(coordinates);
			}
		}
	}
	
	// Crushes a combination in which the first candy is striped
	public void crushStartWithStriped(int[] coordinates) {
		int startRow = coordinates[0];
		int startColumn = coordinates[1];
		int endRow = coordinates[2];
		int endColumn = coordinates[3];
		String endType = candyBoard[endRow][endColumn].getType();
		String startColor = candyBoard[startRow][startColumn].getColor();
		
		// Striped and striped combination
		// Crushes all candy in the vertical and horizontal directions at the ending coordinates with a radius of one
		if (endType.endsWith("Striped")) {
			candyBoard[startRow][startColumn].setType("regular");
			candyBoard[endRow][endColumn].setType("regular");
			// Crushes candy in the horizontal direction
			for (int i = 0; i < row; i++) {
				candyBoard[i][endColumn].setColor("*");
			}
			// Crushes candy in the vertical direction
			for (int i = 0; i < column; i++) {
				candyBoard[endRow][i].setColor("*");
			}
		}
		// Striped and wrapped combination
		// Crushes all in the vertical and horizontal directions at the ending coordinates with a radius of three
		else if (endType.equals("Wrapped")) {
			candyBoard[startRow][startColumn].setType("regular");
			candyBoard[endRow][endColumn].setType("regular");
			// Crushes candy in the horizontal direction
			for (int i = 0; i < row; i++) {
				candyBoard[i][endColumn].setColor("*");
			}
			// Crushes candy in the vertical direction
			for (int i = 0; i < column; i++) {
				candyBoard[endRow][i].setColor("*");
			}
			// The following if statements check if the radius of the pattern can be increased without being out of the bounds of the board
			// If not, do not increase the radius of the pattern in that particular direction
			if (endColumn > 0) {
				for (int i = 0; i < row; i++) {
					candyBoard[i][endColumn - 1].setColor("*");
				}
			}
			if (endColumn + 1 < column) {
				for (int i = 0; i < row; i++) {
					candyBoard[i][endColumn + 1].setColor("*");
				}
			}
			if (endRow > 0) {
				for (int i = 0; i < column; i++) {
					candyBoard[endRow - 1][i].setColor("*");
				}
			}
			if (endRow + 1 < row) {
				for (int i = 0; i < column; i++) {
					candyBoard[endRow + 1][i].setColor("*");
				}
			}
		}
		// Striped and colored combination
		// Converts all candies of the paired color into a striped candy
		else if (endType.equals("Color")) {
			candyBoard[startRow][startColumn].setType("regular");
			candyBoard[endRow][endColumn].setType("regular");
			candyBoard[endRow][endColumn].setColor("*");
			// Checks the board for all candies of the paired color
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					// If the color is found, convert to a striped type
					if (candyBoard[i][j].getColor().equals(startColor)) {
						candyBoard[i][j].setColor("*");
						
						// Uses a random number generator to determine whether to convert it to 
						// a vertical or horizontal striped candy
						int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
						
						if (randomNum == 0) {
							candyBoard[i][j].setType("HStriped");
						}
						else {
							candyBoard[i][j].setType("VStriped");
						}
					}
				}
			}
		}
	}
	
	// Crushes a combination in which the first candy is wrapped
	public void crushStartWithWrapped(int[] coordinates) {
		int startRow = coordinates[0];
		int startColumn = coordinates[1];
		int endRow = coordinates[2];
		int endColumn = coordinates[3];
		String endType = candyBoard[endRow][endColumn].getType();
		String startColor = candyBoard[startRow][startColumn].getColor();
		
		// Wrapped and striped combination
		// Crushes all in the vertical and horizontal directions at the ending coordinates with a radius of three
		if (endType.endsWith("Striped")) {
			candyBoard[startRow][startColumn].setType("regular");
			candyBoard[endRow][endColumn].setType("regular");
			// Crushes all candy in the vertical and horizontal directions at the ending coordinates with a radius of one
			for (int i = 0; i < row; i++) {
				candyBoard[i][endColumn].setColor("*");
			}
			for (int i = 0; i < column; i++) {
				candyBoard[endRow][i].setColor("*");
			}
			// The following if statements check if the radius of the pattern can be increased without being out of the bounds of the board
			// If not, do not increase the radius of the pattern in that particular direction
			if (endColumn > 0) {
				for (int i = 0; i < row; i++) {
					candyBoard[i][endColumn - 1].setColor("*");
				}
			}
			if (endColumn + 1 < column) {
				for (int i = 0; i < row; i++) {
					candyBoard[i][endColumn + 1].setColor("*");
				}
			}
			if (endRow > 0) {
				for (int i = 0; i < column; i++) {
					candyBoard[endRow - 1][i].setColor("*");
				}
			}
			if (endRow + 1 < row) {
				for (int i = 0; i < column; i++) {
					candyBoard[endRow + 1][i].setColor("*");
				}
			}
		}
		// Wrapped and wrapped combination
		// Crushes all candy twice in a 5 X 5 dimension around the pair of wrapped candies
		else if (endType.equals("Wrapped")) {
			candyBoard[startRow][startColumn].setType("regular");
			candyBoard[endRow][endColumn].setType("regular");
			
			// Store the amount the wrapped candy will shift down
			int shift = popDoubleWrapped(coordinates);
			
			// Drop candy and determine if any conversions are possible
			dropCandy();
			convertCandy();
			
			// Create new coordinates that account for the shift
			int[] tempCoord = new int[4];
			
			tempCoord[0] = coordinates[0] + shift;
			tempCoord[1] = coordinates[1];
			tempCoord[2] = coordinates[2] + shift;
			tempCoord[3] = coordinates[3];
			
			// Pop the wrapped candy at the new coordinates
			popDoubleWrapped(tempCoord);			
		}
		// Wrapped and colored combination
		// Converts all candies of the paired color into a wrapped candy
		else if (endType.equals("Color")) {
			candyBoard[startRow][startColumn].setType("regular");
			candyBoard[endRow][endColumn].setType("regular");
			candyBoard[endRow][endColumn].setColor("*");
			// Checks the board for all candies of the paired color
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					// If the color is found, convert to a wrapped type 
					if (candyBoard[i][j].getColor().equals(startColor)) {
						candyBoard[i][j].setColor("*");
						candyBoard[i][j].setType("Wrapped");
					}
				}
			}
		}
	}
	
	// Crushes a combination in which the first candy is colored
	public void crushStartWithColor(int[] coordinates) {
		int startRow = coordinates[0];
		int startColumn = coordinates[1];
		int endRow = coordinates[2];
		int endColumn = coordinates[3];
		String endType = candyBoard[endRow][endColumn].getType();
		String endColor = candyBoard[endRow][endColumn].getColor();
		
		// Colored and striped combination
		// Converts all candies of the paired color into a striped candy
		if (endType.endsWith("Striped")) {
			candyBoard[startRow][startColumn].setType("regular");
			candyBoard[startRow][startColumn].setColor("*");
			candyBoard[endRow][endColumn].setType("regular");
			candyBoard[endRow][endColumn].setColor("*");
			// Checks the board for all candies of the paired color
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					// If the color is found, convert to a striped type 
					if (candyBoard[i][j].getColor().equals(endColor)) {
						candyBoard[i][j].setColor("*");
						
						// Uses a random number generator to determine whether to convert it to 
						// a vertical or horizontal striped candy
						int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
						
						if (randomNum == 0) {
							candyBoard[i][j].setType("HStriped");
						}
						else {
							candyBoard[i][j].setType("VStriped");
						}
					}
				}
			}
		}
		// Colored and wrapped combination
		// Converts all candies of the paired color into a wrapped candy
		else if (endType.equals("Wrapped")) {
			candyBoard[startRow][startColumn].setType("regular");
			candyBoard[startRow][startColumn].setColor("*");
			candyBoard[endRow][endColumn].setType("regular");
			candyBoard[endRow][endColumn].setColor("*");
			// Checks the board for all candies of the paired color
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					// If the color is found, convert to a wrapped type 
					if (candyBoard[i][j].getColor().equals(endColor)) {
						candyBoard[i][j].setColor("*");
						candyBoard[i][j].setType("Wrapped");
					}
				}
			}
		}
		// Colored and colored combination
		// Erases the entire board
		else if (endType.equals("Color")) {
			candyBoard[startRow][startColumn].setType("regular");
			candyBoard[startRow][startColumn].setColor("*");
			candyBoard[endRow][endColumn].setType("regular");
			candyBoard[endRow][endColumn].setColor("*");
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					candyBoard[i][j].setColor("*");
				}
			}
		}
	}
	
	// Determines the actions that occur if a special candy is crushed
	public boolean crushSpecialCandy() {
		boolean crushed = false;
		
		// Traverse board
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (candyBoard[i][j].getColor().equals("*") && !candyBoard[i][j].getType().equals("regular")) {
					switch (candyBoard[i][j].getType()) {
					// If VStriped is crushed, crush all candy in the vertical coordinates of that candy
					case "VStriped":
						for (int k = 0; k < row; k++) {
							candyBoard[k][j].setColor("*");
						}
						crushed = true;
						break;
					// If HStriped is crushed, crush all candy in the horizontal coordinates of that candy
					case "HStriped":
						for (int k = 0; k < column; k++) {
							candyBoard[i][k].setColor("*");
						}
						crushed = true;
						break;
					// If Wrapped is crushed, crush all candy in the immediate proximity of that candy for the first time	
					case "Wrapped":
						popSingleWrapped(i, j, "First");
												
						crushed = true;
						break;
					// If Wrapped is crushed, crush all candy in the immediate proximity of that candy for the second time
					case "WrappedSecond":
						// After the first pop, check if there are any convertable candies
						convertCandy();
						
						popSingleWrapped(i, j, "Second");
						
						crushed = true;
						break;
					// If Color is crushed, crush all candy that has the paired candy's color
					case "Color":
						String color;
						
						if (!candyBoard[0][0].getColor().equals("*")) {
							color = candyBoard[0][0].getColor();
						}
						else {
							color = "red";
						}
						
						for (int k = 0; k < row; i++) {
							for (int l = 0; l < column; j++) {
								if (candyBoard[k][l].getColor().equals(color)) {
									candyBoard[k][l].setColor("*");
								}
							}
						}
						crushed = true;
						break;
					// Else, simply crush the candy
					default:
						candyBoard[i][j].setColor("*");
						candyBoard[i][j].setType("*");
						crushed = true;
						break;
					}
				}
			}
		}
		
		return crushed;
	}
	
	// Crushes a single-wrapped candy
	public void popSingleWrapped(int r, int c, String type) {
		candyBoard[r][c].setColor("*");
		// If it is the first pop, set the second wrapped candy
		if (type.equals("First")) {
			candyBoard[r][c].setType("WrappedSecond");
		}
		// If it is the second pop, set the candy to regular
		else if (type.equals("Second")) {
			candyBoard[r][c].setType("regular");
		}
		// Checks one length below
		if (r + 1 < row) {
			candyBoard[r + 1][c].setColor("*");
			// Checks one length to the right and one length below
			if (c + 1 < column) {
				candyBoard[r + 1][c + 1].setColor("*");
			}
			// Checks one length to the left and one length below
			if (c > 0) {
				candyBoard[r + 1][c - 1].setColor("*");
			}
		}
		// Checks one length above
		if (r > 0) {
			candyBoard[r - 1][c].setColor("*");
			// Checks one length to the right and one length above
			if (c + 1 < column) {
				candyBoard[r - 1][c + 1].setColor("*");
			}
			// Checks one length to the left and one length above
			if (c > 0) {
				candyBoard[r - 1][c - 1].setColor("*");
			}
		}
		// Checks one length to the right
		if (c + 1 < column) {
			candyBoard[r][c + 1].setColor("*");
		}
		// Checks one length to the left
		if (c > 0) {
			candyBoard[r][c - 1].setColor("*");
		}
	}
	
	// Crushes candy of a wrapped-wrapped combination
	public int popDoubleWrapped(int[] coordinates) {
		int shift = 0;
		
		int startRow = coordinates[0];
		int startColumn = coordinates[1];
		int endRow = coordinates[2];
		int endColumn = coordinates[3];
		
		int topRow = (startRow < endRow) ? startRow : endRow;
		int bottomRow = (startRow > endRow) ? startRow : endRow;
		int leftColumn = (startColumn < endColumn) ? startColumn : endColumn;
		int rightColumn = (startColumn > endColumn) ? startColumn : endColumn;
		
		candyBoard[startRow][startColumn].setColor("*");
		candyBoard[endRow][endColumn].setColor("*");
		candyBoard[startRow][startColumn].setType("regular");
		candyBoard[endRow][endColumn].setType("regular");
		
		// Ensures that the pattern will not cross outside the board
		
		// CHECKS BELOW
		// Checks one length below
		if (bottomRow + 1 < row) {
			candyBoard[bottomRow + 1][leftColumn].setColor("*");
			candyBoard[bottomRow + 1][rightColumn].setColor("*");
			shift = 1;
			// Checks two length below
			if (bottomRow + 2 < row) {
				candyBoard[bottomRow + 2][leftColumn].setColor("*");
				candyBoard[bottomRow + 2][rightColumn].setColor("*");
				shift = 2;
				// ALL CHECKS ARE FOR TWO LENGTHS BELOW
				// Checks one length to the right and two lengths below
				if (rightColumn + 1 < column) {
					candyBoard[bottomRow + 2][rightColumn + 1].setColor("*");
					// Checks two length to the right and two lengths below
					if (rightColumn + 2 < column) {
						candyBoard[bottomRow + 2][rightColumn + 2].setColor("*");
					}
				}
				// Checks one length to the left and two lengths below
				if (leftColumn > 0) {
					candyBoard[bottomRow + 2][leftColumn - 1].setColor("*");
					// Checks two lengths to the left and two lengths below
					if (leftColumn - 1 > 0) {
						candyBoard[bottomRow + 2][leftColumn - 2].setColor("*");
					}
				}
			}
			// ALL CHECKS ARE FOR ONE LENGTH BELOW
			// Checks one length to the right and one length below
			if (rightColumn + 1 < column) {
				candyBoard[bottomRow + 1][rightColumn + 1].setColor("*");
				// Checks two lengths to the right and one length below
				if (rightColumn + 2 < column) {
					candyBoard[bottomRow + 1][rightColumn + 2].setColor("*");
				}
			}
			// Checks one length to the left and one length below
			if (leftColumn > 0) {
				candyBoard[bottomRow + 1][leftColumn - 1].setColor("*");
				// Checks two lengths to the left and one length below
				if (leftColumn - 1 > 0) {
					candyBoard[bottomRow + 1][leftColumn - 2].setColor("*");
				}
			}
		}
		// CHECKS ABOVE
		// Checks one length above
		if (topRow > 0) {
			candyBoard[topRow - 1][leftColumn].setColor("*");
			candyBoard[topRow - 1][rightColumn].setColor("*");
			// Checks two lengths above
			if (topRow - 1 > 0) {
				candyBoard[topRow - 2][leftColumn].setColor("*");
				candyBoard[topRow - 2][rightColumn].setColor("*");
				// ALL CHECKS ARE FOR TWO LENGTHS ABOVE
				// Checks one length to the right and two lengths above
				if (rightColumn + 1 < column) {
					candyBoard[topRow - 2][rightColumn + 1].setColor("*");
					// Checks two lengths to the right and two lengths above
					if (rightColumn + 2 < column) {
						candyBoard[topRow - 2][rightColumn + 2].setColor("*");
					}
				}
				// Checks one length to the left and two lengths above
				if (leftColumn > 0) {
					candyBoard[topRow - 2][leftColumn - 1].setColor("*");
					// Checks two lengths to the left and two lengths above
					if (leftColumn - 1 > 0) {
						candyBoard[topRow - 2][leftColumn - 2].setColor("*");
					}
				}
			}
			// ALL CHECKS ARE FOR ONE LENGTH ABOVE
			// Checks one length to the right and one length above
			if (rightColumn + 1 < column) {
				candyBoard[topRow - 1][rightColumn + 1].setColor("*");
				// Checks two lengths to the right and one length above
				if (rightColumn + 2 < column) {
					candyBoard[topRow - 1][rightColumn + 2].setColor("*");
				}
			}
			// Checks one length to the left and one length above
			if (leftColumn > 0) {
				candyBoard[topRow - 1][leftColumn - 1].setColor("*");
				// Checks two lengths to the left and one length above
				if (leftColumn - 1 > 0) {
					candyBoard[topRow - 1][leftColumn - 2].setColor("*");
				}
			}
		}
		// Checks one length to the right
		if (rightColumn + 1 < column) {
			candyBoard[topRow][rightColumn + 1].setColor("*");
			candyBoard[bottomRow][rightColumn + 1].setColor("*");
			// Checks two lengths to the right
			if (rightColumn + 2 < column) {
				candyBoard[topRow][rightColumn + 2].setColor("*");
				candyBoard[bottomRow][rightColumn + 2].setColor("*");
			}
		}
		// Checks one length to the left
		if (leftColumn > 0) {
			candyBoard[topRow][leftColumn - 1].setColor("*");
			candyBoard[bottomRow][leftColumn - 1].setColor("*");
			// Checks two lengths to the left
			if (leftColumn - 1 > 0) {
				candyBoard[topRow][leftColumn - 2].setColor("*");
				candyBoard[bottomRow][leftColumn - 2].setColor("*");
			}
		}
		return shift;
	}
	
	
	
/****************************************CONVERT CANDY****************************************/
	
	
	// Traverses the board and identifies if there are sets that can be converted
	// Used after swapping candy
	public boolean convertCandy(int[] coordinates) {
		boolean checkWrapper, checkVertical, checkHorizontal;
		checkWrapper = convertWrapper();
		checkVertical = convertVertical();
		checkHorizontal = convertHorizontal(coordinates);
		
		return (checkWrapper || checkVertical || checkHorizontal);
	}
	
	// Traverses the board and identifies if there are sets that can be converted
	// Used after swapping candy
	public boolean convertCandy() {
		boolean checkWrapper, checkVertical, checkHorizontal;
		checkWrapper = convertWrapper();
		checkVertical = convertVertical();
		checkHorizontal = convertHorizontal();
			
		return (checkWrapper || checkVertical || checkHorizontal);
	}
	
	// Traverses the board and identifies if there are sets that can be converted to a wrapper
	public boolean convertWrapper() {
		boolean check = false;
		
		// Traverses the board
		for (int i = row - 1; i >= 0; i--) {
			for (int j = column - 1; j >= 0; j--) {
				String color = candyBoard[i][j].getColor();
				// If there exists a horizontal set of 5
				if (j > 3 && !candyBoard[i][j].getColor().equals("*") && candyBoard[i][j - 4].getColor().equals(candyBoard[i][j - 3].getColor()) &&
						candyBoard[i][j - 3].getColor().equals(candyBoard[i][j - 2].getColor()) &&
						candyBoard[i][j - 2].getColor().equals(candyBoard[i][j - 1].getColor()) &&
						candyBoard[i][j - 1].getColor().equals(candyBoard[i][j].getColor())) {
					// For each candy in the horizontal set, check if there is a vertical set
					for (int k = j - 4; k <= j; k++) {
						if (checkVertical(i, k, color)) {
							check = true;
						}
					}
					// If a vertical set is found, crush candy and convert to Color
					if (check) {
						for (int k = j - 4; k <= j; k++) {
							if (!candyBoard[i][k].getType().equals("Color")) {
								candyBoard[i][k].setColor("*");
							}
						}
					}
				}
				// If there exists a horizontal set of 4
				else if (j > 2 && !candyBoard[i][j].getColor().equals("*") && candyBoard[i][j - 3].getColor().equals(candyBoard[i][j - 2].getColor()) &&
						candyBoard[i][j - 2].getColor().equals(candyBoard[i][j - 1].getColor()) &&
						candyBoard[i][j - 1].getColor().equals(candyBoard[i][j].getColor())) {
					// For each candy in the horizontal set, check if there is a vertical set
					for (int k = j - 3; k <= j; k++) {
						if (checkVertical(i, k, color)) {
							check = true;
						}
					}
					// If a vertical set is found, crush candy and convert to wrapped
					if (check) {
						for (int k = j - 3; k <= j; k++) {
							if (!candyBoard[i][k].getType().equals("Wrapped")) {
								candyBoard[i][k].setColor("*");
							}
						}
					}
				}
				// If there exists a horizontal set of 3
				else if (j > 1 && !candyBoard[i][j].getColor().equals("*") && candyBoard[i][j - 2].getColor().equals(candyBoard[i][j - 1].getColor()) &&
						candyBoard[i][j - 1].getColor().equals(candyBoard[i][j].getColor())) {
					// For each candy in the horizontal set, check if there is a vertical set
					for (int k = j - 2; k <= j; k++) {
						if (checkVertical(i, k, color)) {
							check = true;
						}
					}
					// If a vertical set is found, crush candy and convert to wrapped
					if (check) {
						for (int k = j - 2; k <= j; k++) {
							if (!candyBoard[i][k].getType().equals("Wrapped")) {
								candyBoard[i][k].setColor("*");
							}
						}
					}
				}
			}
		}
		
		// Return whether a set was converted to a wrapped
		return check;
	}
	
	// Traverses the board and identifies if there are sets that can be converted to a Color or HStripe
	// If the set cannot be converted, simply crush the set
	public boolean convertVertical() {
		boolean check = false;
		// Traverses the board
		for (int i = row - 1; i >= 0; i--) {
			for (int j = column - 1; j >= 0; j--) {
				// If there exists a vertical set of 5
				if (i > 3 && !candyBoard[i][j].getColor().equals("*") && candyBoard[i - 4][j].getColor().equals(candyBoard[i - 3][j].getColor()) &&
						candyBoard[i - 3][j].getColor().equals(candyBoard[i - 2][j].getColor()) &&
						candyBoard[i - 2][j].getColor().equals(candyBoard[i - 1][j].getColor()) &&
						candyBoard[i - 1][j].getColor().equals(candyBoard[i][j].getColor())) {
					// Crush candy and convert to Color
					candyBoard[i][j].setColor("Color");
					candyBoard[i][j].setType("Color");
					candyBoard[i - 1][j].setColor("*");
					candyBoard[i - 2][j].setColor("*");
					candyBoard[i - 3][j].setColor("*");
					candyBoard[i - 4][j].setColor("*");
					check = true;
				}
				// If there exists a vertical set of 4
				else if (i > 2 && !candyBoard[i][j].getColor().equals("*") && candyBoard[i - 3][j].getColor().equals(candyBoard[i - 2][j].getColor()) &&
						candyBoard[i - 2][j].getColor().equals(candyBoard[i - 1][j].getColor()) &&
						candyBoard[i - 1][j].getColor().equals(candyBoard[i][j].getColor())) {
					// Crush candy and convert to HStriped
					candyBoard[i][j].setType("HStriped");
					candyBoard[i - 1][j].setColor("*");
					candyBoard[i - 2][j].setColor("*");
					candyBoard[i - 3][j].setColor("*");
					check = true;
				}
				// If there exists a vertical set of 3
				else if (i > 1 && !candyBoard[i][j].getColor().equals("*") && candyBoard[i - 2][j].getColor().equals(candyBoard[i - 1][j].getColor()) &&
						candyBoard[i - 1][j].getColor().equals(candyBoard[i][j].getColor())) {
					// Crush candy
					candyBoard[i][j].setColor("*");;
					candyBoard[i - 1][j].setColor("*");
					candyBoard[i - 2][j].setColor("*");
					check = true;
				}
			}
		}
		
		// Return whether a set was converted or crushed
		return check;
	}
	
	// Traverses the board and identifies if there are sets that can be converted to a Color or VStripe
	// If the set cannot be converted, simply crush the set
	public boolean convertHorizontal(int[] coordinates) {
		int startRow = coordinates[0];
		int startColumn = coordinates[1];
		int endRow = coordinates[2];
		boolean targetedConversion = false;
		boolean check = false;
		
		// Traverses the board
		for (int i = row - 1; i >= 0; i--) {
			for (int j = column - 1; j >= 0; j--) {
				// If there exists a horizontal set of 5
				if (j > 3 && !candyBoard[i][j].getColor().equals("*") && candyBoard[i][j - 4].getColor().equals(candyBoard[i][j - 3].getColor()) &&
						candyBoard[i][j - 3].getColor().equals(candyBoard[i][j - 2].getColor()) &&
						candyBoard[i][j - 2].getColor().equals(candyBoard[i][j - 1].getColor()) &&
						candyBoard[i][j - 1].getColor().equals(candyBoard[i][j].getColor())) {
					// Crush candy and convert to Color at the proper coordinates
					for (int k = j - 4; k <= j; k++) {
						if (k == startColumn && (startRow == i || endRow == i)) {
							candyBoard[i][k].setType("Color");
							candyBoard[i][k].setColor("Color");
							targetedConversion = true;
						}
						else {
							candyBoard[i][k].setColor("*");
						}
					}
					// If Color was converted without user input, crush candy and place Color at the very left of the set
					if (!targetedConversion) {
						candyBoard[i][j - 4].setColor("Color");
						candyBoard[i][j - 4].setType("Color");
					}
					j = j - 4;
					check = true;
				}
				// If there exists a horizontal set of 4
				else if (j > 2 && !candyBoard[i][j].getColor().equals("*") && candyBoard[i][j - 3].getColor().equals(candyBoard[i][j - 2].getColor()) &&
						candyBoard[i][j - 2].getColor().equals(candyBoard[i][j - 1].getColor()) &&
						candyBoard[i][j - 1].getColor().equals(candyBoard[i][j].getColor())) {
					String color = candyBoard[i][j].getColor();
					// Crush candy and convert to VStriped at the proper coordinates
					for (int k = j - 3; k <= j; k++) {
						if (k == startColumn && (startRow == i || endRow == i)) {
							candyBoard[i][k].setType("VStriped");
							targetedConversion = true;
						}
						else {
							candyBoard[i][k].setColor("*");
						}
					}
					// If VStriped was converted without user input, crush candy and place VStriped at the very left of the set
					if (!targetedConversion) {
						candyBoard[i][j - 3].setColor(color);
						candyBoard[i][j - 3].setType("VStriped");
					}
					j = j - 3;
					check = true;
				}
				// If there exists a horizontal set of 3
				else if (j > 1 && !candyBoard[i][j].getColor().equals("*") && candyBoard[i][j - 2].getColor().equals(candyBoard[i][j - 1].getColor()) &&
						candyBoard[i][j - 1].getColor().equals(candyBoard[i][j].getColor())) {
					// Crush candy
					candyBoard[i][j - 2].setColor("*");
					candyBoard[i][j - 1].setColor("*");
					candyBoard[i][j].setColor("*");
					j = j - 2;
					check = true;
				}
			}
		}
		
		// Return whether a set was converted or crushed
		return check;
	}
	
	// Traverses the board and identifies if there are sets that can be converted to a Color or VStripe
	// If the set cannot be converted, simply crush the set
	public boolean convertHorizontal() {
		boolean check = false;
			
		// Traverses the board
		for (int i = row - 1; i >= 0; i--) {
			for (int j = column - 1; j >= 0; j--) {
				// If there exists a horizontal set of 5
				if (j > 3 && !candyBoard[i][j].getColor().equals("*") && candyBoard[i][j - 4].getColor().equals(candyBoard[i][j - 3].getColor()) &&
						candyBoard[i][j - 3].getColor().equals(candyBoard[i][j - 2].getColor()) &&
						candyBoard[i][j - 2].getColor().equals(candyBoard[i][j - 1].getColor()) &&
						candyBoard[i][j - 1].getColor().equals(candyBoard[i][j].getColor())) {
					// Crush candy and convert to Color at the proper coordinates
					for (int k = j - 4; k <= j; k++) {
						candyBoard[i][k].setColor("*");
					}
					candyBoard[i][j - 4].setColor("Color");
					candyBoard[i][j - 4].setType("Color");
					j = j - 4;
					check = true;
				}
				// If there exists a horizontal set of 4
				else if (j > 2 && !candyBoard[i][j].getColor().equals("*") && candyBoard[i][j - 3].getColor().equals(candyBoard[i][j - 2].getColor()) &&
					candyBoard[i][j - 2].getColor().equals(candyBoard[i][j - 1].getColor()) &&
					candyBoard[i][j - 1].getColor().equals(candyBoard[i][j].getColor())) {
					String color = candyBoard[i][j].getColor();
					// Crush candy and convert to VStriped at the proper coordinates
					for (int k = j - 3; k <= j; k++) {
						candyBoard[i][k].setColor("*");
					}
					candyBoard[i][j - 3].setColor(color);
					candyBoard[i][j - 3].setType("VStriped");
					j = j - 3;
					check = true;
				}
				// If there exists a horizontal set of 3
				else if (j > 1 && !candyBoard[i][j].getColor().equals("*") && candyBoard[i][j - 2].getColor().equals(candyBoard[i][j - 1].getColor()) &&
						candyBoard[i][j - 1].getColor().equals(candyBoard[i][j].getColor())) {
					// Crush candy
					candyBoard[i][j - 2].setColor("*");
					candyBoard[i][j - 1].setColor("*");
					candyBoard[i][j].setColor("*");
					j = j - 2;
					check = true;
				}
			}
		}
			
		// Return whether a set was converted or crushed
		return check;
	}
	
	// Used in the convertWrapper method
	// If a horizontal set is found, check each candy in the set for a vertical set
	public boolean checkVertical(int i, int j, String color) {
		boolean check = false;
		int top = i;
		int bottom = i;
		
		// Check if there is candy of the same color above the horizontal set
		if (i > 0 && candyBoard[i - 1][j].getColor().equals(color)) {
			top = i - 1;
			if (i > 1 && candyBoard[i - 2][j].getColor().equals(color)) {
				top = i - 2;
			}
		}
		// Check if there is candy of the same color below the horizontal set
		if (column - i > 1 && candyBoard[i + 1][j].getColor().equals(color)) {
			bottom = i + 1;
			if (column - i > 2 && candyBoard[i + 2][j].getColor().equals(color)) {
				bottom = i + 2;
			}
		}
		
		// Check if a set is made between the top and bottom
		if (bottom - top > 1) {
			for (int k = top; k < bottom; k++) {
				candyBoard[k][j].setColor("*");
			}
			// If the set consists of 5 candies, convert to Color
			if (bottom - top > 3) {
				candyBoard[bottom][j].setColor("Color");
				candyBoard[bottom][j].setType("Color");
			}
			// Else convert to wrapped
			else {
				candyBoard[bottom][j].setColor(color);
				candyBoard[bottom][j].setType("Wrapped");
			}
			check = true;
		}
		
		// Return whether a vertical set was found and the candy converted
		return check;
	}
	
	
	
/****************************************DROP CRUSHED CANDY****************************************/
	
	
	
	// Eliminates the candy at that location and drops all candies on top of the location
	public void dropSingleCandy(int r, int c) {
		for (int i = r; i > 0; i--) {
			candyBoard[i][c].setColor(candyBoard[i - 1][c].getColor());
			candyBoard[i][c].setType(candyBoard[i - 1][c].getType());
		}
		candyBoard[0][c] = new Candy();
		Player.incrementScore();
	}
	
	// Traverses the board, and if crushed candy is found, drops the candy
	public boolean dropCandy() {
		printBoard();
		System.out.println();
		
		boolean isCrushed = false;
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (candyBoard[i][j].getColor().equals("*") && !candyBoard[i][j].getType().equals("WrappedSecond")) {
					dropSingleCandy(i, j);
					isCrushed = true;
				}
			}
		}
		
		printBoard();
		System.out.println();
		
		return isCrushed;
	}
	
	
	
/****************************************MOVE****************************************/

	
	
	// Swaps candy on the board and effects the result
	public boolean move(int[] coordinates) {
		// If player chooses to swap a combination of special candies or swaps a colored candy
		if (swapCombinationCandy(coordinates) || swapColorCandy(coordinates)) {
			// Check if any additional special candies were crushed
			if (crushSpecialCandy()) {
				dropCandy();
				crushSpecialCandy();
			}
			// Eliminate any crushed candy
			dropCandy();
			// Continually check whether there are any sets to be crushed
			while (convertCandy(coordinates)) {
				// If a set is found, determine if any special candies were crushed
				crushSpecialCandy();
				// Eliminate any crushed candy
				dropCandy();
			}
			return true;
		}
		// If player chooses to swap regular candy
		else if (swapRegularCandy(coordinates)) {
			// Continually check whether there are any sets to be crushed
		   while (convertCandy(coordinates)) {
				// If a set is found, determine if any special candies were crushed
			   crushSpecialCandy();
				// Eliminate any crushed candy
			   dropCandy();
		   }
		   return true;
		}
		// If a player inputs an invalid move
		else {
			// Print
			System.out.println("Invalid Move!");
			return false;
		}		   	   	   	   
	}
		
	
	
/****************************************ENSURES THAT THERE EXIST A VALID MOVE ON THE BOARD****************************************/
	
	

	// Checks whether there exists a valid swap on the board
	// The absence of a valid swap is one of the conditions for resetting the board
	public boolean hasValidSwap() {
		int[] coordinates = new int[4];
		
		// Check if there exists a valid right or left swap on the board
		for (int i = 0; i < row; i++) {
			for (int j = 1; j < column; j++) {
				// Check if there exists a valid right swap on the board
				coordinates[0] = i;
				coordinates[1] = j - 1;
				coordinates[2] = i;
				coordinates[3] = j;
				if (isCrushable(coordinates)) {
					return true;
				}
				// Check if there exists a valid left swap on the board
				coordinates[1] = j;
				coordinates[3] = j - 1;
				if (isCrushable(coordinates)) {
					return true;
				}
			}
		}
		
		// Check if there exists a valid top or bottom swap on the board
		for (int i = 1; i < row; i++) {
			for (int j = 0; j < column; j++) {
				// Check if there exists a valid top on the board
				coordinates[0] = i - 1;
				coordinates[1] = j;
				coordinates[2] = i;
				coordinates[3] = j;
				if (isCrushable(coordinates)) {
					return true;
				}
				// Check if there exists a valid bottom swap on the board
				coordinates[0] = i;
				coordinates[2] = i - 1;
				if (isCrushable(coordinates)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	// Checks whether the board has a combination of special candy
	// The absence of a combination is condition for resetting the board
	public boolean hasCombination() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				// Checks if there is at least one colored candy on the board
				if (candyBoard[i][j].getType().equals("Color")) {
					return true;
				}
				// If not, checks whether there are two special candies next to each other
				else if (!candyBoard[i][j].getType().equals("regular")) {
					if (j + 1 < column && !candyBoard[i][j + 1].getType().equals("regular")) {
						return true;
					}
					else if (i + 1 < row && !candyBoard[i + 1][j].getType().equals("regular")) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	// Checks whether there exists a valid move for the player to make
	// If not, board must be reset
	public boolean isValidBoard() {
		return (hasValidSwap() || hasCombination());
	}
	


/****************************************PRINTS THE BOARD****************************************/
	
	
	
	// Prints the color of each candy on the board
	public void printColor() {
		System.out.print("\t");
		for (int i = 1; i <= candyBoard[0].length; i++) {
			System.out.print(i + "\t\t");
		}
		System.out.println();
		for (int i = 0; i < candyBoard.length; i++) {
			System.out.print((i + 1) + "\t");
			for (int j = 0; j < candyBoard[i].length; j++) {
				System.out.print(candyBoard[i][j].getColor() + "\t\t");
			}
			System.out.println();
		}
	}
	
	// Prints the type of each candy on the board
	public void printType() {
		System.out.print("\t");
		for (int i = 1; i <= candyBoard[0].length; i++) {
			System.out.print(i + "\t\t");
		}
		System.out.println();
		for (int i = 0; i < candyBoard.length; i++) {
			System.out.print((i + 1) + "\t");
			for (int j = 0; j < candyBoard[i].length; j++) {
				System.out.print(candyBoard[i][j].getType() + "\t\t");
			}
			System.out.println();
		}
	}
	
	// Prints the color and type (abbreviated) of each candy on the board
	public void printBoard() {
		System.out.print("\t");
		for (int i = 1; i <= candyBoard[0].length; i++) {
			System.out.print(i + "\t");
		}
		System.out.println();
		for (int i = 0; i < candyBoard.length; i++) {
			System.out.print((i + 1) + "\t");
			for (int j = 0; j < candyBoard[i].length; j++) {
				System.out.print(candyBoard[i][j].getColor().charAt(0) + " " + candyBoard[i][j].getType().charAt(0) + "\t");
			}
			System.out.println();
		}
	}
}
