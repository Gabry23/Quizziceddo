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

public class ImageQuizController{
	
    @FXML
    private ImageView image3;
    
    @FXML
    private Label checklabel;
    
    @FXML
    private ProgressBar timebar;

    @FXML
    private Button back;
    
    @FXML
    private Label question;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    void goBack(ActionEvent event) throws Exception {
        CambiaScena.getInstance().setCurrentScene("secondmenu.fxml");
    }
    
    @FXML
    void CheckAnswer1(MouseEvent event) throws Exception {
    	if(QuestionsDB.getAnswer1().equals(QuestionsDB.getCorrect())) {
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
void CheckAnswer2(MouseEvent event) throws Exception {
	if(QuestionsDB.getAnswer2().equals(QuestionsDB.getCorrect())) {
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
void CheckAnswer3(MouseEvent  event) throws Exception {
    if(QuestionsDB.getAnswer3().equals(QuestionsDB.getCorrect())) {
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

       
        
        void loadQuestion(int ind) {
            question.setText(QuestionsDB.getQuestion());
            question.setWrapText(true);
            Image img1 = new Image(QuestionsDB.getAnswer1());
            image1.setImage(img1);
            Image img2 = new Image(QuestionsDB.getAnswer2());
            image2.setImage(img2);
            Image img3 = new Image(QuestionsDB.getAnswer3());
            image3.setImage(img3);
            
        }
        
        void nextQuestion() throws Exception {
    		if(QuizziceddoController.getQuestions()<=10)
    		{
        		QuizziceddoController.increaseNumQuest();
        			QuizziceddoController.Game();
        	}
    		else
    			System.out.println("Fine");
    	}   

		public void initialize() {
			if(QuestionsDB.Connect())
                try {
                    QuestionsDB.createQuestionImage();
                } catch (SQLException e) {
                    System.out.println("Errore");
                }
            loadQuestion(0);
		} 
     
}

