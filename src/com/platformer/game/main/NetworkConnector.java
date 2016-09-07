package com.platformer.game.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;

public class NetworkConnector {

public static boolean connected;
private ArrayList<HallOfFamePlayer> hallOfFameRecords;
private Socket socket;
private ObjectInputStream ois;
private ObjectOutputStream oos;
private NetworkData networkData;


public NetworkConnector()
{
	connected = false;
}

public void addAnotherPlayerToHoF(HallOfFamePlayer hofPlayer)
{
	hallOfFameRecords = new ArrayList<HallOfFamePlayer>();
	
	try {
		socket = new Socket();
		socket.connect(new InetSocketAddress("127.0.0.1", 1201), 5000);
		
		
		//hallOfFameRecords = (ArrayList<HallOfFamePlayer>) ois.readObject();
		
		oos = new ObjectOutputStream(socket.getOutputStream());
		
		ArrayList<HallOfFamePlayer> temp = new ArrayList<HallOfFamePlayer>();
		temp.add(hofPlayer);
		
		networkData = new NetworkData(NetworkDataClass.ADD, temp);
		oos.writeObject(networkData);			
		oos.flush();
		
		ois = new ObjectInputStream(socket.getInputStream());
		networkData = (NetworkData) ois.readObject();
		hallOfFameRecords = networkData.getHallOfFamePlayers();
		ois.close();
		MainClass.logging(false, Level.INFO, "Dane z serwera odczytane poprawnie.");
	}
	catch (ClassNotFoundException cnfe)
	{
		cnfe.printStackTrace();
		MainClass.logging(false, Level.WARNING, "Nie znaleziono poprawnej klasy po��czeniowej z serwerem");
		MainClass.logging(false, Level.WARNING, MainClass.getStackTrace(cnfe));
	}

	catch (IOException ioe)
	{
		ioe.printStackTrace();
		MainClass.logging(false, Level.WARNING, "B��d po��czenia z serwerem");
		MainClass.logging(false, Level.WARNING, MainClass.getStackTrace(ioe));
	}
}

public ArrayList<HallOfFamePlayer> getHOFRecordsFromServer()
{
	hallOfFameRecords = new ArrayList<HallOfFamePlayer>();
	
	try {
		socket = new Socket();
		socket.connect(new InetSocketAddress("127.0.0.1", 1201), 5000);
		
		oos = new ObjectOutputStream(socket.getOutputStream());
		
		networkData = new NetworkData(NetworkDataClass.DOWNLOAD, new ArrayList<HallOfFamePlayer>());
		oos.writeObject(networkData);			
		oos.flush();
		
		ois = new ObjectInputStream(socket.getInputStream());
		networkData = (NetworkData) ois.readObject();
		hallOfFameRecords = networkData.getHallOfFamePlayers();
		ois.close();
		MainClass.logging(false, Level.INFO, "Dane z serwera odczytane poprawnie.");
		connected = true;
	}
	catch (ClassNotFoundException cnfe)
	{
		cnfe.printStackTrace();
		MainClass.logging(false, Level.WARNING, "Nie znaleziono poprawnej klasy po��czeniowej z serwerem");
		MainClass.logging(false, Level.WARNING, MainClass.getStackTrace(cnfe));
		hallOfFameRecords = new ArrayList<HallOfFamePlayer>();
		connected = false;
	}

	catch (IOException ioe)
	{
		ioe.printStackTrace();
		MainClass.logging(false, Level.WARNING, "B��d po��czenia z serwerem");
		MainClass.logging(false, Level.WARNING, MainClass.getStackTrace(ioe));
		hallOfFameRecords = new ArrayList<HallOfFamePlayer>();
		connected = false;
	}
	
	return hallOfFameRecords;
}
}