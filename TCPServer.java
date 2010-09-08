import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPServer extends Thread {

	private Socket tcpSocket;
	private ServerSocket server;
	private BufferedReader in = null;
	
	public void run() {
		
		TCPServer tcpServer = new TCPServer();
		try {
			tcpServer.startTCPServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void startTCPServer() throws IOException{
		
		boolean complete = false;
		
		server = new ServerSocket(6070);
		
		while (!complete) {
			
			tcpSocket = server.accept();
			System.out.println("server accepted");
			
			in = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
			String message = new String (in.readLine());
			System.out.println("Recd: " + message);
			
			if (message.compareTo("exit") == 0) {
				complete = true;
			}
			
		}
		
		in.close();
		tcpSocket.close();
		System.out.println("Server closed"); 

		
	}

}
