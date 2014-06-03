package oidaslabs.library.java.joystick.clients;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class RemoteKeyboardJoystick extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	private JTextArea displayArea;
	private JTextField typingArea;
	
	private static String server;
	private static String port;

	public static void main(String[] args) throws Exception {

		if (args.length  == 2) {
			server = args[0];
			port = args[1];
		} else {
			System.out.println("Usage: java -jar Joustick.jar oidaslabs.library.java.joystick.clients.RemoteKeyboardJoystick SERVER PORT");
			return;
		}
		
		UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
		
	}

	private static void createAndShowGUI() {

		RemoteKeyboardJoystick frame = new RemoteKeyboardJoystick("KeyboardJoystick");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.addComponentsToPane();

		frame.pack();
		frame.setVisible(true);
		
	}

	private void addComponentsToPane() {

		typingArea = new JTextField(20);
		typingArea.addKeyListener(this);

		displayArea = new JTextArea();
		displayArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(displayArea);
		scrollPane.setPreferredSize(new Dimension(375, 125));

		getContentPane().add(typingArea, BorderLayout.PAGE_START);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
	}

	public RemoteKeyboardJoystick(String name) {
		
		super(name);
		
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		executeCommand(e, "KEY PRESSED: ");
		
	}

	public void keyReleased(KeyEvent e) {
		typingArea.setText("");
	}

	private void executeCommand(KeyEvent e, String keyStatus) {

		int id = e.getID();
		String keyString;

		int keyCode = e.getKeyCode();
		keyString = "key code = " + keyCode + " (" + KeyEvent.getKeyText(keyCode) + ")";

		displayArea.setText(keyString);
		
		execute(keyCode);

	}
	
    private void execute(int buttonCode) {
    	
    	try {
			
    		Socket requestSocket = new Socket(this.server, Integer.parseInt(this.port));    		
    		ObjectOutputStream out = new ObjectOutputStream(requestSocket.getOutputStream());
    		
    		out.writeObject(buttonCode);    		
			out.flush();
			
			out.close();
			requestSocket.close();
    		
		} catch (Exception e) {
			e.printStackTrace();
		}

    }

}
