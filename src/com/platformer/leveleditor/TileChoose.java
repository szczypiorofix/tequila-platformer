package com.platformer.leveleditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TileChoose extends JButton{


private static final long serialVersionUID = -3972132080556103583L;
private Image image;

public TileChoose(Image image)
{
	super();
	setPreferredSize(new Dimension(40, 50));
	setMinimumSize(new Dimension(40, 50));
	setMaximumSize(new Dimension(40, 50));
	setBackground(new Color(150, 220, 255));
	setIcon(new ImageIcon(image));
}

public Image getImage() {
	return image;
}

public void setImage(Image image) {
	this.image = image;
}
}