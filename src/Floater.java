import processing.core.PApplet;

import java.lang.IllegalArgumentException;

// Nothing in this assignment requires you to modify the Floater class.
// You are allowed to make modifications to it if you want to add functionality, but if you
// feel like you NEED to, you've probably gone wrong somewhere.
public class Floater
{   
    // Because this variable is protected, rather than private, you can reference it directly from subclasses.
    protected PApplet applet;
    
    // Arrays that contain the x/y locations of the corners of the object,
    // relative to its center. Each index represents one vertex, so they MUST be
    // the same length.
    //
    // When the pointDir is 0, positive y values are down, and positive x values are to the right
    private int[] xCorners;   
    private int[] yCorners;  
    
    // A single int that represents the color of the object. Create an int to represent a color
    // by using the color() method of a PApplet.
    private int color;
    private double centerX, centerY; //holds center coordinates   
    private double vX, vY; //holds the speed of travel in the x and y directions  

    // holds current direction the Floater is pointing in degrees. 
    // 0 is to the right, and increasing angle rotates clockwise.
    // So 90 is straight down, and -90 is straight up.  
    private double pointDir; 
    
    
    public Floater(PApplet applet)
    {
        this.applet = applet;
        
        // Defaults to displaying as a small square
        xCorners = new int[]{-5, 5, 5, -5};
        yCorners = new int[]{-5, -5, 5, 5};
        
        color = applet.color(255, 0, 255);

        // If you use this constructor, use setCorners to set the shape of the Floater
    }

    public Floater(PApplet applet, int[] xCorners, int[] yCorners)
    {
        this.applet = applet;
        
        if(xCorners.length != yCorners.length)
        {
            throw new IllegalArgumentException("xCorners and yCorners must have the same length");
        }
        
        this.xCorners = xCorners;
        this.yCorners = yCorners;      
        
        // Floaters default to being colored magenta. You should set the color of all the Floater objects you make.
        // This is here to demonstrate how to use the color() method, and so that floaters that haven't been
        // re-colored are obvious.
        color = applet.color(255, 0, 255);
        
        // centerX, centerY, vX, vY, and pointDir all default to 0.
    }
    
    // Accelerates the floater in the direction it is pointing (pointDir)   
    public void accelerateForward (double a)   
    {          
        //convert the current direction the floater is pointing to radians    
        double dRadians = pointDir * (Math.PI / 180);     
        //change coordinates of direction of travel    
        vX += ((a) * Math.cos(dRadians));    
        vY += ((a) * Math.sin(dRadians));       
    }
    
    // Rotates the floater by a given number of degrees    
    public void rotate(double degreesOfRotation)   
    {     
        pointDir += degreesOfRotation;   
    }   

    // Moves the floater in the current direction of travel 
    // Wraps around if it gets to the edge of the screen.
    public void move ()   
    {      
        //change the x and y coordinates by myDirectionX and myDirectionY       
        centerX += vX;    
        centerY += vY;     

        //wrap around screen    
        if(centerX > applet.width)
        {     
            centerX = 0;    
        }    
        else if (centerX < 0)
        {     
            centerX = applet.width;    
        }    
        if(centerY > applet.height)
        {    
            centerY = 0;    
        }   
        else if (centerY < 0)
        {     
            centerY = applet.height;    
        }   
    }   

    // Draws the floater at the current position  
    public void show ()  
    {             
        applet.fill(color);   
        applet.stroke(color);    
        
        
        // save the current coordinate system
        applet.pushMatrix();
        
        
        // convert degrees to radians for rotate()     
        float dRadians = (float)(pointDir * (Math.PI / 180));
        
        // these change the coordinate system
        applet.translate((float)centerX, (float)centerY);
        applet.rotate(dRadians);
    
        // draw the polygon 
        applet.beginShape();
        for (int i = 0; i < xCorners.length; i++)
        {
          applet.vertex(xCorners[i], yCorners[i]);
        }
        applet.endShape(applet.CLOSE);

        // undo the translation and rotation
        applet.popMatrix();
    }   
    
    
    // Getters and setters
    public void setCorners(int[] xCorners, int[] yCorners)
    {
        if(xCorners.length != yCorners.length)
        {
            throw new IllegalArgumentException("xCorners and yCorners must have the same length");
        }

        this.xCorners = xCorners;
        this.yCorners = yCorners;
    }

    public void setPos(double x, double y)
    {
        centerX = x;
        centerY = y;
    }
    
    public double getX()
    {
        return centerX;
    }
    
    public double getY()
    {
        return centerY;
    }
    
    
    public double getVX()
    {
        return vX;
    }
    
    public double getVY()
    {
        return vY;
    }
    
    public void setV(double vX, double vY)
    {
        this.vX = vX;
        this.vY = vY;
    }
    
    public double getDirection()
    {
        return pointDir;
    }
    
    public void setColor(int c)
    {
        color = c;
    }
    
} 
