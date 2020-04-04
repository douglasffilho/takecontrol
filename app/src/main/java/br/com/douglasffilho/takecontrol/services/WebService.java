package br.com.douglasffilho.takecontrol.services;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import br.com.douglasffilho.takecontrol.queues.QueueManager;
import br.com.douglasffilho.takecontrol.queues.Queues;

public class WebService extends WebSocketClient {

    private WebService(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake handShakeData) {
        QueueManager.publish(Queues.MAIN_ACTIVITY_TEXT_UPDATE, "Connected");
        this.send("App done!");
    }

    @Override
    public void onMessage(String message) {
        QueueManager.publish(Queues.MAIN_ACTIVITY_TEXT_UPDATE, message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        QueueManager.publish(Queues.MAIN_ACTIVITY_TEXT_UPDATE, "Closed");
    }

    @Override
    public void onError(Exception ex) {
        QueueManager.publish(Queues.MAIN_ACTIVITY_TEXT_UPDATE, "Error: " + ex.getMessage());
    }

    static void start() throws URISyntaxException {
        WebService webService = new WebService(new URI("ws://192.168.1.4:9092"));
        webService.connect();
    }
}
