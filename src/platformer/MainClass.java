package platformer;

import java.awt.EventQueue;
import java.io.File;


public class MainClass implements Runnable{

private GameWindow gameWindow;
private MainScreen mainScreen;
public static int WIDTH = 0, HEIGHT = 0;
private Thread thread;
private int fps_count = 0, ticks_count = 0;
private boolean running = false;
private boolean gamepadEnabled = false;


public MainClass()
{
	gameWindow = new GameWindow();
	
	File f = new File("input.txt");
	if(f.exists() && !f.isDirectory()) { 
	    gamepadEnabled = true;
	}
	
	mainScreen = new MainScreen(gameWindow, gamepadEnabled);

	gameWindow.setVisible(true);
	WIDTH = mainScreen.getWidth();
	HEIGHT = mainScreen.getHeight();
	
	mainScreen.addElements();
	gameThreadStart();
}


public synchronized void gameThreadStart()
{
	if (running) return;
	running = true;
	thread = new Thread(this);
	thread.start();
}


@Override
public void run() {
	
	gameWindow.requestFocus();
	
	// GAME LOOP	
	// FIXED GAME LOOP, FPS = variable, TICKS = 60
	
	long lastTime = System.nanoTime();
	double amountOfTicks = 60.0;
	double ns = 1000000000 / amountOfTicks;
	double delta = 0;
	long timer = System.currentTimeMillis();
	int updates = 0;
	int frames = 0;
	
	while(running)
	{	
		long now = System.nanoTime();
		delta += (now - lastTime) / ns;
		lastTime = now;
		
		while(delta >= 1)
		{
			if (mainScreen.isExit()) gameWindow.showWindow(false);  // PROGRAM EXIT
			mainScreen.tick();
			updates++;
			delta--;
		}
		
		mainScreen.render(fps_count, ticks_count);
		frames++;
		
		if (System.currentTimeMillis() - timer > 1000)
		{
			timer += 1000;
			fps_count = frames;
			ticks_count = updates;
			frames = 0;
			updates = 0;
		}
	}
}

public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable()
	{
		@Override
		public void run()
		{
			new MainClass();
		}
	});
}
}