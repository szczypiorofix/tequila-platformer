package leveleditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class LevelEditorMainClass extends JFrame{

private static final long serialVersionUID = -518719736058824960L;
private JScrollPane scrollPane;
private EditorPane editorPane;
private JPanel leftPane;
public static BufferedImage[] tileImage = new BufferedImage[30];
private JMenuBar menuBar = new JMenuBar();
private JMenu mainMenu = new JMenu("Plik");
private JMenuItem mainExit = new JMenuItem("Exit"), mainSave = new JMenuItem("Save"), mainOpen = new JMenuItem("Open");
private TileChoose[] tilesChoose = new TileChoose[30];
private ActionListener tileListener;
public static int selectedTile = 0;


public LevelEditorMainClass()
{
	super("Platformer - Level Editor");
	
	try {
		tileImage[0] = ImageIO.read(getClass().getResource("/00.png"));
		tileImage[1] = ImageIO.read(getClass().getResource("/01.png"));
		tileImage[2] = ImageIO.read(getClass().getResource("/02.png"));
		tileImage[3] = ImageIO.read(getClass().getResource("/03.png"));
		tileImage[4] = ImageIO.read(getClass().getResource("/04.png"));
		tileImage[5] = ImageIO.read(getClass().getResource("/05.png"));
		tileImage[6] = ImageIO.read(getClass().getResource("/06.png"));
		tileImage[7] = ImageIO.read(getClass().getResource("/07.png"));
		tileImage[8] = ImageIO.read(getClass().getResource("/08.png"));
		tileImage[9] = ImageIO.read(getClass().getResource("/09.png"));
		tileImage[10] = ImageIO.read(getClass().getResource("/10.png"));
		tileImage[11] = ImageIO.read(getClass().getResource("/11.png"));
		tileImage[12] = ImageIO.read(getClass().getResource("/12.png"));
		tileImage[13] = ImageIO.read(getClass().getResource("/13.png"));
		tileImage[14] = ImageIO.read(getClass().getResource("/14.png"));
		tileImage[15] = ImageIO.read(getClass().getResource("/15.png"));
		tileImage[16] = ImageIO.read(getClass().getResource("/Bush (1).png"));
		tileImage[17] = ImageIO.read(getClass().getResource("/Bush (2).png"));
		tileImage[18] = ImageIO.read(getClass().getResource("/Cactus (1).png"));
		tileImage[19] = ImageIO.read(getClass().getResource("/Cactus (2).png"));
		tileImage[20] = ImageIO.read(getClass().getResource("/Cactus (3).png"));
		tileImage[21] = ImageIO.read(getClass().getResource("/Crate.png"));
		tileImage[22] = ImageIO.read(getClass().getResource("/Grass (1).png"));
		tileImage[23] = ImageIO.read(getClass().getResource("/Grass (2).png"));
		tileImage[24] = ImageIO.read(getClass().getResource("/Sign.png"));
		tileImage[25] = ImageIO.read(getClass().getResource("/SignArrow.png"));
		tileImage[26] = ImageIO.read(getClass().getResource("/Skeleton.png"));
		tileImage[27] = ImageIO.read(getClass().getResource("/Stone.png"));
		tileImage[28] = ImageIO.read(getClass().getResource("/StoneBlock.png"));
		tileImage[29] = ImageIO.read(getClass().getResource("/Tree.png"));
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
		System.exit(0);
	}
	
	tileListener = new TileListener();
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(800,600);
	setLocationRelativeTo(null);
	setLayout(new BorderLayout());
	editorPane = new EditorPane(40, 100);
	scrollPane = new JScrollPane(editorPane);
	
	leftPane = new JPanel(new GridLayout(10, 3));
	for (int i = 0; i < tileImage.length; i++)
	{
		tilesChoose[i] = new TileChoose(tileImage[i]);
		tilesChoose[i].addActionListener(tileListener);
		tilesChoose[i].setActionCommand(i+"");
		leftPane.add(tilesChoose[i]);
	}
	tilesChoose[selectedTile].setBorder(new LineBorder(Color.RED, 2, true));
	
	add(scrollPane, BorderLayout.CENTER);
	add(leftPane, BorderLayout.WEST);
	add(new JLabel("Obszar: " +editorPane.getRow() +"x" +editorPane.getCol()), BorderLayout.SOUTH);
	mainMenu.add(mainSave);
	mainMenu.add(mainOpen);
	mainMenu.add(mainExit);
	menuBar.add(mainMenu);
	setJMenuBar(menuBar);
	
	
	///   ZAPIS DO PLIKU BMP w systemie RGB
	
}

public class TileListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) {
		selectedTile = Integer.parseInt(e.getActionCommand());
		
		for (int i = 0; i < tilesChoose.length; i++)
			tilesChoose[i].setBorder(new LineBorder(Color.GRAY, 1, false));
		
		tilesChoose[selectedTile].setBorder(new LineBorder(Color.RED, 2, true));
	}
}


public static void main(String[] args)
{
	EventQueue.invokeLater(new Runnable()
	{
		@Override
		public void run()
		{
			new LevelEditorMainClass().setVisible(true);
		}
	});
}
}