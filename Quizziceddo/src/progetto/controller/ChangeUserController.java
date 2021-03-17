package progetto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import progetto.model.UsersDB;

public class ChangeUserController {

@FXML
private Button applyButton;

@FXML
private Button backbutton;

@FXML
private Label label;

@FXML
private TextArea text;

@FXML
void backAction(ActionEvent event) throws Exception {
 CambiaScena.getInstance().setCurrentScene("optionmenu.fxml");    
}

@FXML
void Apply(ActionEvent event) throws Exception {
if(UsersDB.existUsers(text.getText()))
{
	Alert error = new Alert(AlertType.WARNING);
	error.setTitle("Username già esistente!");
	error.setHeaderText("Siamo spiacenti, l'username che hai scelto appartiene già ad un altro utente...");
	error.setContentText("Per favore, scegline uno diverso");

	error.showAndWait(); 
	text.setText("");
}
   else 
	CambiaScena.getInstance().setCurrentScene("optionmenu.fxml");    
}


}   
