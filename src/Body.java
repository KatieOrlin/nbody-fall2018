public class Body {
	
	private double myXPos; //X position of the body
	private double myYPos; //Y position of the body
	private double myXVel; //X Velocity of the body
	private double myYVel; //Y velocity of the body
	private double myMass; //mass of the body
	private String myFilename; //filename being used to make the animation
	
	public Body(double x, double y, double xv, double yv, double mass, String filename) {
		/** Create a Body from parameters
		 * @param x initial x position 
		 * @param y initial y position 
		 * @param xv initial x velocity 
		 * @param yv initial y velocity 
		 * @param mass of object
		 * @param filename of image for object animation
		 */
		myXPos = x; 
		myYPos = y; 
		myXVel = xv; 
		myYVel = yv; 
		myMass = mass; 
		myFilename = filename;
	}
	
	public Body(Body b) {
		/**
		 * copy constructor: this copies instance variables from one 
		 * body to this body 
		 * @param b is used to initialize this body
		 */
		this.myXPos = b.myXPos; 
		this.myYPos = b.myYPos; 
		this.myXVel = b.myXVel; 
		this.myYVel = b.myYVel; 
		this.myMass = b.myMass; 
		this.myFilename = b.myFilename;
	}
	
	public double getX() {
		// Return x position of this Body. @return value of x-position.
		return myXPos; 
	}
	
	public double getY() {
		// Return y position of this Body. @return value of x-position.
		return myYPos;
	}
	
	public double getXVel() {
		// Return x velocity of this Body. @return value of x-velocity.
		return myXVel; 
	}
	
	public double getYVel() {
		// Return y velocity of this Body. @return value of y-velocity.
		return myYVel;
	}
	
	public double getMass() {
		// Return mass of this Body. @return value of mass.
		return myMass; 
	}
	
	public String getName() {
		//Return filename of the animation. @return value of Filename.
		return myFilename;
	}
	
	public double calcDistance(Body b) {
		/**
		 * Return the distance between this body and another
		 * @param b the other body to which the distance is calculated 
		 * @return the distance between this body and body labeled b
		 */
		double dist = Math.sqrt((myXPos-b.myXPos)*(myXPos-b.myXPos) + 
				(myYPos-b.myYPos)*(myYPos-b.myYPos));
		return dist;
	}
	
	public double calcForceExertedBy(Body p) {
		/**
		 * Returns the force exerted on this body by the body 
		 * specified as the other parameter
		 * Uses calcDistance in order to calculate the distance between 
		 * body and body p
		 * @param p is the body which exerts the force on this body
		 * @return the force exerted on this body and body labeled p
		 */
		double dist = calcDistance(p);
		double G = (6.67) * (Math.pow(10,-11));
		double Force = ((p.myMass * myMass) * G) / Math.pow(dist,2);
		return Force;
		
	}
	
	public double calcForceExertedByX(Body p) {
		/**
		 * 
		 * Returns the force exerted in the X direction
		 * @param p is the body which exerts the force in the x direction
		 * Uses calcForceExertedBy in order to find the total force
		 * @return the force on the body from body p in the x direction
		 */
		double dx = (p.myXPos - myXPos);
		double F = calcForceExertedBy(p); 
		double r = calcDistance(p);
		double Fx = ((F*dx) / r);
		return Fx;
	}
	
	public double calcForceExertedByY(Body p) {
		/**
		 * Returns the force exerted in the Y direction
		 * @param p is the body which exerts the force in the y direction
		 * Uses calcForceExertedBy in order to find the total force
		 * @return the force on the body from body p in the y direction
		 */
		double dy = (p.myYPos - myYPos);
		double F = calcForceExertedBy(p); 
		double r = calcDistance(p);
		double Fy = ((F*dy) / r);
		return Fy;
	}
	
	public double calcNetForceExertedByX(Body[] bodies) {
		/***
		 * Returns the total/net force exerted on this body in the x direction by
		 * all the bodies in the array parameter
		 * Sums all of the forces returned by calcForceExertedByX
		 * @param Body[] bodies is an array of all the bodies exerting a force
		 * in the x direction on this body
		 * @return the total force in the x direction exerted on this body
		 */
		double netF = 0;
		for (Body b : bodies) { 
			if (! b.equals(this)) {
				double F = calcForceExertedByX(b); 
				netF = netF + F;
			}
		}
		return netF;
	}
	
	public double calcNetForceExertedByY(Body[] bodies) {
		/***
		 * Returns the total/net force exerted on this body in the y direction by
		 * all the bodies in the array parameter
		 * Sums all of the forces returned by calcForceExertedByY
		 * @param Body[] bodies is an array of all the bodies exerting a force
		 * in the y direction on this body
		 * @return the total force in the y direction exerted on this body
		 */
		double netF = 0;
		for (Body b : bodies) { 
			if (! b.equals(this)) {
				double F = calcForceExertedByY(b); 
				netF = netF + F;
			}
		}
		return netF;
	}
	
	public void update(double deltaT, double xforce, double yforce) {
		/**
		 * Mutator method which does not return a value
		 * Updates the state/instance variables of the Body object on which it is called
		 * Will be used during the simulation in order to continuously update the
		 * body's position and velocity with small time steps
		 */
		double ax = xforce / myMass; 
		double ay = yforce / myMass;
		double nvx = myXVel + (deltaT * ax); 
		double nvy = myYVel + (deltaT * ay); 
		double nx = myXPos + (deltaT * nvx);
		double ny = myYPos + (deltaT * nvy); 
		
		myXPos = nx; 
		myYPos = ny; 
		myXVel = nvx; 
		myYVel = nvy;
		
	}
	
	public void draw() {
		//Code for printing the entire universe
		StdDraw.picture(myXPos,  myYPos,  "images/"+myFilename);
	}

}

