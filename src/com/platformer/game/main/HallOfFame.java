package com.platformer.game.main;

import java.util.ArrayList;

public class HallOfFame {
	




private ArrayList<HallOfFamePlayer> hallOfFame;


public HallOfFame(ArrayList<HallOfFamePlayer> hallOfFame)
{
	this.hallOfFame = hallOfFame;
}



public ArrayList<HallOfFamePlayer> getHallOfFameList() {
	return hallOfFame;
}

public void setHallOfFame(ArrayList<HallOfFamePlayer> hallOfFame) {
	this.hallOfFame = hallOfFame;
}	
}