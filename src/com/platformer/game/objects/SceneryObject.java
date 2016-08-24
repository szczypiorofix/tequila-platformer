package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;


public class SceneryObject extends GameObject{


private float x, y;
private float velX, velY;
private float width, height;
private boolean action, visible;
private int direction;
private int type;



public SceneryObject(float x, float y, int type) {
	super();
	this.x = x;
	this.y = y;
	velX = 0;
	velY = 0;
	direction = 1;
	action = false;
	visible = true;
	width = 45;
	height = 45;
	this.type = type;
}

@Override
public void render(Graphics g) {
	if (type == 16) g.drawImage(Textures.getInstance().sceneryObjects[0], (int) x, (int) y-36, null);
	if (type == 17) g.drawImage(Textures.getInstance().sceneryObjects[1], (int) x, (int) y-22, null);
	if (type == 18) g.drawImage(Textures.getInstance().sceneryObjects[2], (int) x, (int) y-60, null);
	if (type == 19) g.drawImage(Textures.getInstance().sceneryObjects[3], (int) x, (int) y+5, null);
	if (type == 20) g.drawImage(Textures.getInstance().sceneryObjects[4], (int) x, (int) y-45, null);
	if (type == 21) g.drawImage(Textures.getInstance().sceneryObjects[5], (int) x, (int) y, null);
	if (type == 22) g.drawImage(Textures.getInstance().sceneryObjects[6], (int) x, (int) y, null);
	if (type == 23) g.drawImage(Textures.getInstance().sceneryObjects[7], (int) x, (int) y-37, null);
	if (type == 24) g.drawImage(Textures.getInstance().sceneryObjects[8], (int) x, (int) y-37, null);
	if (type == 25) g.drawImage(Textures.getInstance().sceneryObjects[9], (int) x-10, (int) y+2, null);
	if (type == 26) g.drawImage(Textures.getInstance().sceneryObjects[10], (int) x-10, (int) y-20, null);
	if (type == 27) g.drawImage(Textures.getInstance().sceneryObjects[11], (int) x-10, (int) y-209, null);
}

@Override
public void tick(LinkedList<GameObject> object) {
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