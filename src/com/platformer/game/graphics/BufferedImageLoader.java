package com.platformer.game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;

import com.platformer.game.main.MainClass;

public class BufferedImageLoader {


private BufferedImage image;


public BufferedImage loadImage(String path)
{
	try {
		image = ImageIO.read(getClass().getResource(path));
		MainClass.logging(false, "Obraz "+path +" za³adowany poprawnie.");
	} catch (IOException ex) {
		String message = MainClass.getStackTrace(ex);
		MainClass.logging(true, "B³¹d ³adowania obrazu " +path, message);
	}
	return image;
}
}