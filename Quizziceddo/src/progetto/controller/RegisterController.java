package progetto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import progetto.model.UsersDB;

public class RegisterController {
    @FXML
    private PasswordField password;

    @FXML
    private Button registerbutton;
    
    @FXML
    private Button backbutton;

    @FXML
    private TextField username;

    @FXML
    void registerUser(ActionEvent event) throws Exception {
    	if(username!=null && password!=null)
    		UsersDB.createUser(username.getText().toString(), password.getText().toString());
    }

    @FXML
    void backAction(ActionEvent event) throws Exception {
    	CambiaScena.getInstance().setCurrentScene("login.fxml");
    }
}
