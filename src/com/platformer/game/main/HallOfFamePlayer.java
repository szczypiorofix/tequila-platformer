package com.platformer.game.main;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class HallOfFamePlayer implements Serializable{


private static final long serialVersionUID = -3735618211371928538L;

private String name;
private int score;
private long milis;
	
	
public HallOfFamePlayer(String name, int score, long milis)
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

public long getMilis() {
	return milis;
}

public String getTimeFromMilis(long millis)
{
	long second = TimeUnit.MILLISECONDS.toSeconds(millis);
	long minute = TimeUnit.MILLISECONDS.toMinutes(millis);
	millis -= TimeUnit.SECONDS.toMillis(second);
	return String.format("%02d:%02d:%d", minute, second, millis);
}

public void setMilis(long milis) {
	this.milis = milis;
}
}