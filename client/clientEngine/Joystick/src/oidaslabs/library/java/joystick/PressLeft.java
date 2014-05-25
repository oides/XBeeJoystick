package oidaslabs.library.java.joystick;

import oidaslabs.library.java.joystick.api.Joystick;

public class PressLeft {

	public static void main(String[] args) {

		Joystick joystick = new Joystick();

		joystick.openConnection("/dev/ttyUSB0", new int[] { 0x00, 0x13, 0xA2, 0x00, 0x40, 0x69, 0xDD, 0x12 });

		joystick.pressLeft();

		joystick.closeConnection();

	}

}
