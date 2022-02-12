package task2;

import java.util.ArrayList;

public class User 
{
	protected String name;
	protected ArrayList<Car> uncollectedCars = new ArrayList<>();
	protected ArrayList<Car> carz = new ArrayList<>();
	
	//public User()
	//{
	//	
	//}
	public User(String name) 
	{
		this.name = name;
	}
	
	
	public void collectCar( Car car2BeLCollected )
	{
		carz.add(car2BeLCollected);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Car> getCarz() {
		return carz;
	}
	public void setCarz(ArrayList<Car> carz) {
		this.carz = carz;
	}
	public ArrayList<Car> getUncollectedCars() {
		return uncollectedCars;
	}
	public void setUncollectedCars(ArrayList<Car> uncollectedCars) {
		this.uncollectedCars = uncollectedCars;
	}
	public void addUncollectedCars(Car uncollectedCar) {
		uncollectedCars.add(uncollectedCar);//
	}
	
	
}
