package banking;

import framework.FinCo;
import framework.controllers.CommandManager;
import framework.controllers.Controller;

public class Bank {
	private FinCo app;
	private CommandManager commandManager;
	private Controller bankController;
	public Bank() {
		commandManager = new CommandManager();
		bankController = new BankController(commandManager, this);
		app = new FinCo();
	}

	public static void main() {
		(new Bank()).app.start();
	}
}
