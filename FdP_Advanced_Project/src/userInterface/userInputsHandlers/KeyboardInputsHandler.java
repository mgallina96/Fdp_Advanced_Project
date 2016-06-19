package userInterface.userInputsHandlers;

import content.Arm;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;
import userInterface.graphic3DHandler.Transform;

/**
 * @author Michele Franceschetti
 */

public class KeyboardInputsHandler
{
	private static final int DEFAULT_VALUE = 0;
	
	//Left arm
	private int leftXAxis;
	private int leftYAxis;
	private int leftZAxis;
	
	//Right arm
	private int rightXAxis;
	private int rightYAxis;
	private int rightZAxis;
	
	//Other commands
	
	private boolean escape;
	private boolean reset;
	
	Translate leftTarget = new Translate(0,100,0);
	Translate rightTarget = new Translate(0,100,0);
	
	Arm leftArm = new Arm(leftTarget, 100, 100);
	Arm rightArm = new Arm(rightTarget, 100, 100);
	
	Sphere point = new Sphere(10);
	
	/**
	 * @return the leftXAxis
	 */
	public int getLeftXAxis() 
	{
		return leftXAxis;
	}

	/**
	 * @return the leftYAxis
	 */
	public int getLeftYAxis() 
	{
		return leftYAxis;
	}

	/**
	 * @return the leftZAxis
	 */
	public int getLeftZAxis() 
	{
		return leftZAxis;
	}

	/**
	 * @return the rightXAxis
	 */
	public int getRightXAxis()
	{
		return rightXAxis;
	}

	/**
	 * @return the rightYAxis
	 */
	public int getRightYAxis() 
	{
		return rightYAxis;
	}

	/**
	 * @return the rightZAxis
	 */
	public int getRightZAxis() 
	{
		return rightZAxis;
	}

	/**
	 * @return if the key 'escape' is pressed.
	 */
	public boolean isEscape()
	{
		return escape;
	}
	
	/**
	 * @return if the reset key is pressed.
	 */
	public boolean isReset() 
	{
		return reset;
	}

	/**
	 * Default constructor.
	 */
	public KeyboardInputsHandler(Transform parent)
	{
		reset();
		
		parent.getChildren().add(leftArm.upperArm);
		parent.getChildren().add(rightArm.upperArm);
		parent.getChildren().add(point);
	}
	
	public void handleKeyboard(Scene scene) 
	{
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) 
            {
                switch (event.getCode()) {
                    case ESCAPE:
                    	escape = true;
                    	System.out.println("escape");
                        break;
                    case R:
                    	reset = true;
                    	System.out.println("reset");
                        break;
                    case W:
                    	leftYAxis = 1;
                    	System.out.println("W");
                        break;
                    case A:
                    	leftXAxis = -1;
                    	System.out.println("A");
                        break;
                    case S:
                    	leftYAxis = -1;
                    	System.out.println("S");
                        break;
                    case D:
                    	leftXAxis = 1;
                    	System.out.println("D");
                        break;
                    case Q:
                    	leftZAxis = -1;
                    	break;
                    case E:
                    	leftZAxis = 1;
                    	break;
                    case I:
                    	rightYAxis = 1;
                        break;
                    case J:
                    	rightXAxis = -1;
                        break;
                    case K:
                    	rightYAxis = -1;
                        break;
                    case L:
                    	rightXAxis = 1;
                        break;
                    case U:
                    	rightZAxis = -1;
                    	break;
                    case O:
                    	rightZAxis = 1;
                    	break;
                    default:
                    	event.consume();
                    	reset();
                    	break;
                }
                
                leftArm.moveTarget(leftXAxis, leftYAxis, leftZAxis);
                rightArm.moveTarget(rightXAxis, rightYAxis, rightZAxis);
                
                point.setTranslateX(leftArm.getTarget().getX());
                point.setTranslateY(leftArm.getTarget().getY());
                point.setTranslateZ(leftArm.getTarget().getZ());
                
                reset();
            }
        });
    }
	
	private void reset()
	{
		leftXAxis = DEFAULT_VALUE;
		leftYAxis = DEFAULT_VALUE;
		leftZAxis = DEFAULT_VALUE;
		
		rightXAxis = DEFAULT_VALUE;
		rightYAxis = DEFAULT_VALUE;
		rightZAxis = DEFAULT_VALUE;
		
		escape = false;
		reset = false;
	}
}
