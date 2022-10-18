package http.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class HttpClientMain {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException, NoSuchAlgorithmException, KeyManagementException {
		HttpClient httpClient = HttpClient.newBuilder().build();

		{
			HttpRequest mainRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/hello"))
					.build();

			HttpResponse<String> response;
			try {
				response = httpClient.send(mainRequest, BodyHandlers.ofString());
				System.out.println(response.body());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// HTTP POST
		{
			HttpRequest mainRequest = HttpRequest.newBuilder().POST(BodyPublishers.ofString("Schick mich!"))
					.header("Content-Type", "text/plain")
					.uri(URI.create("http://localhost:8080/postIt"))
					.build();
			
			try {
				httpClient.send(mainRequest, BodyHandlers.ofString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Asynchrone Verarbeitung
		{
			HttpRequest mainRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/hello"))
					.build();
			
			CompletableFuture<HttpResponse<String>> responseFuture = httpClient.sendAsync(mainRequest,
					BodyHandlers.ofString());
			
			responseFuture.whenComplete((response, error) -> {
				if (response != null) {
					System.out.println(response.body());
				}
				if (error != null) {
					error.printStackTrace();
				}
			});
			
			System.out.println(responseFuture.join().body());
		}
		
		// BodyHandler
		{
			HttpRequest mainRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/icon.png"))
					.build();

			HttpResponse<byte[]> response;
			try {
				response = httpClient.send(mainRequest, BodyHandlers.ofByteArray());
				System.out.println(Arrays.toString(response.body()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Konfiguration
		{
			HttpClient httpClient2 = HttpClient.newBuilder().executor(ForkJoinPool.commonPool())
					.followRedirects(Redirect.NORMAL).build();
			
			// http://www.oio.de hat eine Weiterleitung auf https://www.oio.de
			HttpRequest mainRequest = HttpRequest.newBuilder().uri(URI.create("http://www.oio.de"))
					.build();

			HttpResponse<String> response;
			try {
				response = httpClient2.send(mainRequest, BodyHandlers.ofString());
				System.out.println(response.body());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
