package granola.template.commands;

import granola.template.context.Context;
import granola.template.exec.SyntaxRulesTemplateCommand;
import granola.template.tokens.syntax.objects.*;
import granola.template.tokens.syntax.rules.*;
import granola.template.tokens.syntax.rules.exec.FunctionCall;
import granola.template.tokens.syntax.rules.exec.Parameter;

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
				new Variable(),				
				new FunctionCall(For.class,"forXinList",new Matches("in")),				
				new Parameter("param",
					new FirstOf(
							new Variable(),
							new NumberListGenerator()))));
			
	}
	
	public void doTimes(Context ctx,Writer os,int i,Children children){
		for(int x = 0; x != i; x++) children.exec(os, "main");
	}
	
	public void forXinList(Context ctx,Writer os,Variable var,Variable reference,Children children)
	{
		Collection<?> coll = (Collection<?>) ctx.getObject(reference.getName(),null);	
		forXinList(ctx,os,var,coll,children);
	}	
	
	public void forXinList(Context ctx,Writer os,Variable var,Collection<?> c,Children children)
	{
		if(c == null || c.isEmpty())
			children.exec(os, "empty");
		else
			for(Object o : c)
			{
				ctx.addObject(var.getName(),o);
				children.exec(os, "main");
			}
	}
	
}
