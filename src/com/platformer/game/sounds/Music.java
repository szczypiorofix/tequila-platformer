package com.platformer.game.sounds;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class Music {

private AdvancedPlayer player;
private InputStream fis = null;
private BufferedInputStream bis = null;
private int pauseOnFrame = 0;


// http://opengameart.org/content/mirage

public Music()
{
	try{
	    fis = getClass().getClassLoader().getResourceAsStream("mirage.mp3");
	    bis = new BufferedInputStream(fis);
	    player = new AdvancedPlayer(bis);
	    player.setPlayBackListener(new PlaybackListener() {
	        @Override
	        public void playbackFinished(PlaybackEvent event) {
	        	pauseOnFrame = event.getFrame();
	        }
	    });
	}
	catch(Exception exc){
	    exc.printStackTrace();
	    System.exit(-1);
	}
}

public void play()
{
	 try {
		player.play();
	} catch (JavaLayerException e) {
		e.printStackTrace();
	}
}

public void stop()
{
	
}

public void pause()
{
	
}

}