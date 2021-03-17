package progetto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class QuestionsDB {
private static Connection conn=null;
private static String question=null;
private static String answer1=null;
private static String answer2=null;
private static String answer3=null;
private static String answer4=null;
private static String correct=null;
private static String img=null;
private static String img1=null;
private static String img2=null;
private static String img3=null;
private static String img4=null;
private static String music=null;
private static ArrayList<Integer> questionsdone=new ArrayList<Integer>();
private static ArrayList<Integer> questionsdoneImage=new ArrayList<Integer>();
private static ArrayList<Integer> questionsdoneMusic=new ArrayList<Integer>();
private static ArrayList<Integer> questionsdoneFinal=new ArrayList<Integer>();

public static boolean Connect() {
	if(conn==null)
		conn=UsersDB.getConnection();
	return true;
}

public static int getRandom() throws SQLException {
	Random r=new Random();
	int n=1;
	String queryid="select id from Questions;";
	PreparedStatement stmt = conn.prepareStatement(queryid);
	ResultSet rs = stmt.executeQuery();
	int max=0;
	while(rs.next()) {
	if(rs.getInt("id")>max)
	max = rs.getInt("id");
	}
	do {
		n=r.nextInt(max+1);
	}while(exists(n) || n==0);
	questionsdone.add(n);
	rs.close();
		
	return n;
}

public static int getRandomImage() throws SQLException {
	Random r=new Random();
	int n=1;
	String queryid="select id from QuizziceddoImage;";
	PreparedStatement stmt = conn.prepareStatement(queryid);
	ResultSet rs = stmt.executeQuery();
	int max=0;
	while(rs.next()) {
	if(rs.getInt("id")>max)
	max = rs.getInt("id");
	}
	do {
		n=r.nextInt(max+1);
	}while(existsImage(n) || n==0);
	questionsdoneImage.add(n);
	rs.close();
		
	return n;
}

public static int getRandomMusic() throws SQLException {
	Random r=new Random();
	int n=1;
	String queryid="select id from QuizziceddoMusic;";
	PreparedStatement stmt = conn.prepareStatement(queryid);
	ResultSet rs = stmt.executeQuery();
	int max=0;
	while(rs.next()) {
	if(rs.getInt("id")>max)
	max = rs.getInt("id");
	}
	do {
		n=r.nextInt(max+1);
	}while(existsMusic(n) || n==0);
	questionsdoneMusic.add(n);
	rs.close();
		
	return n;
}

public static int getRandomFinal() throws SQLException {
	Random r=new Random();
	int n=1;
	String queryid="select id from QuizziceddoFinal;";
	PreparedStatement stmt = conn.prepareStatement(queryid);
	ResultSet rs = stmt.executeQuery();
	int max=0;
	while(rs.next()) {
	if(rs.getInt("id")>max)
	max = rs.getInt("id");
	}
	do {
		n=r.nextInt(max+1);
	}while(existsFinal(n) || n==0);
	questionsdoneFinal.add(n);
	rs.close();
		
	return n;
}

public static void createQuestion() throws SQLException {  
	int n=getRandom();
	String query="select * from Questions where id=?;"; 
	PreparedStatement stmt;
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, n);
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
		question=rs.getString("question");
		answer1=rs.getString("first_answ");
		answer2=rs.getString("second_answ");
		answer3=rs.getString("third_answ");
		answer4=rs.getString("fourth_answ");
		correct=rs.getString("correct");
		}
		rs.close();
}

public static void createQuestionImage() throws SQLException {  
	int n=getRandomImage();
	String query="select * from QuizziceddoImage where Id=?;"; 
	PreparedStatement stmt;
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, n);
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
		question=rs.getString("Question");
		answer1=rs.getString("Image1");
		answer2=rs.getString("Image2");
		answer3=rs.getString("Image3");
	    correct=rs.getString("Correct");
		}
		rs.close();
}

public static void createQuestionMusic() throws SQLException {  
	int n=getRandomMusic();
	String query="select * from QuizziceddoMusic where id=?;"; 
	PreparedStatement stmt;
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, n);
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
		question=rs.getString("question");
		img1=rs.getString("answer1");
		img2=rs.getString("answer2");
		img3=rs.getString("answer3");
		img4=rs.getString("answer4");
		correct=rs.getString("correct");
		music=rs.getString("music");
		}
		rs.close();
}

public static void createQuestionFinal() throws SQLException {  
	int n=getRandomFinal();
	String query="select * from QuizziceddoFinal where Id=?;"; 
	PreparedStatement stmt;
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, n);
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
		correct=rs.getString("Correct");
	    img=rs.getString("Image");
		}
		rs.close();
}

public static boolean exists(int n) {
	for(int i=0;i<questionsdone.size();i++)
		if(n==questionsdone.get(i))
			return true;
	return false;
}

public static boolean existsImage(int n) {
	for(int i=0;i<questionsdoneImage.size();i++)
		if(n==questionsdoneImage.get(i))
			return true;
	return false;
}

public static boolean existsMusic(int n) {
	for(int i=0;i<questionsdoneMusic.size();i++)
		if(n==questionsdoneMusic.get(i))
			return true;
	return false;
}

public static boolean existsFinal(int n) {
	for(int i=0;i<questionsdoneFinal.size();i++)
		if(n==questionsdoneFinal.get(i))
			return true;
	return false;
}

public static String getCorrect() {
	return correct;
}

public static String getAnswer1() {
	return answer1;
}

public static String getAnswer2() {
	return answer2;
}

public static String getAnswer3() {
	return answer3;
}

public static String getAnswer4() {
	return answer4;
}

public static String getQuestion() {
	return question;
}

public static String getImage() {
	return img;
}

public static String getImage1() {
	return img1;
}

public static String getImage2() {
	return img2;
}

public static String getImage3() {
	return img3;
}

public static String getImage4() {
	return img4;
}

public static String getMusic() {
	return music;
}

public static void resetQDone() {
	questionsdone.clear();
}

public static void resetQIDone() {
	questionsdoneImage.clear();
}

public static void resetQMDone() {
	questionsdoneMusic.clear();
}

public static void resetQFDone() {
	questionsdoneFinal.clear();
}
}
