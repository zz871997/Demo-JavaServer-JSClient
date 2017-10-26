package tranquangkhai.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	public MyServer(){
		
	}
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		BufferedReader dataInputStream = null;
		
		try {
			serverSocket = new ServerSocket(8888);
			System.out.println("Listening...");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			socket = serverSocket.accept();
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("can't listen given port \n");
	        System.exit(-1);
		}
		
		try {
			dataInputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("ip: " + socket.getInetAddress());
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("can't read File \n");
            System.exit(-1);
		}
		
			try {
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = dataInputStream.readLine()) != null) {
					sb.append(line + "\n");
				}
				dataInputStream.close();
				System.out.println("Message: " + sb.toString());
			} 
			catch (IOException e) {
				e.printStackTrace();
				System.out.println("Cant read file \n");
			}
			finally {
				if (socket != null) {
					try {
						socket.close();
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (dataInputStream != null) {
					try {
						dataInputStream.close();
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	}
}
