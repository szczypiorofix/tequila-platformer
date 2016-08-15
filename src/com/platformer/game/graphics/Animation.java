package com.platformer.game.graphics;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Animation {

private int speed;
private int maxKlatki;
private int index = 0;
private int klatkaAnimacji = 1;
private BufferedImage[] images;
private BufferedImage currentImage;
private float counter = 0.2f;

public Animation(int speed, BufferedImage... args)
{
	this.speed = speed;
	images = new BufferedImage[args.length];
	
	for (int i = 0; i < args.length; i++)
	{
		images[i] = args[i];
	}
	maxKlatki = args.length;
}

public void runAnimation()
{
	index++;
	if (index > speed)
	{
		index = 0;
		nextFrame();
	}
}

public int geMaxKlatki()
{
	return maxKlatki;
}

public int getKlatkaAnimacji()
{
	return klatkaAnimacji;
}

private void nextFrame()
{
	for (int i = 0; i < maxKlatki; i++)
	{
		if (klatkaAnimacji == i) currentImage = images[i];
	}
	klatkaAnimacji ++;
	if (klatkaAnimacji >= maxKlatki) klatkaAnimacji = 0;
}

public static BufferedImage makeImageTranslucent(BufferedImage source, double alpha) {
    BufferedImage target = new BufferedImage(source.getWidth(),
        source.getHeight(), java.awt.Transparency.TRANSLUCENT);
    // Get the images graphics
    Graphics2D g2d = target.createGraphics();
    // Set the Graphics composite to Alpha
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha));
    // Draw the image into the prepared reciver image
    g2d.drawImage(source, null, 0, 0);
    // let go of all system resources in this Graphics
    g2d.dispose();
    // Return the image
    return target;
}

public void drawAnimation(Graphics g,  int x, int y, boolean hit)
{
	if (!hit) g.drawImage(currentImage, x, y, null);
	else {
		counter += 0.02f;
		if (counter > 0.8f) counter = 0.2f;
		g.drawImage(makeImageTranslucent(currentImage, counter), x, y, null);
	}
}
}