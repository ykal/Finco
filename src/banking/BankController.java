package banking;

import framework.controllers.Controller;
import framework.controllers.commands.*;
import framework.controllers.results.CurrentBalance;
import framework.controllers.results.IResult;
import framework.controllers.ruleengine.AbstractAssessor;
import framework.controllers.ruleengine.IProperty;
import framework.controllers.ruleengine.Rule;
import framework.models.account.Account;
import framework.models.account.Entry;

public class BankController extends Controller {
	@Override
	protected void deposit(Entry entry, Account account){
		LoggedAction deposit = new Deposit(entry, account);
		deposit = new Proxy(deposit);
		IResult result = deposit.execute();
		runNotifyRule(entry, account, result);
	}

	@Override
	protected void withdraw(Entry entry, Account account){
		LoggedAction withdraw = new Withdraw(entry, account);
		withdraw = new Proxy(withdraw);
		IResult result = withdraw.execute();
		runNotifyRule(entry, account, result);
	}

	private void runNotifyRule(Entry entry, Account account, IResult result) {
		IProperty property = new NotifyProperty(entry, result,
				account.getOwner().getCustomerType());
		AbstractAssessor<IProperty> assessor = new NotifyAssessor(property);
		Rule notifyRule = new Rule(assessor, new ENotify());
		notifyRule.applyRule();
	}
}
