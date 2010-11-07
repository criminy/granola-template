package granola.template.commands;

import granola.template.context.Context;
import granola.template.exec.SyntaxRulesTemplateCommand;
import granola.template.tokens.syntax.objects.*;
import granola.template.tokens.syntax.rules.*;
import granola.template.tokens.syntax.rules.exec.FunctionCall;
import granola.template.tokens.syntax.rules.exec.Parameter;

import java.io.Writer;

public class Block extends SyntaxRulesTemplateCommand {

	public String getSupportedSlots() {
		return "main";
	}
	
	public SyntaxRules getSyntax() 
	{
		//for x in y
		//for n
		//for x in [n..m]
		return new FirstOf(
			new Group(
				new FunctionCall(Block.class,"runBlock",null),
				new Parameter("i",new Variable())));
			
	}
	
	public void runBlock(Context ctx,Writer os,String blockname,Children children){
		children.exec(os,"main",null);
	}
}
