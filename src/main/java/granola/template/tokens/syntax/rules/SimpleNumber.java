package granola.template.tokens.syntax.rules;

import granola.template.tokens.syntax.objects.Value;

/**
 * SyntaxRule that parses numbers out of tokens.
 * 
 * @author criminy 
 */
public class SimpleNumber implements SyntaxRules{

	public Value parse(String stringToParse) {
		if(stringToParse.matches("[0-9]+"))
		{
			return new Value(Integer.parseInt(stringToParse));
		}else{
			return null;
		}
	}

}
