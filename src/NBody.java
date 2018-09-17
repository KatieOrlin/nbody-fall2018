	

/**
 * @author KATIE ORLIN
 * 
 * Simulation program for the NBody assignment
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	
	/**
	 * Read the specified file and return the radius
	 * @param fname is name of file that can be open
	 * @return the radius stored in the file
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static double readRadius(String fname) throws FileNotFoundException  {
		
		Scanner s = new Scanner(new File(fname));
	
		int n = s.nextInt(); // assigns integer n to be the number of bodies
		double radius = s.nextDouble(); // assigns double radius to be the radius of the planet
		
		s.close();
		
		return radius;
		
	}
	
	/**
	 * Read all data in file, return array of Celestial Bodies
	 * read by creating an array of Body objects from data read.
	 * @param fname is name of file that can be open
	 * @return array of Body objects read
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static Body[] readBodies(String fname) throws FileNotFoundException {
		
			Scanner s = new Scanner(new File(fname));
			
			int nb = s.nextInt(); // # bodies to be read
			double myDouble = s.nextDouble(); // assigns instance variable to each double used to make array
			Body[] myArray = new Body[nb]; // initializes array
			
			for(int k=0; k < nb; k++) {
				/*
				 * uses a for loop to create the array of Body objevts using the data in the file
				 */
				myArray[k] = new Body(s.nextDouble(), s.nextDouble(), s.nextDouble(), 
						s.nextDouble(), s.nextDouble(), s.next());
			}
			
			s.close();
			
			return myArray;
	}
	public static void main(String[] args) throws FileNotFoundException{
		double totalTime = 157788000.0;
		double dt = 25000.0;
		
		String fname= "./data/planets.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			fname = args[2];
		}	
		
		Body[] bodies = readBodies(fname);
		double radius = readRadius(fname);
		
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
	
		for(double t = 0.0; t < totalTime; t += dt) {
			
			int nbodies = bodies.length;
			double xforces[] = new double[nbodies]; // creates double array xforces acting on each body
			double yforces[] = new double[nbodies]; // creates double array yforces acting on each body
			
			
			for(int q = 0; q < nbodies; q++) {
				/*
				 * loops over all bodies and uses the arrays xforces and yforces
				 * in order to calculate the net forces in the x and y directions
				 * on each body
				 */
				xforces[q] = bodies[q].calcNetForceExertedByX(bodies);
				yforces[q] = bodies[q].calcNetForceExertedByY(bodies);
				
			}
			
			
			for(int x=0; x < nbodies; x++) {
				/*
				 * loops over all bodies and calls update with change in time, dt, 
				 * and the corresponding xforces, yforces values as calculated in for 
				 * loop above
				 */
				bodies[x].update(dt, xforces[x], yforces[x]);
			}
			
			StdDraw.picture(0,0,"images/starfield.jpg");
			
			
			for (int y = 0; y < nbodies; y++) {
				//loops over all the bodies in array, nbodies, and calls draw on each one
				bodies[y].draw(); 
			}
			
			StdDraw.show(10);
		}
		
		// prints final values after simulation
		
		
		System.out.printf("%d\n", bodies.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              bodies[i].getX(), bodies[i].getY(), 
		                      bodies[i].getXVel(), bodies[i].getYVel(), 
		                      bodies[i].getMass(), bodies[i].getName());	
		}
	}
}
	

