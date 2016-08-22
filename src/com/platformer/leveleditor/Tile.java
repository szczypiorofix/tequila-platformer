package com.platformer.leveleditor;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tile extends JButton{

private static final long serialVersionUID = -1517430710709041807L;
private BufferedImage image;


public Tile()
{
	super();
	setPreferredSize(new Dimension(40,40));
	setMinimumSize(new Dimension(40,40));
	setMaximumSize(new Dimension(40,40));
	setContentAreaFilled(false);
}

public Tile(BufferedImage image)
{
	super();
	setPreferredSize(new Dimension(40,40));
	setMinimumSize(new Dimension(40,40));
	setMaximumSize(new Dimension(40,40));
	setContentAreaFilled(false);
	setIcon(new ImageIcon(image));
}

public BufferedImage getImage() {
	return image;
}

public void setImage(BufferedImage image) {
	this.image = image;
}
}