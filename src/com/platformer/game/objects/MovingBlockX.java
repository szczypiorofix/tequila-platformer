package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;

public class MovingBlockX extends GameObject{

	
private Textures tex = MainScreen.getInstance();
private float velX;
private float velY;
private float x, y;
private float width, height;
private int direction;
private ObjectId id;
private boolean action;
private int startPos = 0;




public MovingBlockX(ObjectId id, float x, float y) {
	super();
	this.x = x;
	this.y = y;
	this.id = id;
	velX = 0;
	velY = 0;
	width = 74;
	height = 50;
	direction = 1;
	action = false;
	startPos = (int) x;
}

@Override
public void render(Graphics g) {
	g.drawImage(tex.movingBlockX, (int)x, (int)y, (int) width, (int) height, null);
}

@Override
public void tick(LinkedList<GameObject> object) {

	if (x > startPos + 240) velX = -1.0f;
	else if (x <= startPos) velX = 1.0f;
	
	x += velX;
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, (int) width, (int) height);
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
public float getX(){
	return x;
}

@Override
public float getY() {
	return y;
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
public void setVelX(float velX) {
	this.velX = velX;
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
}