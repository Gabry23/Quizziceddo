package progetto.controller;

import java.sql.SQLException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import progetto.model.QuestionsDB;

public class MusicQuizController {

    @FXML
    private Button backbutton;
    
    @FXML
    private Label checklabel;

    @FXML
    private ImageView imgans4;

    @FXML
    private ImageView imgans3;

    @FXML
    private ImageView imgans2;

    @FXML
    private ImageView imgans1;

    @FXML
    private Label questionlabel;

    @FXML
    private ProgressBar pointsbar;

    @FXML
    void Click1(MouseEvent event) {
    if(QuestionsDB.getImage1().equals(QuestionsDB.getCorrect())) {
    	checklabel.setText("RISPOSTA CORRETTA");
		checklabel.setTextFill(Color.GREEN);
    	checklabel.setOpacity(1.0);
    	QuizziceddoController.increasePoints();
    }
    else {
    	checklabel.setText("RISPOSTA ERRATA");
    	checklabel.setTextFill(Color.RED);
    	checklabel.setOpacity(1.0);
    }

    Timeline timeline=new Timeline(new KeyFrame(Duration.seconds(1), e->{
        try {
            nextQuestion();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
}));
timeline.play();
     
    }

    @FXML
    void Click2(MouseEvent event) {
    	if(QuestionsDB.getImage2().equals(QuestionsDB.getCorrect())) {
    		checklabel.setText("RISPOSTA CORRETTA");
    		checklabel.setTextFill(Color.GREEN);
        	checklabel.setOpacity(1.0);
        	QuizziceddoController.increasePoints();
        }
        else {
        	checklabel.setText("RISPOSTA ERRATA");
        	checklabel.setTextFill(Color.RED);
        	checklabel.setOpacity(1.0);
        }
    	

    	Timeline timeline=new Timeline(new KeyFrame(Duration.seconds(1), e->{
            try {
                nextQuestion();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
    }));
 timeline.play();
    }

    @FXML
    void Click3(MouseEvent event) {
    	if(QuestionsDB.getImage3().equals(QuestionsDB.getCorrect())) {
    		checklabel.setText("RISPOSTA CORRETTA");
    		checklabel.setTextFill(Color.GREEN);
        	checklabel.setOpacity(1.0);
        	QuizziceddoController.increasePoints();
        }
        else {
        	checklabel.setText("RISPOSTA ERRATA");
        	checklabel.setTextFill(Color.RED);
        	checklabel.setOpacity(1.0);
        }

    	Timeline timeline=new Timeline(new KeyFrame(Duration.seconds(1), e->{
            try {
                nextQuestion();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
    }));
 timeline.play();
    }

    @FXML
    void Click4(MouseEvent event) {
    	if(QuestionsDB.getImage4().equals(QuestionsDB.getCorrect())) {
    		checklabel.setText("RISPOSTA CORRETTA");
    		checklabel.setTextFill(Color.GREEN);
        	checklabel.setOpacity(1.0);
        	QuizziceddoController.increasePoints();
        }
        else {
        	checklabel.setText("RISPOSTA ERRATA");
        	checklabel.setTextFill(Color.RED);
        	checklabel.setOpacity(1.0);
        }
		Timeline timeline=new Timeline(new KeyFrame(Duration.seconds(1), e->{
            try {
                nextQuestion();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
    }));
 timeline.play();
    }
    
    @FXML
    void backAction(ActionEvent event) throws Exception {
    	SoundController.getInstance().stopMusic();
        CambiaScena.getInstance().setCurrentScene("secondmenu.fxml");
    }
    
    void loadQuestion(int ind) {
        questionlabel.setText(QuestionsDB.getQuestion());
        
    }
    
    void loadAnswers() {
        Image im=new Image(QuestionsDB.getImage1());
        imgans1.setImage(im);
        im=new Image(QuestionsDB.getImage2());
        imgans2.setImage(im);
        im=new Image(QuestionsDB.getImage3());
        imgans3.setImage(im);
        im=new Image(QuestionsDB.getImage4());
        imgans4.setImage(im);
    }
    
    void nextQuestion() throws Exception {
    	SoundController.getInstance().stopMusic();
    	if(QuizziceddoController.getQuestions()<=10)	{
    		QuizziceddoController.increaseNumQuest();
    			QuizziceddoController.Game();
    	}
    }

	public void initialize() {
		if(QuestionsDB.Connect())
            try {
                QuestionsDB.createQuestionMusic();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
        loadQuestion(0);
        loadAnswers();
        SoundController.getInstance().newSong(QuestionsDB.getMusic());
        SoundController.getInstance().playMusic(); 
	} 


}














