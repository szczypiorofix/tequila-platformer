package com.platformer.game.input;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;


/** Klasa zarządzająca obsługą gamepada.
 * @author Piotrek
 *
 */
public class Joystick {

/** Przyjmuje wartość true jeśli gamepad został podłączony do portu USB komputera.
 * 
 */
private boolean isGamepadFound = false;

/** Tablica wszystkich kontrolerów podłączonych do komputera.
 * 
 */
private Controller[] controllers = null;

/** Kontroler znajdujący się jako pierwszy na liście kontrolerów podłączonych do komputera.
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
public Joystick() {
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


/** Zwraca true jeśli gamepad został podłączony do komputera.
 * @return isGamepadFound - true jeśli podłączono gamepad do portu USB komputera.
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