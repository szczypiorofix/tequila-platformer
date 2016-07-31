package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;
import com.platformer.game.main.ObjectsHandler;

public class ButtonBlock extends GameObject{

	
private float x, y;
private float velX, velY;
private static final int BUTTON_BLOCK_WIDTH = 50, BUTTON_BLOCK_HEIGHT = 50;
private Textures tex = MainScreen.getInstance();
private ObjectsHandler objectsHandler;
private boolean shooting;

public ButtonBlock(ObjectId id, float x, float y, ObjectsHandler objectsHandler)
{
	super(id, x, y, BUTTON_BLOCK_WIDTH, BUTTON_BLOCK_HEIGHT, 0, 0, 0);
	this.x = x;
	this.y = y;
	this.objectsHandler = objectsHandler;
	velX = 0;
	velY = 0;
	shooting = false;
}

@Override
public void render(Graphics g) {
	g.drawImage(tex.buttonBlock, (int) x, (int) y, null);
	Graphics2D g2d = (Graphics2D) g;
	g2d.draw(getImpactBounds());
}

@Override
public void tick(LinkedList<GameObject> object) {

	
	if (shooting) System.out.println("OK");
	/// PUSHING MOVING BLOCK X LIST
	for (int i = 0; i < objectsHandler.getPushingMovingBlockX_List().size(); i++)
	{
		GameObject tempObject = objectsHandler.getPushingMovingBlockX_List().get(i);
		if (getImpactBounds().intersects(tempObject.getBounds()) && shooting)
		{
			tempObject.setShooting(true);
		} else tempObject.setShooting(false);
	}
	
	/// PUSHING MOVING BLOCK Y LIST
		for (int i = 0; i < objectsHandler.getPushingMovingBlockY_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getPushingMovingBlockY_List().get(i);
			if (getImpactBounds().intersects(tempObject.getBounds()) && shooting)
			{
				tempObject.setShooting(true);
			} else tempObject.setShooting(false);
		}
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, BUTTON_BLOCK_WIDTH, BUTTON_BLOCK_HEIGHT);
}

public Rectangle getImpactBounds()
{
	return new Rectangle((int) x-250, (int) y-500, 700, 500);
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
public boolean isShooting() {
	return shooting;
}

@Override
public void setShooting(boolean shooting) {
	this.shooting = shooting;
}
}