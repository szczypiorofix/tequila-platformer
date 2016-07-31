package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;

public class PushingMovingBlockY extends GameObject{

	
private float x, y;
private float velX, velY;
private static final int PUSHING_MOVING_BLOCK_Y_WIDTH = 74, PUSHING_MOVING_BLOCK_Y_HEIGHT = 50;
private Textures tex = MainScreen.getInstance();
private int startPos = 0;
private boolean shooting;


	
public PushingMovingBlockY(ObjectId id, float x, float y)
{
	super(id, x, y, PUSHING_MOVING_BLOCK_Y_WIDTH, PUSHING_MOVING_BLOCK_Y_HEIGHT, 0, 0, 0);
	this.x = x;
	this.y = y;
	velX = 0;
	velY = 0;
	shooting = false;
	startPos = (int) (this.y);
}

@Override
public void render(Graphics g) {
	if (shooting) g.drawImage(tex.pushingMovingBlockYOn, (int) x, (int) y, null);
	else g.drawImage(tex.pushingMovingBlockYOff, (int) x, (int) y, null);
}

@Override
public void tick(LinkedList<GameObject> object) {
	
	if (y >= startPos) velY = -1;
	else if (y < startPos - 160) velY = 1;
	
	if (shooting) y += velY;
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, PUSHING_MOVING_BLOCK_Y_WIDTH, PUSHING_MOVING_BLOCK_Y_HEIGHT);
}

@Override
public float getX() {
	return x;
}

@Override
public float getY() {
	return y;
}

@Override
public float getVelX() {
	return velX;
}

@Override
public float getVelY() {
	return velY;
}

@Override
public void setVelX(float velX) {
	this.velX = velX;
}

@Override
public void setVelY(float velY) {
	this.velY = velY;	
}

@Override
public boolean isShooting() {
	return shooting;
}

@Override
public void setShooting(boolean shooting) {
	this.shooting = shooting;
}
}