package com.platformer.game.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class AchievementsWindow extends JFrame{


private static final long serialVersionUID = -6287250283593155470L;

private JPanel mainPanel;
private Achievements achievements = MainClass.getAchievementsInstance();
private JScrollPane scroll;

public AchievementsWindow(JFrame parent)
{
	super("Osi¹gniêcia");
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	setSize(410,400);
	setLocationRelativeTo(parent);
	setResizable(false);
	setLayout(new BorderLayout());
	mainPanel = new JPanel(new GridLayout(Achievements.maxAchievements, 1));
		
	mainPanel.add(new Button(achievements.getJump10Text(), achievements.getJump10Image(), achievements.isJumpCount10Complete()));
	mainPanel.add(new Button(achievements.getJump25Text(), achievements.getJump25Image(), achievements.isJumpCount25Complete()));
	mainPanel.add(new Button(achievements.getJump50Text(), achievements.getJump50Image(), achievements.isJumpCount50Complete()));
	mainPanel.add(new Button(achievements.getCoin20Text(), achievements.getCoin20Image(), achievements.isCoinCount20Complete()));
	mainPanel.add(new Button(achievements.getCoin50Text(), achievements.getCoin50Image(), achievements.isCoinCount50Complete()));
	mainPanel.add(new Button(achievements.getCoin100Text(), achievements.getCoin100Image(), achievements.isCoinCount100Complete()));
	mainPanel.add(new Button(achievements.getCoin150Text(), achievements.getCoin150Image(), achievements.isCoinCount150Complete()));
	mainPanel.add(new Button(achievements.getPowerup3Text(), achievements.getPowerup3Image(), achievements.isPowerupCount3Complete()));
	mainPanel.add(new Button(achievements.getComplete1LevelText(), achievements.getComplete1LevelImage(), achievements.isComplete1LevelComplete()));
	mainPanel.add(new Button(achievements.getComplete2LevelText(), achievements.getComplete2LevelImage(), achievements.isComplete2LevelComplete()));
	mainPanel.add(new Button(achievements.getComplete3LevelText(), achievements.getComplete3LevelImage(), achievements.isComplete3LevelComplete()));
	mainPanel.add(new Button(achievements.getComplete4LevelText(), achievements.getComplete4LevelImage(), achievements.isComplete4LevelComplete()));
	mainPanel.add(new Button(achievements.getComplete5LevelText(), achievements.getComplete5LevelImage(), achievements.isComplete5LevelComplete()));
	mainPanel.add(new Button(achievements.getFindAllCoinsText(), achievements.getFindAllCoinsImage(), achievements.isFindAllCoinsComplete()));
	mainPanel.add(new Button(achievements.getFindAllPowerupsText(), achievements.getFindAllPowerupsImage(), achievements.isFindAllPowerupsComplete()));
	mainPanel.add(new Button(achievements.getNoHarmText(), achievements.getNoHarmImage(), achievements.isNoHarmComplete()));
	
	
	scroll = new JScrollPane(mainPanel);
	scroll.getVerticalScrollBar().setUnitIncrement(16);
	
	add(scroll, BorderLayout.CENTER);
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