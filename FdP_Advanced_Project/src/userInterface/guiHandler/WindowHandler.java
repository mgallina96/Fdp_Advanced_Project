package userInterface.guiHandler;

import content.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
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

	private static final double MAIN_WINDOW_WIDTH_RESOLUTION = 1440;
	private static final double MAIN_WINDOW_HEIGHT_RESOLUTION = 900;

	private static final String INFO_BOX_TITLE = "Info paziente";
	
	/**
	 * The width of the 3d view window measured in pixels
	 */
	public static final double SCENE3D_WIDTH_RESOLUTION = MAIN_WINDOW_WIDTH_RESOLUTION * 2/3;
	/**
	 * The height of the 3d view window measured in pixels
	 */
	public static final double SCENE3D_HEIGHT_RESOLUTION = MAIN_WINDOW_HEIGHT_RESOLUTION;
	
	private static final double INFO_BOX_WIDTH_RESOLUTION = MAIN_WINDOW_WIDTH_RESOLUTION - SCENE3D_WIDTH_RESOLUTION;
	private static final double INFO_BOX_HEIGHT_RESOLUTION = MAIN_WINDOW_HEIGHT_RESOLUTION;
	
	private static final double INFO_BOX_GRID_VGAP = 8;
	private static final double INFO_BOX_GRID_HGAP = INFO_BOX_WIDTH_RESOLUTION * 2 / 3 - 10;
		
	private static final double SEPARATOR_MAX_WIDTH = INFO_BOX_WIDTH_RESOLUTION -20;
	
	private static final String TOOL_SELECTION_TITLE = "Selezione strumento";
	
	private static final double TOOL_GRID_HGAP = INFO_BOX_WIDTH_RESOLUTION / 4 + 20;
	private static final double TOOL_GRID_VGAP = 0;
	
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
		
		Separator separator2 = new Separator();
			separator2.setId("separator");
			separator2.setOrientation(Orientation.HORIZONTAL);
			separator2.setMaxWidth(SEPARATOR_MAX_WIDTH);
			separator2.setHalignment(HPos.CENTER);
			separator2.setValignment(VPos.CENTER);
			
			
		HBox buttonBox = new HBox();
			buttonBox.setSpacing(30);
			buttonBox.setAlignment(Pos.BASELINE_CENTER);
		Button start = new Button("Start");
		Button stop = new Button("Stop");
		
		buttonBox.getChildren().addAll(start, stop);
			
		box.getChildren().addAll(infoBox, separator, toolSelection, separator2, buttonBox);
		
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
			grid.setHgap(INFO_BOX_GRID_HGAP);
			grid.setVgap(INFO_BOX_GRID_VGAP);
	
		
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
	
		Label title = new Label(TOOL_SELECTION_TITLE);
	
		GridPane grid = new GridPane();
			grid.setId("toolSelectionGrid");
			grid.setHgap(TOOL_GRID_HGAP);
			grid.setVgap(TOOL_GRID_VGAP);
	
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Pinza",
			        "Bisturi",
			        "Aspiratore"
			    );

		
		ComboBox<String> tool1 = new ComboBox<String>(options);
			tool1.setId("tool");
		ComboBox<String> tool2 = new ComboBox<String>(options);
			tool2.setId("tool");
		
		Label selection1 = new Label("Strumento dx");
		Label selection2 = new Label("Strumento sx");
		
		grid.add(selection1, 0, 0);
		grid.add(selection2, 0, 1);
		grid.add(tool1, 1, 0);
		grid.add(tool2, 1, 1);
		
		box.getChildren().addAll(title, grid);
		
		return box;
	}
	
}