package platformer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;


public class Block extends GameObject{

protected static final int brickWidth = 32;
protected static final int brickHeight = 32;
Textures tex = MainScreen.getInstance();
private int type;

public Block(ObjectId id, float x, float y, int type) {
	super(id, x, y, brickWidth, brickHeight);
	this.type = type;
}

@Override
public void render(Graphics g) {
	if (type == 0) g.drawImage(tex.block[0], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == 1) g.drawImage(tex.block[1], (int)x, (int)y, brickWidth, brickHeight, null);
}

@Override
public void tick(LinkedList<GameObject> object) {
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, brickWidth, brickHeight);
}
}