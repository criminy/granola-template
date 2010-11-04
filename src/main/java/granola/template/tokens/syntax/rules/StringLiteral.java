package granola.template.tokens.syntax.rules;

import granola.template.tokens.syntax.objects.Value;

/**
 * SyntaxRule that represents a string literal,
 * a series of characters surrounded by quotes.
 * 
 * @author criminy
 *
 */
public class StringLiteral implements SyntaxRules {

	
	public Value parse(String stringToParse) {
		if(stringToParse.matches("\".+\""))
			return new Value(stringToParse.substring(0,stringToParse.length()-1));
		return null;
	}

}
