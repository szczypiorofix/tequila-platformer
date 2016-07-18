package platformer;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

public class GameWindow extends Frame implements WindowListener{

private static final long serialVersionUID = 8434543456858249978L;
private BufferedImage programIcon;
public static DisplayMode currentDisplayMode = null;
private BufferedImageLoader loader;
private BufferedImage cursorImage = null;

public GameWindow()
{
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice gd = ge.getDefaultScreenDevice();
	
	//currentDisplayMode = ge.getDefaultScreenDevice().getDisplayMode();
	//gd.setDisplayMode(new DisplayMode(1366, 768, 32, 60));
	
	this.setUndecorated(true);
	this.setIgnoreRepaint(true);
	this.setResizable(false);
	setBackground(Color.BLACK);
	gd.setFullScreenWindow(this);
    
    loader = new BufferedImageLoader();
    programIcon = loader.loadImage("/programIcon.png");
	this.setIconImage(programIcon);
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	cursorImage = loader.loadImage("/cursor.png");
	Cursor cursor = toolkit.createCustomCursor(cursorImage , new Point(getX(), getY()), "img");
	this.setCursor(cursor);
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