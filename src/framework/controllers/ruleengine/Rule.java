package framework.controllers.ruleengine;

import framework.controllers.commands.AbstractAction;
import framework.controllers.commands.ENotify;

public class Rule<T extends IProperty>{
	private AbstractAssessor<IProperty> assesor;
	private AbstractAction action;
	public Rule(AbstractAssessor<IProperty> assessor, AbstractAction action) {
		this.action = action;
		this.assesor = assessor;
	}
	public void applyRule() {
		if(this.assesor.evaluate())
			this.action.execute();
	};
}
