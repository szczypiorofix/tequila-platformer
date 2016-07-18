package platformer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class GameObject {

protected float x,y;
protected ObjectId id;
protected float width, height;


/**
 * @param id
 * @param x
 * @param y
 */
public GameObject(ObjectId id, float x, float y, float width, float height) {
	super();
	this.id = id;
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
}

public abstract void render(Graphics g);
public abstract void tick(ArrayList<GameObject> object);
public abstract Rectangle getBounds();

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

public ObjectId getId() {
	return id;
}

public void setId(ObjectId id) {
	this.id = id;
}
}