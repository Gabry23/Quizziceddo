package progetto.model;

import java.util.ArrayList;

public class Question {
private int ID;
private String question;
private ArrayList<String> answers=new ArrayList<String>();
private String correctAnswer;

public Question(int i,String q,ArrayList<String> a,String c) {
	this.ID=i;
	this.question=q;
	this.answers=a;
	this.correctAnswer=c;
}

public String getQuestion() {
	return question;
}

public int getID() {
	return ID;
}

public ArrayList<String> getAnswers(){
	return answers;
}

public int getAnswersSize(){
	return answers.size();
}

public String getCorrectAnswer(){
	return correctAnswer;
}
}
