package granola.template.tokens.syntax.rules;

import granola.template.tokens.syntax.objects.Value;
import granola.template.tokens.syntax.objects.ValueList;

import java.util.Arrays;
import java.util.List;

/**
 * SyntaxRule for a list of tokens that parse
 * out 1-character length tokens.
 * @author criminy
 *
 */
public class Series implements SyntaxRules{

	List<SyntaxRules> rules;
	
	public Series(SyntaxRules... r)
	{
		rules = Arrays.asList(r);
	}
	
	public Value parse(String stringToParse) {
		String[] o = stringToParse.split(".");
		int idx = 0;
		
		ValueList newRules = new ValueList();
		
		for(idx = 0; idx!=o.length;idx++)
		{
			if(rules.size() == idx) return null;
			SyntaxRules r = rules.get(idx);
			Value tmp = r.parse(o[idx].trim());
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
