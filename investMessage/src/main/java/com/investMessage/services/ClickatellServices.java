package com.investMessage.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClickatellServices {
	private static final Logger log = LoggerFactory.getLogger(ClickatellServices.class);

	public static void sendMessage(String message, String numero) throws IOException, InterruptedException {

		String phoneNumber = "+229" + numero;
		String sender = "investdrink";
		String messageSend = URLEncoder.encode(message, "UTF-8");
		String login = "investdrink";
		String apiKey = "c5ed693f13f44c4";
		String smsData = "<DATA><MESSAGE><![CDATA[[" + messageSend + "]]></MESSAGE><TPOA>" + sender
				+ "</TPOA><SMS><MOBILEPHONE>" + phoneNumber + "</MOBILEPHONE></SMS></DATA>";
		String url = "https://api.allmysms.com/http/9.0/sendSms/?login=" + login + "&apiKey=" + apiKey + "&smsData="
				+ smsData;
		// String messageSend = URLEncoder.encode(message, "Windows-1252");
		// String url =
		// "http://api.clickatell.com/http/sendmsg?user=investDrink&password=papa1212"
		// + "&api_id=3620208"
		// + "&to=" + numero + "&text=" + "helloinvestdrink";
		envoi(url);
	}

	private static void envoi(String url) throws IOException {
		URL client = new URL(url);
		URLConnection conn = client.openConnection();
		InputStream responseBody = conn.getInputStream();
		// Convert in XML document

		byte[] contents = new byte[1024];

		int bytesRead = 0;
		String strFileContents = null;
		while ((bytesRead = responseBody.read(contents)) != -1) {
			strFileContents = new String(contents, 0, bytesRead);
		}

		responseBody.close();
		System.out.println(strFileContents);
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
