package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {


	
public GameObject() {
}


public abstract void render(Graphics g);
public abstract void tick(LinkedList<GameObject> object);
public abstract Rectangle getBounds();


public abstract boolean isVisible();
public abstract void setVisible(boolean visible);

public abstract float getX();
public abstract void setX(float x);

public abstract float getY();
public abstract void setY(float y);

public abstract float getWidth();
public abstract void setWidth(float width);

public abstract float getHeight();
public abstract void setHeight(float height);

public abstract float getVelX();
public abstract void setVelX(float velX);

public abstract float getVelY();
public abstract void setVelY(float velY);

public abstract boolean isAction();
public abstract void setAction(boolean action);

public abstract int getDirection();
public abstract void setDirection(int direction);

}