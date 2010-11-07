package granola.template.tokens.syntax.rules.exec;

import org.apache.log4j.Logger;

import granola.template.tokens.syntax.objects.InvocationModifyingValue;
import granola.template.tokens.syntax.objects.Value;
import granola.template.tokens.syntax.rules.SyntaxRules;

public class Parameter implements SyntaxRules {

	SyntaxRules r;
	
	Logger log = Logger.getLogger(Parameter.class);
	public Parameter(String variableName, SyntaxRules r) {
		this.r = r;
	}

	public Value parse(String input) {
		Value v = r.parse(input);
		if(v != null)
		{
			return new InvocationModifyingValue(v) {	
				
				@Override
				public void exec(CallInvocation ctx) {
					Object o = this.getValue();
					log.debug(o);
					ctx.addArgument((Value) o);
				}
			};
		}
		return null;
	}
	
	public Parameter() {
		
	}
}
