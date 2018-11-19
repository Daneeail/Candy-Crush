package candyCrushRivals;

import java.util.Scanner;

public class test{

public static void main(String args[]){
	   Board board = new Board();
	   Player p1 = new Player();
	   Scanner scan = new Scanner(System.in);
	   boolean response = true;
	   
	   board.printBoard();
	   System.out.println();
	   	   
	   while(response) {
		   System.out.println("Choose an option:");
		   System.out.println("0. Set color");
		   System.out.println("1. Set type");
		   System.out.println("2. Swap candy");
		   System.out.println("3. Exit");
		   int option = Integer.parseInt(scan.nextLine());
		   int row;
		   int column;
		   String color;
		   String type;
		   
		   switch(option) {
		   case 0:
			   System.out.println("Select row: ");
			   row = Integer.parseInt(scan.nextLine());
			   System.out.println("Select column: ");
			   column = Integer.parseInt(scan.nextLine());
			   System.out.println("Choose color: ");
			   color = scan.nextLine();
			   board.setColor(row, column, color);
			   
			   board.printBoard();
			   break;
		   case 1:
			   System.out.println("Select row: ");
			   row = Integer.parseInt(scan.nextLine());
			   System.out.println("Select column: ");
			   column = Integer.parseInt(scan.nextLine());
			   System.out.println("Choose type: ");
			   type = scan.nextLine();
			   board.setType(row, column, type);
			   
			   board.printBoard();
			   break;
		   
		   case 2:
			   int[] coordinates = p1.chooseCoordinates(scan);
	   
			   board.move(coordinates);
			   	   	   	   
			   System.out.println();
			   
			   break;
		   case 3:
			   response = false;
			   break;
		   }
	   }
   }
}