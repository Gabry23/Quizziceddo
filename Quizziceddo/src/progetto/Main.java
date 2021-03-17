package progetto;

import javafx.application.Application;
import javafx.stage.Stage;
import progetto.controller.CambiaScena;


public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	 @Override
	    public void start(Stage primaryStage) {
	        try {
	            CambiaScena.getInstance().init(primaryStage);
	        } 
	        catch(Exception e) {            
	        }
	 }
}
