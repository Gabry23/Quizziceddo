package progetto.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class ConnectionDB{
private static Connection conn=null;	
private static String url="jdbc:sqlite:progetto.db";


public static Connection connect() {       
        try {
            conn = DriverManager.getConnection(url);            
            return conn;
        } catch (SQLException e) {
        	Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Connessione al Database non riuscita");
            alert.setContentText("Per favore, prova a riavviare il gioco");
            alert.showAndWait();
            return null;
        } 
    }

public static void closeConnection(Connection conn) {
	try {
        if (conn != null) {
            conn.close();
        }
    } 
	catch (SQLException ex) {
		Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Connessione al Database non interrotta");
        alert.setContentText("Per favore, riprova");
        alert.showAndWait();
    }
}
}
