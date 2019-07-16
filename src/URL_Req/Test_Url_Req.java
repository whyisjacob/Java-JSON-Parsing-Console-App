package URL_Req;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import org.json.*;

public class Test_Url_Req {
	public static void main(String[] args) {
		try { 
			//Scanner scanner = new Scanner(System.in);
			
			//System.out.println("Enter the URL to lookup (Without http{s})\n");
			//String userUrl = scanner.nextLine();
			
			//String url = "http://api.ipstack.com/" + userUrl + "?access_key=cb122886d96830d0cbda3e0e45f64658";
			String url = "http://jsonplaceholder.typicode.com/albums";
			
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
			
			client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
				.thenApply(HttpResponse::body)
				.thenApply(Test_Url_Req::parse)
				.join();
			
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
	
	public static String parse(String responseBody) {
		JSONArray albums = new JSONArray(responseBody);
		for(int i = 0; i < albums.length(); i++) {
			JSONObject album = albums.getJSONObject(i);
			int id = album.getInt("id");
			int userId = album.getInt("userId");
			String title = album.getString("title");
			System.out.println(id+ " " + title + " " + userId +'\n');
			
		}
		return null;
	}
	
}
