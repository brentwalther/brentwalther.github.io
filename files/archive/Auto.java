/*
 *Brent Walther
 *04.01.10
 *Desc: Automobile class that has various methods concering gas and trips.
 */


public class Auto {

    public static void main(String args[]) {
    	
    	// Creates a new automobile
    	Automobile myBmw = new Automobile(24);
    	
    	// Puts 20 gallons in the tank
    	myBmw.fillUp(20);
    	// Takes a 100 mile trip
    	myBmw.takeTrip(100);
    	// Finds the amount of fuel left
    	double fuel_left = myBmw.reportFuel();
    	
    	System.out.println(fuel_left);
    }    
    
}
class Automobile {
	
	// State variables
	private int mpg;
	private double gallons;
	
	// Constructor
	public Automobile(int mileage)
	{
		mpg = mileage;
		gallons = 0;
	}
	
	// Puts gas in the tank
	public void fillUp(int amount)
	{
		gallons += amount;
	}
	
	// Takes a trip and subtracts gas from tank according to mileage
	public void takeTrip(int length)
	{
		gallons -= (double)length / mpg;
	}
	
	// Returns the amount of fuel left
	public double reportFuel()
	{
		return gallons;
	}
}