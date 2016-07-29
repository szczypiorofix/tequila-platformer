package com.platformer.game.main;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class HowToPlayWindow extends JFrame{

private static final long serialVersionUID = 4829978840676840856L;




public HowToPlayWindow(JFrame parent)
{
	super("Jak graæ");
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	setSize(400,400);
	setLocationRelativeTo(parent);
	setResizable(false);
	
	
}
}