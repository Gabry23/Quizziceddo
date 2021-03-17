package progetto.controller;

import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import progetto.model.QuestionsDB;

public class QuizziceddoController {
private static int questions=0;
private static double points=0;

public static void increaseNumQuest() {
	questions++;
}

public static int getQuestions() {
	return questions;
}

public static void increasePoints() {
	points+=6.3;
}

public static double getPoints() {
	return points;
}

public static void init() {
	questions=0;
	points=0;
	QuestionsDB.resetQIDone();
	QuestionsDB.resetQMDone();
	QuestionsDB.resetQFDone();
}

public static void info() {
	Alert info = new Alert(AlertType.INFORMATION);
	info.setTitle("REGOLE DEL GIOCO");
	info.setHeaderText("Ciao, Benvenuto su Quizziceddo!");
	info.setContentText("Le regole sono semplici, ti verranno poste 10 domande, tra 'Chi canta questo brano?' e 'Chi o cosa è?'.  Una volta completate le 10 domande arriverai al gioco finale, 'Indovina Chi', in cui avrai a disposizione 30 secondi per indovinare il personaggio nascosto! La risposta corretta al gioco finale varrà ben 10 punti! Ah...più domande indovinerai, più sarai avvantaggiato nel gioco finale. Questo è tutto, quando sei pronto clicca su Ok! Buon divertimento!");

    info.showAndWait();
	init();
}

public static void Game() throws Exception {
    if(questions<10) {
    Random r = new Random();
    int n = r.nextInt(2);
    if(n==0)
        try {
            CambiaScena.getInstance().setCurrentScene("imagequiz.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    if(n==1)
        try {
            CambiaScena.getInstance().setCurrentScene("musicquiz.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    else
    {	
    	try {
            CambiaScena.getInstance().setCurrentScene("finalquiz.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
}
