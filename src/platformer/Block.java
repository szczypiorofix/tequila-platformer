package platformer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;


public class Block extends GameObject{

protected static final int brickWidth = 50;
protected static final int brickHeight = 50;
Textures tex = MainScreen.getInstance();
private TilesTypes type;

public Block(ObjectId id, float x, float y, TilesTypes type) {
	super(id, x, y, brickWidth, brickHeight);
	this.type = type;
}

@Override
public void render(Graphics g) {
	if (type == TilesTypes.UpFullLeft) g.drawImage(tex.blocks[0], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == TilesTypes.UpFullMid) g.drawImage(tex.blocks[1], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == TilesTypes.UpFullRight) g.drawImage(tex.blocks[2], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == TilesTypes.MidEdgeLeft) g.drawImage(tex.blocks[3], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == TilesTypes.MidEdgeMid) g.drawImage(tex.blocks[4], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == TilesTypes.MidEdgeRight) g.drawImage(tex.blocks[5], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == TilesTypes.UpStartLeft) g.drawImage(tex.blocks[6], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == TilesTypes.UpStartRight) g.drawImage(tex.blocks[7], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == TilesTypes.MidLeft) g.drawImage(tex.blocks[8], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == TilesTypes.MidRight) g.drawImage(tex.blocks[9], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == TilesTypes.FlyMid) g.drawImage(tex.blocks[10], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == TilesTypes.FlyLeft) g.drawImage(tex.blocks[11], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == TilesTypes.FlyRight) g.drawImage(tex.blocks[12], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == TilesTypes.FlyThinLeft) g.drawImage(tex.blocks[13], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == TilesTypes.FlyThinMid) g.drawImage(tex.blocks[14], (int)x, (int)y, brickWidth, brickHeight, null);
	if (type == TilesTypes.FlyThinRight) g.drawImage(tex.blocks[15], (int)x, (int)y, brickWidth, brickHeight, null);
	
}

@Override
public void tick(LinkedList<GameObject> object) {
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, brickWidth, brickHeight);
}
}