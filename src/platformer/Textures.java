package platformer;

import java.awt.image.BufferedImage;

public class Textures {

SpriteSheet bs, ps;
private BufferedImage block_sheet = null;
private BufferedImage player_sheet = null;
public BufferedImage[] block = new BufferedImage[12];
public BufferedImage[] player = new BufferedImage[6];

public Textures()
{
	BufferedImageLoader loader = new BufferedImageLoader();
	
	block_sheet = loader.loadImage("/generic_platformer_tiles.png"); //TILE SHEET: http://opengameart.org/content/generic-platformer-tileset-16x16-background
	player_sheet = loader.loadImage("/res_viewer.php.png"); // HERO SHEET http://opengameart.org/content/classic-knight-animated
	
	// http://charas-project.net/charas2/  - GENERATOR !!!
	
	bs = new SpriteSheet(block_sheet);
	ps = new SpriteSheet(player_sheet);
	
	getTextures();
	
}

private void getTextures()
{
	block[0] = bs.grabImage(1, 1, 16, 16);  // First block
	block[1] = bs.grabImage(2, 1, 16, 16); // Second block
	block[2] = bs.grabImage(3, 1, 16, 16);
	block[3] = bs.grabImage(1, 2, 16, 16);
	block[4] = bs.grabImage(2, 2, 16, 16);
	block[5] = bs.grabImage(3, 2, 16, 16);
	
	player[0] = ps.grabImage(1, 2, 24, 32);
	player[1] = ps.grabImage(2, 2, 24, 32);
	player[2] = ps.grabImage(3, 2, 24, 32);
	player[3] = ps.grabImage(1, 4, 24, 32);
	player[4] = ps.grabImage(2, 4, 24, 32);
	player[5] = ps.grabImage(3, 4, 24, 32);
}


}