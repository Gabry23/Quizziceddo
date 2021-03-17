package progetto.controller;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import progetto.model.UsersDB;

public class LoginController{
    @FXML
    private Button Guest;

    @FXML
    private Label lbl;

    @FXML
    private ImageView imgvw;
    
    @FXML
    private Button exitbutton;
    
    @FXML
    private Button loginbutton;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField username;

    @FXML
    private Button register;

    @FXML
    void PasswordAction(ActionEvent event) throws SQLException, Exception {
    	if(!Password.getText().equals("") && !username.getText().equals(""))
    		login();
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Username o Password vuoti");
            alert.setContentText("Per favore, riempi tutti i campi!");
            alert.showAndWait();
    	}
    }

    @FXML
    void UsernameAction(ActionEvent event) throws SQLException, Exception {
    	if(!Password.getText().equals("") && !username.getText().equals(""))
    		login();
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Username o Password vuoti");
            alert.setContentText("Per favore, riempi tutti i campi!");
            alert.showAndWait();
    	}
    }

    @FXML
    void registerAction(ActionEvent event) throws Exception {
    	CambiaScena.getInstance().setCurrentScene("registermenu.fxml");
    }
    @FXML
    void guestAction(ActionEvent event) throws Exception {
    	UsersDB.Guest(true);
    	CambiaScena.getInstance().setMenuScene();
    }
    
    @FXML
    void loginAction(ActionEvent event) throws Exception {
    	login();
    	
    }
    
    void login() throws SQLException, Exception {
    	if(username!=null && Password!=null && UsersDB.stateConnected())
    	{
        	if(UsersDB.checkUser(username.getText().toString(), Password.getText().toString()))
    	     CambiaScena.getInstance().setMenuScene();
        	else
        	{
        		Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setHeaderText("Username o Password Errata");
                alert.setContentText("Prova ancora");
                alert.showAndWait();
        	}
        }
    }
    
    @FXML
    void exitAction(ActionEvent event) {
    	System.exit(0);
    }

	public void initialize() {
	 UsersDB.isConnected();
	}

}
