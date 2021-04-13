package framework;

import framework.controllers.CommandManager;
import framework.controllers.Controller;

public class FinCo {
	private Controller controller;
	private CommandManager commandManager;

	public FinCo(){}

	public FinCo(Controller controller, CommandManager cm) {
		this.controller = controller;
		this.commandManager = cm;
		// TODO attach controller as an action listener to GUI
	}

	public void start() {
		//TODO main app life cycle goes here.
	}

	public static void main(String[] args) {
		FinCo app = new FinCo();
		app.start();
	}
}
