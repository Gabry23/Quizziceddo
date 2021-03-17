package progetto.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import progetto.model.QuestionsDB;

public class QuizTwoController{
    @FXML
    private TextField answerfield;

    @FXML
    private Label questionlabel;
    
    @FXML
    private ProgressBar timebar;

    @FXML
    private Pane answerpane9;

    @FXML
    private Pane answerpane8;

    @FXML
    private Pane answerpane7;

    @FXML
    private Pane answerpane6;
    
    @FXML
    private Pane answerpane5;

    @FXML
    private Pane answerpane4;

    @FXML
    private Pane answerpane3;

    @FXML
    private Pane answerpane2;

    @FXML
    private Pane answerpane1;

    @FXML
    private Pane answerpane0;
    
    @FXML
    private Button playagainbutton;
    
    @FXML
    private Button backmenubutton;
    
    @FXML
    private Label pointslabel;
    
    @FXML
    private ImageView pointspane;
    
    @FXML
    private Button backbutton;
    
    private int correctanswers;
    
    private ArrayList<Pane> pannels;
    
    public static double i=1.0;
    
    private int index=0;
    
    private boolean stillplaying;
    
    private boolean clicked;
    
    private Task<Void> t;
    
    private Thread th;
    
    @FXML
    void checkAnswer(ActionEvent event) {
    	if(!clicked)
    	{
    	clicked=true;
    	if(answerfield.getText().equalsIgnoreCase(QuestionsDB.getCorrect()))
    	{
    		answerfield.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    		correctanswers+=1;
    		pannels.get(index).setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    	}
    	else
    	{
    		answerfield.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
    		pannels.get(index).setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
    	}
    	Timeline timeline=new Timeline(new KeyFrame(Duration.seconds(1), e->{
			index+=1;
				try {
					nextQuestion(index);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}));
    	timeline.play();
    	}
    }
    
    void loadQuestion(int ind) {
    	questionlabel.setText(QuestionsDB.getQuestion());
    	questionlabel.setWrapText(true);
    }
    
    public void nextQuestion(int ind) throws SQLException{
    	if(ind<10)
    	{
    	answerfield.setBackground(null);	
    	clicked=false;	
    	answerfield.setText("");	
    	i=1.0;
    	QuestionsDB.createQuestion();
    	loadQuestion(ind);
    	}
    	else
    		showPoints();
        }
    
    void outOfTime() throws SQLException {
 		pannels.get(index).setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
			index+=1;
				nextQuestion(index);
 }
    
    public void showPoints() {
    	stillplaying=false;
    	playagainbutton.setDisable(false);
    	backmenubutton.setDisable(false);
    	pointspane.setOpacity(1);
    	pointslabel.setText("Hai risposto correttamente a: "+correctanswers+" domande");
    	pointslabel.setWrapText(true);
    	pointslabel.setOpacity(1);
    	playagainbutton.setOpacity(1);
    	backmenubutton.setOpacity(1);
    	answerfield.setEditable(false);
    }
    
    @FXML
    void backToMenu(ActionEvent event) throws Exception {
    	stillplaying=false;
    	CambiaScena.getInstance().setCurrentScene("secondmenu.fxml");
    }
    
    @FXML
    void playAgain(ActionEvent event) throws Exception {
    	playagainbutton.setDisable(true);
    	backmenubutton.setDisable(true);
    	pointspane.setOpacity(0);
    	pointslabel.setOpacity(0);
    	playagainbutton.setOpacity(0);
    	backmenubutton.setOpacity(0);
    	for(int i=0;i<10;i++)
    		pannels.get(i).setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    	index=0;
    	correctanswers=0;
    	clicked=false;
    	answerfield.setEditable(true);
    	restart();
    }
    
    @FXML
    void backAction(ActionEvent event) throws Exception {
    	stillplaying=false;
    	CambiaScena.getInstance().setCurrentScene("secondmenu.fxml");
    }

    
    public void restart() throws Exception {
		stillplaying=true;
		QuestionsDB.resetQDone();
		nextQuestion(index);
    }
    
    public void startTask() {
		t=new Task<Void>() {
		@Override
		protected Void call(){
			if(stillplaying)
			{
			for(int num=0;num<10;num++) {
				boolean exit=false;
				for(i=1.0;i>=0;)
	    	    {
	    			try {
	    			Thread.sleep(10);
	    			timebar.setProgress(i);
	    			if(!clicked)
	    			i-=0.001;
	    			}
	    		catch(InterruptedException e){
	    			exit=true;
	    			break;
	    		}
	    		}
	    		if(!clicked && stillplaying && !exit)
	    		Platform.runLater(()->{
	    			try {
						outOfTime();
					} catch (SQLException e) {
						e.printStackTrace();
					}
	    			});
	    		if(exit)
					break;
	    		}
			
			}
			return null;
		}
		};
		
	th=new Thread(t);
	th.setDaemon(true);
	th.start();
	}
    
	public void initialize() {
		pannels=new ArrayList<Pane>();
		pannels.add(answerpane0);
		pannels.add(answerpane1);
		pannels.add(answerpane2);
		pannels.add(answerpane3);
		pannels.add(answerpane4);
		pannels.add(answerpane5);
		pannels.add(answerpane6);
		pannels.add(answerpane7);
		pannels.add(answerpane8);
		pannels.add(answerpane9);
		index=0;
		correctanswers=0;
		stillplaying=true;
		clicked=false;
		playagainbutton.setDisable(true);
    	backmenubutton.setDisable(true);
    	answerfield.setEditable(true);
    	QuestionsDB.resetQDone();
        if(QuestionsDB.Connect())
            try {
                QuestionsDB.createQuestion();
            } catch (SQLException e) {
                System.out.println("Errore");
            }
		loadQuestion(index);
		info();
		
		timebar.setProgress(1.0);
	}
	
	public void info() {
		Alert info = new Alert(AlertType.INFORMATION);
		info.setTitle("REGOLE DEL GIOCO");
		info.setHeaderText("Ciao, Benvenuto su Quizziceddo!");
		info.setContentText("Quanto sei veloce a scrivere? Scrivi la risposta ti metterà alla prova! 10 domande di cultura generale, 10 secondi per rispondere, sarai all'altezza?");

	    info.showAndWait();
	    startTask();
	}
}
