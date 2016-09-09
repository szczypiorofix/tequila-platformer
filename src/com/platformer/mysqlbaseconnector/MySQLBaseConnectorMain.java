package com.platformer.mysqlbaseconnector;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.platformer.game.main.HallOfFamePlayer;
import com.platformer.game.main.NetworkData;
import com.platformer.game.main.NetworkDataClass;


public class MySQLBaseConnectorMain {

private	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
private	static final String DB_URL = "jdbc:mysql://localhost:3306/HallOfFame_database";
private	static final String USER = "root";
private	static final String PASS = "Scypiur1";
private Connection conn;
private Statement stmt;
private ResultSet rs;


private ServerSocket serverSocket;
private Socket socket;
private ObjectInputStream ois;
private ObjectOutputStream oos;
private final int SERVER_PORT = 1201;
private boolean serverRunning;
private ArrayList<HallOfFamePlayer> players;
private NetworkData networkData;

	
public MySQLBaseConnectorMain()
{
	serverRunning = true;
	message("Server starting...");
	
	conn = null;
	stmt = null;
	
	downloadResults();
	
	try {
		serverSocket = new ServerSocket(SERVER_PORT);
			
		while (serverRunning)
		{
			socket = serverSocket.accept();
			message("Another client connected!");
			oos = new ObjectOutputStream(socket.getOutputStream());
			
			ois = new ObjectInputStream(socket.getInputStream());
			
			networkData = (NetworkData) ois.readObject();
			
			if (networkData.getNetworkDataClass() == NetworkDataClass.DOWNLOAD)
			{
				downloadResults();
				networkData = new NetworkData(NetworkDataClass.DOWNLOAD, players);
				oos.writeObject(networkData);			
				oos.flush();	
			}
			
			if (networkData.getNetworkDataClass() == NetworkDataClass.ADD)
			{
				players.add(new HallOfFamePlayer(networkData.getHallOfFamePlayers().get(0).getName(), networkData.getHallOfFamePlayers().get(0).getScore(),
						 networkData.getHallOfFamePlayers().get(0).getMilis(), networkData.getHallOfFamePlayers().get(0).getLevel()));
				
				message("Another gamer: " +players.get(players.size()-1).getName());
				
				try {
				
					PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `BestScores` (`Name`, `Score`, `Millis`, `Level`) VALUES (?, ?, ?, ?)");

					pstmt.setString(1, networkData.getHallOfFamePlayers().get(0).getName());
					pstmt.setInt(2, networkData.getHallOfFamePlayers().get(0).getScore());
					pstmt.setLong(3, networkData.getHallOfFamePlayers().get(0).getMilis());
					pstmt.setInt(4, networkData.getHallOfFamePlayers().get(0).getLevel());
					
					pstmt.executeUpdate();
				}
				catch (SQLException sqle)
				{
					sqle.printStackTrace();
				}
				
				networkData = new NetworkData(NetworkDataClass.DOWNLOAD, players);
				oos.writeObject(networkData);			
				oos.flush();	
			}
		}
		
		try {
			rs.close();
			stmt.close();
			conn.close();
	 	}
		catch(SQLException se){
			se.printStackTrace();
	 	}
		catch(Exception e){
			e.printStackTrace();
	 	}
		finally {
	    try{
	       if(stmt!=null) stmt.close();
	    }
	    catch(SQLException se2){
	    }
	    try{
	       if(conn!=null) conn.close();
	    }
	    catch(SQLException se) {
	       se.printStackTrace();
	    }
	 	}
		
	}
	catch (IOException | ClassNotFoundException ioe)
	{
		ioe.printStackTrace();
		System.exit(-1);
	}
	finally
	{
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}

public void downloadResults()
{
	players = new ArrayList<HallOfFamePlayer>(30);
	
	try {
		message("JDBC registering... " +JDBC_DRIVER);
		Class.forName("com.mysql.jdbc.Driver");
		message("MySQL database connecting ... "+DB_URL);
		conn = DriverManager.getConnection(DB_URL,USER,PASS);

	    stmt = conn.createStatement();
	    String sql;
	    
	    sql = "SELECT Id, Name, Score, Millis, Level FROM BestScores ORDER BY Score DESC";
	    rs = stmt.executeQuery(sql);
	    
	    //INSERT INTO `numery` (`Indeks`, `Numer`, `Nazwa`) VALUES (NULL, '1003', 'Zdzis³aw Dyrman');
	    
	    //rs = stmt.executeQuery(sql);

	    while(rs.next()){
		     int id  = rs.getInt("Id");
		     String name = rs.getString("Name");
		     int score = rs.getInt("Score");
		     int millis = rs.getInt("Millis");
		     int level = rs.getInt("Level");
		     
		     message("Id: " +id +" Name: " +name +" Score: " +score +" Millis: " +millis +" Level: " +level);
		     players.add(new HallOfFamePlayer(name, score, millis, level));
	    }
	    
	 	}catch(SQLException se){
	 		se.printStackTrace();
	 	}catch(Exception e){
	 		e.printStackTrace();
	 	}
	 	
		message("Data from MySQL database receoived.");
}
	
public void message(String s)
{
	String timeStamp = new SimpleDateFormat("dd.MM.yyy HH:mm:ss").format(Calendar.getInstance().getTime());
	System.out.println(timeStamp +" " +s);
}

public static void main(String[] args) {
	new MySQLBaseConnectorMain();
}
}