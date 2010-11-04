package granola.template.tokens.syntax.rules;

import granola.template.tokens.syntax.objects.Value;
import granola.template.tokens.syntax.objects.ValueList;

import java.util.Arrays;
import java.util.List;

/**
 * SyntaxRule for splitting a whitespace-delimited
 * string of tokens.
 * 
 * @author criminy
 *
 */
public class Group implements SyntaxRules{

	List<SyntaxRules> rules;
	
	public Group(SyntaxRules... r)
	{
		rules = Arrays.asList(r);
	}
	
	public Value parse(String stringToParse) {
		ValueList newRules = new ValueList();
		
		for(SyntaxRules r : rules)
		{
			Value tmp = r.parse(stringToParse);
			if(tmp != null)
			{
				newRules.add(tmp);
			}else{
				return null;
			}
		}
		return newRules;
	}

	
	
}
