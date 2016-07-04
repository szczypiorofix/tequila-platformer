package platformer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {

protected float x,y;
protected ObjectId id;




/**
 * @param id
 * @param x
 * @param y
 */
public GameObject(ObjectId id, float x, float y) {
	super();
	this.id = id;
	this.x = x;
	this.y = y;
}

public abstract void render(Graphics g);
public abstract void tick(LinkedList<GameObject> object);
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

public ObjectId getId() {
	return id;
}

public void setId(ObjectId id) {
	this.id = id;
}
}