package com.platformer.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public final class InputManager implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{

private final int MAX_KEYS = 256;;

private boolean[] keys = null;
private KeyState[] keyState = null;
private int keyCode;
private char key;


private enum KeyState
{
	PRESSED, RELEASED, ONCE;
}

public InputManager()
{
	keys = new boolean[MAX_KEYS];
	keyState = new KeyState[MAX_KEYS];
	for (int i = 0; i < MAX_KEYS; i++) keyState[i] = KeyState.RELEASED;
}


public synchronized void update() {
	for( int i = 0; i < MAX_KEYS; ++i )
	{
	      if( keys[ i ] ) {
	        if( keyState[ i ] == KeyState.RELEASED )
	        	keyState[ i ] = KeyState.ONCE;
	        else
	        	keyState[ i ] = KeyState.PRESSED;
	      } else {
	    	  keyState[ i ] = KeyState.RELEASED;
	      }
	}
}

public boolean isKeyPressed(int key)
{
	return keyState[key] == KeyState.ONCE || keyState[key] == KeyState.PRESSED;
}

public boolean isKeyPressedOnce(int key)
{
	return keyState[key] == KeyState.ONCE;
}

public boolean isAnyKeyPressedOnce()
{
	boolean b = false;
	for (int i = 0; i < MAX_KEYS; i++)
	{
		if (keys[i] == true && keyState[i] == KeyState.ONCE) {
			b = true;
			break;
		}
	}
	return b;
}

public boolean isAnyKeyPressed()
{
	boolean b = false;
	for (int i = 0; i < MAX_KEYS; i++)
	{
		if (keys[i] == true && keyState[i] == KeyState.PRESSED) {
			b = true;
			break;
		}
	}
	return b;
}

public int getKeyNumber()
{
	return keyCode;
}

public char getKey()
{
	return key;
}

@Override
public synchronized void keyPressed(KeyEvent e) {
	keyCode = e.getKeyCode();
	keyCode = e.getKeyCode();
	key = e.getKeyChar();
	if (keyCode >= 0 && keyCode < MAX_KEYS)
	{
		keys[keyCode] = true;
	}
}

@Override
public synchronized void keyReleased(KeyEvent e) {

	keyCode = e.getKeyCode();
	if (keyCode >= 0 && keyCode < MAX_KEYS)
	{
		keys[keyCode] = false;
	}
}

@Override
public void keyTyped(KeyEvent e) {}


@Override
public void mouseWheelMoved(MouseWheelEvent m) {
}


@Override
public void mouseDragged(MouseEvent e) {
}


@Override
public void mouseMoved(MouseEvent e) {
}


@Override
public void mouseClicked(MouseEvent e) {
}


@Override
public void mouseEntered(MouseEvent e) {
}


@Override
public void mouseExited(MouseEvent e) {
}


@Override
public void mousePressed(MouseEvent e) {
}


@Override
public void mouseReleased(MouseEvent e) {
}

}