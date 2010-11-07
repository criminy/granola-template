package granola.template.commands;

import java.io.Writer;

import granola.template.context.Context;
import granola.template.exec.SyntaxRulesTemplateCommand;
import granola.template.tokens.syntax.objects.Children;
import granola.template.tokens.syntax.rules.FirstOf;
import granola.template.tokens.syntax.rules.Group;
import granola.template.tokens.syntax.rules.Matches;
import granola.template.tokens.syntax.rules.Sentence;
import granola.template.tokens.syntax.rules.SimpleNumber;
import granola.template.tokens.syntax.rules.StringLiteral;
import granola.template.tokens.syntax.rules.SyntaxRules;
import granola.template.tokens.syntax.rules.Variable;
import granola.template.tokens.syntax.rules.exec.FunctionCall;
import granola.template.tokens.syntax.rules.exec.Parameter;

public class If extends SyntaxRulesTemplateCommand{
	
	public String getSupportedSlots() {
		return "main,else";
	}
	
	public SyntaxRules getSyntax() 
	{
		// if x
		// if x operator y
		// if "x" operator y
		// if x operator "y"
		// if "x" operator "y"
		
		return new FirstOf(
			new Group(
					new FunctionCall(If.class,"variableExistence",null),
					new Parameter("param",new Variable())),
			new Sentence(
				new Parameter("one",new FirstOf(new Variable(),new StringLiteral(),new SimpleNumber())),
				new FirstOf(
						new FunctionCall(If.class,"equals",new Matches("==")),
						new FunctionCall(If.class,"notEquals",new Matches("!=")),
						new FunctionCall(If.class,"lessThan",new Matches("<")),
						new FunctionCall(If.class,"lessThanOrEqual",new Matches("<=")),
						new FunctionCall(If.class,"greaterThanOrEqual",new Matches(">=")),
						new FunctionCall(If.class,"greaterThan",new Matches(">"))),
				new Parameter("two",new FirstOf(new Variable(),new StringLiteral(),new SimpleNumber()))));		
	}
	
	public void variableExistence(Context ctx,Writer wr,Object variable,Children children)
	{
		if(variable != null)
		{
			children.exec(wr,"main",null);
		}else{
			children.exec(wr,"else",null);
		}
	}
	
	public void equals(Context ctx,Writer wr,Object var1,Object var2,Children children)
	{
		if(var1.equals(var2))
		{
			children.exec(wr,"main",null);			
		}else{
			children.exec(wr,"else",null);			
		}
	}
	
	public void notEquals(Context ctx,Writer wr,Object var1,Object var2,Children children)
	{
		if(!var1.equals(var2))
		{
			children.exec(wr,"main",null);				
		}else{
			children.exec(wr,"else",null);
		}
	}
	
	public void lessThan(Context ctx,Writer wr, int var1, int var2,Children children)
	{
		if(var1 < var2)
		{
			children.exec(wr,"main",null);
		}else{
			children.exec(wr,"else",null);
		}
	}
	
	public void lessThanOrEqualTo(Context ctx,Writer wr, int var1, int var2,Children children)
	{
		if(var1 <= var2)
		{
			children.exec(wr,"main",null);
		}else{
			children.exec(wr,"else",null);
		}
	}
	
	public void greaterThanOrEqualTo(Context ctx,Writer wr, int var1, int var2,Children children)
	{
		if(var1 >= var2)
		{
			children.exec(wr,"main",null);
		}else{
			children.exec(wr,"else",null);
		}
	}
	
	
	public void greaterThan(Context ctx,Writer wr, int var1, int var2,Children children)
	{
		if(var1 > var2)
		{
			children.exec(wr,"main",null);
		}else{
			children.exec(wr,"else",null);
		}
	}
	
}
