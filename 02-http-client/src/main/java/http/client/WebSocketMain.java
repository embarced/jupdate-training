package http.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Builder;
import java.net.http.WebSocket.Listener;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

public class WebSocketMain {

	public static void main(String[] args) throws InterruptedException {
		Executor executor = ForkJoinPool.commonPool();
		HttpClient httpClient = HttpClient.newBuilder().executor(executor).build();

		Builder webSocketBuilder = httpClient.newWebSocketBuilder();
		CompletableFuture<WebSocket> webSocketFuture = webSocketBuilder
				.buildAsync(URI.create("ws://localhost:8080/messages"), new Listener() {
					@Override
					public void onOpen(WebSocket webSocket) {
						System.out.println("WebSocket Client verbunden");
						webSocket.sendText("Nachricht vom Client", true);
						Listener.super.onOpen(webSocket);
					}

					@Override
					public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
						System.out.println("Nachricht vom Server empfangen: " + data);
						return Listener.super.onText(webSocket, data, last);
					}

					@Override
					public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
						System.out.println("Verbindung geschlossen: " + reason);
						return Listener.super.onClose(webSocket, statusCode, reason);
					}
				});
		WebSocket webSocket = webSocketFuture.join();
		System.out.println("WebSocket erstellt");
		Thread.sleep(10000);
		CompletableFuture<WebSocket> sendClose = webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "ok");
		sendClose.thenRun(() -> System.out.println("Verbindung wird beendet"));
	}
}
