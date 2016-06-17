package userInterface.graphic3DHandler;

import javafx.scene.transform.Rotate;

/**
 * @author Michele Franceschetti
 */

public class Rotation 
{
	public Rotate x = new Rotate();
    { x.setAxis(Rotate.X_AXIS); }
    public Rotate y = new Rotate();
    { y.setAxis(Rotate.Y_AXIS); }
    public Rotate z = new Rotate();
    { z.setAxis(Rotate.Z_AXIS); }

    /**
     * Default Constructor.
     */
    public Rotation()
    {
    	setRotation(0,0,0);
    }
    
    public void setRotation(double _x, double _y, double _z)
    {
        this.x.setAngle(_x);
        this.y.setAngle(_y);
        this.z.setAngle(_z);
    }
    
    public void setX(double _x) { this.x.setAngle(_x); }
    public void setY(double _y) { this.y.setAngle(_y); }
    public void setZ(double _z) { this.z.setAngle(_z); }
    
    public double getX() { return this.x.getAngle(); }
    public double getY() { return this.y.getAngle(); }
    public double getZ() { return this.z.getAngle(); }

    @Override
    public String toString()
    {
	    return "r = (" +
	    x.getAngle() + ", " +
	    y.getAngle() + ", " +
	    z.getAngle() + ")  ";
    }
}
