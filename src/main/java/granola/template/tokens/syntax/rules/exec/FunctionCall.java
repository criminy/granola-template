package granola.template.tokens.syntax.rules.exec;

import java.lang.reflect.Method;

import granola.template.tokens.syntax.objects.InvocationModifyingValue;
import granola.template.tokens.syntax.objects.Value;
import granola.template.tokens.syntax.rules.SyntaxRules;

public class FunctionCall implements SyntaxRules {

	SyntaxRules r;
	Method m;
	
	public FunctionCall(Class<?> clazz, String method, SyntaxRules rule) {
		this.r = rule;
		
		//TODO: make not slow!
		for(Method mx :  clazz.getMethods())
		{
			if(mx.getName().equals(method)){
				m = mx;
				break;
			}
		}
	}
	
	public Value parse(String input) {
		Value v = (r == null ? null : r.parse(input));
		if(v != null || r == null)
		{
			return new InvocationModifyingValue(m) {				
				@Override
				public void exec(CallInvocation ctx) {
					ctx.setMethod((Method) this.getValue());
				}
			};
		}
		return null;
	}
	

	public void exec(CallInvocation ctx) {
		ctx.setMethod(m);
	}


}
