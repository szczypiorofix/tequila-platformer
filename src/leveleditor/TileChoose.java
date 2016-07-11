package leveleditor;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TileChoose extends JButton{


private static final long serialVersionUID = -3972132080556103583L;
private BufferedImage image;

public TileChoose(BufferedImage image)
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