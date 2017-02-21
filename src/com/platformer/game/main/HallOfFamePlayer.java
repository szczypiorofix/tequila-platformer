package com.platformer.game.main;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HallOfFamePlayer implements Serializable {



private static final long serialVersionUID = 8884657204736598287L;

private String name;
private int score;
private long millis;
private int level;
private String insert_date;

	
public HallOfFamePlayer(String name, int score, long millis, int level, String date)
{
	this.name = name;
	this.score = score;
	this.millis = millis;
	this.level = level;
	this.insert_date = date;
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

public long getMillis() {
	return millis;
}

public void setMillis(long milis) {
	this.millis = milis;
}

public int getLevel() {
	return level;
}

public void setLevel(int level) {
	this.level = level;
}

public void setDate(String date) {
	this.insert_date = date;
}

public String getDate() {
	return this.insert_date;
}

}