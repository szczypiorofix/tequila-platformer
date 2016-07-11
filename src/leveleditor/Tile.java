package leveleditor;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tile extends JButton{

private static final long serialVersionUID = -1517430710709041807L;
private BufferedImage image;


public Tile(String name)
{
	super(name);
	setPreferredSize(new Dimension(50,30));
	setMinimumSize(new Dimension(50,30));
	setMaximumSize(new Dimension(50,30));
	setContentAreaFilled(false);
}

public Tile(BufferedImage image)
{
	super();
	this.image = image;
	setPreferredSize(new Dimension(50,30));
	setMinimumSize(new Dimension(50,30));
	setMaximumSize(new Dimension(50,30));
	setContentAreaFilled(false);
	setIcon(new ImageIcon(this.image));
}
}