package progetto.controller;


import java.sql.SQLException;
import javafx.application.Platform;
import javafx.concurrent.Task;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import progetto.model.QuestionsDB;
import progetto.model.UsersDB;


public class QuizOneController{

    @FXML
    private Label answer4label;
    
    public static double i=1.0;

    @FXML
    private Pane answerpane7;
    
    @FXML
    private ImageView pointspane;
    
    @FXML
    private Label pointslabel;

    @FXML
    private Pane answerpane6;
    
    @FXML
    private Button backbutton;

    @FXML
    private Pane answerpane9;

    @FXML
    private Pane answerpane8;

    @FXML
    private ToggleButton answer3;

    @FXML
    private ProgressBar timebar;

    @FXML
    private ToggleButton answer2;

    @FXML
    private Label questionlabel;

    @FXML
    private ToggleButton answer4;

    @FXML
    private ToggleButton answer1;

    @FXML
    private Label answer2label;

    @FXML
    private Pane answerpane3;

    @FXML
    private Pane answerpane2;

    @FXML
    private Label answer1label;

    @FXML
    private Label answer3label;

    @FXML
    private Pane answerpane5;
    
    @FXML
    private Button playagainbutton;
    
    @FXML
    private Button backmenubutton;

    @FXML
    private Pane answerpane4;

    @FXML
    private Pane answerpane1;

    @FXML
    private Pane answerpane0;
    
    private ArrayList<Pane> pannels;
    
    private Task<Void> t;
    
    private Thread th;
    
    private boolean cliccato=false;
    
    private boolean stillPlaying=false;
    
    private int correctanswers=0;
    
    private int index=0;
    
    void loadQuestion(int ind) {
    	questionlabel.setText(QuestionsDB.getQuestion());
    	questionlabel.setWrapText(true);
    }
    
    void loadAnswers(int ind) {
    	answer1label.setText(QuestionsDB.getAnswer1());
    	answer1label.setWrapText(true);
    	answer2label.setText(QuestionsDB.getAnswer2());
    	answer2label.setWrapText(true);
    	answer3label.setText(QuestionsDB.getAnswer3());
    	answer3label.setWrapText(true);
    	answer4label.setText(QuestionsDB.getAnswer4());
    	answer4label.setWrapText(true);
    }
    
    void outOfTime() throws SQLException {
    	   showCorrectAnswer();
    		pannels.get(index).setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
			index+=1;
				nextQuestion(index);
    }

    @FXML
    void checkAnswer1(ActionEvent event) throws Exception {
    	if(!cliccato)
    	{
    	cliccato=true;
    	if(answer1label.getText().equals(QuestionsDB.getCorrect()))
    	{
    		answer1label.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY))); 
    		pannels.get(index).setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    	correctanswers+=1;
    	if(UsersDB.stateConnected())
    	UsersDB.setPoints(1);
    	}
    	else
    	{
    		showCorrectAnswer();
    		answer1label.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
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

    @FXML
    void checkAnswer2(ActionEvent event) throws Exception {
    	if(!cliccato)
    	{
    	cliccato=true;
    	if(answer2label.getText().equals(QuestionsDB.getCorrect()))
    	{
    		answer2label.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY))); 
    		pannels.get(index).setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    		correctanswers+=1;
    		if(UsersDB.stateConnected())
    		UsersDB.setPoints(1);
    	}
    	else
    	{
    		showCorrectAnswer();
    		answer2label.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
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
    
    @FXML
    void checkAnswer3(ActionEvent event) throws Exception {
    	if(!cliccato)
    	{
    	cliccato=true;
    	if(answer3label.getText().equals(QuestionsDB.getCorrect()))
    	{
    		answer3label.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY))); 
    		pannels.get(index).setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    		correctanswers+=1;
    		if(UsersDB.stateConnected())
    		UsersDB.setPoints(1);
    	}
    	else
    	{
    		showCorrectAnswer();
    		answer3label.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
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

    @FXML
    void checkAnswer4(ActionEvent event) throws Exception {
    	if(!cliccato)
    	{
    	cliccato=true;
    	if(answer4label.getText().equals(QuestionsDB.getCorrect()))
    	{
    		answer4label.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY))); 
    		pannels.get(index).setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    		correctanswers+=1;
    		if(UsersDB.stateConnected())
    		UsersDB.setPoints(1);
    	}
    	else
    	{
    		showCorrectAnswer();
    		answer4label.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
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
    
    @FXML
    void backAction(ActionEvent event) throws InterruptedException{
    	stillPlaying=false;
    	try {
    		th.interrupt();
			CambiaScena.getInstance().setCurrentScene("secondmenu.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void playAgain(ActionEvent event) throws Exception {
    	playagainbutton.setDisable(true);
    	backmenubutton.setDisable(true);
    	answer1.setDisable(false);
    	answer2.setDisable(false);
    	answer3.setDisable(false);
    	answer4.setDisable(false);
    	pointspane.setOpacity(0);
    	pointslabel.setOpacity(0);
    	playagainbutton.setOpacity(0);
    	backmenubutton.setOpacity(0);
    	for(int i=0;i<10;i++)
    		pannels.get(i).setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    	index=0;
    	correctanswers=0;
    	QuestionsDB.resetQDone();
    	restart();
    }
    
    public void restart() throws Exception {
		stillPlaying=true;
		i=1.0;
	    cliccato=false;
	    QuestionsDB.resetQDone();
		nextQuestion(index);
    }

    @FXML
    void backToMenu(ActionEvent event) throws InterruptedException{
    	stillPlaying=false;
    	try {
    		th.interrupt();
			CambiaScena.getInstance().setCurrentScene("secondmenu.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
  
    public void nextQuestion(int ind) throws SQLException{
    	if(ind<10)
    	{
    	i=1.0;
    	QuestionsDB.createQuestion();
    	loadQuestion(ind);
    	loadAnswers(ind);
    	answer1label.setBackground(null);
    	answer2label.setBackground(null);
    	answer3label.setBackground(null);
    	answer4label.setBackground(null);
    	cliccato=false;
    	}
    	else
    		showPoints();
        }
    
    public void showPoints() {
    	stillPlaying=false;
    	answer1.setDisable(true);
    	answer2.setDisable(true);
    	answer3.setDisable(true);
    	answer4.setDisable(true);
    	playagainbutton.setDisable(false);
    	backmenubutton.setDisable(false);
    	pointspane.setOpacity(1);
    	pointslabel.setText("Hai risposto correttamente a: "+correctanswers+" domande");
    	pointslabel.setWrapText(true);
    	pointslabel.setOpacity(1);
    	playagainbutton.setOpacity(1);
    	backmenubutton.setOpacity(1);
    }
    
    public void showCorrectAnswer() {
    	if(answer1label.getText().equals(QuestionsDB.getCorrect()))
    		answer1label.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    	else if(answer2label.getText().equals(QuestionsDB.getCorrect()))
    		answer2label.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    	else if(answer3label.getText().equals(QuestionsDB.getCorrect()))
    		answer3label.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    	else if(answer4label.getText().equals(QuestionsDB.getCorrect()))
    		answer4label.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    	
    }
   
	public void initialize(){
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
		playagainbutton.setDisable(true);
    	backmenubutton.setDisable(true);
    	QuestionsDB.resetQDone();
    	if(QuestionsDB.Connect())
			try {
				QuestionsDB.createQuestion();
			} catch (SQLException e) {
				System.out.println("Errore");
			}
		loadQuestion(index);
		loadAnswers(index);
		stillPlaying=true;
		cliccato=false;
		info();
		
		
		timebar.setProgress(1.0);
	}
	
	public void info() {
		Alert info = new Alert(AlertType.INFORMATION);
		info.setTitle("REGOLE DEL GIOCO");
		info.setHeaderText("Ciao, Benvenuto su Quizziceddo!");
		info.setContentText("Nella Sfida a tempo affronterai 10 quesiti di cultura generale e per ogni domanda avrai a disposizione 10 secondi. Ogni risposta esatta varrà 1 punto, i punti ti faranno salire di livello e determineranno la tua posizione in classifica. Bene..Iniziamo...Sarai abbastanza veloce?");

	    info.showAndWait();
	    startTask();
	}

    public void startTask() {
			t=new Task<Void>() {
			@Override
			protected Void call(){
				if(stillPlaying)
				{
				for(int num=0;num<10;num++) {
					boolean exit=false;
					for(i=1.0;i>=0;)
		    	    {
		    			try {
		    			Thread.sleep(10);
		    			timebar.setProgress(i);
		    			if(!cliccato)
		    			i-=0.001;
		    			}
		    		catch(InterruptedException e){
		    			exit=true;
		    			break;
		    		}
		    		}
		    		if(!cliccato && stillPlaying && !exit)
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
}
