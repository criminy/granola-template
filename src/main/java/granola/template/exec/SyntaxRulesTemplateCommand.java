package granola.template.exec;

import granola.template.context.Context;
import granola.template.tokens.syntax.impl.AutoboxingRunner;
import granola.template.tokens.syntax.objects.Children;
import granola.template.tokens.syntax.objects.Value;
import granola.template.tokens.syntax.rules.SyntaxRules;

import java.io.Writer;
import java.util.List;

class CountingChildren implements Children
{
	
	public List<String> nodes = new java.util.LinkedList<String>();
	
	public void exec(Writer os, String node) {
		this.nodes.add(node);
	}
}


public abstract class SyntaxRulesTemplateCommand implements TemplateCommand{

	public abstract SyntaxRules getSyntax();
	
	public List<String> exec(Writer wr, Context ctx, String buffer) {
		AutoboxingRunner runner = new AutoboxingRunner();
		CountingChildren ch = new CountingChildren();		
		Value v;
		v = getSyntax().parse(buffer); //todo: replace getSyntax with a cached lookup.
		runner.invokeMethod(wr, ctx, this, v,ch);
		return ch.nodes;
	}

}
