package com.platformer.game.main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;

public class HallOfFame {
	

private ObjectOutputStream oos;


private ArrayList<HallOfFamePlayer> hallOfFame;


public HallOfFame(ArrayList<HallOfFamePlayer> hallOfFame)
{
	this.hallOfFame = hallOfFame;
}


public void writeScoreToFile()
{	
	if(MainClass.hallOfFameFile.exists() && !MainClass.hallOfFameFile.isDirectory())
	{
		try {
		oos = new ObjectOutputStream(new FileOutputStream((MainClass.hallOfFameFile)));
	    oos.writeObject(getHallOfFameList());
	    oos.close();
		}
		catch (IOException ioe)
		{
			MainClass.logging(false, Level.WARNING, "B³¹d zapisu kolejnego uczestnika do pliku "+MainClass.hallOfFameFile.getName());
			MainClass.logging(true, Level.WARNING, MainClass.getStackTrace(ioe));
		}
	}
}

public ArrayList<HallOfFamePlayer> getHallOfFameList() {
	return hallOfFame;
}

public void setHallOfFame(ArrayList<HallOfFamePlayer> hallOfFame) {
	this.hallOfFame = hallOfFame;
}	
}