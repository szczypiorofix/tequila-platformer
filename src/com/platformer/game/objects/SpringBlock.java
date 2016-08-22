package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainClass;

public class SpringBlock extends GameObject{


private Textures tex = MainClass.getTexturesInstance();
private float x, y;
private float velX, velY;
private float width, height;
private int direction;
private boolean action, visible;
private int counter;

	
	
public SpringBlock(float x, float y)
{
	super();
	this.x = x;
	this.y = y;
	width = 50;
	height = 55;
	velX = 0;
	velY = 0;
	counter = 0;
	direction = 1;
	action = false;
	visible = true;
}


@Override
public void render(Graphics g) {
	if (action) g.drawImage(tex.springBlock2, (int) x, (int) y-10, null);
	else g.drawImage(tex.springBlock, (int) x, (int) y-5, null);
	//Graphics2D g2d = (Graphics2D) g;
	//g2d.draw(getBounds());
}

@Override
public void tick(LinkedList<GameObject> object) {
	if (action)
	{
		counter ++;	
	}
	if (counter > 12)
	{
		action = false;
		counter = 0;
	}
	
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y-10, (int) width, (int) height+10);
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