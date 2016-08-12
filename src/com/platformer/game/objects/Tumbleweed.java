package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Animation;
import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;
import com.platformer.game.main.ObjectsHandler;

public class Tumbleweed extends GameObject{

	
private Textures tex = MainScreen.getInstance();
private ObjectId id;
private float x, y;
private float velX, velY;
private float width, height;
private boolean action;
private int direction;
private Animation tumbling;
private boolean jumping;
private float gravity;
private boolean onGround;
private ObjectsHandler objectsHandler;


public Tumbleweed(ObjectId id, float x, float y, ObjectsHandler objectsHandler)
{
	super();
	this.x = x;
	this.y = y;
	this.id = id;
	this.objectsHandler = objectsHandler;
	width = 65;
	height = 65;
	velX = 0;
	velY = 0;
	direction = 1;
	action = false;
	onGround = false;
	jumping = false;
	gravity = 0.2f;
	velX = -2f;
	tumbling = new Animation(3, tex.tumbleweed7, tex.tumbleweed6, tex.tumbleweed5, tex.tumbleweed4, tex.tumbleweed3, tex.tumbleweed2, tex.tumbleweed1, tex.tumbleweed);
}
	
	
@Override
public void render(Graphics g) {
	tumbling.drawAnimation(g, (int) x, ( int ) (y - 15), false);
	
	Graphics2D g2d = (Graphics2D) g;
	
	g2d.draw(getBounds());
	g2d.draw(getBoundsTop());
	g2d.draw(getBoundsLeft());
	g2d.draw(getBoundsRight());
}

@Override
public void tick(LinkedList<GameObject> object) {
		
	if (!jumping && onGround) {
		
		velY = -3f;
		jumping = true;
	}
	
	if (!onGround) velY += gravity;
	if (velY > 4) velY = 4;

	x += velX;
	y += velY;
	
	onGround = false;
	
	tumbling.runAnimation();
	
	collisions();
}

public void collisions()
{
	GameObject tempObject = null;
	
	for (int i = 0; i < objectsHandler.getBlock_List().size(); i++)
	{
		tempObject = objectsHandler.getBlock_List().get(i);

		if (getBounds().intersects(tempObject.getBounds()))
		{			
			y = tempObject.getY() - 55;
			jumping = false;
			velY = 0;
			onGround = true;
		}
		
		if (getBoundsRight().intersects(tempObject.getBounds())) {
			velX = -2f;
			x = tempObject.getX() -65;
		}
		
		if (getBoundsLeft().intersects(tempObject.getBounds())) {
			velX = 2f;
			x = tempObject.getX() + 65;
		}
	}
	
	for (int i = 0; i < objectsHandler.getMovingCrate_List().size(); i++)
	{
		tempObject = objectsHandler.getMovingCrate_List().get(i);

		if (getBounds().intersects(tempObject.getBounds()))
		{			
			y = tempObject.getY() - 51;
			jumping = false;
			velY = 0;
			onGround = true;
		}
		
		if (getBoundsRight().intersects(tempObject.getBounds())) {
			velX = -2f;
			x = tempObject.getX() -78;
		}
		
		if (getBoundsLeft().intersects(tempObject.getBounds())) {
			velX = 2f;
			x = tempObject.getX() + 78;
		}
	}
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x+8, (int) y+40, (int) (width-20), (int) 10);
}

public Rectangle getBoundsTop() {
	return new Rectangle((int) x+8, (int) y-15, (int) (width-20), (int) 10);
}

public Rectangle getBoundsLeft() {
	return new Rectangle((int) x-7, (int) y - 8, (int) (10), (int) 50);
}

public Rectangle getBoundsRight() {
	return new Rectangle((int) x+57, (int) y - 8, (int) (10), (int) 50);
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
}