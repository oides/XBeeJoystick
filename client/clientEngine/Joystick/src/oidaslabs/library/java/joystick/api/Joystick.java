package oidaslabs.library.java.joystick.api;

import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeAddress64;
import com.rapplogic.xbee.api.XBeeException;
import com.rapplogic.xbee.api.wpan.TxRequest64;

public class Joystick {

	private static final int FREQUENCE = 9600;
	
	private XBee xbee;
	private XBeeAddress64 addr64;
	
	private static final int LEFT = 1;
	private static final int RIGHT = 2;
	private static final int A = 3;
	private static final int B = 4;
	
	public void pressLeft() {
		
		this.pressButton(LEFT);	
		
	}
	
	public void pressRight() {
		
		this.pressButton(RIGHT);	
		
	}
	
	public void pressA() {
		
		this.pressButton(A);	
		
	}
	
	public void pressB() {
		
		this.pressButton(B);	
		
	}
	
	private void pressButton(int button) {
		
		try {
			
			TxRequest64 tx = new TxRequest64(addr64, new int[] { button });
			
			xbee.sendSynchronous(tx);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void openConnection(String xbeeExplorerUSB, int[] xbeeAddress) {

		try {

			this.xbee = new XBee();
			this.xbee.open(xbeeExplorerUSB, FREQUENCE);
			
			this.addr64 = new XBeeAddress64(xbeeAddress);

		} catch (XBeeException e) {

			e.printStackTrace();

		}

	}

	public void closeConnection() {

		this.xbee.close();
		
	}

}