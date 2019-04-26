package com.amz.currency.exchange.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class CurrencyExchangeClientApplication {

	public static void main(String[] args) {
		
		boolean responseFlag=false;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in); 
		String input[] = {};

	       while(scanner.hasNextLine()){

	    	   input = scanner.nextLine().trim().split(" ");
	           break;
	       }
		if(input.length == 4) {
		
		StringBuffer url = new StringBuffer("http://localhost:8282/currencyexchange/convertcurrency/"+input[0]+"/"+input[3]+"/"+input[1]);
		
		HttpURLConnection urlConn = null;
		BufferedReader reader = null;
		try {
			URL urlObj = new URL(url.toString());
			urlConn = (HttpURLConnection) urlObj.openConnection();
			urlConn.setRequestMethod("GET");
			urlConn.setConnectTimeout(5000);
			urlConn.setReadTimeout(5000);
			urlConn.setRequestProperty("Accept", "application/json");
			if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.err.println("Unable to connect to the URL...");
				return;
			}
			
			InputStream is = urlConn.getInputStream();
			reader = new BufferedReader(new InputStreamReader((is)));
			String tmpStr = null;
			while ((tmpStr = reader.readLine()) != null) {
					System.out.println(input[0] + " " + input[1] + " = " + input[3] + " " + tmpStr);
					responseFlag=true;
			}
			
			if(!responseFlag) {
			System.out.println("Unable to find rate for " + input[0] + "/" + input[3]);
			}
			} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
				if (urlConn != null)
					urlConn.disconnect();
			} catch (Exception ex) {

			}
		} 
		} else {
			System.out.println("Please check input");
			
		}
	} 

}
