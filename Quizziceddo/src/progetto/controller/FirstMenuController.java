package progetto.controller;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import progetto.model.UsersDB;

public class FirstMenuController{
	  @FXML
	    private Button playbutton;

	    @FXML
	    private Label userviewer;

	    @FXML
	    private ImageView userImage;

	    @FXML
	    private Button settingsbutton;

	    @FXML
	    private Button highscorebutton;

	    @FXML
	    private Label levelviewer;
	    
	    @FXML
	    private Label levellabel;

	    @FXML
	    private Button logoutbutton;

    @FXML
    void playAction(ActionEvent event) throws Exception {
    	CambiaScena.getInstance().setCurrentScene("secondmenu.fxml");
    }

    @FXML
    void highscoreAction(ActionEvent event) throws Exception {
    	CambiaScena.getInstance().setCurrentScene("highscore.fxml");
    }

    @FXML
    void settingsAction(ActionEvent event) throws Exception {
    	CambiaScena.getInstance().setCurrentScene("optionmenu.fxml");
    }

    @FXML
    void logoutAction(ActionEvent event) throws Exception {
    	if(!UsersDB.isGuest()) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Logging out");
    	alert.setHeaderText("Stai per uscire dal gioco");
    	alert.setContentText("Sei sicuro di volerlo fare?");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		SoundController.getInstance().stopSound();
        	CambiaScena.getInstance().setLoginScene();
    	} 
    	}
    	else {
    		UsersDB.Guest(false);
    		CambiaScena.getInstance().setLoginScene();
    	}
    }

    public void initialize() {
        if(UsersDB.stateConnected())
        {	
        if(!UsersDB.isGuest()){
         userviewer.setText(UsersDB.getUsername());
         levelviewer.setText(UsersDB.getLevel().toString());
         Image img = new Image(UsersDB.getImage());
         userImage.setImage(img);
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
