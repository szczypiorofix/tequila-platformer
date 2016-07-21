package com.platformer.game.main;

import javax.swing.JFrame;

public class CreditsWindow extends JFrame{


private static final long serialVersionUID = 3999992812968059965L;





public CreditsWindow(JFrame parent)
{
	super("O grze ...");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setSize(400,400);
	setLocationRelativeTo(parent);
	setResizable(false);
}
	
	
}