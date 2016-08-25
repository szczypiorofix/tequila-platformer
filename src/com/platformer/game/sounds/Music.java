package com.platformer.game.sounds;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class Music {

private Player player;
private InputStream fis = null;
private BufferedInputStream bis = null;
private long pauseLocation;


// http://opengameart.org/content/mirage

public Music()
{
	///https://www.youtube.com/watch?v=LavMuqK5Is0
}

public void play()
{
	try {
		fis = this.getClass().getResourceAsStream("/mirage.mp3");
		bis = new BufferedInputStream(fis);
		player = new Player(bis);
	}
	catch (JavaLayerException jle)
	{
		jle.printStackTrace();
		System.exit(-1);
	}
	
	new Thread()
	{
		@Override
		public void run()
		{
			try {
				player.play();
			} catch (JavaLayerException e) {
				e.printStackTrace();
			}
		}
	}.start();
}

public void stop()
{
	if (player != null)
	player.close();
}

public void pause()
{
	if (player != null)
		try {
			pauseLocation = bis.available();
			player.close();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	{
		
	}
}

}