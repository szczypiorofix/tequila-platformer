package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import com.platformer.game.graphics.Textures;



public class AngryCactus extends GameObject{


private float width, height;
private int direction;
private boolean action, visible;
private static final int SHOOTING_MAX = 100;
private int shooting_time = SHOOTING_MAX;
private float x, y;
private float velX, velY;


	
public AngryCactus(float x, float y) {
	super();
	this.x = x;
	this.y = y;
	width = 32;
	height = 32;
	velX = 0;
	velY = 0;
	direction = 1;
	action = false;
	visible = true;
}

@Override
public void render(Graphics g) {
	if (action) {
		if (direction == 1) g.drawImage(Textures.getInstance().angryCactusR, (int) x, (int) y-30, null);
		else g.drawImage(Textures.getInstance().angryCactusL, (int) x, (int) y-30, null);	
	}
	else g.drawImage(Textures.getInstance().angryCactus0, (int) x, (int) y-30, null);
}

@Override
public void tick(LinkedList<GameObject> object) {
	if (action)
	{
		if (shooting_time > 0) shooting_time--;
		else {
			shooting_time = SHOOTING_MAX;
			action = false;
		}
	}
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x-300, (int) y-30, 700, 100);
}


@Override
public float getX() {
	return x;
}

@Override
public void setX(float x) {
	this.x = x;
}

@Override
public float getY() {
	return y;
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
public float getVelX() {
	return velX;
}

@Override
public void setVelX(float velX) {
	this.velX = velX;
}

@Override
public float getVelY() {
	return velY;
}

@Override
public void setVelY(float velY) {
	this.velY = velY;
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