package userInterface.guiHandler;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Manuel Gallina
 */
public class WindowHandler 
{
	private static final int MAIN_WINDOW_WIDTH = 1280;
	private static final int MAIN_WINDOW_HEIGHT = 768;
	private static final String INFO_BOX_TITLE = "Info paziente";
	
	private BorderPane root;
	private Scene scene;
	
	/**
	 * Constructor for the WindowHandler class.
	 * 
	 * It creates a window for the application.
	 * 
	 * @param primaryStage the primaryStage of the application
	 */
	public WindowHandler(Stage primaryStage, String[] patientInfo)
	{
		this.root = new BorderPane();
		
		this.scene = new Scene(root, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
		this.scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		VBox lateralWindow = lateralWindow(patientInfo);
		lateralWindow.setId("lateralWindow");
		
		root.setRight(lateralWindow);
		
		primaryStage.setScene(scene);
		primaryStage.show();
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