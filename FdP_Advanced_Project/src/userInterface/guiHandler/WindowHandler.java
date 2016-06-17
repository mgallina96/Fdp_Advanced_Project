package userInterface.guiHandler;

import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import userInterface.graphic3DHandler.View;
import userInterface.userInputsHandlers.KeyboardInputsHandler;
import userInterface.userInputsHandlers.MouseInputsHandler;

/**
 * @author Manuel Gallina
 */
public class WindowHandler 
{
	private static final String WINDOW_TITLE = "Laparoscopy";
	private static final double MAIN_WINDOW_WIDTH_RESOLUTION = 1600;
	private static final double MAIN_WINDOW_HEIGHT_RESOLUTION = 900;
	
	/**
	 * The width of the 3d view window measured in pixels
	 */
	public static final double SCENE3D_WIDTH_RESOLUTION = MAIN_WINDOW_WIDTH_RESOLUTION * 2/3;
	/**
	 * The height of the 3d view window measured in pixels
	 */
	public static final double SCENE3D_HEIGHT_RESOLUTION = MAIN_WINDOW_HEIGHT_RESOLUTION;
	
	private static final String INFO_BOX_TITLE = "Info paziente";
	private static final double INFO_BOX_WIDTH_RESOLUTION = MAIN_WINDOW_WIDTH_RESOLUTION - SCENE3D_WIDTH_RESOLUTION;
	private static final double INFO_BOX_HEIGHT_RESOLUTION = MAIN_WINDOW_HEIGHT_RESOLUTION;
	
	private HBox root;
	private Scene scene;
	
	/**
	 * Constructor for the WindowHandler class.
	 * 
	 * It creates a window for the application.
	 * 
	 * @param window the main stage of the application
	 * @param patientInfo the list of attributes of the patient
	 * @param scene3D the subscene containing the 3d view
	 * @param keyboard the keyboard input handler
	 * @param mouse the mouse input handler
	 * @param camera the point of view of the 3d window
	 */
	public WindowHandler(Stage window, String[] patientInfo, SubScene scene3D, KeyboardInputsHandler keyboard, 
								MouseInputsHandler mouse, View camera)
	{
		this.root = new HBox();
		
		VBox lateralWindow = lateralWindow(patientInfo);
		lateralWindow.setId("lateralWindow");
		
//		scene3D.setWidth(SCENE3D_WIDTH_RESOLUTION);
//		scene3D.setWidth(SCENE3D_HEIGHT_RESOLUTION);
		
		root.getChildren().addAll(scene3D, lateralWindow);
		
		this.scene = new Scene(root, MAIN_WINDOW_WIDTH_RESOLUTION, MAIN_WINDOW_HEIGHT_RESOLUTION);
		this.scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		//Handle user inputs
		
		keyboard.handleKeyboard(scene);
	    mouse.handleMouse(scene, camera);
	    
	    ////////////////////
		
		window.setScene(scene);
		window.setTitle(WINDOW_TITLE);
		window.show();
	}
	
	/*
	 * This method creates the lateral window with the patient's info box and the selection of the tools.
	 */
	private VBox lateralWindow(String[] patientInfo)
	{
		VBox box = new VBox();
		VBox infoBox = infoBox(patientInfo);
		
		box.setPrefSize(INFO_BOX_WIDTH_RESOLUTION, INFO_BOX_HEIGHT_RESOLUTION);
		
		box.getChildren().add(infoBox);
		
		return box;
	}
	
	/*
	 * This method creates the patient's info box.
	 */
	private VBox infoBox(String[] patientInfo)
	{
		VBox box = new VBox();
		
		Label title = new Label(INFO_BOX_TITLE);
		title.setId("infoBoxTitle");
		
		GridPane grid = new GridPane();
		Label[] attributes = new Label[patientInfo.length];
		
		for(int i = 0; i < patientInfo.length; i++)
		{
			attributes[i] = new Label(patientInfo[i]);
			attributes[i].setId("infoBoxLabels");
			
			grid.add(attributes[i], 0, i);
		}
		
		box.getChildren().add(title);
		box.getChildren().add(grid);
		
		return box;
	}
	
}