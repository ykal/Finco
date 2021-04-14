package framework.controllers.commands;

import framework.models.account.Account;
import framework.models.account.Entry;
import framework.models.account.IEntry;

public abstract class LoggableAction extends AbstractAction{
	abstract IEntry getEntry();
	abstract Account getAccount();
}
