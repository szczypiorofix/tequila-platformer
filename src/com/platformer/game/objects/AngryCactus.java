package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;

public class AngryCactus extends GameObject{


private static final int DART_THROWER_WIDTH = 32, DART_THROWER_HEIGHT = 32;
private Textures tex = MainScreen.getInstance();
private int direction = 1;
private boolean shooting = false;
private static final int SHOOTING_MAX = 100;
private int shooting_time = SHOOTING_MAX;
private float x, y;
private int watching;


	
	public AngryCactus(ObjectId id, float x, float y, int direction) {
		super(id, x, y-15, DART_THROWER_WIDTH, DART_THROWER_HEIGHT, 0, 0, direction);
		this.x = x;
		this.y = y;
		watching = 0;
		this.direction = direction;
	}

	@Override
	public void render(Graphics g) {
		if (watching == 1)
		{
			if (direction == 1) g.drawImage(tex.angryCactusR, (int) x, (int) y-30, null);
			else g.drawImage(tex.angryCactusL, (int) x, (int) y-30, null);	
		}
		else g.drawImage(tex.angryCactus0, (int) x, (int) y-30, null);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		if (shooting)
		{
			if (shooting_time > 0) shooting_time--;
			else {
				shooting_time = SHOOTING_MAX;
				shooting = false;
			}
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x-300, (int) y-30, 700, 100);
	}

	public int getDirection()
	{
		return direction;
	}
	
	@Override
	public void setDirection(int direction)
	{
		this.direction = direction;
	}
	
	@Override
	public boolean isShooting() {
		return shooting;
	}
	
	@Override
	public void setShooting(boolean shooting)
	{
		this.shooting = shooting;
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
		return 0;
	}

	@Override
	public float getVelY() {
		return 0;
	}

	@Override
	public void setVelX(float velX) {
		watching = (int) velX;
	}

	@Override
	public void setVelY(float velY) {		
	}
}