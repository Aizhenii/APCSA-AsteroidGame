import processing.core.PApplet;


public class SpaceShip extends Floater
{
	private int[] xValues = {0, 5, 40, 5, 0, -10};
	private int[] yValues = {-30, -10, 0, 10, 30, 0};

    public SpaceShip(PApplet applet)
    {
        super(applet);
        
        setCorners(xValues, yValues);
        setColor(applet.color(127, 131, 255));
        setStartPos();
    }
    
    public void accelerateForward(double a) {
    	super.accelerateForward(a);
    	
    	// trailing effects
    }
    private void setStartPos()
    {
        // Because the `applet` variable in Floater has "protected" access,
        // we can use it directly in subclasses.
        double x = applet.width/2;
        double y = applet.height*3/4;
        
        setPos(x,y);
    }

}
