/*
  XBeeJoystick.h - Library for XBeeJoystick arduino code.
  Coded by Oidas Andrade.
*/
#include "Arduino.h"
#include "XBee.h"
#include "XBeeJoystick.h"

XBeeJoystick::XBeeJoystick() {

	_xbee = XBee();
	_response = XBeeResponse();
	_rx16 = Rx16Response();

}

int XBeeJoystick::readJoystick() {

	_xbee.readPacket();

	if (_xbee.getResponse().isAvailable())
	{
		_xbee.getResponse().getRx16Response(_rx16);      
		return _rx16.getData(0);    
	}
	else {
		return NO_BUTTON_PRESSED;
	}

}
