package com.platformer.game.main;

import java.io.Serializable;
import java.util.ArrayList;

public class NetworkData implements Serializable {

	

private static final long serialVersionUID = 8691704653727200329L;
	
private NetworkDataClass networkDataClass;
private ArrayList<HallOfFamePlayer> hallOfFamePlayers;



public NetworkData(NetworkDataClass networkDataClass, ArrayList<HallOfFamePlayer> hallOfFamePlayers) {
	super();
	this.networkDataClass = networkDataClass;
	this.hallOfFamePlayers = hallOfFamePlayers;
}


public NetworkDataClass getNetworkDataClass() {
	return networkDataClass;
}

public void setNetworkDataClass(NetworkDataClass networkDataClass) {
	this.networkDataClass = networkDataClass;
}

public ArrayList<HallOfFamePlayer> getHallOfFamePlayers() {
	return hallOfFamePlayers;
}

public void setHallOfFamePlayers(ArrayList<HallOfFamePlayer> hallOfFamePlayers) {
	this.hallOfFamePlayers = hallOfFamePlayers;
}
}