package com.platformer.game.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.platformer.game.graphics.Textures;

public class HandMenuItem {


	private BufferedImage image;
	private float x, y;
	private final float width, height;
	private boolean active;

	HandMenuItem(BufferedImage image, float x, float y) {
		this.image = image;
		active = false;
		this.x = x;
		this.y = y;
		width = 47;
		height = 41;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

	void drawItem(Graphics2D g2d) {
		g2d.drawImage(image, (int) x, (int) y, null);
		if (active) g2d.drawImage(Textures.getInstance().handMenuItemFrame, (int) x-2, (int) y-8, null);
		g2d.setFont(MainClass.verdana18Font);
		g2d.setColor(Color.BLACK);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	boolean isActive() {
		return active;
	}

	void setActive(boolean active) {
		this.active = active;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
}
