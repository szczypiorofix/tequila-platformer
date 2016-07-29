package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;

public class MovingBlockX extends GameObject{

	
private final static int MOVING_BLOCK_WIDTH = 74;
private final static int MOVING_BLOCK_HEIGHT = 50;
private Textures tex = MainScreen.getInstance();
private float velX = 0;
private float velY = 0;
private float x, y;
private int startPos = 0;




public MovingBlockX(ObjectId id, float x, float y) {
	super(id, x, y, MOVING_BLOCK_WIDTH, MOVING_BLOCK_HEIGHT, 0, 0, 0);
	this.x = x;
	this.y = y;
	velX = 0;
	velY = 0;
	startPos = (int) x;
}

@Override
public void render(Graphics g) {
	g.drawImage(tex.movingBlockX, (int)x, (int)y, MOVING_BLOCK_WIDTH, MOVING_BLOCK_HEIGHT, null);
}

@Override
public void tick(LinkedList<GameObject> object) {

	
	if (x > startPos + 240) velX = -1.0f;
	else if (x <= startPos) velX = 1.0f;
	
	x += velX;
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
public float getX()
{
	return x;
}

@Override
public float getY() {
	return y;
}

@Override
public void setVelX(float velX) {	
}

@Override
public void setVelY(float velY) {	
}

}