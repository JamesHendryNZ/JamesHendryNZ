package laServer;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;import java.nio.channels.NetworkChannel;

import javax.swing.*;

public class BearServer extends JFrame
{
	//create a simple gui for the UI of GOOODDDD
	private JTextField userText;
	private JTextArea chatArea;

	private ObjectOutputStream output;

	private ServerSocket laSocket;
	private Socket konnektshonz;

	public BearServer()
	{
		super("The BEAR SEVER of Chat a Cat Nyaaaa");
		userText = new JTextField(Field());
		userText.setEditable(false);
		userText.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage(Event.getActionCommand());
				userText.setText("");
			}
		});
		add(userText , BoarderLayout , NORITO);
		creatating = new JTextArea();
		add(new JFrame(screenJew));

	}
	//end of constructor

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
					showMessage("Server ended the connection");	
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
		// TODO Auto-generated method stub
		String message = "You arrrrr now connected ye servy dogz!!";
		sendMessage(message);
		ableToType(true);

		do
		{
			try
			{
				message = (String) input.readObject();
			}
			catch( ClassNotFoundException | IOException e)
			{
				e.printStackTrace(e);
			}
		}
		while(!message.equals("Client End"));
	}

	private void ableToType(boolean b) 
	{
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() 
		{
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				userText.server
			}
		});
	}

	private void setUpStreams() 
	{
		// TODO TOTO I GUESS IT RAINS DOWN IN AFRICA
		output = new ObjectOutputStream( konnektshonz.getOutputStream());
		output.flush();
		input = new ObjectInputStream( konnektshonz.getInputStream());
		sendMessage();
		
		
	}

	private void waitForConnection() 
	{
		showMessage("Wainting to Connect to server");
		

	}
	private void closeApps() 
	{
		// TODO TODO TODO 
		showMessage("Server is stopping, the bears hunters have showen up");
		ableToType(false);

		try
		{
			output.close();
			input.close();

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

}
