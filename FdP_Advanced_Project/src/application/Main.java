package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.paint.Paint;

import userInterface.guiHandler.WindowHandler;
import userInterface.graphic3DHandler.Axis;
import userInterface.graphic3DHandler.Transform;
import userInterface.graphic3DHandler.View;
import userInterface.userInputsHandlers.KeyboardInputsHandler;
import userInterface.userInputsHandlers.MouseInputsHandler;
import content.Patient;

public class Main extends Application 
{
	@Override
	public void start(Stage window) 
    {
		try 
		{
			final Group root = new Group();
		    final Transform world = new Transform();
		    final Transform axis = new Transform();
		    
		    Axis axisMesh = new Axis(axis);
		    
		    final View camera = new View(world);
		    final SubScene scene = new SubScene(root, WindowHandler.SCENE3D_WIDTH_RESOLUTION, WindowHandler.SCENE3D_HEIGHT_RESOLUTION, true, SceneAntialiasing.BALANCED);
		    final KeyboardInputsHandler keyboard = new KeyboardInputsHandler(world);
		    final MouseInputsHandler mouse = new MouseInputsHandler();
		    
		    root.getChildren().add(world);
	        world.getChildren().addAll(axisMesh.getParent());
			
		    scene.setCamera(camera.getCamera());
			scene.setFill(Paint.valueOf("white"));
	        
			//Provvisorio per test gui
			
	        String[] infoTest = new String[]{"Nome", "Cognome","Età", "Altezza", "Peso"};
	        String[] dataTest = new String[]{"Mario", "Rossi","50", "1.70", "65"};
	        
	        Patient patient = new Patient(infoTest, dataTest);
	        
	        //////////////////////////////
	        
	        new WindowHandler(window, patient, scene, keyboard, mouse, camera);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			
		}
	}

	@Override	
	public void stop()
	{
		Platform.exit();
	}
	
    public static void main(String[] args) 
    {
		launch(args);
	}
}
