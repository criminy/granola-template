package granola.template.exec;

import granola.template.context.Context;
import granola.template.tokens.syntax.impl.AutoboxingRunner;
import granola.template.tokens.syntax.objects.Children;
import granola.template.tokens.syntax.objects.Value;
import granola.template.tokens.syntax.rules.SyntaxRules;
import granola.template.util.Runnable1F;

import java.io.Writer;
import java.util.List;

import org.apache.log4j.Logger;




class CountingChildren implements Children
{
	
	public List<INodeRunner> nodes = new java.util.LinkedList<INodeRunner>();
	
	public void exec(Writer os, String node) {

	}
	
	public void exec(Writer os, String node, Runnable1F<Context> runnable) {
		this.nodes.add(new INodeRunner(node, runnable));
	}


}


public abstract class SyntaxRulesTemplateCommand implements TemplateCommand{

	Logger log = Logger.getLogger(SyntaxRulesTemplateCommand.class);
	
	public abstract SyntaxRules getSyntax();
	
	public List<INodeRunner> exec(Writer wr, Context ctx, String buffer) {
		
		
		AutoboxingRunner runner = new AutoboxingRunner();
		CountingChildren ch = new CountingChildren();		
		Value v;
		log.debug(buffer);
		v = getSyntax().parse(buffer); //todo: replace getSyntax with a cached lookup.
		runner.invokeMethod(wr, ctx, this, v,ch);
		return ch.nodes;
	}

}
