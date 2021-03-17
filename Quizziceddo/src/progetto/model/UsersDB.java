package progetto.model;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.springframework.security.crypto.bcrypt.BCrypt;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import progetto.controller.CambiaScena;

public class UsersDB {
private static String savedusername=null;
private static Integer savedlevel=null;
private static Integer savedpoints=null;
private static String image=null;
private static ArrayList<User> users=new ArrayList<User>();
private static Connection conn=null;
private static boolean guest;

public static boolean isConnected() {
	if(getConnection()==null)
	{
		conn=ConnectionDB.connect();
		return true;
	}
	return false;
}

public static boolean stateConnected() {
	if(getConnection()!=null)
		return true;
	return false;
}

public static void createTable() throws SQLException{
	String query1="CREATE TABLE IF NOT EXISTS users(id int , username varchar (50) , password varchar (50)) ; " ;
    String query2="CREATE TABLE IF NOT EXISTS normalquestions(question varchar(50) , first_answ varchar (50), second_answ varchar (50) , third_answ varchar (50), fourth_answ varchar(50), correct varchar(50) );";
    Statement stmt=getConnection().createStatement();
    stmt.executeUpdate(query1);
    stmt.executeUpdate(query2);
    stmt.close();
}

public static void createUser(String u, String p) throws Exception {
    boolean presente = false;
    String originalPassword = p;
    String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
   
    String query2 = "select * from users where username = ?;";
    PreparedStatement stmt2= getConnection().prepareStatement(query2);
    stmt2.setString(1, u);
    ResultSet rs = stmt2.executeQuery();
    while(rs.next())
        if(rs!=null)
            presente = true;
    rs.close();

 

    if(presente==false) {
    String query = "INSERT INTO users(username,password,image) VALUES(?,?,?);" ;
    PreparedStatement stmt = getConnection().prepareStatement(query);
    stmt.setString( 1 ,u ) ;
    stmt.setString( 2 ,generatedSecuredPasswordHash) ;
    stmt.setString(3, "/progetto/resources/Image/user.jpg");
    stmt.executeUpdate();
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Registrazione Effettuata");
    alert.setHeaderText(null);
    alert.setContentText("Complimenti, Ora puoi cominciare a giocare");
    alert.showAndWait();
    stmt.close();
    CambiaScena.getInstance().setCurrentScene("login.fxml");
    }
    else
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("L'username che hai inserito è già in uso");
        alert.setContentText("Per favore, scegli un nuovo username");
        alert.showAndWait();
    }
}

public static void Guest(boolean b) {
	guest=b;
}

public static boolean isGuest() {
	if(guest)
		return true;
	return false;
}

public static boolean checkUser(String u,String p) throws SQLException {
    String query="select * from users where username=?;"; 
    PreparedStatement stmt = getConnection().prepareStatement(query);
        stmt.setString(1, u);
        ResultSet rs = stmt.executeQuery();
        while(rs.next())
        if(rs!=null) {
            boolean matched = BCrypt.checkpw(p,rs.getString("password"));
            if(matched)
            {
                savedusername=u;
                savedlevel=rs.getInt("livello");
                savedpoints=rs.getInt("punti");
                image=rs.getString("image");
            }
            return matched;
        }
        stmt.close();
        return false;
}

public static boolean existUsers(String text) throws SQLException {
    String query="select * from users where username=?;"; 
    PreparedStatement stmt = getConnection().prepareStatement(query);
        stmt.setString(1, text);
        ResultSet rs = stmt.executeQuery();
        while(rs.next())
        if(rs.getString("username").equals(text)) {
            return true;
        }
        stmt.close();
        changeName(text);
        return false;
}


private static void changeName(String text) throws SQLException {
String query1="UPDATE users SET username = ? WHERE username = ?;";    
 PreparedStatement stmt1 = getConnection().prepareStatement(query1);
 stmt1.setString(1,text);
 stmt1.setString(2, savedusername);
 stmt1.executeUpdate();
 
 savedusername=text;
}

public static void sendFeedback(String text) throws SQLException {
	 String query = "INSERT INTO feedback(feedBack) VALUES(?);" ;
	    PreparedStatement stmt = getConnection().prepareStatement(query);
	    stmt.setString( 1 ,text);
	    stmt.executeUpdate(); 
}

public static void sendQuestion(String question, String correct, String option1,String option2,String option3,String option4) throws SQLException {
	 String query = "INSERT INTO QuestionsToCheck(question,answer1,answer2,answer3,answer4,correct) VALUES(?,?,?,?,?,?);" ;
	    PreparedStatement stmt = getConnection().prepareStatement(query);
	    stmt.setString(1,question);
	    stmt.setString(2,option1);
	    stmt.setString(3,option2);
	    stmt.setString(4,option3);
	    stmt.setString(5,option4);
	    stmt.setString(6,correct);
	    stmt.executeUpdate();
	}

public static String getUsername() {
	return savedusername;
}

public static Integer getLevel() {
	return savedlevel;
}

public static Integer getPoints() {
	return savedpoints;
}

public static String getImage() {
	return image;
}

public static void setPoints(int punti) throws SQLException {
	if(!guest) {
	savedpoints=savedpoints+punti;
	int expoints=savedpoints;
	while(expoints>=100)
	{
		expoints=expoints/100;
		savedlevel++;
	}
	update();
	}
}

public static void update() throws SQLException {
    String query1="UPDATE users SET punti = ?,livello =?,image = ? WHERE username = ?;";    
    PreparedStatement stmt1 = getConnection().prepareStatement(query1);
    stmt1.setInt(1,savedpoints);
    stmt1.setInt(2,savedlevel);
    stmt1.setString(3,image);
    stmt1.setString(4,savedusername);
    stmt1.executeUpdate();
}

public static void changeImage() throws SQLException, IOException {
    FileChooser fc = new FileChooser();
    
    File selectedFile = fc.showOpenDialog(CambiaScena.getInstance().getStage());
    
	image=selectedFile.toURI().toURL().toExternalForm();
	
    update();
    }

public static int getNum() throws SQLException {
	int n=0;
	String query1="SELECT COUNT(*) AS count FROM users;"; 
	PreparedStatement stmt1 = getConnection().prepareStatement(query1);
	ResultSet rs = stmt1.executeQuery();
	rs.next();
	n=rs.getInt("count");
	return n;
}

public static ArrayList<User> getMax() throws SQLException{
	String username;
	int points;
	String Image;
	String query="select username,punti,image from users ORDER BY punti DESC;";
	PreparedStatement stmt1 = getConnection().prepareStatement(query);
	ResultSet rs = stmt1.executeQuery();
	
	while(rs.next()) {
	if(rs!=null) {
		username=rs.getString("username");
	    points=rs.getInt("punti");
	    Image=rs.getString("image");
	    User u=new User(username,points,Image);
	    users.add(u);
	 }
	}
	stmt1.close();
	return users;
}

public static void eraseList() {
	users.clear();
}

public static Connection getConnection() {
	return conn;
}
}