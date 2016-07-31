package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;

public class PushingMovingBlockX extends GameObject{


private float x, y;
private float velX, velY;
private static final int PUSHING_MOVING_BLOCK_X_WIDTH = 74, PUSHING_MOVING_BLOCK_X_HEIGHT = 50;
private Textures tex = MainScreen.getInstance();
private int startPos = 0;
private boolean shooting;

	
public PushingMovingBlockX(ObjectId id, float x, float y)
{
	super(id, x, y, PUSHING_MOVING_BLOCK_X_WIDTH, PUSHING_MOVING_BLOCK_X_HEIGHT, 0, 0, 0);
	this.x = x;
	this.y = y;
	velX = 0;
	velY = 0;
	shooting = false;
	startPos = (int) this.x;
}

@Override
public void render(Graphics g) {
	if (shooting) g.drawImage(tex.pushingMovingBlockXOn, (int) x, (int) y, null);
	else g.drawImage(tex.pushingMovingBlockXOff, (int) x, (int) y, null);
}

@Override
public void tick(LinkedList<GameObject> object) {

	if (x > startPos + 240) velX = -1.0f;
	else if (x <= startPos) velX = 1.0f;
	if (shooting) x += velX;
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, PUSHING_MOVING_BLOCK_X_WIDTH, PUSHING_MOVING_BLOCK_X_HEIGHT);
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