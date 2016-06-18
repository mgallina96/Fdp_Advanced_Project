package userInterface.guiHandler;

import content.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
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
	
	private static final double SEPARATOR_MAX_WIDTH = INFO_BOX_WIDTH_RESOLUTION -20;
	
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
	public WindowHandler(Stage window, Patient patient, SubScene scene3D, KeyboardInputsHandler keyboard, 
								MouseInputsHandler mouse, View camera)
	{
		this.root = new HBox();
		
		VBox lateralWindow = lateralWindow(patient);
		lateralWindow.setId("lateralWindow");
		
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
	private VBox lateralWindow(Patient patient)
	{
		VBox box = new VBox();
			box.setSpacing(10);
		
		VBox infoBox = infoBox(patient);
			infoBox.setId("infoBox");
				
		Separator separator = new Separator();
			separator.setId("separator");
			separator.setOrientation(Orientation.HORIZONTAL);
			separator.setMaxWidth(SEPARATOR_MAX_WIDTH);
			separator.setHalignment(HPos.CENTER);
			separator.setValignment(VPos.CENTER);
		
		box.setPrefSize(INFO_BOX_WIDTH_RESOLUTION, INFO_BOX_HEIGHT_RESOLUTION);
		
		VBox toolSelection = toolSelection();
			toolSelection.setId("toolSelection");
		
		box.getChildren().addAll(infoBox, separator, toolSelection);
		
		return box;
	}
	
	/*
	 * This method creates the patient's info box.
	 */
	private VBox infoBox(Patient patient)
	{
		VBox box = new VBox();
		
		Label title = new Label(INFO_BOX_TITLE);
		
		GridPane grid = new GridPane();
			grid.setId("infoBoxGrid");
		
		Label[] attributes = new Label[patient.getPatientAttributes().length];
		Label[] data = new Label[patient.getPatientInfo().length];
		
		for(int i = 0; i < patient.getPatientAttributes().length; i++)
		{
			attributes[i] = new Label(patient.getPatientAttributes()[i]);
			
			data[i] = new Label(patient.getPatientInfo()[i]);
			
			grid.add(attributes[i], 0, i);
			grid.add(data[i], 1, i);
		}
		
		box.getChildren().addAll(title, grid);
		
		return box;
	}

	private VBox toolSelection() 
	{
		VBox box = new VBox();
	
		Label title = new Label(INFO_BOX_TITLE);
	
		GridPane grid = new GridPane();
			grid.setId("infoBoxGrid");
	
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Option 1",
			        "Option 2",
			        "Option 3"
			    );

		
		ComboBox<String> tool1 = new ComboBox<String>(options);
		ComboBox<String> tool2 = new ComboBox<String>(options);
		
		grid.add(tool1, 0, 0);
		grid.add(tool2, 0, 1);
		
		box.getChildren().addAll(title, grid);
		
		return box;
	}
	
}