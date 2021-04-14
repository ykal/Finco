package framework.controllers.commands;

import framework.models.account.Account;
import framework.models.account.Entry;

public abstract class LoggableAction extends AbstractAction{
	abstract Entry getEntry();
	abstract Account getAccount();
}
