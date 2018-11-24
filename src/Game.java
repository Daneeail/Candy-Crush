package candyCrushRivals;

import java.util.Scanner;

public class Game {
	int turns;
	
	public Game() {
		turns = 3;
	}
	
	public Game(int turns) {
		this.turns = turns;
	}
	
	public void startGame() {
		Board board = new Board();
		Player p1 = new Player();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("<------------------------------------------------------------------------------------------->");
		System.out.println("<----------------------------------------CANDY CRUSH---------------------------------------->");
		System.out.println("<------------------------------------------------------------------------------------------->");
		System.out.println();
		System.out.println("<--------------------------------------------KEY-------------------------------------------->");
		System.out.println();
		System.out.println("Color:   r = red, y = yellow, b = blue, g = green, o = orange, C = Colored, * = crushed");
		System.out.println("Type:    r = regular, V = vertical striped, H = horizontal striped, W = wrapped, C = Colored");
		System.out.println("Example: g r = green regular, r W = red wrapped, C C = colored colored");
		System.out.println();
		System.out.println("<------------------------------------------------------------------------------------------->");
		System.out.println();
		System.out.println("You have " + turns + " turns remaining");
		System.out.println();
		
		board.printBoard();
		System.out.println();
		
		while (turns > 0) {
			int[] coordinates = p1.chooseCoordinates(scan);
		   
		    if (board.move(coordinates)) {
		    	turns--;
		    }
		   	   	   	   
		    System.out.println();
		    System.out.println("Your current score is: " + p1.getScore());
		    System.out.println("You have " + turns + " turns remaining");
		    System.out.println();
		}
		
		System.out.println("Game has ended!");
		System.out.println("<------------------------------------------------------------------------------------------->");
	}
}
