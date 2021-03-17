package progetto.controller;


import java.sql.SQLException;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import progetto.model.QuestionsDB;
import progetto.model.UsersDB;

public class FinalQuizController {

	 @FXML
	    private Button backbutton;

	    @FXML
	    private TextField answertext;

	    @FXML
	    private ImageView imagequest;

	    @FXML
	    private Label questionlabel;
	    
	    @FXML
	    private ProgressBar timebar;
	    
	    private Task<Void> t;
	    
	    private Thread th;
	    
	    public static double i;
	    
	    private boolean stillPlaying=true;
	    
	    boolean clicked = false;
	    
	    public static String result;
	    
	    public static String message;

	    @FXML
	    void backAction(ActionEvent event) throws Exception {
	    	stillPlaying=false;
	    	SoundController.getInstance().stopMusic();
	    	CambiaScena.getInstance().setCurrentScene("secondmenu.fxml");
	    }

	    @FXML
	    void checkAnswer(ActionEvent event) throws Exception {
	    	clicked=true;
	    	answertext.setEditable(false);
	    	if(answertext.getText().equalsIgnoreCase(QuestionsDB.getCorrect()))
	    	{
	    		result="RISPOSTA ESATTA";
	    	    message="Complimenti, hai indovinato!";
	    	    UsersDB.setPoints(10);
	    	}
	    	else
	    	{
	    		result="RISPOSTA SBAGLIATA";
	    		message="Peccato, andrà meglio la prossima volta!";
	    	}
	    	SoundController.getInstance().stopMusic();
	    	Alert finish = new Alert(AlertType.INFORMATION);
	        finish.setTitle(result);
	        finish.setHeaderText(null);
	        finish.setContentText(message);

	        finish.showAndWait();
	        CambiaScena.getInstance().setCurrentScene("secondmenu.fxml");
	    }
	    
	    void loadQuestion(int ind) {
	    	questionlabel.setText("INDOVINA CHI?");
	    	Image img=new Image(QuestionsDB.getImage());
	        imagequest.setImage(img);
	        GaussianBlur blur=new GaussianBlur();
	        blur.setRadius(63.0);
	        imagequest.setEffect(blur);
	    }

	
		public void initialize() {
			if(QuestionsDB.Connect())
	            try {
	                QuestionsDB.createQuestionFinal();
	            } catch (SQLException e) {
	            	e.printStackTrace();
	            }
	        loadQuestion(0);
	        answertext.setEditable(false);
	        i=1.0;
	        stillPlaying=true;
	        Alert ready = new Alert(AlertType.INFORMATION);
            ready.setTitle("E' tutto pronto");
            ready.setHeaderText(null);
            ready.setContentText("Tieniti pronto! Il gioco finale comincerà tra pochi secondi!");
            ready.show();
	        Timeline timeline=new Timeline(new KeyFrame(Duration.seconds(5), e->{
	        	ready.hide();
	        	GaussianBlur blur=new GaussianBlur();
		        blur.setRadius(63.0-QuizziceddoController.getPoints());
		        imagequest.setEffect(blur);
		        SoundController.getInstance().newSong("/progetto/resources/Music/final.mp3");
	            startTask();
	    }));
	 timeline.play();
	        
		}
		
		public void startTask() {
			t=new Task<Void>() {
			@Override
			protected Void call(){
				if(stillPlaying)
				{
				answertext.setEditable(true);
				SoundController.getInstance().playMusic();
				for(i=1.0;i>=0;)
		    			try {
		    			Thread.sleep(10);
		    			timebar.setProgress(i);
		    			if(!clicked)
		    			i-=0.00033;
		    			else
					     return null;
		    			}
		    		catch(InterruptedException e){
		    			System.out.println("InterruptedException");
		    		}
		    		}
		    		if(!clicked && stillPlaying)
		    		Platform.runLater(()->{
		    			try {
							outOfTime();
						} catch (SQLException e) {
							System.out.println("ErrorOutOfTime");
						}
		    			});
				return null;
			}
			};
			
    	th=new Thread(t);
		th.setDaemon(true);
		th.start();
    	}
		
		 void outOfTime() throws SQLException {
			 SoundController.getInstance().stopMusic();
			 Alert timeout = new Alert(AlertType.INFORMATION);
			 timeout.setTitle("Tempo Scaduto");
			 timeout.setHeaderText(null);
			 timeout.setContentText("Spiacente, hai esaurito il tempo a tua disposizione! Ritenta!");
			 timeout.showAndWait();
			 try {
				CambiaScena.getInstance().setCurrentScene("secondmenu.fxml");
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }

}
