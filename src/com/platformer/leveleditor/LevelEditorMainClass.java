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
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

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
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

public class LevelEditorMainClass extends JFrame{

private static final long serialVersionUID = -518719736058824960L;
public final int ROWS = 12, COLS = 300;
private JScrollPane scrollPane;
private EditorPane editorPane;
private JPanel leftPane, bottomPane;
private JLabel selectedLabel;
private static final int MAX_TILES = 51;
public static BufferedImage[] tileImage = new BufferedImage[MAX_TILES];

private JMenuBar menuBar = new JMenuBar();

private JMenu menuPlik = new JMenu("Plik");
private JMenuItem menuPlikZakoncz = new JMenuItem("Zakoñcz"), menuPlikZapisz = new JMenuItem("Zapisz"), menuPlikOtworz = new JMenuItem("Otwórz"), menuPlikNowy = new JMenuItem("Nowy");

private JMenu menuGenerator = new JMenu("Generator");
private JMenuItem menuGeneratorPodloze = new JMenuItem("Pod³o¿e");
private JMenuItem menuGeneratorLosowyTeren = new JMenuItem("Losowy Teren");
private JMenuItem menuGeneratorWyrownanieTerenu = new JMenuItem("Wyrównanie teren");

private TileChoose[] tilesChoose = new TileChoose[MAX_TILES];
private ActionListener tileListener, menuListener;
public int selectedTile = 0;
private ObjectOutputStream oos;
private ObjectInputStream ois;
private String currentFile = "", currentLevelName = "level1";
private Random random;
private final KeyStroke ctrl_T = KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK);
private final KeyStroke ctrl_P = KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK);
private final KeyStroke ctrl_S = KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK);
private final KeyStroke ctrl_N = KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK);
private final KeyStroke ctrl_O = KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK);
private final KeyStroke ctrl_X = KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK);
private final KeyStroke ctrl_W = KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK);



public LevelEditorMainClass()
{
	super("Platformer - Level Editor");
	
	random = new Random();
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
		tileImage[50] = ImageIO.read(getClass().getResource("/collectibles0.png"));
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
	menuPlik.add(menuPlikNowy);
	menuPlik.add(menuPlikZapisz);
	menuPlik.add(menuPlikOtworz);
	menuPlik.add(menuPlikZakoncz);
	menuGenerator.add(menuGeneratorPodloze);
	menuGenerator.add(menuGeneratorLosowyTeren);
	menuGenerator.add(menuGeneratorWyrownanieTerenu);
	menuBar.add(menuPlik);
	menuBar.add(menuGenerator);
	setJMenuBar(menuBar);
	
	menuPlikNowy.addActionListener(menuListener);
	menuPlikNowy.setActionCommand("New");
	menuPlikNowy.setAccelerator(ctrl_N);

	
	menuPlikZapisz.addActionListener(menuListener);
	menuPlikZapisz.setActionCommand("Save");
	menuPlikZapisz.setAccelerator(ctrl_S);

	
	menuPlikOtworz.addActionListener(menuListener);
	menuPlikOtworz.setActionCommand("Open");
	menuPlikOtworz.setAccelerator(ctrl_O);

	
	menuPlikZakoncz.addActionListener(menuListener);
	menuPlikZakoncz.setActionCommand("Exit");
	menuPlikZakoncz.setAccelerator(ctrl_X);
	
	menuGeneratorPodloze.addActionListener(menuListener);
	menuGeneratorPodloze.setActionCommand("Podloze");
	menuGeneratorPodloze.setAccelerator(ctrl_P);
	
	menuGeneratorLosowyTeren.addActionListener(menuListener);
	menuGeneratorLosowyTeren.setActionCommand("LosowyTeren");
	menuGeneratorLosowyTeren.setAccelerator(ctrl_T);
	
	menuGeneratorWyrownanieTerenu.addActionListener(menuListener);
	menuGeneratorWyrownanieTerenu.setActionCommand("Wyrownanie");
	menuGeneratorWyrownanieTerenu.setAccelerator(ctrl_W);
}

public class MenuListener implements ActionListener
{
	
public void clearMap()
{
	for (int i = 0; i < ROWS; i++)
		for (int j = 0; j < COLS; j++)
		{
			editorPane.editorTiles[i][j].setIcon(null);
			editorPane.tileValues[i][j] = -1;
		}
}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equalsIgnoreCase("Exit")) System.exit(0);
		
		if (e.getActionCommand().equalsIgnoreCase("New"))
		{
			clearMap();
		}
		
		if (e.getActionCommand().equalsIgnoreCase("Open"))
		{
			
			String filename = "";
			
			do {
				filename = JOptionPane.showInputDialog(null, "Podaj nazwê pliku: ", currentLevelName);	
			}
			
			while(filename.length() == 0);
			
			clearMap();
			
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
		
		if (e.getActionCommand().equalsIgnoreCase("Podloze"))
		{
			for (int i = 0; i < ROWS; i++)
			{
				for (int j = 0; j < COLS; j++)
				{
					
					if (i == (ROWS-2) & (j!=0) && (j!=COLS-1))
					{
						editorPane.tileValues[i][j] = 1;
						Image newImage = tileImage[editorPane.tileValues[i][j]].getScaledInstance(40, 40, Image.SCALE_DEFAULT);
						editorPane.editorTiles[i][j].setIcon(new ImageIcon(newImage));						
					}
					if (i == (ROWS-1) & (j!=0) && (j!=COLS-1))
					{
						editorPane.tileValues[i][j] = 4;
						Image newImage = tileImage[editorPane.tileValues[i][j]].getScaledInstance(40, 40, Image.SCALE_DEFAULT);
						editorPane.editorTiles[i][j].setIcon(new ImageIcon(newImage));						
					}
					
				}
			}
			editorPane.tileValues[ROWS-2][0] = 0;
			Image newImage = tileImage[editorPane.tileValues[ROWS-2][0]].getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			editorPane.editorTiles[ROWS-2][0].setIcon(new ImageIcon(newImage));	
			editorPane.tileValues[ROWS-1][0] = 3;
			newImage = tileImage[editorPane.tileValues[ROWS-1][0]].getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			editorPane.editorTiles[ROWS-1][0].setIcon(new ImageIcon(newImage));
			
			editorPane.tileValues[ROWS-2][COLS-1] = 2;
			newImage = tileImage[editorPane.tileValues[ROWS-2][COLS-1]].getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			editorPane.editorTiles[ROWS-2][COLS-1].setIcon(new ImageIcon(newImage));	
			editorPane.tileValues[ROWS-1][COLS-1] = 5;
			newImage = tileImage[editorPane.tileValues[ROWS-1][COLS-1]].getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			editorPane.editorTiles[ROWS-1][COLS-1].setIcon(new ImageIcon(newImage));
		}
		
		if (e.getActionCommand().equalsIgnoreCase("LosowyTeren"))
		{			
			int[][] tempMap = new int[ROWS-2][COLS-1];
			
			// LOSOWY TEREN
			for (int i = 0; i < ROWS-2; i++)
			{
				for (int j = 0; j < COLS-1; j++)
				{
					if (random(3) == 0) tempMap[i][j] = 1;
					else tempMap[i][j] = -1;
				}
			}
			
			
			// DODANIE DO OBECNEGO TERENU - TERENU LOSOWEGO
			for (int i = 0; i < ROWS-2; i++)
			{
				for (int j = 0; j < COLS-1; j++)
				{
					editorPane.tileValues[i+1][j+1] = tempMap[i][j];
					Image newImage = null;
					if (editorPane.tileValues[i][j] != -1)
					{
						newImage = tileImage[editorPane.tileValues[i][j]].getScaledInstance(40, 40, Image.SCALE_DEFAULT);
						editorPane.editorTiles[i][j].setIcon(new ImageIcon(newImage));	
					}
				}
			}
		}
		
		if (e.getActionCommand().equalsIgnoreCase("Wyrownanie"))
		{

			int MX = ROWS;
			int MY = COLS;
			int[][] tempMap = new int[MX][MY];
			
			// PRZEPISANIE OBECNEJ MAPY NA TEMPMAPE
			for (int i = 0; i < MX; i++)
			{
				for (int j = 0; j < MY; j++)
				{
					tempMap[i][j] = editorPane.tileValues[i][j];
				}
			}
			
			
			// TUTAJ JEST WYGLADZANIE TERENU
			int[][] maxT = new int[MX][MY]; // TABLICA BLOKÓW
			for (int i = 0; i < MX; i++)
				for (int j = 0; j < MY; j++)
					maxT[i][j] = -1;
			
			
			
			for (int i = 0; i < MX; i++)
			{
				for (int j = 0; j < MY; j++)
				{
					if ((i > 1) && i < (MX-1) && (j > 1) && (j < MY-2))
					{
						
						/// SPRAWDZANIE KONKRETNEGO BLOKU PE£NEGO
						
						maxT[i][j] = 1;
						int[][] temp = new int[3][3];
						
						// ZBIERANIE SASIADÓW
						for (int a = -1; a < 2; a++)
							for (int b = -1; b < 2; b++)
								temp[a+1][b+1] = tempMap[i+a][j+b];
						
						for (int a = 0; a < 3; a++)
							for (int b = 0; b < 3; b++)
							{
								if (temp[a][b] == 1) maxT[i][j] += 1; // NABIJANIE ILOŒCI SASIADÓW
							}
					
						
						if (maxT[i][j] > 4)
						{
							tempMap[i][j] = 1;
						} else tempMap[i][j] = -1;
						
					}
				}
			}
			
			/**
			for (int i = 0; i < ROWS; i++)
			{
				for (int j = 0; j < COLS; j++)
				{
					
					if (i > 0 && i < ROWS && j > 0 && j < COLS-1)
					{
						if (tempMap[i][j] == 1)
						{
							/// SPRAWDZANIE KONKRETNEGO BLOKU PE£NEGO
							
							maxT[i][j] = 1;
							int[][] temp = new int[3][3];
							
							
							// ZBIERANIE SASIADÓW
							for (int a = -1; a < 2; a++)
								for (int b = -1; b < 2; b++)
									temp[a+1][b+1] = tempMap[i+a][j+b];
							
							
							for (int a = 0; a < 3; a++)
								for (int b = 0; b < 3; b++)
								{
									if (temp[a][b] == 1) maxT[i][j] += 1; // NABIJANIE ILOŒCI SASIADÓW
								}
						
							
							if (maxT[i][j] > 3)
							{
								tempMap[i][j] = 1;
							}
						}
					}
				}
				
			}**/
			
			// TU SIE KONCZY WYGLADZANIE TERENU
			
			for (int i = 0; i < ROWS-2; i++)
			{
				for (int j = 0; j < COLS-1; j++)
				{
					editorPane.tileValues[i][j] = tempMap[i][j];
					Image newImage = null;
					if (editorPane.tileValues[i][j] != -1)
					{
						newImage = tileImage[editorPane.tileValues[i][j]].getScaledInstance(40, 40, Image.SCALE_DEFAULT);
						editorPane.editorTiles[i][j].setIcon(new ImageIcon(newImage));	
					}
				}
			}
		}
	}

public int random(int number)
{
	int i = random.nextInt(number);
	return i;
}
	
public int random(int start, int end)
{
	int i = random.nextInt(end-start+1)+start;
	return i;
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