package com.platformer.game.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class HallOfFameWindow extends JFrame {

private static final long serialVersionUID = -5965729465210285476L;


private JScrollPane scroll;
private JPanel mainPanel;
private HallOfFame hallOfFame;


public HallOfFameWindow(JFrame parent, HallOfFame hallOfFame)
{
	super("Najlepsze wyniki");
	this.hallOfFame = hallOfFame;
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	setSize(400,400);
	setLocationRelativeTo(parent);
	setResizable(false);
	
	mainPanel = new JPanel(new GridLayout(hallOfFame.getHallOfFameList().size(), 1));
	
	for (int i = 0; i < this.hallOfFame.getHallOfFameList().size(); i++)
	{
		mainPanel.add(new Button(i+1+ ".  "+this.hallOfFame.getHallOfFameList().get(i).getName(), this.hallOfFame.getHallOfFameList().get(i).getScore(), this.hallOfFame.getHallOfFameList().get(i).getMilis()));
	}
	
	scroll = new JScrollPane(mainPanel);
	scroll.getVerticalScrollBar().setUnitIncrement(16);
	
	add(scroll, BorderLayout.CENTER);
}

private class Button extends JButton 
{

private static final long serialVersionUID = -136714280294712562L;

public Button(String name, int score, float milis)
{
	super();
	setText(name +" " +score +" " +milis);
	setContentAreaFilled(false);
	setFocusable(false);
	setHorizontalAlignment(SwingConstants.LEFT);
}
}
}