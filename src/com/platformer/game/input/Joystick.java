package com.platformer.game.input;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;


/** Klasa zarz¹dzaj¹ obs³ug¹ gamepada.
 * @author Piotrek
 *
 */
public class Joystick {

/** Przyjmuje wartoœæ true jeœli gamepad zosta³ pod³¹czony do portu USB komputera.
 * 
 */
private boolean isGamepadFound = false;

/** Tablica wszystkich kontrolerów pod³¹czonych do komputera.
 * 
 */
private Controller[] controllers = null;

/** Kontroler znajduj¹cy siê jako pierwszy na liœcie kontrolerów pod³¹czonych do komputera.
 * 
 */
private Controller myFirstGamepad = null;

/** Lista wszystkich komponentów kontrolera, czyli przyciski, osie itp.
 * 
 */
private Component[] gamepadComponents = null;
	

/** Podstawowy konstruktor klasy Joystick.
 * 
 */
public Joystick()
{
	controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();   
	for(int i =0;i<controllers.length;i++)
	{
	   	if (controllers[i].getType() == Controller.Type.GAMEPAD || controllers[i].getType() == Controller.Type.STICK)
	   	{
	   		myFirstGamepad = controllers[i];
	  		isGamepadFound = true;
	   	}
	}
}


/** Zwraca true jeœli gamepad zosta³ pod³¹czony do komputera.
 * @return isGamepadFound - true jeœli pod³¹czono gamepad do portu USB komputera.
 */
public boolean isGamepadFound() {
	return isGamepadFound;
}

public void setGamepadFound(boolean isGamepadFound) {
	this.isGamepadFound = isGamepadFound;
}

public Controller[] getControllers() {
	return controllers;
}

public void setControllers(Controller[] controllers) {
	this.controllers = controllers;
}

public Controller getMyFirstGamepad() {
	return myFirstGamepad;
}

public void setMyFirstGamepad(Controller myFirstGamepad) {
	this.myFirstGamepad = myFirstGamepad;
}

public Component[] getGameoadComponents() {
	return gamepadComponents;
}

public void setGameoadComponents(Component[] gameoadComponents) {
	this.gamepadComponents = gameoadComponents;
}
}