package userInterface.userInputsHandlers;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import userInterface.graphic3DHandler.View;

/**
 * @author Michele Franceschetti
 */

public class MouseInputsHandler
{
	//Mouse buttons
	
	private boolean leftMouseButton;
	private boolean rightMouseButton;
	
	//Mouse position
	
	private double[] mousePosition = new double[2];
	private double[] lastMousePosition = new double[2];
	private double[] deltaMousePosition = new double[2];
	private double mouseWheelScroll;
	
	public boolean isLeftButtonPressed()
	{
		return leftMouseButton;
	}

	public void setLeftButtonPressed(boolean leftMouseButton)
	{
		this.leftMouseButton = leftMouseButton;
	}

	public boolean isRightButtonPressed()
	{
		return rightMouseButton;
	}

	public void setRightButtonPressed(boolean rightMouseButton) 
	{
		this.rightMouseButton = rightMouseButton;
	}
	
	public double getX()
	{
		return mousePosition[0];
	}
	
	public double getY()
	{
		return mousePosition[1];
	}
	
	public double getMouseWheelScroll()
	{
		return mouseWheelScroll;
	}

	public void handleMouse(Scene scene, View camera) 
	{
        scene.setOnMousePressed(new EventHandler<MouseEvent>() 
        {
            @Override 
            public void handle(MouseEvent event) 
            {
                updateInputs(event, scene);
            }
        });
        
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() 
        {
            @Override 
            public void handle(MouseEvent event) 
            {
                updateInputs(event, scene);
                
                if(event.isMiddleButtonDown())
                {
                	camera.translateCamera(deltaMousePosition[0]);
                	camera.rotateCamera(deltaMousePosition[1]);
                }
            }
        });
        
        scene.setOnScroll(new EventHandler<ScrollEvent>() 
        {
            @Override
            public void handle(ScrollEvent event) 
            {
            	mouseWheelScroll= event.getDeltaY() / event.getMultiplierY();
                System.out.println("scale: "+ mouseWheelScroll);
                
                camera.zoomCamera(mouseWheelScroll);
            }
        });

    }
	
	private void updateInputs(MouseEvent event, Scene scene)
	{
		lastMousePosition[0] = mousePosition[0];
		lastMousePosition[1] = mousePosition[1];
		
        mousePosition[0] = scene.getWidth() / 2 - event.getSceneX();
        mousePosition[1] = event.getSceneY() - scene.getHeight() / 2;
        
        deltaMousePosition[0] = (mousePosition[0] - lastMousePosition[0]); 
        deltaMousePosition[1] = (mousePosition[1] - lastMousePosition[1]);
        
        if(event.isPrimaryButtonDown())
		{
			setLeftButtonPressed(true);
		}
        else
        {
        	setLeftButtonPressed(false);
        }
		
		if(event.isSecondaryButtonDown())
		{
			setRightButtonPressed(true);
		}
		else
		{
			setRightButtonPressed(false);
		}
	}
}
