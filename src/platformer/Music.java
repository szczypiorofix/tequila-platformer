package platformer;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.swing.JOptionPane;
import javazoom.jl.player.Player;

public class Music {

private Player player;	
InputStream fis = null;
BufferedInputStream bis = null;

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
			    JOptionPane.showMessageDialog(null, "B³¹d odczytu pliku music1.mp3");
			}
		}
	}).start();
}
}