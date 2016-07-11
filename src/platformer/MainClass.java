package platformer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class MainClass implements Runnable{

private GameWindow gameWindow;
private MainScreen mainScreen;
public static int WIDTH = 0, HEIGHT = 0;
private Thread thread;
private int fps_count = 0, ticks_count = 0;
private boolean running = false;


public MainClass()
{
	gameWindow = new GameWindow();
	mainScreen = new MainScreen(gameWindow);
	gameWindow.showWindow(true);	
		
	WIDTH = mainScreen.getWidth();
	HEIGHT = mainScreen.getHeight();

	mainScreen.addElements();
	gameThreadStart();
}


public void gameThreadStart()
{
	if (running)
		return;
	running = true;
	
	thread = new Thread(this);
	thread.start();
}


@Override
public void run() {
	
	// GAME LOOP
	
	// TIMER GAMER LOOP, FPS = 60, TICKS = 60
	
	int FPS = 50;
	int nrOfFrames = (int) 1000 / FPS;
	Timer timerLoop = new Timer(nrOfFrames, new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			gameWindow.requestFocusInWindow();
			mainScreen.tick();
			mainScreen.render(FPS, FPS);
			if (mainScreen.isExit()) gameWindow.showWindow(false);  // PROGRAM EXIT
		}
	});
	
	timerLoop.setInitialDelay(100);
	timerLoop.start();
	
	
	
	// FIXED GAME LOOP, FPS = variable, TICKS = 60
	/**
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
		//gameWindow.requestFocus();
		
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
	**/
}

public static void main(String[] args) {
	new MainClass();
}
}