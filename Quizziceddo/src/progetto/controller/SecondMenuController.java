package progetto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import progetto.model.UsersDB;

public class SecondMenuController{
	 @FXML
	 private Label userviewer;

	 @FXML
	 private Button backbutton;

	 @FXML
	 private Button complete;

	 @FXML
	 private Label levelviewer;

	 @FXML
	 private Button quizziceddobutton;

	 @FXML
	 private Button timebutton;
	    
	 @FXML
	 private Label levellabel;

    @FXML
    void timeAction(ActionEvent event) throws Exception {
    	CambiaScena.getInstance().setCurrentScene("quiz1menu.fxml");
    }

    @FXML
    void completeAction(ActionEvent event) throws Exception {
    	CambiaScena.getInstance().setCurrentScene("quiz2menu.fxml");
    }

    @FXML
    void quizziceddoAction(ActionEvent event) throws Exception {
    	SoundController.getInstance().stopSound();
    	QuizziceddoController.info();
    	QuizziceddoController.Game();
    }
    
    @FXML
    void backAction(ActionEvent event) throws Exception {
    	CambiaScena.getInstance().setCurrentScene("firstmenu.fxml");
    }

	public void initialize() {
		if(UsersDB.stateConnected())
		{
		if(!UsersDB.isGuest()) {	
		userviewer.setText(UsersDB.getUsername());
		levelviewer.setText(UsersDB.getLevel().toString());
		}
		else {
        userviewer.setText("Guest");
        levellabel.setText("");
        }
		if(!SoundController.getInstance().isStoppedByUser())
			SoundController.getInstance().playSound();
		}
	}
}
