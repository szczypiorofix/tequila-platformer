package com.platformer.leveleditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class LevelEditorMainClass extends JFrame{

private static final long serialVersionUID = -518719736058824960L;
public final int ROWS = 12, COLS = 300;
private JScrollPane scrollPane;
private EditorPane editorPane;
private JPanel leftPane, bottomPane;
private JLabel selectedLabel;
private static final int MAX_TILES = 50;
public static BufferedImage[] tileImage = new BufferedImage[MAX_TILES];
private JMenuBar menuBar = new JMenuBar();
private JMenu mainMenu = new JMenu("Plik");
private JMenuItem mainExit = new JMenuItem("Zakoñcz"), mainSave = new JMenuItem("Zapisz"), mainOpen = new JMenuItem("Otwórz"), mainNew = new JMenuItem("Nowy");
private TileChoose[] tilesChoose = new TileChoose[MAX_TILES];
private ActionListener tileListener, menuListener;
public int selectedTile = 0;
private ObjectOutputStream oos;
private ObjectInputStream ois;
private String currentFile = "", currentLevelName = "level1";



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
		tileImage[21] = ImageIO.read(getClass().getResource("/Grass (1).png"));
		tileImage[22] = ImageIO.read(getClass().getResource("/Grass (2).png"));
		tileImage[23] = ImageIO.read(getClass().getResource("/Sign.png"));
		tileImage[24] = ImageIO.read(getClass().getResource("/SignArrow.png"));
		tileImage[25] = ImageIO.read(getClass().getResource("/Skeleton.png"));
		tileImage[26] = ImageIO.read(getClass().getResource("/Stone.png"));
		tileImage[27] = ImageIO.read(getClass().getResource("/Tree.png"));
		tileImage[28] = ImageIO.read(getClass().getResource("/Idle00R.png"));
		tileImage[29] = ImageIO.read(getClass().getResource("/level_end.png"));
		tileImage[30] = ImageIO.read(getClass().getResource("/coin32.png")).getSubimage(0, 0, 32, 32);
		tileImage[31] = ImageIO.read(getClass().getResource("/BeeR01.png"));
		tileImage[32] = ImageIO.read(getClass().getResource("/tequila_bottle.png"));
		tileImage[33] = ImageIO.read(getClass().getResource("/taco.png"));
		tileImage[34] = ImageIO.read(getClass().getResource("/MovingBlockX.png"));
		tileImage[35] = ImageIO.read(getClass().getResource("/MovingBlockY.png"));
		tileImage[36] = ImageIO.read(getClass().getResource("/16_5.png"));
		tileImage[37] = ImageIO.read(getClass().getResource("/17.png"));
		tileImage[38] = ImageIO.read(getClass().getResource("/BadCactusR.png"));
		tileImage[39] = ImageIO.read(getClass().getResource("/MovingCrate.png"));
		tileImage[40] = ImageIO.read(getClass().getResource("/ButtonBlock.png"));
		tileImage[41] = ImageIO.read(getClass().getResource("/PushingMovingBlockX Off.png"));
		tileImage[42] = ImageIO.read(getClass().getResource("/PushingMovingBlockY Off.png"));
		tileImage[43] = ImageIO.read(getClass().getResource("/Spike1.png"));
		tileImage[44] = ImageIO.read(getClass().getResource("/Tumbleweed.png"));
		tileImage[45] = ImageIO.read(getClass().getResource("/SpringBlock.png"));
		tileImage[46] = ImageIO.read(getClass().getResource("/FallingBlock.png"));
		tileImage[47] = ImageIO.read(getClass().getResource("/clouds1.png"));
		tileImage[48] = ImageIO.read(getClass().getResource("/clouds2.png"));
		tileImage[49] = ImageIO.read(getClass().getResource("/clouds3.png"));
	}
	catch (Exception e)
	{
		e.printStackTrace();
		System.exit(0);
	}
	
	tileListener = new TileListener();
	menuListener = new MenuListener();
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(800,600);
	//setResizable(false);
	setLocationRelativeTo(null);
	setLayout(new BorderLayout());
	editorPane = new EditorPane(ROWS, COLS);
	scrollPane = new JScrollPane(editorPane);
	scrollPane.getHorizontalScrollBar().setUnitIncrement(26);
	scrollPane.getVerticalScrollBar().setUnitIncrement(26);
	
	leftPane = new JPanel(new GridLayout(Math.round((tileImage.length / 3))+1, 3));
	
	for (int i = 0; i < tileImage.length; i++)
	{
		tilesChoose[i] = new TileChoose(tileImage[i]);
		tilesChoose[i].addActionListener(tileListener);
		tilesChoose[i].setActionCommand(i+"");
		leftPane.add(tilesChoose[i]);
	}
	
	tilesChoose[selectedTile].setBorder(new LineBorder(Color.RED, 2, true));
	
	bottomPane = new JPanel(new FlowLayout());
	bottomPane.add(new JLabel("Obszar: " +editorPane.getRow() +"x" +editorPane.getCol()));
	selectedLabel = new JLabel("   Selected: "+selectedTile);
	bottomPane.add(selectedLabel);
	
	add(scrollPane, BorderLayout.CENTER);
	add(leftPane, BorderLayout.WEST);
	add(bottomPane, BorderLayout.SOUTH);
	mainMenu.add(mainNew);
	mainMenu.add(mainSave);
	mainMenu.add(mainOpen);
	mainMenu.add(mainExit);
	menuBar.add(mainMenu);
	setJMenuBar(menuBar);
	
	mainNew.addActionListener(menuListener);
	mainNew.setActionCommand("New");
	mainSave.addActionListener(menuListener);
	mainSave.setActionCommand("Save");
	mainOpen.addActionListener(menuListener);
	mainOpen.setActionCommand("Open");
	mainExit.addActionListener(menuListener);
	mainExit.setActionCommand("Exit");
	
	///   ZAPIS DO PLIKU BMP w systemie RGB	
}

public class MenuListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equalsIgnoreCase("Exit")) System.exit(0);
		
		if (e.getActionCommand().equalsIgnoreCase("New"))
		{
			for (int i = 0; i < ROWS; i++)
				for (int j = 0; j < COLS; j++)
				{
					editorPane.editorTiles[i][j].setText(i+"");
					editorPane.editorTiles[i][j].setIcon(null);
					editorPane.tileValues[i][j] = -1;
				}
		}
		
		if (e.getActionCommand().equalsIgnoreCase("Open"))
		{
			
			String filename = "";
			
			do {
				filename = JOptionPane.showInputDialog(null, "Podaj nazwê pliku: ", currentLevelName);	
			}
			
			while(filename.length() == 0);
			
			for (int i = 0; i < ROWS; i++)
				for (int j = 0; j < COLS; j++)
					editorPane.tileValues[i][j] = -1;
			currentLevelName = filename;
			filename = "res/Other/" +filename +".lvl";
			currentFile = filename;
			setTitle("Platformer - Level Editor: "+currentFile);
			
			try {
				ois = new ObjectInputStream(new FileInputStream(filename));
				editorPane.setTileValues((int[][]) (ois.readObject()));
				ois.close();
			}
			catch (IOException | ClassNotFoundException ioe)
			{
				ioe.printStackTrace();
				System.exit(0);
			}
			
			for (int a = 0; a < ROWS; a++)
				for (int b = 0; b < COLS; b++)
				{
					//editorPane.editorTiles[a][b].setText(a+"");
					editorPane.editorTiles[a][b].setIcon(null);
					
					if (editorPane.tileValues[a][b] >= 0) 
					{
						Image newImage = tileImage[editorPane.tileValues[a][b]].getScaledInstance(40, 40, Image.SCALE_DEFAULT);
						editorPane.editorTiles[a][b].setIcon(new ImageIcon(newImage));
					}
				}
		}
		
		if (e.getActionCommand().equalsIgnoreCase("Save"))
		{
			String filename = "";
			do {
				filename = JOptionPane.showInputDialog(null, "Podaj nazwê pliku: ", currentLevelName);	
			}	
			while(filename.length() == 0);

			currentLevelName = filename;
			filename = "res/Other/" +filename +".lvl";
			currentFile = filename;
			setTitle("Platformer - Level Editor: "+currentFile);
			
			try {
				oos = new ObjectOutputStream(new FileOutputStream(filename));
			    oos.writeObject(editorPane.getTileValues());
			    oos.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
				System.exit(-1);
			}
		}
	}
}


public class TileListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) {
		selectedTile = Integer.parseInt(e.getActionCommand());
		
		for (int i = 0; i < tilesChoose.length; i++)
			tilesChoose[i].setBorder(new LineBorder(Color.GRAY, 1, false));
		
		tilesChoose[selectedTile].setBorder(new LineBorder(Color.RED, 2, true));
		selectedLabel.setText("   Selected: "+selectedTile);
		//System.out.println(selectedTile);
	}
}


public class EditorPane extends JPanel{

	
private static final long serialVersionUID = -5692560801368198843L;
private int row, col;
private Tile[][] editorTiles;
private ActionListener editorTilesListener;
private int[][] tileValues;

public EditorPane(int row, int col)
{
	super(new GridLayout(row, col));
	this.row = row;
	this.col = col;
	setPreferredSize(new Dimension(row * 1000, col));
	setMinimumSize(new Dimension(row * 1000, col));
	setMaximumSize(new Dimension(row * 1000, col));
	setBackground(new Color(150, 220, 255));
	
	tileValues = new int[this.row][this.col];
	editorTiles = new Tile[this.row][this.col];
	editorTilesListener = new EditorTilesListener();
	
	for (int a = 0; a < row; a++)
		for (int b = 0; b < col; b++)
		{
			editorTiles[a][b] = new Tile();
			editorTiles[a][b].addActionListener(editorTilesListener);
			editorTiles[a][b].setActionCommand(a+":"+b);
			tileValues[a][b] = -1;
			add(editorTiles[a][b]);
		}
}


public class EditorTilesListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String s = e.getActionCommand();
		String first="", second="";
		boolean sec = false;
		
		for (int i = 0; i < s.length(); i++)
		{
			if (!sec && s.charAt(i)!= ':') first += s.charAt(i);
			if (sec) second += s.charAt(i);
			if (s.charAt(i) == ':') sec = true;
		}
		
		if (editorTiles[Integer.parseInt(first)][Integer.parseInt(second)].getIcon() == null)
		{
			Image newImage = tileImage[selectedTile].getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			editorTiles[Integer.parseInt(first)][Integer.parseInt(second)].setIcon(new ImageIcon(newImage));
			tileValues[Integer.parseInt(first)][Integer.parseInt(second)] = selectedTile;	
		}
		else
		{
			editorTiles[Integer.parseInt(first)][Integer.parseInt(second)].setIcon(null);
			tileValues[Integer.parseInt(first)][Integer.parseInt(second)] = -1;
		}
		
	}
}

public void setTileValues(int[][] i)
{
	tileValues = i;
}

public int[][] getTileValues()
{
	return tileValues;
}

public int getRow() {
	return row;
}


public void setRow(int row) {
	this.row = row;
}


public int getCol() {
	return col;
}


public void setCol(int col) {
	this.col = col;
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