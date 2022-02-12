package workingSolution;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.*;

public class Server extends JFrame{
	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	
	//setup the gui
	public Server() {
		super("Weltec's Instant Messenger");
		userText = new JTextField();
		userText.setEditable(false);
		userText.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				sentMessage(event.getActionCommand());
				userText.setText(""); //this will reset the display text
			}
		});
		getContentPane().add(userText, BorderLayout.NORTH);
		chatWindow = new JTextArea();
		getContentPane().add(new JScrollPane(chatWindow));
		setSize(400, 337);
		setVisible(true);
	}

	public void startRunning() {
		try {
			server = new ServerSocket(6789,200);
			//to make the server run indefinitely
			while(true) {
				try {
				waitForConnection();
				setupStream();
				whileChatting();
				} catch(Exception e) {
					showMessage("\nServer ended the connection...");
				} finally {
					closeApp();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
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

	private void whileChatting() {
		String message = "You are now connected";
		sentMessage(message);
		ableToType(true); //to allow the user to type into the textbox
		do {
			try {
				message = (String) input.readObject();
				showMessage("\n" + message);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		} while(!message.equals("CLIENT - END"));
	}

	private void ableToType(boolean trueOrFalse) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				userText.setEditable(trueOrFalse);			
			}
		});
		
	}

	private void setupStream() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\nstreams are now setup\n");
	}

	private void waitForConnection() throws IOException {
		showMessage("Waiting for someone to connect...");
		connection = server.accept();
		showMessage("connected to: " + connection.getInetAddress().getHostName());
	}

	public void sentMessage(String message) {
		try {
			output.writeObject("Server - "+ message);
			output.flush();
			showMessage("\nServer - " + message);
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public void showMessage(String text) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				chatWindow.append(text); //to add the message to the end of the text area	
			}			
		});		
	}



}
