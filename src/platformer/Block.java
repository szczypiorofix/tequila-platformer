package platformer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;


public class Block extends GameObject{

protected static final int brickWidth = 45;
protected static final int brickHeight = 45;
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
	if (type == 2) g.drawImage(tex.block[2], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == 3) g.drawImage(tex.block[3], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == 4) g.drawImage(tex.block[4], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == 5) g.drawImage(tex.block[5], (int)x, (int)y, brickWidth, brickHeight, null);
}

@Override
public void tick(LinkedList<GameObject> object) {
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, brickWidth, brickHeight);
}
}