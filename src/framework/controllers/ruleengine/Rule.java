package framework.controllers.ruleengine;

import framework.controllers.CommandManager;
import framework.controllers.commands.AbstractAction;

public class Rule<T extends IProperty>{
	private AbstractAssessor<IProperty> assesor;
	private AbstractAction action;
	private CommandManager cm;
	public Rule(AbstractAssessor<IProperty> assessor, AbstractAction action, CommandManager cm) {
		this.action = action;
		this.assesor = assessor;
		this.cm = cm;
	}
	public void applyRule() {
		if(this.assesor.evaluate())
			cm.submit(action);
	};
}
