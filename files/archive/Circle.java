/**
 * @(#)Circle.java
 *
 *
 * @author 
 * @version 1.00 2011/1/18
 */


public class Circle {
	
	public static void main(String args[])
	{
		Circle c = new Circle(16.62, "yards");
		System.out.println(c.circumference()+" "+c.units);
		System.out.println(c.area()+" "+c.units+" squared");
		
	}

	double radius;
	String units;
    public Circle(double r, String u) 
    {
    	radius = r;
    	units = u;
    }
    
    public double circumference()
    {
    	return radius*2*Math.PI;
    }
    
    public double area()
    {
    	return Math.PI*radius*radius;
    }
}