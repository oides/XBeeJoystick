/*
  XBeeJoystick.h - Library for XBeeJoystick arduino code.
  Coded by Oidas Andrade.
*/
#ifndef XBeeJoystick_h

#define XBeeJoystick_h

#define NO_BUTTON_PRESSED 0
#define BUTTON_LEFT 1
#define BUTTON_RIGHT 2
#define BUTTON_A 3
#define BUTTON_B 4

#include "Arduino.h"
#include "XBee.h"

class XBeeJoystick
{
	public:
		XBeeJoystick();
		int readJoystick();

	private:
		XBee _xbee;
		XBeeResponse _response;
		Rx16Response _rx16;

};

#endif
