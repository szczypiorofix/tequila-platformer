package com.platformer.game.main;

import java.util.ArrayList;

class HallOfFame {

	private ArrayList<HallOfFamePlayer> hallOfFame;

	HallOfFame(ArrayList<HallOfFamePlayer> hallOfFame) {
		this.hallOfFame = hallOfFame;
	}


	ArrayList<HallOfFamePlayer> getHallOfFameList() {
		return hallOfFame;
	}

	void setHallOfFame(ArrayList<HallOfFamePlayer> hallOfFame) {
		this.hallOfFame = hallOfFame;
	}
}
