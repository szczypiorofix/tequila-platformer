package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.ObjectsHandler;

public class ButtonBlock extends GameObject{


private float x, y;
private float velX, velY;
private float width, height;
private ObjectsHandler objectsHandler;
private boolean action, visible;
private int direction;
private GameObject tempObject;

public ButtonBlock(float x, float y, ObjectsHandler objectsHandler)
{
	super();
	this.x = x;
	this.y = y;
	this.objectsHandler = objectsHandler;
	width = 50;
	height = 50;
	velX = 0;
	velY = 0;
	direction = 1;
	action = false;
	visible = true;
}

@Override
public void render(Graphics g) {
	g.drawImage(Textures.getInstance().buttonBlock, (int) x, (int) y,  null);
	//Graphics2D g2d = (Graphics2D) g;
	//g2d.draw(getImpactBounds());
}

@Override
public void tick(LinkedList<GameObject> object) {

	/// PUSHING MOVING BLOCK X LIST
	for (int i = 0; i < objectsHandler.getPushingMovingBlockX_List().size(); i++)
	{
		tempObject = objectsHandler.getPushingMovingBlockX_List().get(i);
		if (getImpactBounds().intersects(tempObject.getBounds()) && action && tempObject.isVisible())	tempObject.setAction(true);
	}
	
	// PUSHING MOVING BLOCK Y LIST
	for (int i = 0; i < objectsHandler.getPushingMovingBlockY_List().size(); i++)
	{
		tempObject = objectsHandler.getPushingMovingBlockY_List().get(i);
		if (getImpactBounds().intersects(tempObject.getBounds()) && action && tempObject.isVisible())	tempObject.setAction(true);
	}
	
	action = false;
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, (int )width, (int) height);
}

public Rectangle getImpactBounds() {
	return new Rectangle((int) x-250, (int) y-500, 700, 500);
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