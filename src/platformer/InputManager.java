package platformer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class InputManager implements KeyListener {

private final int MAX_KEYS = 256;;
private int keys[] = new int[MAX_KEYS];
private boolean[] keyUp = new boolean[MAX_KEYS];
private boolean[] keyDown = new boolean[MAX_KEYS];
private boolean keypressed;
private boolean keyreleased;
private String keyCache = "";
private int key = 0;
private static InputManager instance = new InputManager();


public InputManager()
{
}

public static InputManager getInstance() {
	return instance;
}

public void update() {
	keyUp = new boolean[MAX_KEYS];
	keyreleased = false;
	if( keyCache.length() > 1024 ) {
		keyCache = "";
	}
}

@Override
public void keyPressed(KeyEvent e) {
	if (e.getKeyCode() >= 0 && e.getKeyCode() < MAX_KEYS)
	{
		keys[e.getKeyCode()] = (int) System.currentTimeMillis();
		keyDown[e.getKeyCode()] = true;
		keyUp[e.getKeyCode()] = false;
		key = e.getKeyCode();
		keypressed = true;
		keyreleased = false;
	}
}

@Override
public void keyReleased(KeyEvent e) {
	if (e.getKeyCode() >= 0 && (e.getKeyCode() < MAX_KEYS))
	{
		keys[e.getKeyCode()] = 0;
		keyDown[e.getKeyCode()] = false;
		keyUp[e.getKeyCode()] = true;
		key = 0;
		keypressed = false;
		keyreleased = true;
	}
}

@Override
public void keyTyped(KeyEvent e) {
	keyCache += e.getKeyChar();
}

public int getKey()
{
	return key;
}

public boolean isKeyDown(int key)
{
	return keyDown[key];
}

public boolean isKeyUp(int key)
{
	return keyUp[key];
}

public boolean isAnyKeyDown()
{
	return keypressed;
}

public boolean isAnyKeyUp()
{
	return keyreleased;
}
}