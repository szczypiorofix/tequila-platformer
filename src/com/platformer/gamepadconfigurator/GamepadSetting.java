package com.platformer.gamepadconfigurator;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.platformer.game.graphics.BufferedImageLoader;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;



public class GamepadSetting {


private JFrame frame;
private JPanel northPane = new JPanel(new FlowLayout()), centerPane = new JPanel(), eastPane = new JPanel(), westPane = new JPanel(), southPane = new JPanel();
private JLabel componentsNumber = new JLabel("0");
private boolean anyGamePad = false;
private Controller myGamepad;
private Controller[] controllers;
private Component[] components;
private Component left, right, jump, start;
private float leftValue, rightValue, jumpValue, startValue;
private JButton saveButton = new JButton("Zapisz i zamknij"), jumpButton = new JButton("JUMP"), leftButton = new JButton("LEFT"), rightButton = new JButton("RIGHT"), startButton = new JButton("START/EXIT");
private ActionListener buttonListener;
private int awaitingButton = 0; // 1 - right, 2 - left, 3 - jump, 4 - start/exit
private JDialog awaitingDialog;
private JLabel leftLabel, rightLabel, jumpLabel, startLabel;
private JLabel awaitingLabel;
private Thread thread;
private InputThread inputThread;


public GamepadSetting()
{
	frame = new JFrame("GamePad configuration");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(400,  200);
	frame.setLocationRelativeTo(null);
	frame.setResizable(false);
	frame.setLayout(new BorderLayout());
	
	frame.add(northPane, BorderLayout.NORTH);
	frame.add(centerPane, BorderLayout.CENTER);
	frame.add(eastPane, BorderLayout.EAST);
	frame.add(westPane, BorderLayout.WEST);
	frame.add(southPane, BorderLayout.SOUTH);
	frame.setIconImage(new BufferedImageLoader().loadImage("/gamepad.png"));
	southPane.add(saveButton);
	saveButton.setEnabled(false);
	saveButton.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			Properties prop = new Properties();
			prop.put("Left", left.getName());
			prop.put("value_left", Float.toString(leftValue));
			prop.put("Right", right.getName());
			prop.put("value_right", Float.toString(rightValue));
			prop.put("Jump", jump.getName());
			prop.put("value_jump", Float.toString(jumpValue));
			prop.put("Start", start.getName());
			prop.put("value_start", Float.toString(startValue));
			File file = new File("input.cfg");
			try {
			FileOutputStream fileOut = new FileOutputStream(file);
			prop.store(fileOut, "Gamepad Input");
			fileOut.close();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			System.exit(0);
		}
	});

	controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
	
    for(int i =0;i<controllers.length;i++) {

    	if (controllers[i].getType() == Controller.Type.GAMEPAD || controllers[i].getType() == Controller.Type.STICK) {
    		myGamepad = controllers[i];
    		anyGamePad = true;
    	}
    }
    
    if (!anyGamePad) {
    	JOptionPane.showMessageDialog(frame, "Nie wykryto ¿adnego urz¹dzenia typu gamepad lub stick", "ERROR 13", JOptionPane.ERROR_MESSAGE);
    	System.exit(0);
    }
    
    northPane.add(new JLabel(myGamepad.getName()));
	northPane.add(new JLabel(" Przyciski i osie: "));
	northPane.add(componentsNumber);
	
	components = myGamepad.getComponents();
	componentsNumber.setText(components.length+"");
   
	leftLabel = new JLabel("<null>");
	rightLabel = new JLabel("<null>");
	jumpLabel = new JLabel("<null>");
	startLabel = new JLabel("<null>");
	
   centerPane.setLayout(new GridLayout(4, 3));
        
   leftButton.setActionCommand("LEFT");
   rightButton.setActionCommand("RIGHT");
   jumpButton.setActionCommand("JUMP");
   startButton.setActionCommand("START");
   
   
   centerPane.add(new JLabel("W lewo"));
   centerPane.add(leftLabel);
   centerPane.add(leftButton);
   centerPane.add(new JLabel("W prawo"));
   centerPane.add(rightLabel);
   centerPane.add(rightButton);
   centerPane.add(new JLabel("Skok"));
   centerPane.add(jumpLabel);
   centerPane.add(jumpButton);
   centerPane.add(new JLabel("Start/Exit"));
   centerPane.add(startLabel);
   centerPane.add(startButton);
    
   buttonListener = new ButtonListener();
   leftButton.addActionListener(buttonListener);
   rightButton.addActionListener(buttonListener);
   jumpButton.addActionListener(buttonListener);
   startButton.addActionListener(buttonListener);
   
   awaitingDialog = new JDialog(frame, "Oczekiwanie...", true);
   awaitingDialog.setSize(300, 100);
   awaitingDialog.setLocationRelativeTo(frame);
   awaitingDialog.setResizable(false);
   awaitingLabel = new JLabel();
   awaitingDialog.add(awaitingLabel);
   frame.setVisible(true);   
   
   inputThread = new InputThread();
   thread = new Thread(inputThread);
   thread.start();
}



public class ButtonListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) {		
		if (e.getActionCommand().equalsIgnoreCase("RIGHT")) awaitingButton = 1;
		if (e.getActionCommand().equalsIgnoreCase("LEFT")) awaitingButton = 2;
		if (e.getActionCommand().equalsIgnoreCase("JUMP")) awaitingButton = 3;
		if (e.getActionCommand().equalsIgnoreCase("START")) awaitingButton = 4;
		
		if (awaitingButton == 1) awaitingLabel.setText("Oczekiwanie na przycisk RIGHT ...");
		else if (awaitingButton == 2) awaitingLabel.setText("Oczekiwanie na przycisk LEFT ...");
		else if (awaitingButton == 3) awaitingLabel.setText("Oczekiwanie na przycisk JUMP ...");
		else if (awaitingButton == 3) awaitingLabel.setText("Oczekiwanie na przycisk START/EXIT ...");
		awaitingDialog.setVisible(true);
		
	}
}


public class InputThread implements Runnable
{

@Override
public void run()
{

	while(true) {
		myGamepad.poll();
        
        net.java.games.input.EventQueue queue = myGamepad.getEventQueue();
        
        Event event = new Event();
        while(queue.getNextEvent(event))
        {
        	Component comp = event.getComponent();
        	float value = event.getValue();          	
        	if ((awaitingDialog.isVisible()) && awaitingButton != 0) {
        		
        		if (awaitingButton == 1 && (value > 0.2f || value < -0.2f)) {
        			System.out.println("RIGHT przypisany do " +comp.getName() +" " +value+"");
        			rightLabel.setText(comp.getName() +" " +value);
        			right = comp;
        			rightValue = value;
        		}
        		if (awaitingButton == 2 && (value > 0.2f || value < -0.2f)) {
        			System.out.println("LEFT przypisany do " +comp.getName() +" " +value+"");
        			leftLabel.setText(comp.getName() +" " +value);
        			left = comp;
        			leftValue = value;
        		}
        		if (awaitingButton == 3 && (value > 0.2f || value < -0.2f)) {
        			System.out.println("JUMP przypisany do " +comp.getName() +" " +value+"");
        			jumpLabel.setText(comp.getName() +" " +value);
        			jump = comp;
        			jumpValue = value;
        		}
        		if (awaitingButton == 4 && (value > 0.2f || value < -0.2f)) {
        			System.out.println("START/EXIT przypisany do " +comp.getName() +" " +value+"");
        			startLabel.setText(comp.getName() +" " +value);
        			start = comp;
        			startValue = value;
        		}
        		awaitingButton = 0;
        		awaitingDialog.setVisible(false);
        		if (left != null && right != null && jump != null && start != null) saveButton.setEnabled(true);
        	}
        }
	}
}
}


public static void main(String[] args)
{
	EventQueue.invokeLater(new Runnable()
	{
		@Override
		public void run()
		{
			new GamepadSetting();	
		}
	});
}
}