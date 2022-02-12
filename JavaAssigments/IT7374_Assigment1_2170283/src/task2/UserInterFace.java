package task2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/*
 * The user interface for the custom car ordering system
 * James hendry
 * 19/4/2019
 * Version 1.4
 */
public class UserInterFace 
{



	//ArrayList<Car> carOrders = new ArrayList<>();
	private static ArrayList<User> users = new ArrayList<>();
	private static User currentUser;
	private static Scanner in = new Scanner(System.in);
	private static String strChoice = "";
	private static int intChoice = 0;

	public static void main(String[] args) 
	{
		String name;
		System.out.println("Welcome to the Katkyat custom made car ordering system\n" +
				"What is your name?");	
		users.add(new User(in.nextLine()));
		currentUser = users.get(0);

		displayOptions();
		//System.out.println(users.get(0).getName());
	}

	public static void displayOptions()
	{
		int choice = 0;
		do
		{
			System.out.println("Welcome " + currentUser.getName() + ". What do you want to do\n" + 
					"0:Quit\n" +
					"1:Place Custom Car Order\n" +
					"2:Make new User\n" + 
					"3:Change User\n" +
					"4:Collect Car");
			try
			{
				choice = in.nextInt();
			}
			catch( InputMismatchException e )
			{
				System.out.println("Wrong type of answer needs to be a number");
				in.nextLine();
				choice = -1;
			}
			switch(choice)
			{
			case 0:
				System.exit(0);
			case 1:
				placeCarOrder();
				break;
			case 2:
				makeNyuUser();
				break;
			case 3:
				changeUser();
				break;
			case 4:
				collectCar();
				break;
			default:
				break;
			}

		}while(choice != 0);
	}

	//collects all cars then clears the 
	private static void collectCar() 
	{
		if(currentUser.getUncollectedCars().size() == 0)
			System.out.println("You have no cars to collect");
		else
		{
			currentUser.setCarz(currentUser.getUncollectedCars());
			//for (int i = 0; i < currentUser.getUncollectedCars().size(); i++) 
			//	currentUser.collectCar(currentUser.getUncollectedCars().get(i));
			//
			currentUser.setUncollectedCars(new ArrayList<>());
			System.out.println("All Cars collected");
		}
	}

	private static void changeUser() 
	{
		//intChoice = -1;
		do
		{
			try
			{
				intChoice = displayArrayOfOptions("Select Users" , users);
				//intChoice = in.nextInt();
			}
			catch( InputMismatchException e )
			{
				System.out.println("Wrong type of answer needs to be a number");
				in.nextLine();
				//intChoice = -1;
			}
		}while( intChoice < 0 || intChoice > users.size() + 1);

		currentUser = users.get( intChoice );
		System.out.println("The current User is now " + currentUser.getName());
	}

	private static void makeNyuUser() {

		//String nyuName;
		//String strChoice;
		System.out.println("Making a new user.\n"+
				"What is this new user's name?");
		in.nextLine();
		users.add(new User(in.nextLine()));

		System.out.println("Do you want to switch to the new User?\n" + 
				"Y or YES for yes and all other keys for no");
		strChoice = in.nextLine().toUpperCase();
		if( strChoice.equals("YES") || strChoice.equals("Y"))
			currentUser = users.get(users.size() - 1);
	}

	private static void placeCarOrder() 
	{
		Car nyuCarOrder;
		PartsCatagory parts = new PartsCatagory();
		String availableParts[][] = parts.getAvailableParts(); 
		ArrayList<String> orderedParts = new ArrayList<>();
		String color;
		String type;
		//String strChoice;
		//int intChoice;
		//Boolean going;

		do
		{
			type = parts.getAvailableTypes()[ displayArrayOfOptions("What type of car do you want to be built?" , parts.getAvailableTypes()) ];
			color = parts.getAvailableColors()[ displayArrayOfOptions( "What color do you want your car?" , parts.getAvailableColors() ) ];

			orderedParts.add(availableParts[0][0]);
			orderedParts.add(availableParts[0][1]);
			System.out.println("Do you want a van door on this car? Y or YES for yes, all other keys for no");
			in.nextLine();
			strChoice = in.nextLine().toUpperCase();
			if(strChoice.equals("Y")  || strChoice.equals("YES"))
				orderedParts.add(availableParts[0][2]);

			strChoice = "B";
			do
			{
				System.out.println("You you want a boot or a hatch.\nB: For boot\nH: For Hatch");
				strChoice = in.nextLine().toUpperCase();
			}while(!strChoice.equals("B") && !strChoice.equals("H"));

			if( strChoice.equals("B") )
				orderedParts.add(availableParts[0][4]);
			else if( strChoice.equals("H") )
				orderedParts.add(availableParts[0][3]);

			intChoice = displayArrayOfOptions("What engine do you want you car to have" , availableParts[1]);
			orderedParts.add(availableParts[1][intChoice]);

			for (int i = 0; i < availableParts[2].length - 1; i++) 
				orderedParts.add(availableParts[2][i]);

			System.out.println("Do you want your car to have rally lights Y or YES for yes, all other keys for no.");
			in.nextLine().toUpperCase();
			strChoice = in.nextLine();
			if(strChoice.equals("Y") || strChoice.equals("YES"))
				orderedParts.add(availableParts[0][2]);

			intChoice = displayArrayOfOptions("How many seats do you want you car to have" , availableParts[3]);
			orderedParts.add(availableParts[3][intChoice]);

			intChoice = displayArrayOfOptions("How many wheels do you want you car to have" , availableParts[4]);
			orderedParts.add(availableParts[4][intChoice]);

			intChoice = displayArrayOfOptions("What type of transmission do you want your car to have" , availableParts[5]);
			orderedParts.add(availableParts[5][intChoice]);

			intChoice = displayArrayOfOptions("What speed gearbox do you want your car to have" , availableParts[6]);
			orderedParts.add(availableParts[6][intChoice]);

			for (int i = 0; i < availableParts[7].length; i++) 
				orderedParts.add(availableParts[7][i]);

			//

			nyuCarOrder = new Car(orderedParts, type, color, currentUser);
			nyuCarOrder.describeCar();
			System.out.println("Are you sure you want you car to be built to those specifications?\n"+
					"Y or YES for yes.\n" +
					"Q or QUIT to quit.\n" +
					"N or NO for not sure (Try agian)");
			in.nextLine();
			strChoice = in.nextLine().toUpperCase();
		}while(strChoice.equals("NO") || strChoice.equals("N"));
		//
		if(strChoice.equals("YES") || strChoice.equals("Y"))
			buildCar( nyuCarOrder );

	}

	public static int displayArrayOfOptions(String question, Object[] theArray )
	{
		intChoice = 0;

		System.out.println( question );
		do
		{
			for (int i = 0; i < theArray.length; i++) 
				System.out.println( (i + 1) + ": " + theArray[i].toString());
			try
			{
				intChoice = in.nextInt();
			}
			catch( InputMismatchException e)
			{
				System.out.println("Wrong type of answer needs to be a number");
				in.nextLine();
			}
		}while(intChoice > theArray.length || intChoice < 1);

		return intChoice -1;
	}

	public static int displayArrayOfOptions(String question, ArrayList<User> theArray )
	{
		int choice = 1;

		System.out.println( question );
		do
		{
			for (int i = 0; i < theArray.size(); i++) 
				System.out.println( (i + 1) + ": " + theArray.get(i).getName());

			try
			{
				choice = in.nextInt();
			}
			catch( InputMismatchException e)
			{
				System.out.println("Wrong type of answer needs to be a number");
				in.nextLine();
				choice = 0;
			}
		}while(choice > theArray.size() || choice < 1);

		return choice -1;
	}

	public static void buildCar( Car unbuiltCar )
	{
		//the factory floor will build a car
		//for testing convience the time between adding bits will
		//be 3 seconds
		Thread factoryFloor = new Thread( new Runnable() 
		{
			int time = 3000;
			@Override
			public void run() 
			{
				System.out.println("Now Building " + unbuiltCar.getOwner().getName() + "'s car.");
				//System.out.println("Built the frame");
				try 
				{
					Thread.sleep(time * 2);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				System.out.println("Installing " + unbuiltCar.getOwner().getName() + "'s car's parts");
				for (int i = 0; i < unbuiltCar.getOrderedParts().size(); i++) 
				{
					//System.out.println("Installed the " + unbuiltCar.getOrderedParts().get(i));
					try 
					{
						Thread.sleep(time);
						unbuiltCar.addParts(unbuiltCar.getOrderedParts().get(i));
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
				System.out.println("painting the car");
				try 
				{
					Thread.sleep(time);
					unbuiltCar.setBuilt(true);
					System.out.println( unbuiltCar.getOwner().getName()+ "'s " + unbuiltCar.getType() + " is built");
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				} 

				unbuiltCar.getOwner().addUncollectedCars(unbuiltCar);
				//currentUser.addUncollectedCars(unbuiltCar);
			}

		});
		factoryFloor.start();

		//return null;
	}
}
