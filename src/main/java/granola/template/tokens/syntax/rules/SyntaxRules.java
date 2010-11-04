package granola.template.tokens.syntax.rules;

import granola.template.tokens.syntax.objects.Value;

/**
 * SyntaxRule interface.
 * @author criminy
 */
public interface SyntaxRules {

	/**
	 * @param input the string to parse
	 * @return the parsed SyntaxRule of the given string, or null
	 */
	public Value parse(String input);
	
}
