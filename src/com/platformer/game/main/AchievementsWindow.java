package com.platformer.game.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class AchievementsWindow extends JFrame{


private static final long serialVersionUID = -6287250283593155470L;

private JPanel mainPanel;
private Achievements achievements = MainClass.getAchievementsInstance();

public AchievementsWindow(JFrame parent)
{
	super("Osi¹gniêcia");
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	setSize(400,400);
	setLocationRelativeTo(parent);
	setResizable(false);
	setLayout(new BorderLayout());
	mainPanel = new JPanel(new GridLayout(5, 1));
	add(mainPanel, BorderLayout.CENTER);
		
	mainPanel.add(new Button(achievements.getJump10Text(), achievements.getJump10Image(), achievements.isJumpCount10Complete()));
	mainPanel.add(new Button(achievements.getJump25Text(), achievements.getJump25Image(), achievements.isJumpCount25Complete()));
	mainPanel.add(new Button(achievements.getCoin20Text(), achievements.getCoin20Image(), achievements.isCoinCount20Complete()));
	mainPanel.add(new Button(achievements.getCoin50Text(), achievements.getCoin50Image(), achievements.isCoinCount50Complete()));
	mainPanel.add(new Button(achievements.getPowerup3Text(), achievements.getPowerup3Image(), achievements.isPowerupCount3Complete()));
}


private class Button extends JButton 
{

private static final long serialVersionUID = 7769376821914414727L;


public Button(String text, BufferedImage icon, boolean enabled)
{
	super(text);
	setContentAreaFilled(false);
	setFocusable(false);
	setIcon(new ImageIcon(icon));
	setEnabled(enabled);
	setHorizontalAlignment(SwingConstants.LEFT);
}
}

}