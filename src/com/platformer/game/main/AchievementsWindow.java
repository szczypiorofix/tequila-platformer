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

public class AchievementsWindow extends JFrame{


private static final long serialVersionUID = -6287250283593155470L;

private JPanel mainPanel;
private JScrollPane scroll;
private Achievements achievements;

public AchievementsWindow(JFrame parent, Achievements achievements)
{
	super("Osi¹gniêcia");
	this.achievements = achievements;
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setSize(410,400);
	setLocationRelativeTo(parent);
	setResizable(false);
	setLayout(new BorderLayout());
	mainPanel = new JPanel(new GridLayout(Achievements.maxAchievements, 1));
		
	mainPanel.add(new Button(this.achievements.getJump10TextShort(), this.achievements.getJump10Text(), this.achievements.getJump10Image(), this.achievements.isJumpCount10Complete()));
	mainPanel.add(new Button(this.achievements.getJump25TextShort(), this.achievements.getJump25Text(), this.achievements.getJump25Image(), this.achievements.isJumpCount25Complete()));
	mainPanel.add(new Button(this.achievements.getJump50TextShort(), this.achievements.getJump50Text(), this.achievements.getJump50Image(), this.achievements.isJumpCount50Complete()));
	mainPanel.add(new Button(this.achievements.getCoin20TextShort(), this.achievements.getCoin20Text(), this.achievements.getCoin20Image(), this.achievements.isCoinCount20Complete()));
	mainPanel.add(new Button(this.achievements.getCoin50TextShort(), this.achievements.getCoin50Text(), this.achievements.getCoin50Image(), this.achievements.isCoinCount50Complete()));
	mainPanel.add(new Button(this.achievements.getCoin100TextShort(), this.achievements.getCoin100Text(), this.achievements.getCoin100Image(), this.achievements.isCoinCount100Complete()));
	mainPanel.add(new Button(this.achievements.getCoin150TextShort(), this.achievements.getCoin150Text(), this.achievements.getCoin150Image(), this.achievements.isCoinCount150Complete()));
	mainPanel.add(new Button(this.achievements.getPowerup3TextShort(), this.achievements.getPowerup3Text(), this.achievements.getPowerup3Image(), this.achievements.isPowerupCount3Complete()));
	mainPanel.add(new Button(this.achievements.getComplete1LevelTextShort(), this.achievements.getComplete1LevelText(), this.achievements.getComplete1LevelImage(), this.achievements.isComplete1LevelComplete()));
	mainPanel.add(new Button(this.achievements.getComplete2LevelTextShort(), this.achievements.getComplete2LevelText(), this.achievements.getComplete2LevelImage(), this.achievements.isComplete2LevelComplete()));
	mainPanel.add(new Button(this.achievements.getComplete3LevelTextShort(), this.achievements.getComplete3LevelText(), this.achievements.getComplete3LevelImage(), this.achievements.isComplete3LevelComplete()));
	mainPanel.add(new Button(this.achievements.getComplete4LevelTextShort(), this.achievements.getComplete4LevelText(), this.achievements.getComplete4LevelImage(), this.achievements.isComplete4LevelComplete()));
	mainPanel.add(new Button(this.achievements.getComplete5LevelTextShort(), this.achievements.getComplete5LevelText(), this.achievements.getComplete5LevelImage(), this.achievements.isComplete5LevelComplete()));
	mainPanel.add(new Button(this.achievements.getFindAllCoinsTextShort(), this.achievements.getFindAllCoinsText(), this.achievements.getFindAllCoinsImage(), this.achievements.isFindAllCoinsComplete()));
	mainPanel.add(new Button(this.achievements.getFindAllPowerupsTextShort(), this.achievements.getFindAllPowerupsText(), this.achievements.getFindAllPowerupsImage(), this.achievements.isFindAllPowerupsComplete()));
	mainPanel.add(new Button(this.achievements.getNoHarmTextShort(), this.achievements.getNoHarmText(), this.achievements.getNoHarmImage(), this.achievements.isNoHarmComplete()));
	
	
	scroll = new JScrollPane(mainPanel);
	scroll.getVerticalScrollBar().setUnitIncrement(16);
	
	add(scroll, BorderLayout.CENTER);
}


private class Button extends JButton 
{

private static final long serialVersionUID = 7769376821914414727L;

public Button(String text, String tooltip, BufferedImage icon, boolean enabled)
{
	super();
	if (enabled) {
		setText(text);
		setToolTipText(tooltip);
	}
	setIcon(new ImageIcon(icon));
	setContentAreaFilled(false);
	setFocusable(false);
	setEnabled(enabled);
	setHorizontalAlignment(SwingConstants.LEFT);
}
}

}