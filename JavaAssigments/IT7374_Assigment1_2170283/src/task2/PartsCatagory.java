package task2;

public class PartsCatagory 
{
	private String availableParts[][] = {{"Hood" , "Doors" , "Van Door" , "Hatch" , "Boot" } ,
			{"3 cylinder engine" , "4 cylinder engine" , "V6 engine" , "V8 engine" , "V10 engine" , "Electric Motor"} ,
			{"Front lights" , "Rear Lights" , "Indicators" , "Rally Lights"},
			{"2 seats" , "3 seats" , "4 seats" , "5 seats" , "6 seats" , "7 seats"},
			{"3 Wheels" , "4 Wheels" , "5 Wheels" , "6 Wheels"},
			{"Front Wheel Drive" , "Rear Wheel Drive" , "All Wheel Drive"},
			{"5-speed Gearbox" , "6-speed Gearbox"},
			{"Fuel Tank" , "Radiator" , "axels" , "drivetrain" , "exhaust pipe" , "spoiler" , "horn" , "Seat Bealts"}};
	
	private String availableTypes[] = { "Hatchback" , "Sedan" , "Station Wagon" , "SUV" , "Sports Car" , "Super Car"};
	private String availableColors[] = { "Black" , "Red" , "Yellow" , "Green" , "Green-Blue" , "Cyan" , "Blue" , "Indgio" , "Purple" , "Grey" , "White"};
	
	
	
	public String[][] getAvailableParts() {
		return availableParts;
	}
	public void setAvailableParts(String[][] availableParts) {
		this.availableParts = availableParts;
	}
	public String[] getAvailableTypes() {
		return availableTypes;
	}
	public void setAvailableTypes(String[] availableTypes) {
		this.availableTypes = availableTypes;
	}
	public String[] getAvailableColors() {
		return availableColors;
	}
	public void setAvailableColors(String[] availableColors) {
		this.availableColors = availableColors;
	}
	
	
}
