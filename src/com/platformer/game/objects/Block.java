package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainClass;


public class Block extends GameObject{

private float width, height;
private Textures tex = MainClass.getTexturesInstance();
private float x, y;
private float velX, velY;
private int direction;
private boolean action, visible;
private int type;

public Block(float x, float y, int type) {
	super();
	this.x = x;
	this.y = y;
	width = 50;
	height = 50;
	velX = 0;
	velY = 0;
	action = false;
	visible = true;
	direction = 1;
	this.type = type;
}

@Override
public void render(Graphics g) {
	if (type == 0) g.drawImage(tex.blocks[0], (int)x, (int)y, null);
	if (type == 1) g.drawImage(tex.blocks[1], (int)x, (int)y, null);
	if (type == 2) g.drawImage(tex.blocks[2], (int)x, (int)y, null);
	if (type == 3) g.drawImage(tex.blocks[3], (int)x, (int)y, null);
	if (type == 4) g.drawImage(tex.blocks[4], (int)x, (int)y, null);
	if (type == 5) g.drawImage(tex.blocks[5], (int)x, (int)y, null);
	if (type == 6) g.drawImage(tex.blocks[6], (int)x, (int)y, null);
	if (type == 7) g.drawImage(tex.blocks[7], (int)x, (int)y, null);
	if (type == 8) g.drawImage(tex.blocks[8], (int)x, (int)y, null);
	if (type == 9) g.drawImage(tex.blocks[9], (int)x, (int)y, null);
	if (type == 10) g.drawImage(tex.blocks[10], (int)x, (int)y, null);
	if (type == 11) g.drawImage(tex.blocks[11], (int)x, (int)y, null);
	if (type == 12) g.drawImage(tex.blocks[12], (int)x, (int)y, null);
	if (type == 13) g.drawImage(tex.blocks[13], (int)x, (int)y, null);
	if (type == 14) g.drawImage(tex.blocks[14], (int)x, (int)y, null);
	if (type == 15) g.drawImage(tex.blocks[15], (int)x, (int)y, null);
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