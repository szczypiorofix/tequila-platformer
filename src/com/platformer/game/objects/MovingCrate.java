package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.ObjectsHandler;

public class MovingCrate extends GameObject {



	private float width;
	private float height;
	private ObjectsHandler objectsHandler;
	private float x, y;
	private float velX, velY;
	private boolean onGround;
	private float gravity;
	private boolean action, visible;
	private int direction;



	public MovingCrate(float x, float y, ObjectsHandler objectsHandler) {
		super();
		this.x = x;
		this.y = y;
		width = 64;
		height = 64;
		action = false;
		visible = true;
		direction = 1;
		this.objectsHandler = objectsHandler;
		onGround = false;
		gravity = 0.5f;
		velX = 0;
		velY = 0;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Textures.getInstance().movingCrate, (int) x, (int) y-16, null);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;

		if (!onGround) velY += gravity;

		if (y > 2000) {
			objectsHandler.getMovingCrate_List().remove(this);
		}

		collisions();
	}

	private void collisions() {
		GameObject tempObject = null;

		for (int i = 0; i < objectsHandler.getBlock_List().size(); i++) {
			tempObject = objectsHandler.getBlock_List().get(i);
			if (getBoundsBottom().intersects(tempObject.getBounds())) {
				velY = 0;
				y = tempObject.getY() - 48;
				onGround = true;
			} else onGround = false;

			if (getBoundsRight().intersects(tempObject.getBounds())) {
				x = tempObject.getX() - 65;
				velX = 0;
			}
			if (getBoundsLeft().intersects(tempObject.getBounds())) {
				x = tempObject.getX() + 55;
				velX = 0;
			}
		}

		for (int i = 0; i < objectsHandler.getButtonBlock_List().size(); i++) {
			tempObject = objectsHandler.getButtonBlock_List().get(i);
			if (getBoundsBottom().intersects(tempObject.getBounds())) {
				tempObject.setAction(true);
				velY = 0;
				y = tempObject.getY() - 48;
				onGround = true;
			} else {
				onGround = false;
			}

			if (getBoundsRight().intersects(tempObject.getBounds())) {
				x = tempObject.getX() - 65;
				velX = 0;
			}
			if (getBoundsLeft().intersects(tempObject.getBounds())) {
				x = tempObject.getX() + 55;
				velX = 0;
			}
		}

		GameObject tempObject2 = null;
		for (int i = 0; i < objectsHandler.getMovingCrate_List().size(); i++) {
			tempObject2 = objectsHandler.getMovingCrate_List().get(i);

			if (getBoundsBottom().intersects(tempObject2.getBounds()) && this != tempObject2) {
				velY = 0;
				y = tempObject2.getY() - 64;
				onGround = true;
			}
			if (getBoundsRight().intersects(tempObject2.getBounds()) && this != tempObject2) {
				x = tempObject2.getX() - 67;
				velX = 0;
			}
			if (getBoundsLeft().intersects(tempObject2.getBounds()) && this != tempObject2) {
				x = tempObject2.getX() + 67;
				velX = 0;
			}
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y-16, (int) width, (int) height);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x-5, (int) y-16, 10, 60);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) x+57, (int) y-16, 10, 60);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x+10, (int) y+40, 45, 10);
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public float getVelX() {
		return velX;
	}

	@Override
	public float getVelY() {
		return velY;
	}

	@Override
	public void setVelX(float velX) {
		this.velX = velX;
	}

	@Override
	public void setVelY(float velY) {
		this.velY = velY;
	}

	@Override
	public void setX(float x) {
		this.x = x;
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

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
