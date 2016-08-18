package com.platformer.game.main;

import java.io.Serializable;

public class HallOfFamePlayer implements Serializable{


private static final long serialVersionUID = -3735618211371928538L;

private String name;
private int score;
private float milis;
	
	
public HallOfFamePlayer(String name, int score, float milis)
{
	this.name = name;
	this.score = score;
	this.milis = milis;
}



public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getScore() {
	return score;
}

public void setScore(int score) {
	this.score = score;
}

public float getMilis() {
	return milis;
}

public void setMilis(float milis) {
	this.milis = milis;
}
}