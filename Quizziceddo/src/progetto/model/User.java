package progetto.model;

public class User {
private String username;
private int points;
private String Image;

public User(String u,int p,String i) {
	username=u;
	points=p;
	Image=i;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public int getPoints() {
	return points;
}

public void setPoints(int points) {
	this.points = points;
}

public String getImage() {
	return Image;
}

public void setImage(String image) {
	Image = image;
}


}
