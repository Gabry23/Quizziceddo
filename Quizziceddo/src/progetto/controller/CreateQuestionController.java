package progetto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import progetto.model.UsersDB;

public class CreateQuestionController {

    @FXML
    private TextArea questionarea;

    @FXML
    private TextField correct;

    @FXML
    private Button backbutton;

    @FXML
    private TextField option3;

    @FXML
    private TextField option4;

    @FXML
    private TextField option1;

    @FXML
    private Button sendbutton;

    @FXML
    private TextField option2;

    @FXML
    void backAction(ActionEvent event) throws Exception {
    	CambiaScena.getInstance().setCurrentScene("optionmenu.fxml");
    }

    @FXML
    void sendQuestion(ActionEvent event) throws Exception {
    	if(!questionarea.getText().equals("") && !option1.getText().equals("") && !option2.getText().equals("") && !option3.getText().equals("") && !option4.getText().equals("") && !correct.getText().equals("")) {
    		UsersDB.sendQuestion(questionarea.getText(),correct.getText(),option1.getText(),option2.getText(),option3.getText(),option4.getText());    
    		Alert info = new Alert(AlertType.INFORMATION);
    		info.setTitle("GRAZIE");
    		info.setHeaderText("La domanda è stata spedita agli sviluppatori, che la controlleranno il prima possibile, grazie per il tuo supporto!");
   
    	    info.showAndWait();
    	    CambiaScena.getInstance().setCurrentScene("optionmenu.fxml");
    	}
    	else {
    		Alert error = new Alert(AlertType.ERROR);
            error.setTitle("ATTENZIONE!");
            error.setHeaderText("Tutti i campi devono essere riempiti!");
            error.setContentText("Per favore, completa tutta la domanda");
            error.showAndWait();
    	}
    }

}
