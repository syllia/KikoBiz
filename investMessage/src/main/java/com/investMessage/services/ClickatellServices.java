package com.investMessage.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClickatellServices {
	private static final Logger log = LoggerFactory.getLogger(ClickatellServices.class);

	public static void sendMessage(String message, String numero) throws IOException, InterruptedException {

		String messageSend = URLEncoder.encode(message, "Windows-1252");
		String url = "http://api.clickatell.com/http/sendmsg?user=j.adossou&password=papa1212" + "&api_id=3624849"
				+ "&to=" + numero + "&text=" + messageSend + "&from=14184550039";
		sendGET(url);
	}

	private static void sendGET(String url) throws IOException, InterruptedException {
		String USER_AGENT = "Mozilla/5.0";
		URL obj = new URL(url);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		log.info("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			log.info(response.toString());
			in.close();
		} else {
			log.info("GET request not worked");
		}
	}
}
