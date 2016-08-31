package com.platformer.game.main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.platformer.game.graphics.Textures;

public class HandMenuItem {


private BufferedImage image;
private float x, y;
private final float width, height;
private boolean active;
private int count;
	
public HandMenuItem(BufferedImage image, float x, float y)
{
	this.image = image;
	active = false;
	this.x = x;
	this.y = y;
	width = 47;
	height = 41;
}

public Rectangle getBounds()
{
	return new Rectangle((int) x, (int) y, (int) width, (int) height);
}

public void drawItem(Graphics2D g2d)
{
	if (active) 
		g2d.drawImage(image, (int) x, (int) y, null);
	else g2d.drawImage(Textures.getInstance().collectibleG, (int) x, (int) y, null);
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

public boolean isActive() {
	return active;
}

public void setActive(boolean active) {
	this.active = active;
}

public int getCount() {
	return count;
}

public void setCount(int count) {
	this.count = count;
}

public float getWidth() {
	return width;
}

public float getHeight() {
	return height;
}
}