package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import userInterface.guiHandler.WindowHandler;
import java.util.ArrayList;

import content.Patient;
import javafx.application.Platform;
import userInterface.graphic3DHandler.Transform;
import userInterface.graphic3DHandler.View;
import userInterface.userInputsHandlers.KeyboardInputsHandler;
import userInterface.userInputsHandlers.MouseInputsHandler;
import javafx.scene.Group;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Main extends Application 
{
	private static final double AXES_RADIUS = 1;

	private static final double AXES_LENGTH = 250;

	final Group root = new Group();
    final Transform world = new Transform();
    final Transform axis = new Transform();
    
    final View camera = new View(world);
    final SubScene scene = new SubScene(root, WindowHandler.SCENE3D_WIDTH_RESOLUTION,
    										WindowHandler.SCENE3D_HEIGHT_RESOLUTION, true, SceneAntialiasing.BALANCED);
    final KeyboardInputsHandler keyboard = new KeyboardInputsHandler(world);
    final MouseInputsHandler mouse = new MouseInputsHandler();
    
    private static final ArrayList<Core> threads = new ArrayList<Core>();

    @Override
	public void start(Stage window) 
    {
		try 
		{
			root.getChildren().add(world);
	        
	        //////////////////////////Awake
	        
	        if(threads.size() > 0)
	        {
	        	for(Core thread : threads)
		        {
		        	thread.awake();
		        }
	        }
	        
	        //////////////////////////
	        
			buildAxes();
			
		    scene.setCamera(camera.getCamera());
			scene.setFill(Paint.valueOf("white"));
	        
			//Provvisorio per test gui
			
	        String[] infoTest = new String[]{"Nome", "Cognome","Et�", "Altezza", "Peso"};
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
			//////////////////////////Terminate
			
			if(threads.size() > 0)
			{
				for(Core thread : threads)
				{
					thread.terminate();
				}
			}
			
			//////////////////////////
		}
	}

	@Override	
	public void stop()
	{
		Platform.exit();
	}
	
    private void buildAxes() 
    {
        System.out.println("buildAxes()");
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.RED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.GREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.BLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final Box xAxis = new Box(AXES_LENGTH, AXES_RADIUS, AXES_RADIUS);
        final Box yAxis = new Box(AXES_RADIUS, AXES_LENGTH, AXES_RADIUS);
        final Box zAxis = new Box(AXES_RADIUS, AXES_RADIUS, AXES_LENGTH);
        
        final Box xAxisEnd = new Box(AXES_RADIUS * 2, AXES_RADIUS * 2, AXES_RADIUS * 2);
        final Box yAxisEnd = new Box(AXES_RADIUS * 2, AXES_RADIUS * 2, AXES_RADIUS * 2);
        final Box zAxisEnd = new Box(AXES_RADIUS * 2, AXES_RADIUS * 2, AXES_RADIUS * 2);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        xAxisEnd.setMaterial(redMaterial);
        yAxisEnd.setMaterial(greenMaterial);
        zAxisEnd.setMaterial(blueMaterial);
        
        xAxisEnd.setTranslateX(-AXES_LENGTH / 2);
        yAxisEnd.setTranslateY(-AXES_LENGTH / 2);
        zAxisEnd.setTranslateZ(-AXES_LENGTH / 2);
        
        axis.getChildren().addAll(xAxis, yAxis, zAxis, xAxisEnd, yAxisEnd, zAxisEnd);
        axis.setVisible(true);
        world.getChildren().addAll(axis);
    }
    
    public static void main(String[] args) 
    {
		launch(args);
	}
}
