package com.platformer.game.sounds;

import java.io.BufferedInputStream;
import java.io.InputStream;
import javazoom.jl.player.Player;

public class Music {

private Player player;
private InputStream fis = null;
private BufferedInputStream bis = null;

// http://opengameart.org/content/mirage

public Music()  // Start w GameWindow
{
	new Thread(new Runnable()
	{
		@Override
		public void run()
		{
			try{
			    do {
			    fis = getClass().getClassLoader().getResourceAsStream("mirage.mp3");
			    bis = new BufferedInputStream(fis);
			    player = new Player(bis);
			    player.play();
			    }
			    while(true);
			}
			catch(Exception exc){
			    exc.printStackTrace();
			    System.exit(-1);
			}
		}
	}).start();
}
}