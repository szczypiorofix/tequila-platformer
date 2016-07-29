package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;
import com.platformer.game.main.ObjectsHandler;

public class MovingCrate extends GameObject{

	
private static final int MOVINGCRATE_WIDTH  = 64;
private static final int MOVINGCRATE_HEIGHT = 64;
private Textures tex = MainScreen.getInstance();
private ObjectsHandler objectsHandler;
private float x, y;
private float velX, velY;


public MovingCrate(ObjectId id, float x, float y, ObjectsHandler objectsHandler) {
	super(id, x, y, MOVINGCRATE_WIDTH, MOVINGCRATE_HEIGHT, 0, 0, 0);
	this.x = x;
	this.y = y;
	this.objectsHandler = objectsHandler;
	velX = 0;
	velY = 0;
}

@Override
public void render(Graphics g) {
	g.drawImage(tex.movingCrate, (int) x, (int) y-16, null);
	//Graphics2D g2d = (Graphics2D) g;
	
	//g2d.draw(getBoundsLeft());
	//g2d.draw(getBoundsRight());
}

@Override
public void tick(LinkedList<GameObject> object) {
	x += velX;
	y += velY;
	
	collisions();
}

private void collisions()
{
	for (int i = 0; i < objectsHandler.getBlock_List().size(); i++)
	{
		GameObject tempObject = objectsHandler.getBlock_List().get(i);
					
		if (getBoundsRight().intersects(tempObject.getBounds()))
		{			
			x = tempObject.getX() - 65;
			velX = 0;
		}
		if (getBoundsLeft().intersects(tempObject.getBounds()))
		{			
			x = tempObject.getX() + 55;
			velX = 0;
		}
	}
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y-16, MOVINGCRATE_WIDTH, MOVINGCRATE_HEIGHT);
}

public Rectangle getBoundsLeft() {
	return new Rectangle((int) x-5, (int) y-16, 10, 60);
}

public Rectangle getBoundsRight() {
	return new Rectangle((int) x+57, (int) y-16, 10, 60);
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
}