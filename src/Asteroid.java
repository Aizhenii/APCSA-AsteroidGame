import processing.core.PApplet;

public class Asteroid extends Floater {

	private double radius = 0;
	private int numCorners = 10;
	private double[] angles = new double[numCorners];
	private int[] xCorner = new int[numCorners];
	private int[] yCorner = new int[numCorners];
	private double rotationSpeed = 2;
	
	public Asteroid(PApplet applet) {
		super(applet);
		// TODO Auto-generated constructor stub
		radius = (Math.random() * 20) + 10;
		
		setCornerValues();
		setCorners(xCorner, yCorner);
		setColor(200);
		
		double chance = Math.random();
		if (chance <= 0.5) {
			rotationSpeed*= -1;
		}
		
		setAsteroidPos();
		setAsteroidVel();
	}
	
	//for split asteroids
	public Asteroid(PApplet applet, Asteroid a) {
		super(applet);
		// TODO Auto-generated constructor stub
		
		radius = a.radius()/2;
		setCornerValues();
		setCorners(xCorner, yCorner);
		setColor(200);
		
		double chance = Math.random();
		if (chance <= 0.5) {
			rotationSpeed*= -1;
		}
		
		setPos(a.getX(), a.getY());
		setAsteroidVel();
		
	}
	
	public void move() {
		rotate(rotationSpeed);
		super.move();
	}
	
	public void setAsteroidPos() {
		// try setting the position outside of the screen so the player has time
		double xPos = ((Math.random() * (applet.width + 50)) - 50);
		double yPos = ((Math.random() * (applet.height + 50)) - 50);
		setPos(xPos, yPos);
		//System.out.println(xPos + " " + yPos);
	}
	
	public void setAsteroidVel() {
		int xVel = (int)((Math.random() * 5) - 3);
		int yVel = (int)((Math.random() * 5) - 3);
		
		if (xVel == 0 && yVel == 0) {
			xVel = 1;
			yVel = 1;
		}
		setV(xVel, yVel);
	}
	
	public boolean isColliding(Floater f) {
		//Checks if Asteroids is colliding with the spaceship
		double dx = getX() - f.getX();
		double dy = getY() - f.getY();
		double distance = Math.sqrt(dx * dx + dy * dy); 
		
		if (distance < radius) {
			return true;
		}
		return false;
	}
	
	public double radius() {
		return radius;
	}
	
	private void setCornerValues() {
		setAngles();
		
		for (int i = 0; i < numCorners; i ++) {
			double distance = (Math.random() *radius * 1.3)+ radius * 0.7;
			xCorner[i] = (int)(distance * Math.cos(angles[i]));
			yCorner[i] = (int)(distance * Math.sin(angles[i]));
			//System.out.println(xCorner[i] + " " + yCorner[i]);
		}
	}
	
	private void setAngles() {
		for (int i = 1; i < numCorners; i++) {
			angles[i] = angles[i-1] + (2*Math.PI)/numCorners;
		}
	}

}
