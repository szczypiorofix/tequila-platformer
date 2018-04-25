package com.platformer.game.graphics;

import java.awt.image.BufferedImage;

class SpriteSheet {

	private BufferedImage image;

	SpriteSheet(BufferedImage image) {
		this.image = image;
	}

	BufferedImage grabImage(int col, int row, int width, int height) {
		return image.getSubimage((col * width) - width, (row * height) - height, width, height);
	}
}
