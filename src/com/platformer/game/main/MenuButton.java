package com.platformer.game.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;

import com.platformer.game.graphics.Textures;

public class MenuButton {


private Textures tex = MainClass.getTexturesInstance();
private String name;
private float x, y;
private boolean selected;
private Composite defComposite, c;


public MenuButton(String name, float x, float y)
{
	this.x = x;
	this.y = y;
	this.name = name;
	selected = false;
}

public void render(Graphics2D g2d)
{
	c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f);
	defComposite = g2d.getComposite();
	g2d.setComposite(c);
    if (selected) g2d.drawImage(tex.mainMenuButtonSelected, (int) x, (int) y, null);
    else g2d.drawImage(tex.mainMenuButton, (int) x, (int) y, null);
    g2d.setColor(Color.BLUE);
	g2d.setFont(MainClass.texasFont.deriveFont(Font.BOLD, 54f));
	g2d.drawString(name, x + 30, y + 45);
	g2d.setComposite(defComposite); // DOMYŒLNE COMPOSITE
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
}