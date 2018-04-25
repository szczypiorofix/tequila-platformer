package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;

public class Goat extends GameObject {


	private float x, y;
	private float width, height;
	private float velX, velY;
	private boolean action, visible;
	private int direction;

	public Goat(float x, float y) {
		super();
		this.x = x;
		this.y = y - 10;
		velX = 0;
		velY = 0;
		width = 70;
		height = 70;
		action = false;
		visible = true;
		direction = 0;
	}


	@Override
	public void render(Graphics g) {
		g.drawImage(Textures.getInstance().koza, (int) x, (int) y, null);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
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
