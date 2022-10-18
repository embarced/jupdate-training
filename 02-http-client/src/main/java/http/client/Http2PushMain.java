package http.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse.PushPromiseHandler;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.function.Function;

public class Http2PushMain {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException, NoSuchAlgorithmException, KeyManagementException {
		Executor executor = ForkJoinPool.commonPool();
		
		HttpClient httpClient = HttpClient.newBuilder().sslContext(SSLHelper.createSSLContext()).executor(executor).build();

		HttpRequest mainRequest = HttpRequest.newBuilder().version(Version.HTTP_2)
				.uri(URI.create("https://localhost:8443/getIt")).build();

		Collection<Future<HttpResponse<String>>> futures = ConcurrentHashMap.newKeySet();

		CompletableFuture<HttpResponse<String>> response = httpClient.sendAsync(mainRequest, BodyHandlers.ofString(),
				new PushPromiseHandler<String>() {
					@Override
					public void applyPushPromise(HttpRequest initiatingRequest, HttpRequest pushPromiseRequest,
							Function<BodyHandler<String>, CompletableFuture<HttpResponse<String>>> acceptor) {
						System.out.println("Ressource per server push: " + pushPromiseRequest.uri());
						futures.add(acceptor.apply(BodyHandlers.ofString()));
					}
				});
		futures.add(response);

		Thread.sleep(1000);

		futures.forEach(f -> {
			try {
				HttpResponse<String> httpResponse = f.get();
				System.out.println("Response URL: " + httpResponse.uri());
				System.out.println("Response Content: " + httpResponse.body());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
	}
}
