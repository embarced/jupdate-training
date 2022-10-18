package http.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.ForkJoinPool;

public class ReactiveHttpClientMain {

	private static class LineSubscriber implements Subscriber<String> {

		private Subscription subscription;

		@Override
		public void onSubscribe(Subscription subscription) {
			this.subscription = subscription;
			this.subscription.request(1);
		}

		@Override
		public void onNext(String line) {
			if (line.contains("27")) {
				System.out.println("Zeile gefunden: " + line);
				subscription.cancel();
			} else {
				System.out.println("Zeile noch nicht gefunden: " + line);
				subscription.request(1);
			}
		}

		@Override
		public void onComplete() {
		}

		@Override
		public void onError(Throwable e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
			throws InterruptedException, ExecutionException, NoSuchAlgorithmException, KeyManagementException {
		Executor executor = ForkJoinPool.commonPool();

		HttpClient httpClient = HttpClient.newBuilder().executor(executor).build();
		HttpRequest mainRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/streamIt")).build();

		CompletableFuture<HttpResponse<Void>> response = httpClient.sendAsync(mainRequest,
				BodyHandlers.fromLineSubscriber(new LineSubscriber()));

		response.join();
	}
}
