package granola.template.tokens.syntax.objects;

import granola.template.tokens.syntax.rules.exec.CallInvocation;

public abstract class InvocationModifyingValue extends Value {

	public InvocationModifyingValue(Object l) {
		super(l);
	}

	public abstract void exec(CallInvocation ctx);
	
}
