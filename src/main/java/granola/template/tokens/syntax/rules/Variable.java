package granola.template.tokens.syntax.rules;

import granola.template.tokens.syntax.objects.Value;
import granola.template.tokens.syntax.objects.VariableReference;

/**
 * Represents a Variable token, which is a string
 * starting with a letter, and containing
 * letters, numbers, and underscores.
 * 
 * @author criminy
 *
 */
public class Variable implements SyntaxRules{
	
	public Value parse(String stringToParse) {
		if(stringToParse.matches("^[a-zA-Z]+[a-zA-Z0-9_\\.]*")) {
			this.name = stringToParse;
			return new VariableReference(this.name);
		}
		return null;
	}

	private String name;
	
	public String getName(){
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

}
