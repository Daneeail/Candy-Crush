package candyCrushRivals;

import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class Candy {
	private String type = "regular";
	private String color = "red";
	
	// Creates a candy of a random color
	public Candy() {		
		int randomNum = ThreadLocalRandom.current().nextInt(0, 5);
		
		switch(randomNum) {
		case 0:
			color = "red";
			break;
		case 1:
			color = "green";
			break;
		case 2:
			color = "blue";
			break;
		case 3:
			color = "orange";
			break;
		case 4:
			color = "yellow";
			break;
		default:
			color = "Invalid color";
			break;
		}
	}
	
	// Creates a candy of a particular type and color
	public Candy(String type, String color) {
		this.type = type;
		this.color = color;
	}
	
	// Gets the type of candy
	public String getType() {
		return type;
	}
	
	// Gets the color of the candy
	public String getColor() {
		return color;
	}
	
	// Sets the type of candy
	public void setType(String type) {
		this.type = type;
	}
	
	// Sets the color of the candy
	public void setColor(String color) {
		this.color = color;
	}
	
	// Sets a random color to a candy
	public void setRandomColor() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, 5);
		List<String> randomColors = new LinkedList<String>();
		
		randomColors.add("red");
		randomColors.add("green");
		randomColors.add("blue");
		randomColors.add("orange");
		randomColors.add("yellow");
		
		color = randomColors.get(randomNum);
	}
	
	// Creates a candy of a random color minus the notColor
	public void setNotColor(String notColor) {
		int randomNum = ThreadLocalRandom.current().nextInt(0, 4);
		List<String> remainingColors = new LinkedList<String>();
		
		remainingColors.add("red");
		remainingColors.add("green");
		remainingColors.add("blue");
		remainingColors.add("orange");
		remainingColors.add("yellow");
		remainingColors.remove(notColor);
		
		color = remainingColors.get(randomNum);
	}
	
	// Creates a candy of a random color minus the notColor1 and notColor2
	public void setNotColor(String notColor1, String notColor2) {
		int randomNum = ThreadLocalRandom.current().nextInt(0, 3);
		List<String> remainingColors = new LinkedList<String>();
		
		remainingColors.add("red");
		remainingColors.add("green");
		remainingColors.add("blue");
		remainingColors.add("orange");
		remainingColors.add("yellow");
		remainingColors.remove(notColor1);
		remainingColors.remove(notColor2);
		
		color = remainingColors.get(randomNum);
	}
}
