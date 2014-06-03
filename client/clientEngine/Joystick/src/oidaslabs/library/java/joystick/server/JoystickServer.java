package oidaslabs.library.java.joystick.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import oidaslabs.library.java.joystick.api.Joystick;

public class JoystickServer {

	public static void main(String args[]) {		
		
		if (args.length != 2) {
			System.out.println("Usage: java -jar Joystick.jar oidaslabs.library.java.joystick.server.JoystickServer SOCKET_PORT XBEE_PORT");
			return;
		} else {
			System.out.println("Joystick server started.");
		}
		
		Joystick joystick = new Joystick();
		joystick.openConnection(args[1], new int[] { 0x00, 0x13, 0xA2, 0x00, 0x40, 0x69, 0xDD, 0x12 });
		
		int buttonCode = 0;
		ObjectInputStream in;
		Socket connection;
		ServerSocket providerSocket;
		
		while (true) {			
			
			try {
				providerSocket = new ServerSocket(Integer.parseInt(args[0]));				
				connection = providerSocket.accept();				
				in = new ObjectInputStream(connection.getInputStream());
				
				buttonCode = (int) in.readObject();
				
				joystick.execute(buttonCode);
				System.out.println("Botao: " + buttonCode);

				try {
					in.close();
					providerSocket.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}

			} catch (Exception exception) {
				exception.printStackTrace();
			}
			
		}
	}

}
