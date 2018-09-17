public class Body {
	
	private double myXPos; 
	private double myYPos;
	private double myXVel;
	private double myYVel; 
	private double myMass; 
	private String myFilename; 
	
	public Body(double x, double y, double xv, double yv, double mass, String filename) {
		
		myXPos = x; 
		myYPos = y; 
		myXVel = xv; 
		myYVel = yv; 
		myMass = mass; 
		myFilename = filename;
	}
	
	public Body(Body b) {
		this.myXPos = b.myXPos; 
		this.myYPos = b.myYPos; 
		this.myXVel = b.myXVel; 
		this.myYVel = b.myYVel; 
		this.myMass = b.myMass; 
		this.myFilename = b.myFilename;
	}
	
	public double getX() {
		return myXPos; 
	}
	
	public double getY() {
		return myYPos;
	}
	
	public double getXVel() {
		return myXVel; 
	}
	
	public double getYVel() {
		return myYVel;
	}
	
	public double getMass() {
		return myMass; 
	}
	
	public String getName() {
		return myFilename;
	}
	
	public double calcDistance(Body b) {
		double dist = Math.sqrt((myXPos-b.myXPos)*(myXPos-b.myXPos) + 
				(myYPos-b.myYPos)*(myYPos-b.myYPos));
		return dist;
	}
	
	public double calcForceExertedBy(Body p) {
		double dist = calcDistance(p);
		double G = (6.67) * (Math.pow(10,-11));
		double Force = ((p.myMass * myMass) * G) / Math.pow(dist,2);
		return Force;
		
	}
	
	public double calcForceExertedByX(Body p) {
		double dx = (p.myXPos - myXPos);
		double F = calcForceExertedBy(p); 
		double r = calcDistance(p);
		double Fx = ((F*dx) / r);
		return Fx;
	}
	
	public double calcForceExertedByY(Body p) {
		double dy = (p.myYPos - myYPos);
		double F = calcForceExertedBy(p); 
		double r = calcDistance(p);
		double Fy = ((F*dy) / r);
		return Fy;
	}
	
	public double calcNetForceExertedByX(Body[] bodies) {
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
		StdDraw.picture(myXPos,  myYPos,  "images/"+myFilename);
	}

}

