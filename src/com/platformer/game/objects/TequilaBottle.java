package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;



public class TequilaBottle extends GameObject{


private float x, y;
private float velX, velY;
private float width, height;
private int direction;
private boolean action, visible;

	
public TequilaBottle(float x, float y) {
		super();
		this.x = x;
		this.y = y;
		direction = 0;
		action = false;
		visible = true;
		width = 30;
		height = 80;
		velX = 0;
		velY = 0;
}

@Override
public void render(Graphics g) {
	g.drawImage(Textures.getInstance().tequilaImage.getScaledInstance((int) width, (int) height, 0), (int) x, (int) y-40, null);
}

@Override
public void tick(LinkedList<GameObject> object) {	
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y-40, (int) width, (int) height);
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