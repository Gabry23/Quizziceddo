package progetto.controller;

import java.net.URL;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class SoundController {
	private static SoundController s=null;
	private static Media sound=null;
    private static MediaPlayer mediaPlayer=null;
    private static MediaPlayer mp=null;
    private static boolean isstopped;
    
    private SoundController(){
    	URL path=getClass().getResource("/progetto/resources/Music/sound.mp3");
    	sound=new Media(path.toString());
		mediaPlayer=new MediaPlayer(sound);
		isstopped=false;
    }
   
    public static SoundController getInstance(){
    	if(s==null)
    		s=new SoundController();
    	return s;
    }
    
    public void newSong(String s) {
    	URL path=getClass().getResource(s);
    	Media song=new Media(path.toString());
		mp=new MediaPlayer(song);
    }
    
    void playSound() {
    	 mediaPlayer.setVolume(0.1);
    	 mediaPlayer.play();
    	 mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }
    
    void playMusic() {
    	mp.setVolume(0.1);
    	mp.play();
    	mp.setCycleCount(MediaPlayer.INDEFINITE);
    }
    
    void stopSound() {
    	mediaPlayer.stop();
    }
    
    void stopMusic() {
    	mp.stop();
    }
    
    public void stopByUser(boolean b) {
    	isstopped=b;
    }
    
    public boolean isStoppedByUser() {
    	return isstopped;
    }

}
