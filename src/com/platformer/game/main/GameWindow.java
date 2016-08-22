package com.platformer.game.main;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.platformer.game.graphics.BufferedImageLoader;

public class GameWindow extends Frame implements WindowListener{

private static final long serialVersionUID = 8434543456858249978L;
public static DisplayMode currentDisplayMode = null;
private BufferedImageLoader loader = new BufferedImageLoader();

public GameWindow()
{	
	this.setIgnoreRepaint(true);
	this.setTitle("TEQUILA PLATFORMER");
	this.setResizable(false);
	this.setSize(1006, 628);
	this.setLocationRelativeTo(null);
	setBackground(Color.BLACK);    
	this.setIconImage(loader.loadImage("/programIcon.png"));
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
public void windowClosing(WindowEvent arg0) {
	System.exit(0);
}

@Override
public void windowDeactivated(WindowEvent arg0) {}

@Override
public void windowDeiconified(WindowEvent arg0) {}

@Override
public void windowIconified(WindowEvent arg0) {}

@Override
public void windowOpened(WindowEvent arg0) {}

}