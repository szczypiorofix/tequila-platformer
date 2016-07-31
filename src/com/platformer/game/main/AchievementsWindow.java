package com.platformer.game.main;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class AchievementsWindow extends JFrame{


private static final long serialVersionUID = -6287250283593155470L;



public AchievementsWindow(JFrame parent)
{
	super("Osi¹gniêcia");
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	setSize(400,400);
	setLocationRelativeTo(parent);
	setResizable(false);
}

}