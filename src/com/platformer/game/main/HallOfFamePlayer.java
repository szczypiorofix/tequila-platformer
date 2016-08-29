package com.platformer.game.main;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HallOfFamePlayer implements Serializable{


private static final long serialVersionUID = -3735618211371928538L;

private String name;
private int score;
private long milis;
private int level;

	
public HallOfFamePlayer(String name, int score, long milis, int level)
{
	this.name = name;
	this.score = score;
	this.milis = milis;
	this.level = level;
}



public String getTimeFromMilis(long millis)
{
	return (new SimpleDateFormat("mm:ss:SSS")).format(new Date(millis));
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

public long getMilis() {
	return milis;
}

public void setMilis(long milis) {
	this.milis = milis;
}

public int getLevel() {
	return level;
}

public void setLevel(int level) {
	this.level = level;
}
}