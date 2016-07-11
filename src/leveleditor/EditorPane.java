package leveleditor;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class EditorPane extends JPanel{

	
private static final long serialVersionUID = -5692560801368198843L;
private int row, col;
private Tile[][] editorTiles;
private ActionListener editorTilesListener;

	
public EditorPane(int row, int col)
{
	super(new GridLayout(row, col));
	this.row = row;
	this.col = col;
	setPreferredSize(new Dimension(row * 120, col * 20));
	setMinimumSize(new Dimension(row * 120, col * 20));
	setMaximumSize(new Dimension(row * 120, col * 20));
	
	editorTiles = new Tile[this.row][this.col];
	editorTilesListener = new EditorTilesListener();
	
	for (int a = 0; a < row; a++)
		for (int b = 0; b < col; b++)
		{
			editorTiles[a][b] = new Tile(a+"");
			editorTiles[a][b].addActionListener(editorTilesListener);
			editorTiles[a][b].setActionCommand(a+":"+b);
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
		
		editorTiles[Integer.parseInt(first)][Integer.parseInt(second)].setIcon(new ImageIcon(LevelEditorMainClass.tileImage[LevelEditorMainClass.selectedTile]));
		
	}
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