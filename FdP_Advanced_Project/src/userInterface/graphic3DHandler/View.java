package userInterface.graphic3DHandler;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;

/**
 * @author Michele Franceschetti
 */

public class View 
{
	private static final double CAMERA_NEAR_CLIP = 0.1;
	private static final double CAMERA_FAR_CLIP = 10000.0;
	private static final double CAMERA_INITIAL_DISTANCE = 500;
	
	private static final double CAMERA_INITIAL_Y_ANGLE = 0.0;
	private static final double CAMERA_INITIAL_X_ANGLE = -90.0;
	private static final double CAMERA_INITIAL_Z_ANGLE = 0.0;
	
	private static final double PIVOT_INITIAL_Y_ANGLE = 0.0;
	private static final double PIVOT_INITIAL_X_ANGLE = 0.0;
	private static final double PIVOT_INITIAL_Z_ANGLE = 0.0;
	
	private static final double CAMERA_SPEED = 5;
	private static final double CAMERA_ROTATION_SPEED = 0.2;
	private static final double CAMERA_ZOOM_SPEED = 50;
	
	private static final double MAX_HORIZONTAL_MOVEMENT = 1000.0;
	private static final double MAX_ROTATION_ANGLE = 90.0;
	private static final double MIN_ROTATION_ANGLE = 0.0;
	private static final double MAX_ZOOM_OUT_DISTANCE = 20000.0;
	private static final double MIN_ZOOM_IN_DISTANCE = 200.0;

	final PerspectiveCamera camera = new PerspectiveCamera(true);
	public final Transform pivot = new Transform();
	public final Transform pivot2 = new Transform();
	    
    
	public PerspectiveCamera getCamera()
	{
		return camera;
	}

	public void buildCamera(Group parent) 
	{
		resetCamera();
        
        parent.getChildren().add(pivot);
        pivot.getChildren().add(pivot2);
        pivot2.getChildren().add(camera);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        
        System.out.println("buildCamera()");
    }
	
	/**
	 * Resets the camera to the initial values.
	 */
	public void resetCamera()
	{
		pivot.rotation.setY(PIVOT_INITIAL_Y_ANGLE);
        pivot.rotation.setX(PIVOT_INITIAL_X_ANGLE);
        pivot.rotation.setZ(PIVOT_INITIAL_Z_ANGLE);
		
		pivot2.rotation.setY(CAMERA_INITIAL_Y_ANGLE);
        pivot2.rotation.setX(CAMERA_INITIAL_X_ANGLE);
        pivot2.rotation.setZ(CAMERA_INITIAL_Z_ANGLE);
        
        pivot2.position.setY(-CAMERA_INITIAL_DISTANCE);
	}
	
	/**
	 * Translate the camera on the X axis.
	 * @param horizontal A value of the delta movement in order to change the position in x.
	 */
	public void translateCamera(double horizontal)
	{
		pivot.position.setX(pivot.position.getX() + horizontal * CAMERA_SPEED);
		
		if(pivot.position.getX() > MAX_HORIZONTAL_MOVEMENT)
			pivot.position.setX(MAX_HORIZONTAL_MOVEMENT);
		
		if(pivot.position.getX() < -MAX_HORIZONTAL_MOVEMENT)
			pivot.position.setX(-MAX_HORIZONTAL_MOVEMENT);
	}
	
	/**
	 * Rotate the camera around the X axis.
	 * @param vertical A value of the delta movement in order to change the rotation in x.
	 */
	public void rotateCamera(double vertical)
	{
		pivot.rotation.setX(pivot.rotation.getX() + vertical * CAMERA_ROTATION_SPEED); 
		
		if(pivot.rotation.getX() > MAX_ROTATION_ANGLE - 90)
			pivot.rotation.setX(MAX_ROTATION_ANGLE - 90);
		
		if(pivot.rotation.getX() < MIN_ROTATION_ANGLE - 90)
			pivot.rotation.setX(MIN_ROTATION_ANGLE - 90);
	}
	
	/**
	 * Zoom the camera.
	 * @param delta A value of the delta scroll movement in order to change the distance of the camera to the x axis.
	 */
	public void zoomCamera(double delta)
	{
		pivot2.position.setY(pivot2.position.getY() + delta * CAMERA_ZOOM_SPEED);
		
		if(pivot2.position.getY() < -MAX_ZOOM_OUT_DISTANCE)
			pivot2.position.setY(-MAX_ZOOM_OUT_DISTANCE);
		
		if(pivot2.position.getY() > -MIN_ZOOM_IN_DISTANCE)
			pivot2.position.setY(-MIN_ZOOM_IN_DISTANCE);
	}
}
