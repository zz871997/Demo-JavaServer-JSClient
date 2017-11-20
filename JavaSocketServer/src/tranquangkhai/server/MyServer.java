package tranquangkhai.server;

import com.corundumstudio.socketio.listener.*;
import com.corundumstudio.socketio.*;


public class MyServer {
	public static void main(String[] args) throws InterruptedException {
		Configuration config = new Configuration();
		config.setHostname("localhost");
		config.setPort(9092);
		
		final SocketIOServer server = new SocketIOServer(config);
		
		/* Chat event - Server listens 'chatevent' from client then send to all client */
		server.addEventListener("chatevent", ChatObject.class, new DataListener<ChatObject>() {

			@Override
			public void onData(SocketIOClient client, ChatObject data, AckRequest ackRequest) throws Exception {
				// Broadcast messages to all clients
				server.getBroadcastOperations().sendEvent("chatevent", data);
			}
		});
		
		/* Start server */
		server.start();
		
		Thread.sleep(Integer.MAX_VALUE);
		
		server.stop();

	}
}
