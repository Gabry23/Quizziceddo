package progetto.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import progetto.model.UsersDB;

public class OptionMenuController {
	  
@FXML
private ToggleButton togglesound;

@FXML
private Button backbutton;

@FXML
private Button changeusernamebutton;

@FXML
private Button changeimagebutton;

@FXML
private Button createbutton;

@FXML
private Button feedbackbutton;
	    
private boolean music=true;

@FXML
void changeimageAction(ActionEvent event) throws SQLException, IOException {
	if(!UsersDB.isGuest())
	  UsersDB.changeImage();
	    else 
	      getErrorGuest();
	    	
}

@FXML
void changeusernameAction(ActionEvent event) throws Exception {
	if(!UsersDB.isGuest())
	  CambiaScena.getInstance().setCurrentScene("changeUser.fxml");
	    else
	     getErrorGuest();
}

@FXML
void createAction(ActionEvent event) throws Exception {
	CambiaScena.getInstance().setCurrentScene("createquestion.fxml");
}

@FXML
void sendFeedbackAction(ActionEvent event) throws Exception {
	CambiaScena.getInstance().setCurrentScene("feedback.fxml");
}

@FXML
void soundAction(ActionEvent event) throws MalformedURLException {
	if(music) {
	    music=false;
	    	SoundController.getInstance().stopByUser(true);
	    	SoundController.getInstance().stopSound();
    }
       else{
	    music=true;
	    	SoundController.getInstance().stopByUser(false);
		    SoundController.getInstance().playSound();
	   }
}

@FXML
void backAction(ActionEvent event) throws Exception {
	CambiaScena.getInstance().setCurrentScene("firstmenu.fxml");
}
	    
public void getErrorGuest() {
	Alert error = new Alert(AlertType.ERROR);
    error.setTitle("ERRORE");
    error.setHeaderText("I Guest non possono modificare Immagine e/o Username!");
    error.setContentText("Per favore, scegli un'altra opzione");
    error.showAndWait();
	}
}
