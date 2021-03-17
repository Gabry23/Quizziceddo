package progetto.controller;

import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXMLLoader;

public class CambiaScena {
    private Scene scene;
    private Stage stage;
    
    private static CambiaScena instance = null;
    
    private CambiaScena() {        
    }
    
    public void init(Stage primaryStage) throws Exception {        
        stage = primaryStage;
        stage.initStyle(StageStyle.UNDECORATED);
        initScene("login.fxml");
        stage.setScene(scene);
        stage.setTitle("QUIZZICEDDO");
        stage.setResizable(false);
        stage.show();
    }
    
    public static CambiaScena getInstance() {
        if(instance == null)
            instance = new CambiaScena();
        return instance;
    }        
    
    public void setLoginScene() throws Exception {
        setCurrentScene("login.fxml");
        stage.hide();
        stage.setResizable(false);
        stage.setWidth(720);
        stage.setHeight(405);
        stage.show();
    }
    
    public void setMenuScene() throws Exception {
        setCurrentScene("firstmenu.fxml");
        stage.hide();
        stage.setResizable(true);
        stage.setWidth(1067);
        stage.setHeight(600);
        stage.show();
    }
    
    public void setCurrentScene(String filename) throws Exception {        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/progetto/view/"+filename));
        Parent root = (Parent) loader.load();
        scene.setRoot(root);
    }
    
    private void initScene(String filename) throws Exception {        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/progetto/view/"+filename));
        Parent root = (Parent) loader.load();
        scene = new Scene(root, 720, 405);        
    }
    
    public Stage getStage() {
    	return stage;
    }
   
}
