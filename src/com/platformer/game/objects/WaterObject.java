package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Animation;
import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainClass;

public class WaterObject extends GameObject{

	

private Textures tex = MainClass.getTexturesInstance();
private int type;
private float x, y;
private float velX, velY;
private float width, height;
private boolean action, visible;
private int direction;
private Animation waterAnim;


public WaterObject(float x, float y, int type) {
	super();
	this.x = x;
	this.y = y;
	width = 50;
	height = 50;
	action = false;
	visible = true;
	direction = 1;
	velX = 0;
	velY = 0;
	waterAnim = new Animation(10, tex.water5, tex.water4, tex.water3, tex.water2, tex.water1);
	this.type = type;
}

@Override
public void render(Graphics g) {
	if (type == 0) {
		waterAnim.drawAnimation(g, (int) x, (int) y, false);
	}
	else g.drawImage(tex.waterDeep, (int)x, (int)y, (int) width, (int) height, null);
}

@Override
public void tick(LinkedList<GameObject> object) {
	waterAnim.runAnimation();
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