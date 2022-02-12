package workingSolution;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.*;

public class Client extends JFrame{
	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Socket connection;
	
	private String message = "";
	private String serverIP;
	
	public Client(String host) {
		super("Weltec's Instant Messenger - Client");
		serverIP = host;
		userText = new JTextField();
		userText.setEditable(false);
		userText.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				sentMessage(event.getActionCommand());
				userText.setText("");
			}
		});
		
		add(userText, BorderLayout.NORTH);
		chatWindow = new JTextArea();
		add(new JScrollPane(chatWindow));
		setSize(400, 250);
		setVisible(true);
	} //end of constructor

	public void startRunning() {
		try {
		connectToServer();
		setupStreams();
		whileChatting();
		} catch(Exception e) {
			showMessage("\nClient closed the connection...");
		} finally {
			closeApp();
		}
	}
	
	
	
private void closeApp() {
	showMessage("\nClosing connection...");
	ableToType(false);
	
	try {
		output.close();
		input.close();
		connection.close();
		
	} catch (IOException e) {
		e.printStackTrace();
	}
		
	}

private void ableToType(boolean tof) {
	SwingUtilities.invokeLater(new Runnable() {

		@Override
		public void run() {
			userText.setEditable(tof);			
		}
	});
	
}

private void whileChatting() {
		ableToType(true);
		do {
			try {
				message = (String) input.readObject();
				showMessage("\n"+message);
			} catch (ClassNotFoundException | IOException e) {
				showMessage("\n unknown object type.");
			}
			
		} while (!message.equals("SERVER - END"));	
		
	}

private void setupStreams() throws IOException {
	output = new ObjectOutputStream(connection.getOutputStream());
	output.flush();
	input = new ObjectInputStream(connection.getInputStream());
	showMessage("\nstreams are working fine...\n");
		
	}

	private void connectToServer() throws UnknownHostException, IOException {
		showMessage("\nattempting connection...");
		connection = new Socket(InetAddress.getByName(serverIP),6789);
		showMessage("\nConnected to: "+ connection.getInetAddress().getHostName());
	}

	public void sentMessage(String message) {
		try {
			output.writeObject("CLIENT - "+ message);
			output.flush();
			showMessage("\nCLIENT - " + message);
			
		} catch (IOException e) {
			chatWindow.append("\nERROR: Can't send the message");
		}	
	}

	private void showMessage(String clientText) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				chatWindow.append(clientText); //to add the message to the end of the text area	
			}			
		});	
		
	}
}
