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



public MenuButton(String name, float x, float y)
{
	this.x = x;
	this.y = y;
	this.name = name;
}

public void render(Graphics2D g2d)
{	
	Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f);
    g2d.setComposite(c);
    g2d.drawImage(tex.mainMenuButton, (int) x, (int) y, null);
    g2d.setColor(Color.BLUE);
	g2d.setFont(MainClass.texasFont.deriveFont(Font.BOLD, 54f));
	g2d.drawString(name, x + 30, y + 45);
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
}