package progetto.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import progetto.model.User;
import progetto.model.UsersDB;

public class HighscoreMenuController{

    @FXML
    private ImageView img4;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img6;

    @FXML
    private ImageView img5;

    @FXML
    private ImageView img8;

    @FXML
    private Button backbutton;

    @FXML
    private ImageView img7;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private Label label6;

    @FXML
    private Label label7;

    @FXML
    private Label label8;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img1;
    
    private ArrayList<User> users;
    
    private ArrayList<ImageView> images;
    
    private ArrayList<Label> labels;

    @FXML
    void backAction(ActionEvent event) throws Exception {
    	CambiaScena.getInstance().setCurrentScene("firstmenu.fxml");
    }
    
    public void loadImages() {
    	images=new ArrayList<ImageView>();
    	
    	images.add(img1);
    	images.add(img2);
    	images.add(img3);
    	images.add(img4);
    	images.add(img5);
    	images.add(img6);
    	images.add(img7);
    	images.add(img8);
    }
    
    public void loadLabels() {
    	labels=new ArrayList<Label>();
    	
    	labels.add(label1);
    	labels.add(label2);
    	labels.add(label3);
    	labels.add(label4);
    	labels.add(label5);
    	labels.add(label6);
    	labels.add(label7);
    	labels.add(label8);
    }
  
	public void initialize() throws SQLException {
		users=new ArrayList<User>();
		
		users=UsersDB.getMax();
		loadImages();
		loadLabels();
		
	    for(int i=0;i<users.size();i++) {
		images.get(i).setImage(new Image(users.get(i).getImage()));
		labels.get(i).setText((i+1)+".   "+users.get(i).getUsername()+"   "+users.get(i).getPoints()+" punti");
	    }
	    UsersDB.eraseList();
	}

}
