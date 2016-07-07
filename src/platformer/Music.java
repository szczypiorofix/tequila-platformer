package platformer;

import java.io.FileInputStream;
import javax.swing.JOptionPane;
import javazoom.jl.player.Player;

public class Music {

private Player player;	
	
// http://opengameart.org/content/trance-menu

public Music()
{
	new Thread(new Runnable()
	{
		@Override
		public void run()
		{
			try{
			    FileInputStream fis = new FileInputStream("music.mp3");
			    player = new Player(fis);
			    player.play();
			}
			catch(Exception exc){
			    exc.printStackTrace();
			    JOptionPane.showMessageDialog(null, "B³¹d odczytu pliku music1.mp3");
			}
		}
	}).start();
}
}