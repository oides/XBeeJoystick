package oidaslabs.library.java.joystick.clients;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import oidaslabs.library.java.joystick.api.Joystick;

public class LocalKeyboardJoystick extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	private JTextArea displayArea;
	private JTextField typingArea;
	
	private Joystick joystick;
	private static String xbeePort;

	public static void main(String[] args) throws Exception {

		if (args.length  == 1) {
			xbeePort = args[0];
		} else {
			System.out.println("Usage: java -cp Joystick.jar oidaslabs.library.java.joystick.clients.LocalKeyboardJoystick /dev/ttyUSBX");
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

		LocalKeyboardJoystick frame = new LocalKeyboardJoystick("KeyboardJoystick");
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

	public LocalKeyboardJoystick(String name) {
		
		super(name);
		
		this.joystick = new Joystick();
		joystick.openConnection(xbeePort, new int[] { 0x00, 0x13, 0xA2, 0x00, 0x40, 0x69, 0xDD, 0x12 });
		
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
		
		if (65 == keyCode) {
			this.joystick.pressA();
		}
		if (66 == keyCode) {
			this.joystick.pressB();
		}
		if (67 == keyCode) {
			this.joystick.pressC();
		}
		if (68 == keyCode) {
			this.joystick.pressD();
		}
		if (38 == keyCode) {
			this.joystick.pressUp();
		}
		if (40 == keyCode) {
			this.joystick.pressDown();
		}
		if (37 == keyCode) {
			this.joystick.pressLeft();
		}
		if (39 == keyCode) {
			this.joystick.pressRight();
		}

	}
}
