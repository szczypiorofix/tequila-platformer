package platformer;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

public class GameWindow extends JFrame implements WindowListener{

private static final long serialVersionUID = 8434543456858249978L;
//private Image programIcon;
public static DisplayMode currentDisplayMode = null;

public GameWindow()
{
	//super();
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice gd = ge.getDefaultScreenDevice();
	//currentDisplayMode = ge.getDefaultScreenDevice().getDisplayMode();
	
	this.setUndecorated(true);
	this.setIgnoreRepaint(true);
	this.setResizable(false);
	
	gd.setFullScreenWindow(this);

	//gd.setDisplayMode(new DisplayMode(1366, 768, 32, 60));
	
	//setSize(1366,768);
	
	//try {
		//programIcon = ImageIO.read(getClass().getResourceAsStream("/programIcon.png"));
	//} catch (IOException e) {
	//	e.printStackTrace();
	//	System.exit(-1);
	//}
	
	//this.setIconImage(programIcon);
	this.addWindowListener(this);
}

public void showWindow(boolean showWindow)
{
	this.setVisible(showWindow);
	if (!showWindow) {
		
		this.dispose();
		System.exit(0);
	}
}

@Override
public void windowActivated(WindowEvent arg0) {}

@Override
public void windowClosed(WindowEvent arg0) {}

@Override
public void windowClosing(WindowEvent arg0) {}

@Override
public void windowDeactivated(WindowEvent arg0) {}

@Override
public void windowDeiconified(WindowEvent arg0) {}

@Override
public void windowIconified(WindowEvent arg0) {}

@Override
public void windowOpened(WindowEvent arg0) {}

}