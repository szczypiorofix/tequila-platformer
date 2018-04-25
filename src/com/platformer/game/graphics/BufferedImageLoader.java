package com.platformer.game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import com.platformer.game.main.MainClass;

public class BufferedImageLoader {


private BufferedImage image;


public BufferedImage loadImage(String path)
{
	try {
		image = ImageIO.read(getClass().getResource(path));
		MainClass.logging(false, Level.INFO, "Obraz "+path +" załadowany poprawnie.");
	} catch (IOException ex) {
		MainClass.logging(false, Level.WARNING, "Błąd ładowania obrazu " +path);
		MainClass.logging(false, Level.WARNING, MainClass.getStackTrace(ex));
	}
	return image;
}
}