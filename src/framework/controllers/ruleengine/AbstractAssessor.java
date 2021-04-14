package framework.controllers.ruleengine;

public abstract class AbstractAssessor <T extends IProperty>{
	public abstract boolean evaluate();
}
