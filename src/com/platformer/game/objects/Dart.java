package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;

public class Dart extends GameObject{

private Textures tex = MainScreen.getInstance();
private static final int DART_WIDTH = 32;
private static final int DART_HEIGHT = 12;
private float x, y;
private int direction;
private float velX, velY;
private int sx;
private int howLondToRun = 75;

	public Dart(ObjectId id, float x, float y, int direction) {
		super(id, x, y, DART_WIDTH, DART_HEIGHT, 0, 0, 1);
		this.x = x;
		this.y = y;
		velX = 0;
		velY = 0;
		sx = 0;
		if (direction == 1) velX = 5;
		else velX = -5;
		this.direction = direction;
	}

	@Override
	public void render(Graphics g) {
		if (direction == 1) g.drawImage(tex.dartR, (int) x, (int) y, null);
		else g.drawImage(tex.dartL, (int) x, (int) y, null);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {

		x += velX;
		
		if (direction == -1)
		{
			sx--;
			if (sx < (howLondToRun * -1)) velX = 0;	
		}
		if (direction == 1)
		{
			sx++;
			if (sx > howLondToRun) velX = 0;	
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, DART_WIDTH, DART_HEIGHT);
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
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public float getVelX()
	{
		return velX;
	}

	@Override
	public float getVelY()
	{
		return velY;
	}

	@Override
	public void setVelX(float velX) {		
	}

	@Override
	public void setVelY(float velY) {		
	}
	
	
}