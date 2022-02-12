package task2;
import java.util.*;

public class Car 
{
	/*
	String availableParts[][] = {{"Door" , "Hatch" , "Hood" , "Boot" ,"Van Door"} ,
			{"3 cylinder engine" , "4 cylinder engine" , "V6 engine" , "V8 engine" , "V10 engine" , "Electric Motor"} ,
			{"Front lights" , "Rear Lights" , "Indicators" , "Rally Lights"},
			{"2 seats" , "3 seats" , "4 seats" , "5 seats" , "6 seats" , "7 seats"},
			{"3 Wheels" , "4 Wheels" , "5 Wheels" , "6 Wheels"},
			{"Front Wheel Drive" , "Rear Wheel Drive" , "All Wheel Drive"},
			{"5-speed Gearbox" , "6-speed Gearbox"},
			{"Fuel Tank, Radiator , axels , drivetrain , exhaust pipe , spoiler , horn" , "Seat Bealts"}};
	String partCatagroies;
	String availableTypes[] = { "Hatchback" , "Saloon" , "Station Wagon" , "SUV" , "Sports Car" , "Super Car"};
	String availableColors[] = { "Black" , "Red" , "Yellow" , "Green" , "Green-Blue" , "Cyan" , "Blue" , "Indgio" , "Purple" , "Grey" , "White"};
	*/
	private String color;
	private String type;
	private ArrayList<String> parts = new ArrayList<>(); 
	private ArrayList<String> orderedParts = new ArrayList<>();
	boolean built = false;
	//boolean collected = false;
	private User owner;
	
	/*makes the default model the Katkyat 130
	public Car()
	{
		color = availableColors[0];
		
		for (int i = 0; i < 2; i++)
			orderedParts.add(availableParts[0][0]);
		orderedParts.add(availableParts[0][2]);
		orderedParts.add(availableParts[0][3]);	
		
		orderedParts.add(availableParts[1][1]);
		for (int i = 0; i < 3; i++) 
			orderedParts.add(availableParts[2][i]);
		
		for (int i = 3; i <= 6; i++) 
			orderedParts.add(availableParts[i][1]);;
			
		for (int i = 0; i < availableParts[7].length; i++)
			orderedParts.add(availableParts[7][i]);	
		type = availableTypes[1];
	} */
	
	public Car( ArrayList<String> orderedBits, String typeOCar , String colorOfCar , User theNyuOwner)// for custom built cars
	{
		type = typeOCar;
		color = colorOfCar;
		owner = theNyuOwner;
		
		for (int i = 0; i < orderedBits.size(); i++) 
			orderedParts.add(orderedBits.get(i));

	}
	
	public void describeCar()
	{
		System.out.println("This car is a " + color + " " + type);
		System.out.println( (!built) ? "It has not been built yet.\nIf it was it would have" : "This car has");
		
		System.out.println("It has");
		for (int i = 0; i < orderedParts.size(); i++) 
		{
			System.out.println(orderedParts.get(i));
		}
		
		
	}
		
	public void addParts( String part2Add )
	{
		parts.add(part2Add);
	}
//	public boolean isCollected() 
//	{
//		return collected;
//	}
	public boolean isBuilt() 
	{
		return built;
	}
	public ArrayList<String> getParts() {
		return parts;
	}
	public void setParts(ArrayList<String> parts) {
		this.parts = parts;
	}

	public void setBuilt(boolean built) {
		this.built = built;
	}

//	public void setCollected(boolean collected) {
//		this.collected = collected;
//	}

	public String getColor() {
		return color;
	}

	public String getType() {
		return type;
	}

	public ArrayList<String> getOrderedParts() {
		return orderedParts;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public User getOwner() {
		return owner;
	}
	
	
	
	
}
