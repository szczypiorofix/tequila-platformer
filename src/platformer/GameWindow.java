package platformer;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GameWindow extends JFrame {

private static final long serialVersionUID = 8434543456858249978L;
private int width, height;
private Image programIcon;


public GameWindow(String title, int width, int height)
{
	super(title);
	this.width = width;
	this.height = height;
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(this.width, this.height);
	setLocationRelativeTo(null);
	setResizable(false);
	
	try {
		programIcon = ImageIO.read(getClass().getResourceAsStream("/res/programIcon.png"));
	} catch (IOException e) {
		e.printStackTrace();
		System.exit(-1);
	}
	
	this.setIconImage(programIcon);
}

public void showWindow(boolean showWindow)
{
	this.setVisible(showWindow);
	if (!showWindow) {
		this.dispose();
		System.exit(0);
	}
}

public void blank()
{
	// DO NOTHING IS THE BEST YOU CAN DO :)
}

}