package com.platformer.game.main;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class CollectiblesWindow extends JFrame {

private static final long serialVersionUID = -2134642895477978506L;








public CollectiblesWindow(JFrame parent)
{
	super("Znajdüki");
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	setSize(400,400);
	setLocationRelativeTo(parent);
	setResizable(false);
}	
}