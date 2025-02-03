import processing.core.PApplet;

public class Bullet extends Floater {
	private double acceleration = 5;
	public Bullet(PApplet applet, SpaceShip s) {
		
		super(applet);
		
		//sets bullet pos, vel, direction to be same a spaceship
		setPos(s.getX(), s.getY()); 
		setV(s.getVX(), s.getVY());
		rotate(s.getDirection());
		accelerateForward(acceleration);
			
		
	}
	
	public void show() {
		int yellow = applet.color(255, 255, 0);
		applet.fill(yellow);
		applet.ellipse((float)getX(), (float)getY(), 3, 3);
	}
	
	public void move() {
		setPos(getX() + getVX(), getY() + getVY()); 
	}
	
	public boolean outOfBounds() {
		if ((getX() > applet.width || getX() < 0 ) || 
				(getY() > applet.height || getY() < 0)) {
			return true;
		}
		return false;
	}
}
