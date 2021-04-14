package banking;

import framework.controllers.CommandManager;
import framework.controllers.Controller;
import framework.controllers.commands.*;
import framework.controllers.results.IResult;
import framework.controllers.ruleengine.AbstractAssessor;
import framework.controllers.ruleengine.IProperty;
import framework.controllers.ruleengine.Rule;
import framework.models.account.Account;
import framework.models.account.Entry;

import java.awt.event.ActionEvent;

public class BankController extends Controller {
	private CommandManager commandManager;
	private Bank view;

	public BankController(CommandManager commandManager, Bank view) {
		this.commandManager = commandManager;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	protected void deposit(Entry entry, Account account){
		LoggableAction deposit = new Deposit(entry, account);
		deposit = new Proxy(deposit);
		IResult result = commandManager.submit(deposit);
		runNotifyRule(entry, account, result);
	}

	@Override
	protected void withdraw(Entry entry, Account account){
		LoggableAction withdraw = new Withdraw(entry, account);
		withdraw = new Proxy(withdraw);
		IResult result = commandManager.submit(withdraw);
		runNotifyRule(entry, account, result);
	}

	private void runNotifyRule(Entry entry, Account account, IResult result) {
		IProperty property = new NotifyProperty(entry, result,
				account.getOwner().getCustomerType());
		AbstractAssessor<IProperty> assessor = new NotifyAssessor(property);
		Rule notifyRule = new Rule(assessor, new ENotify(), commandManager);
		notifyRule.applyRule();
	}
}
