package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import userInterface.guiHandler.WindowHandler;

public class Main extends Application 
{
	@Override
	public void start(Stage primaryStage) 
	{
		String[] infoTest = new String[]{"Nome", "Cognome","Età", "Altezza", "Peso"};
		new WindowHandler(primaryStage, infoTest);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
