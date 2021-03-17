package progetto.model;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class QuestionImage extends Question{
private Image img=null;


public QuestionImage(int i,String q,ArrayList<String> a,String ca,Image img) {
	super(i, q, a, ca);
	this.img=img;
}

public Image getImg() {
	return img;
}

public void setImg(Image img) {
	this.img = img;
}
}
