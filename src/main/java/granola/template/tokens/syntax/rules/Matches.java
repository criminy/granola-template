package granola.template.tokens.syntax.rules;

import granola.template.tokens.syntax.objects.Value;
import granola.template.tokens.syntax.objects.Word;

/**
 * SyntaxRule that compares the given String
 * with the token for regexp/string equality using String.matches.
 * @author criminy
 *
 */
public class Matches implements SyntaxRules{

	public boolean equals(Object o)
	{
		return this.m.equals(o);
	}
	
	public Matches(String l) {		
		this.m = l;
	}
	
	private String m;

	
	public Value parse(String stringToParse) {
		if(stringToParse.matches(this.m))
			//return this;
			return new Word(stringToParse);
		return null;
	}

}
