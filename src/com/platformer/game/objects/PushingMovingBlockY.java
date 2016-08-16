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
private float width, height;
private Textures tex = MainScreen.getTexturesInstance();
private int startPos = 0;
private boolean action, visible;
private int direction;
private ObjectId id;
private int counter;
private int WAITING_TIME = 30;

	
public PushingMovingBlockY(ObjectId id, float x, float y)
{
	super();
	this.x = x;
	this.y = y;
	this.id = id;
	velX = 0;
	velY = 0;
	width = 74;
	height = 50;
	direction = 0;
	counter = 0;
	action = false;
	visible = true;
	startPos = (int) (this.y);
}

@Override
public void render(Graphics g) {
	if (action) g.drawImage(tex.pushingMovingBlockYOn, (int) x, (int) y, null);
	else g.drawImage(tex.pushingMovingBlockYOff, (int) x, (int) y, null);
}

@Override
public void tick(LinkedList<GameObject> object) {
	
	if (action)
	{
		if (y >= startPos) {
			counter++;
			velY = 0;
			if (counter >= WAITING_TIME) 
				velY = -1f;
		}
		if (y <= startPos - 160) {
			counter--;
			velY = 0;
			if (counter < 0) 
				velY = 1f;
		}
	}
	
	if (action) y += velY;
	action = false;
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, (int) width, (int) height);
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
public ObjectId getId() {
	return id;
}

@Override
public void setId(ObjectId id) {
	this.id = id;
}

@Override
public void setX(float x) {
	this.x = x;
}

@Override
public void setY(float y) {
	this.y = y;
}

@Override
public float getWidth() {
	return width;
}

@Override
public void setWidth(float width) {
	this.width = width;
}

@Override
public float getHeight() {
	return height;
}

@Override
public void setHeight(float height) {
	this.height = height;
}

@Override
public boolean isAction() {
	return action;
}

@Override
public void setAction(boolean action) {
	this.action = action;
}

@Override
public int getDirection() {
	return direction;
}

@Override
public void setDirection(int direction) {
	this.direction = direction;
}

@Override
public boolean isVisible() {
	return visible;
}

@Override
public void setVisible(boolean visible) {
	this.visible = visible;
}

}