package candyCrushRivals;

import java.util.Scanner;

public class Player {
	private static int score;

	// Creates a player
	public Player() {
		score = 0;
	}
	
	// Gets the player's score
	public int getScore() {
		return score;
	}
	
	// Increment the player's score
	public static void incrementScore() {
		score++;
	}
	
	// Player chooses the coordinates to swap the candy
	public int[] chooseCoordinates(Scanner reader) {
		int[] coordinates = new int[4];
				
		System.out.println("Enter starting row: ");
		coordinates[0] = Integer.parseInt(reader.nextLine()) - 1;
		
		System.out.println("Enter starting column: ");
		coordinates[1] = Integer.parseInt(reader.nextLine()) - 1;
		
		System.out.println("Enter ending row: ");
		coordinates[2] = Integer.parseInt(reader.nextLine()) - 1;
		
		System.out.println("Enter ending column: ");
		coordinates[3] = Integer.parseInt(reader.nextLine()) - 1;
		
		return coordinates;
	}
}