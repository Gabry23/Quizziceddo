package progetto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import progetto.model.UsersDB;

public class feedbackController {

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
    void Send(ActionEvent event) throws Exception {
     if(!text.getText().equals("")) {
     UsersDB.sendFeedback(text.getText());
     Alert info = new Alert(AlertType.INFORMATION);
	 info.setTitle("GRAZIE");
	 info.setHeaderText("Il messaggio è stato mandato agli sviluppatori, grazie per il tuo feedback!");
     info.showAndWait();
     CambiaScena.getInstance().setCurrentScene("optionmenu.fxml"); 
     }
     else {
 		Alert error = new Alert(AlertType.ERROR);
        error.setTitle("ATTENZIONE!");
        error.setHeaderText("Non è possibile inviare feedback vuoti!");
        error.setContentText("Per favore, se vuoi dirci qualcosa, scrivilo nell'area di testo, grazie");
        error.showAndWait();
	}
    }

}
