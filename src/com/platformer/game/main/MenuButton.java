package com.platformer.game.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;


import com.platformer.game.graphics.Textures;

public class MenuButton {


private String name;
private float x, y;
private float width, height;
private boolean selected;
private Composite defComposite, c;
private boolean locked;


public MenuButton(String name, float x, float y)
{
	this.x = x;
	this.y = y;
	this.name = name;
	width = 380;
	height = 64;
	locked = false;
	selected = false;
}

public MenuButton(String name, float x, float y, float width, float height)
{
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.name = name;
	locked = false;
	selected = false;
}

public void render(Graphics2D g2d)
{
	if (!locked) {
	c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f);
	defComposite = g2d.getComposite();
	g2d.setComposite(c);
	
	g2d.setColor(Color.BLUE);
	if (height != 0 && width != 0)
	{
	    if (selected) g2d.drawImage(Textures.getInstance().mainMenuButtonSelected, (int) x, (int) y, (int) width, (int) height, null);
	    else g2d.drawImage(Textures.getInstance().mainMenuButton, (int) x, (int) y, (int) width, (int) height, null);
	    
	    g2d.setFont(MainClass.texasFont.deriveFont(Font.BOLD, (width / height) * 7.5f));
	    g2d.drawString(name, x + (int) (width * 0.05f), y + (int) (height * 0.78f));
	}
	else
	{
	    if (selected) g2d.drawImage(Textures.getInstance().mainMenuButtonSelected, (int) x, (int) y, null);
	    else g2d.drawImage(Textures.getInstance().mainMenuButton, (int) x, (int) y, null);
	    
	    g2d.setFont(MainClass.texasFont.deriveFont(Font.BOLD, 54f));
	    g2d.drawString(name, x + 30, y + 45);
	}
	g2d.setComposite(defComposite); // DOMYŚLNE COMPOSITE
	}
	else {
		if (MainClass.language == MainClass.Languages.polish) g2d.drawImage(Textures.getInstance().lockedLevelPL, (int) x, (int) y, (int) width, (int) height, null);
		if (MainClass.language == MainClass.Languages.english) g2d.drawImage(Textures.getInstance().lockedLevelENG, (int) x, (int) y, (int) width, (int) height, null);
	}
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
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

public boolean isSelected() {
	return selected;
}

public void setSelected(boolean selected) {
	this.selected = selected;
}

public float getWidth() {
	return width;
}

public void setWidth(float width) {
	this.width = width;
}

public float getHeight() {
	return height;
}

public void setHeight(float height) {
	this.height = height;
}

public boolean isLocked() {
	return locked;
}

public void setLocked(boolean locked) {
	this.locked = locked;
}
}