package granola.template.tokens.syntax.rules;

import granola.template.tokens.syntax.objects.Value;

import java.util.Arrays;
import java.util.List;

/**
 * SyntaxRule that returns the first
 * sub-rule that matches.
 * 
 * @author criminy
 *
 */
public class FirstOf implements SyntaxRules
{

	List<SyntaxRules> rules;
	
	public FirstOf(SyntaxRules... r)
	{
		rules = Arrays.asList(r);
	}

	public Value parse(String stringToParse) {
		
		for(SyntaxRules r : rules)
		{
			//SyntaxRules tmp = r.parse(stringToParse);
			Value tmp = r.parse(stringToParse);
			if(tmp != null) return tmp;
		}
		return null;
	}

	
}
