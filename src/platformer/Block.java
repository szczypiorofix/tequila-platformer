package platformer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;


public class Block extends GameObject{

protected static final int brickWidth = 50;
protected static final int brickHeight = 50;
Textures tex = MainScreen.getInstance();
private int type;

public Block(ObjectId id, float x, float y, int type) {
	super(id, x, y, brickWidth, brickHeight);
	this.type = type;
}

@Override
public void render(Graphics g) {
	if (type == 0) g.drawImage(tex.blocks[0], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == 1) g.drawImage(tex.blocks[1], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == 2) g.drawImage(tex.blocks[2], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == 3) g.drawImage(tex.blocks[3], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == 4) g.drawImage(tex.blocks[4], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == 5) g.drawImage(tex.blocks[5], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == 6) g.drawImage(tex.blocks[6], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == 7) g.drawImage(tex.blocks[7], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == 8) g.drawImage(tex.blocks[8], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == 9) g.drawImage(tex.blocks[9], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == 10) g.drawImage(tex.blocks[10], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == 11) g.drawImage(tex.blocks[11], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == 12) g.drawImage(tex.blocks[12], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == 13) g.drawImage(tex.blocks[13], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == 14) g.drawImage(tex.blocks[14], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == 15) g.drawImage(tex.blocks[15], (int)x, (int)y, brickWidth, brickHeight, null);
	
}

@Override
public void tick(LinkedList<GameObject> object) {
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, brickWidth, brickHeight);
}
}