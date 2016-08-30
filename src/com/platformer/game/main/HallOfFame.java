package com.platformer.game.main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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
			String message = MainClass.getStackTrace(ioe);
			MainClass.logging(true, "B³¹d zapisu kolejnego uczestnika do pliku "+MainClass.hallOfFameFile.getName(), message);
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