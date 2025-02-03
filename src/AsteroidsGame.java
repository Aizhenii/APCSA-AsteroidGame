import processing.core.PApplet;
import java.util.ArrayList;

public class AsteroidsGame extends PApplet
{
    //private Floater f; // This is just an example object
    private SpaceShip s;
    //private Bullet b;
    private ArrayList<Floater> floaters = new ArrayList<Floater>();
    private int numAsteroids = 5;
    private final int SPACE = 32; //sets ASCII code of space bar
    private double rotationSpeed = 4;
    private boolean gameWon = false;
    private boolean gameLost = false;
    private boolean gameOnGoing = false;
    
    
    public void settings()
    {
        size(600,600);
    }

    public void setup() 
    {
        //f = new Floater(this);
    	s = new SpaceShip(this);
    	//b = new Bullet(this, s);
    	
    	floaters.add(s); //spaceship is always stored at index 0 of arraylist
    	
    	for (int i = 0; i < numAsteroids; i++) {
    		Asteroid a = new Asteroid(this);
    		floaters.add(a);
    	}
        
        // The default shape of the Floater is a small square. It can be changed
        // with the setCorners method.
        
        // Each x,y pair is one corner of the shape of the Floater, relative to the
        // center. So the first corner here is at (-30,-20)...30 pixels to the left of
        // the Floater's center, and 20 pixels up.
        // This is where they will be when the Floater is facing to the *right*.
        //int[] xCorners = {-30, 0, 70};
        //int[] yCorners = {-20, 20, 0};
        
        //f.setCorners(xCorners, yCorners);
        
        
        // This sets the center location of the Floater. 
        //f.setPos(300, 100);
        
       
    }

    public void draw() 
    {	
    	background(0);
    	controller();
    	
    	// test bullet
    	//b.show();
    	//b.move();
    	if (gameOnGoing) {
    		// show floaters
			gameWon = false;
			gameLost = false;

        	for (Floater f: floaters) {
        		f.show();
        		f.move();
        	}
        	
    	}
    	else {
        	if (gameWon) {
        		button("RESTART");
        		fill(255);
        		textSize(50);
        		text("YOU WIN!", this.width/2, this.height/2);
        		//System.out.println("You win!");
        	}
        	else if (gameLost) {
        		button("RESTART");
        		fill(255);
        		textSize(50);
        		text("YOU LOSE!", this.width/2, this.height/2);
        	}
        	else {
        		button("START");
        	}
    	}
    	
    	
    	//check if asteroids colliding with spaceship
    	boolean bulletsCollideAsteroids = false;
    	for (int i = floaters.size() - 1; i >= 1; i--) {
    		
			//Check if all asteroids are destroyed, player wins if true
	    	//Checks if the floater at index 1 is an asteroid or not
	    	if (!(floaters.get(1) instanceof Asteroid) && (floaters.get(0) instanceof SpaceShip)|| floaters.size() == 1) {
	    		gameWon = true;
	    		gameOnGoing = false;
	    		break;
	    	}
	    	
    		if (floaters.get(i) instanceof Asteroid) { //error out of bounds
				// Checks if asteroids are colliding with the spaceship
    			if (((Asteroid)floaters.get(i)).isColliding(floaters.get(0))) {
        			floaters.remove(i);
        			floaters.remove(0);//removes spaceship
        			gameLost = true;
        			gameOnGoing = false;
        			break;
        		}
    			
    			//check if bullets are colliding with asteroids
    			for (int j = floaters.size() - 1; j >= 1; j--) {
    				if (floaters.get(j) instanceof Bullet) {
    	    			if (((Asteroid)floaters.get(i)).isColliding(floaters.get(j))) {
    	    				bulletsCollideAsteroids = true;
    	    				floaters.remove(j);
    	        		}		
    	    		}
    			}
    			
    			if (bulletsCollideAsteroids) {
    				Asteroid temp = (Asteroid)floaters.get(i);
    				floaters.remove(i);
    				if (temp.radius() > 15) {
    					//adds two smaller asteroids in the same position as the previous one
    					splitAsteroid(temp, i);
        				//System.out.println("split!");
    				}
    				
    			}
    			
    			bulletsCollideAsteroids = false;
    		}
    		

    	}

    	
    	//check if bullets are out of bounds 
    	for (int i = floaters.size() - 1; i >= 1; i--) {
    		if (floaters.get(i) instanceof Bullet) {
    			if (((Bullet)floaters.get(i)).outOfBounds()) {
        			floaters.remove(i);
        		}
    		}
    	}
        
    }
    
    public void keyPressed() {
    	if (key == SPACE) {
    		Bullet b = new Bullet(this, s);
    		floaters.add(b);
    	}
    }
    
	//creates two asteroids
	public void splitAsteroid(Asteroid a, int index) {
		for (int i = 0; i < 2; i++) {
			Asteroid a2 = new Asteroid(this, a);
			floaters.add(index, a2);
		}
		
	}
	
	private void button(String text) {
		int w = 100;
		int h = 40;
		float xPos = this.width/2 - w/2;
		float yPos = this.height/2 + h;
		
		fill(255);
		rect(xPos, yPos, w, h);
		
		fill(0);
		textSize(24);
		textAlign(CENTER);
		text(text, xPos + w/2, yPos + h/2 + 8);
		
		 if(mousePressed){
			  if((mouseX > xPos && mouseX < xPos+w) && (mouseY > yPos && mouseY < yPos+h)){
				  if (text.equals("START")) {
					  gameOnGoing = true;
				  }	  
				  else if (text.equals("RESTART")) {
					  floaters.clear(); //clears everything in arraylist
					  s = new SpaceShip(this);
					  floaters.add(s);
				 
					  for (int i = 0; i < numAsteroids; i++) {
						  Asteroid a = new Asteroid(this);
						  floaters.add(a);
					  }
					  
					  gameOnGoing = true;
				  }
			  }
		 }
	}
	
    private void controller() {
    	//create spaceship controls here
    	if (keyPressed) {
    		if (keyCode == UP) {
    			s.accelerateForward(0.2);
    			s.move();
    		}
    		if (keyCode == RIGHT) {
    			s.rotate(rotationSpeed);
    		}
    		else if (keyCode == LEFT) {
    			s.rotate(-rotationSpeed);
    		}
    		
    	}
    	else {
    		s.setV(0, 0);
    	}
    }
    
}