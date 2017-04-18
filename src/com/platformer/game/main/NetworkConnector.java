package com.platformer.game.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;


public class NetworkConnector {

public static boolean connected;

private ArrayList<HallOfFamePlayer> hallOfFameRecords;
private static final String USER_AGENT = "Mozilla/5.0";
private static final String POST_URL_GET_RESULTS = "https://www.wroblewskipiotr.pl/gethalloffameresults.php";
private static final String POST_URL_ADD_RESULT = "https://www.wroblewskipiotr.pl/addhalloffameresult.php";
private static final String POST_PARAMS = "getallresults";
private static String post_addplayer = "";


public NetworkConnector()
{
	connected = false;
}

public void addAnotherPlayerToHoF(HallOfFamePlayer hofPlayer)
{
	hallOfFameRecords = new ArrayList<HallOfFamePlayer>();
	try {
		URL obj = new URL(POST_URL_ADD_RESULT);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Charset", "UTF-8");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("charset", "UTF-8");

		post_addplayer = "name="+hofPlayer.getName()+"&score="+hofPlayer.getScore()+"&millis="
				+hofPlayer.getMillis()+"&level="+hofPlayer.getLevel()+"&date="+hofPlayer.getDate();
		
		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(post_addplayer.getBytes());
		os.flush();
		os.close();
		// For POST only - END
		
		String results = "";
		int responseCode = con.getResponseCode();
		if (responseCode == HttpsURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			results = response.toString();
		}
		else MainClass.logging(false, Level.WARNING, "Połączenie ze skryptem serwera - błąd !!!");
		
		//System.out.println(results); // Wyswietla `OK` jesli dodanie wyniku do bazy przebiegnie pomyslnie.
		
		if (results.equals("OK"))
			MainClass.logging(false, Level.INFO, "Poprawnie dodano kolejnego gracza");
		else 
			MainClass.logging(false, Level.WARNING, "Dodawanie gracza do listy zakończone błędem !!!");
		}
		catch (Exception ex) {
			ex.printStackTrace();
			MainClass.logging(false, Level.WARNING, "Błąd łączenia ze skryptem na serwerze!");
			MainClass.logging(false, Level.WARNING, MainClass.getStackTrace(ex));
		}
}

public ArrayList<HallOfFamePlayer> getHOFRecordsFromServer()
{
	hallOfFameRecords = new ArrayList<HallOfFamePlayer>();
	
	try {
	URL obj = new URL(POST_URL_GET_RESULTS);
	HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
	con.setRequestMethod("POST");
	con.setRequestProperty("User-Agent", USER_AGENT);
	con.setRequestProperty("Accept-Charset", "UTF-8");
	con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	con.setRequestProperty("charset", "UTF-8");

	// For POST only - START
	con.setDoOutput(true);
	OutputStream os = con.getOutputStream();
	os.write(POST_PARAMS.getBytes());
	os.flush();
	os.close();
	// For POST only - END
	
	String results = "";
	int responseCode = con.getResponseCode();
	if (responseCode == HttpsURLConnection.HTTP_OK) {
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		results = response.toString();
	}
	else System.out.println("POST request not worked");
		
	//System.out.println(results);
	
	Gson g = new Gson();
	HallOfFamePlayer[] persons = g.fromJson(""+results, HallOfFamePlayer[].class);
	
	for (int i=0; i < persons.length; i++)
		hallOfFameRecords.add(new HallOfFamePlayer(persons[i].getName(), persons[i].getScore(), persons[i].getMillis(), persons[i].getLevel(), persons[i].getDate()));
	
	}
	catch (Exception ex) {
		ex.printStackTrace();
		MainClass.logging(false, Level.WARNING, "Błąd łączenia ze skryptem na serwerze!");
		MainClass.logging(false, Level.WARNING, MainClass.getStackTrace(ex));
	}
	connected = true;
	return hallOfFameRecords;
}
}