package platformer;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class Joystick {

private boolean isGamepadFound = false;
private Controller[] controllers = null;
private Controller myFirstGamepad = null;
private Component[] gamepadComponents = null;
	

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