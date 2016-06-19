package userInterface.guiHandler;

import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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

	private static final int MAIN_WINDOW_WIDTH = 900;
	private static final int MAIN_WINDOW_HEIGHT = 600;
	private static final String INFO_BOX_TITLE = "Info paziente";
	
	private BorderPane root;
	private Scene scene;
	
	/**
	 * Constructor for the WindowHandler class.
	 * 
	 * It creates a window for the application.
	 * 
	 * @param window the primaryStage of the application
	 */
	public WindowHandler(Stage window, String[] patientInfo, SubScene scene3D, KeyboardInputsHandler keyboard, MouseInputsHandler mouse, View camera)
	{
		this.root = new BorderPane();
		
		VBox lateralWindow = lateralWindow(patientInfo);
		lateralWindow.setId("lateralWindow");
		
		root.setRight(lateralWindow);
		root.setLeft(scene3D);
		
		this.scene = new Scene(root, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
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