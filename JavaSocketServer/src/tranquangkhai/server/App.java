package tranquangkhai.server;

/*
 * The client Node JS send a query, the server NodeJS answer the query, simple. 
 * I'm using netty-socketio to implement the server part in java. 
 * Here is what I have right now:
 */


import java.io.UnsupportedEncodingException;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

public class App {
	public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
		Configuration config = new Configuration();
		config.setHostname("localhost");
		config.setPort(8080);
		
		final SocketIOServer server = new SocketIOServer(config);
		
		server.addConnectListener(new ConnectListener() {
			
			@Override
			public void onConnect(SocketIOClient client) {
				System.out.println("Client connected! : " + client.toString());
			}
		});
		
		server.addEventListener("myEvent", String.class, new DataListener<String>() {
			@Override
			public void onData(final SocketIOClient client, String data, AckRequest ackRequest	){
				System.out.println("myEvent triggered");
				System.out.println("Here is the query from the client: " + data);
				ackRequest.sendAckData("I am the answer from Java Server");
			}
		});
		
		server.addDisconnectListener(new DisconnectListener() {
			@Override
			public void onDisconnect(SocketIOClient client) {
				System.out.println("client disconnected! : " + client.toString());
			}
		});
		
		server.start();
		System.out.println("ServerJava started");
		
		Thread.sleep(20000);	//Integer.MAX_VALUE;
		
		server.stop();
		System.out.println("ServeJava stopped!");
	}	
}


