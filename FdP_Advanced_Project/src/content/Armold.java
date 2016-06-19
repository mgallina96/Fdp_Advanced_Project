package content;

import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Translate;
import userInterface.graphic3DHandler.Transform;

/**
 * @author Michele Franceschetti
 */

public class Armold
{	
	private static final double RADIUS = 8;
	
	public Transform upperArm = new Transform();
	//private Transform foreArm = new Transform();
	//private Transform hand = new Transform();
	
	//Cylinder upperArmMesh = new Cylinder(RADIUS, 100);
	
	//private float upperArmLenght;
	//private float foreArmLenght;
	
	//private Tool tool;
	//private Translate target;
	
	public Armold(Translate _upperArm, Translate _target, float _upperArmLenght, float _foreArmLenght)
	{
		upperArm.reset();
		//foreArm.reset();
		//hand.reset();
		
		Transform t = new Transform();
		t.reset();
		t.position = _upperArm;
		
		//this.target = _target;
		this.upperArm = t;
		//this.upperArmLenght = _upperArmLenght;
		//this.foreArmLenght = _foreArmLenght;
		
		
		//UpperArm
		
		Cylinder upperArmMesh = new Cylinder(RADIUS, 100);

		//if(!upperArm.getChildren().contains(upperArmMesh))
			upperArm.getChildren().add(upperArmMesh);
		
		//Cylinder upperArmMesh = new Cylinder(RADIUS, upperArmLenght);
		//upperArmMesh.setRotate(90);
        //upperArmMesh.setTranslateX(upperArm.position.getX() + upperArmLenght / 2);
        //upperArmMesh.setTranslateY(upperArm.position.getY());
        //upperArmMesh.setTranslateZ(upperArm.position.getZ());
		
        
		/*foreArm.setPosition(upperArm.position.getX() + (double)upperArmLenght, upperArm.position.getY(), upperArm.position.getZ());
		
		upperArm.getChildren().add(foreArm);
		
		//ForeArm
		
		Cylinder foreArmMesh = new Cylinder(RADIUS, foreArmLenght);
		foreArmMesh.setRotate(90);
		foreArmMesh.setTranslateX(foreArmLenght / 2);
		
		hand.setPosition(foreArm.position.getX() + (double)foreArmLenght, foreArm.position.getY(), foreArm.position.getZ());
		
		foreArm.getChildren().add(foreArmMesh);
		foreArm.getChildren().add(hand);*/
	}
	
	/*public void setArm()
	{
		double distanceFromTarget = Transform.distance(getUpperArm().position, target);
		
		if(distanceFromTarget > (upperArmLenght + foreArmLenght))
			distanceFromTarget = upperArmLenght + foreArmLenght;
		
		double upperAngle = Math.acos((foreArmLenght * foreArmLenght - upperArmLenght * upperArmLenght - distanceFromTarget * distanceFromTarget) / (upperArmLenght * distanceFromTarget));
		
		double foreAngle = Math.acos((distanceFromTarget * distanceFromTarget - upperArmLenght * upperArmLenght - foreArmLenght * foreArmLenght) / (upperArmLenght * foreArmLenght));
		
		
	}*/
	
	public void moveTarget(double deltaX, double deltaY, double deltaZ)
	{
		upperArm.position.setX(upperArm.position.getX() + deltaX);
		upperArm.position.setY(upperArm.position.getY() + deltaY);
		upperArm.position.setZ(upperArm.position.getZ() + deltaZ);
	}
}
