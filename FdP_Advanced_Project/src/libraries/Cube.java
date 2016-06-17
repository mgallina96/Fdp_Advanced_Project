package libraries;

import javafx.scene.shape.Box;
import userInterface.graphic3DHandler.Transform;

public class Cube {
	
	public Transform root = new Transform();
	
	public Cube()
	{
		root.reset();
		
		Box b = new Box(20,20,20);
		root.getChildren().add(b);
	}
	
	public void moveTarget(double deltaX, double deltaY, double deltaZ)
	{
		root.position.setX(root.position.getX() + deltaX);
		root.position.setY(root.position.getY() + deltaY);
		root.position.setZ(root.position.getZ() + deltaZ);
	}
}
