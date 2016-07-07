package platformer;

import java.awt.image.BufferedImage;

public class Textures {

SpriteSheet bs, ps;
private BufferedImage block_sheet = null;
private BufferedImage player_sheet = null;
public BufferedImage[] block = new BufferedImage[2];
public BufferedImage[] player = new BufferedImage[8];

public Textures()
{
	BufferedImageLoader loader = new BufferedImageLoader();
	
	block_sheet = loader.loadImage("/generic_platformer_tiles.png"); //TILE SHEET: http://opengameart.org/content/generic-platformer-tileset-16x16-background
	player_sheet = loader.loadImage("/running.png"); // HERO SHEET http://opengameart.org/content/classic-knight-animated
	
	bs = new SpriteSheet(block_sheet);
	ps = new SpriteSheet(player_sheet);
	
	getTextures();
	
}

private void getTextures()
{
	block[0] = bs.grabImage(1, 1, 16, 16);  // First block
	block[1] = bs.grabImage(2, 1, 16, 16); // Second block
	
	player[0] = ps.grabImage(1, 1, 50, 50);
	player[1] = ps.grabImage(2, 1, 50, 50);
	player[2] = ps.grabImage(3, 1, 50, 50);
	player[3] = ps.grabImage(4, 1, 51, 50);
	player[4] = ps.grabImage(5, 1, 51, 50);
	player[5] = ps.grabImage(6, 1, 51, 50);
	player[6] = ps.grabImage(7, 1, 51, 50);
	player[7] = ps.grabImage(8, 1, 50, 50);
}


}