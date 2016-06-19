package content;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;
import userInterface.graphic3DHandler.Transform;

/**
 * @author Michele Franceschetti
 */

public class Arm 
{
	private static final double ARM_RADIUS = 6;

	private static final double KNEE_RADIUS = 5;
	
	private static final Color ARM_COLOR = Color.BLUE;
	private static final Color KNEE_COLOR = Color.CYAN;
	
	public Transform upperArm = new Transform();
	private Transform foreArm = new Transform();
	private Transform hand = new Transform();
	
	private float upperArmLenght;
	private float foreArmLenght;
		
	private Tool tool;
	private Translate target;
	
	/**
	 * Returns the target.
	 * 
	 * @return the position of the target.
	 */
	public Translate getTarget()
	{
		return target;
	}
	
	/**
	 * @return The tool.
	 */
	public Tool getTool() 
	{
		return tool;
	}



	
	/**
	 * @param tool The tool to use on this arm.
	 */
	public void setTool(Tool tool) 
	{
		this.tool = tool;
	}
	



	/**
	 * The constructor.
	 * 
	 * @param _target The position of the target.
	 * @param _upperArmLenght The length of the Upper arm.
	 * @param _foreArmLenght The length of the Fore arm.
	 */
	public Arm(Translate _target, float _upperArmLenght, float _foreArmLenght)
	{
		upperArm.reset();
		foreArm.reset();
		hand.reset();
		
		this.target = _target;
		this.upperArmLenght = _upperArmLenght;
		this.foreArmLenght = _foreArmLenght;
		
		//Materials
		
		final PhongMaterial ArmColor = new PhongMaterial();
		ArmColor.setDiffuseColor(ARM_COLOR);
		ArmColor.setSpecularColor(ARM_COLOR);
		
		final PhongMaterial kneeColor = new PhongMaterial();
		kneeColor.setDiffuseColor(KNEE_COLOR);
		kneeColor.setSpecularColor(KNEE_COLOR);
		
		//UpperArm
		
		Cylinder upperArmMesh = new Cylinder(ARM_RADIUS, upperArmLenght);
		Sphere upperArmKnee = new Sphere(KNEE_RADIUS);
		
		upperArmMesh.setMaterial(ArmColor);
		upperArmKnee.setMaterial(kneeColor);
		
		if(!upperArm.getChildren().contains(upperArmMesh))
			upperArm.getChildren().add(upperArmMesh);
		
		if(!upperArm.getChildren().contains(upperArmKnee))
			upperArm.getChildren().add(upperArmKnee);
		
		upperArmMesh.setRotate(90);
        upperArmMesh.setTranslateX(upperArm.position.getX() + upperArmLenght / 2);
        upperArmMesh.setTranslateY(upperArm.position.getY());
        upperArmMesh.setTranslateZ(upperArm.position.getZ());
		
        
		foreArm.setPosition(upperArm.position.getX() + (double)upperArmLenght, upperArm.position.getY(), upperArm.position.getZ());
		
		upperArm.getChildren().add(foreArm);
		
		//ForeArm
		
		Cylinder foreArmMesh = new Cylinder(ARM_RADIUS, foreArmLenght);
		Sphere foreArmKnee = new Sphere(KNEE_RADIUS);
		
		foreArmMesh.setMaterial(ArmColor);
		foreArmKnee.setMaterial(kneeColor);
		
		if(!foreArm.getChildren().contains(foreArmMesh))
			foreArm.getChildren().add(foreArmMesh);
		
		if(!foreArm.getChildren().contains(foreArmKnee))
			foreArm.getChildren().add(foreArmKnee);
		
		foreArmMesh.setRotate(90);
		foreArmMesh.setTranslateX(upperArm.position.getX() + foreArmLenght / 2);
		foreArmMesh.setTranslateY(upperArm.position.getY());
		foreArmMesh.setTranslateZ(upperArm.position.getZ());
		
		hand.setPosition(foreArm.position.getX() + (double)foreArmLenght, foreArm.position.getY(), foreArm.position.getZ());
		
		foreArm.getChildren().add(hand);
		
		setArm();
	}
	
	/**
	 * Sets the position and rotation of each node, so the hand position is the target.
	 */
	public void setArm()
	{
		double distanceFromTarget = Transform.distance(upperArm.position, target);
		
		if(distanceFromTarget > (upperArmLenght + foreArmLenght))
			distanceFromTarget = upperArmLenght + foreArmLenght;
		
		
		double targetAngle = Math.asin((target.getY() - upperArm.position.getY()) / distanceFromTarget) * Transform.RAD_TO_DEG;
		
		if(target.getX() < upperArm.position.getX())
			targetAngle = 180.0 - targetAngle;
		
	 	
	 	double upperAngleX = Math.acos((foreArmLenght * foreArmLenght - upperArmLenght * upperArmLenght - distanceFromTarget * distanceFromTarget) / (- 2 * upperArmLenght * distanceFromTarget)) * Transform.RAD_TO_DEG;
	 	
	 	if(target.getY() < upperArm.position.getY())
	 		upperAngleX = -upperAngleX + 180;
	 	
	 	double distanceZ = target.getZ() - upperArm.position.getZ();
	 	
	 	if(distanceZ > (upperArmLenght + foreArmLenght))
	 		distanceZ = upperArmLenght + foreArmLenght;
	 	
	 	double upperAngleCorrection = Math.asin(distanceZ / distanceFromTarget) * Transform.RAD_TO_DEG;
	 	
	 	
	 	double upperAngle = upperAngleX + upperAngleCorrection;
	 	
	 	if(target.getY() < upperArm.position.getY())
	 		upperAngle = -upperAngle;
	 	
	 	upperArm.reset();
	 	
	 	upperArm.rotation.setZ(90);
	 	upperArm.rotation.setX(upperAngle);
	 	
	 	upperArm.position.setX(target.getX());
	 		 
	 	double heightZ = Math.sin(upperAngle / Transform.RAD_TO_DEG) * (upperArmLenght + foreArmLenght);
	 	double heightY = Math.cos(upperAngle / Transform.RAD_TO_DEG) * (upperArmLenght + foreArmLenght);
	 	double height = Math.sqrt((heightY - target.getY()) * (heightY - target.getY()) + (heightZ - target.getZ()) * (heightZ - target.getZ()));
	 	
	 	double deltaForeArmY = upperArm.position.getY() + Math.cos(upperAngle / Transform.RAD_TO_DEG) * upperArmLenght - target.getY();
	 	double deltaForeArmZ = upperArm.position.getZ() + Math.sin(upperAngle / Transform.RAD_TO_DEG) * upperArmLenght - target.getZ();
	 	double distance = Math.sqrt(deltaForeArmY * deltaForeArmY + deltaForeArmZ * deltaForeArmZ);
	 	
	 	if((distance - foreArmLenght) > 0.001)
	 		height = 0;
	 	
	 	double foreAngle = Math.acos(1 - height * height / (2 * foreArmLenght * foreArmLenght)) * Transform.RAD_TO_DEG;
	 	
	 	foreArm.rotation.setY(foreAngle);
	}
	
	/**
	 * Moves the target position.
	 * 
	 * @param deltaX The translation on the X axis.
	 * @param deltaY The translation on the Y axis.
	 * @param deltaZ The translation on the Z axis.
	 */
	public void moveTarget(double deltaX, double deltaY, double deltaZ)
	{
		target.setX(target.getX() + deltaX);
		target.setY(target.getY() + deltaY);
		target.setZ(target.getZ() + deltaZ);
		
		setArm();
	}
}
