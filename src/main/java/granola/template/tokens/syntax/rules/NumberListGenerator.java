package granola.template.tokens.syntax.rules;

import granola.template.tokens.syntax.objects.Value;

import java.util.LinkedList;
import java.util.List;

/**
 * SyntaxRule for textual descriptions of generator lists,
 * in the form of [n..m].
 * 
 * @author criminy
 *
 */
public class NumberListGenerator implements SyntaxRules {

	//TODO: should be a read-ahead List with:
	//		Matches([)
	//		Number()
	//		Matches(..)
	//		Number()
	//		Matches(])
	
	public Value parse(String stringToParse) {
		if(stringToParse.matches("\\[[0-9]+\\.\\.[0-9]+\\]"))
		{
			String[] a = stringToParse.substring(1,stringToParse.length()-1)
				.split("\\.\\.");
			int start = Integer.parseInt(a[0]);
			int end = Integer.parseInt(a[1]);
			int inc = 1;
			
			List<Integer> l = new LinkedList<Integer>();
			for(int i = start; i <= end; i+=inc)
			{
				l.add(i);
			}
			
			return new Value(l);
		}else{
			return null;
		}
	}	
}
