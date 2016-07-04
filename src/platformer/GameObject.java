package platformer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {

protected float x,y;
protected ObjectId id;
protected boolean falling = true;
protected boolean jumping = false;
protected float gravity = 0.5f;
protected float speedX = 0;
protected float speedY = 0;



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

public boolean isFalling() {
	return falling;
}

public void setFalling(boolean falling) {
	this.falling = falling;
}

public boolean isJumping() {
	return jumping;
}

public void setJumping(boolean jumping) {
	this.jumping = jumping;
}

public float getGravity() {
	return gravity;
}

public void setGravity(float gravity) {
	this.gravity = gravity;
}

public float getSpeedX() {
	return speedX;
}

public void setSpeedX(float speedX) {
	this.speedX = speedX;
}

public float getSpeedY() {
	return speedY;
}

public void setSpeedY(float speedY) {
	this.speedY = speedY;
}
}