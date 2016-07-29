package com.platformer.game.main;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class HallOfFameWindow extends JFrame {

private static final long serialVersionUID = -5965729465210285476L;






public HallOfFameWindow(JFrame parent)
{
	super("Najlepsze wyniki");
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	setSize(400,400);
	setLocationRelativeTo(parent);
	setResizable(false);
}

	

	
}