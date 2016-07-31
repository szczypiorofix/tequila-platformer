package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;

public class MovingBlockY extends GameObject{

	
private final static int MOVING_BLOCK_WIDTH = 74;
private final static int MOVING_BLOCK_HEIGHT = 50;
private Textures tex = MainScreen.getInstance();
private float x, y;
private float velX = 0;
private float velY = 0;
private int startPos = 0;




public MovingBlockY(ObjectId id, float x, float y) {
	super(id, x, y, MOVING_BLOCK_WIDTH, MOVING_BLOCK_HEIGHT, 0, 0, 0);
	this.x = x;
	this.y = y + 15;
	velX = 0;
	velY = 0;
	startPos = (int) (this.y);
}

@Override
public void render(Graphics g) {
	g.drawImage(tex.movingBlockY, (int)x, (int) y, MOVING_BLOCK_WIDTH, MOVING_BLOCK_HEIGHT, null);
}

@Override
public void tick(LinkedList<GameObject> object) {

	if (y >= startPos) velY = -1;
	if (y < startPos - 160) velY = 1;
	
	y += velY;
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, MOVING_BLOCK_WIDTH, MOVING_BLOCK_HEIGHT);
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
public float getY()
{
	return y;
}

@Override
public float getX() {
	return x;
}

@Override
public void setVelX(float velX) {	
}

@Override
public void setVelY(float velY) {	
}

@Override
public void setShooting(boolean shooting) {	
}

@Override
public boolean isShooting() {
	return false;
}
}