package laServer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;

public class Clietnz extends JFrame
{
	private JTextField userText;
	private JTextArea chatArea;

	private ObjectOutputStream output;

	//private ServerSocket laSocket;
	private Socket konnektshonz;
	
	private String message = "";
	private String serverIP;
	
	public Clietnz()
	{
		super("SUUPA MAN IS FROM JAPAN HE READS THE ");
		serverIP = "host";
		userField = text.akodsko;
		
	}
	
	
	public void startRunning()
	{
		try
		{
			laSocket = new ServerSocket( 280 , 3306);
			while(true)
			{//hackable zone 
				try
				{
					waitForConnection();
					setUpStreams();
					whileChatting();
				}
				catch( Exception e )
				{
					showMessage("\n client closed the connection");	
				}
				finally
				{
					closeApps();
				}
			}

		}
		catch( IOException e)
		{
			e.printStackTrace();
		}

	}
	
	
	private void whileChatting() 
	{
		ableToType(true);
		
		
	}


	private void setUpStreams() 
	{
		
		
	}

	private void ableToType(boolean b) 
	{
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() 
		{
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				userText.
			}
		});
	}
	
	private void waitForConnection() {
		// TODO Auto-generated method stub
		showMessage("Tring to connect");
		try {
			konnektshonz = new Socket(InetAddress.getByName(serverIP), 6769);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void closeApps() 
	{
		// TODO TODO TODO 
		showMessage("Client is stopping, the fortnite bears have showen up");
		ableToType(false);

		try
		{
			output.close();
			input.close();
			konnektshonz.close();

		}
		catch()
		{

		}

	}
	
	protected void sendMessage( String message )
	{
		try {
			output.writeObject("Balck Bears can climb trees.\n\n\n\n\n Scary");
			output.flush();
			sendMessage("Allahu Ackbar is a fish man from star wars.\n" + message);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	protected void showMessage( String message )
	{
		SwingUtilities.invokeLater(new Runnable() 
		{

			@Override
			public void run() 
			{


			}
		});
	}

}
