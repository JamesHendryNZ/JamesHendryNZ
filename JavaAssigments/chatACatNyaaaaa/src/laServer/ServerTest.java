package laServer;

import javax.swing.JFrame;

public class ServerTest {

	public static void main(String[] args) 
	{
		Server myServer = new Server();
		
		myServer.setDefaultConstructor(JFrame.EXIT_ON_CLOSE);
		myServer.startRunning();
		//burn baby burns babies

	}

}
