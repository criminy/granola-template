package granola.template.commands;

import granola.template.context.Context;
import granola.template.exec.SyntaxRulesTemplateCommand;
import granola.template.tokens.syntax.objects.*;
import granola.template.tokens.syntax.rules.*;
import granola.template.tokens.syntax.rules.exec.FunctionCall;
import granola.template.tokens.syntax.rules.exec.Parameter;
import granola.template.util.Runnable1F;

import java.io.Writer;
import java.util.Collection;

public class For extends SyntaxRulesTemplateCommand {

	public String getSupportedSlots() {
		return "main,empty";
	}
	
	public SyntaxRules getSyntax()
	{
		//for x in y
		//for n
		//for x in [n..m]
		return new FirstOf(
			new Group(
				new FunctionCall(For.class,"doTimes",null),
				new Parameter("i",new SimpleNumber())),
			new Sentence(
				new Parameter("variableDefinition",new Matches("[a-zA-Z]+")),				
				new FunctionCall(For.class,"forXinList",new Matches("in")),				
				new Parameter("param",
					new FirstOf(
							new Variable(),
							new NumberListGenerator()))));
			
	}
	
	public void doTimes(Context ctx,Writer os,int i,Children children){
		for(int x = 0; x != i; x++) children.exec(os, "main",null);
	}
	
	
	public void forXinList(Context ctx,Writer os,final String var,Collection<?> c,Children children)
	{
		if(c == null || c.isEmpty())
			children.exec(os, "empty",null);
		else
		{
			for(final Object o : c)
			{			
				children.exec(os, "main",new Runnable1F<Context>() {					
					public void run(Context t) {
						t.addObject(var,o);
					}
				});
			}
		}
	}
	
}
