package granola.template.util;

import java.util.LinkedList;
import java.util.List;

public class StringUtils {

	/**
	 * Tests if the given string is a literal in the form 
	 * of ".*".
	 * @param str The given string.
	 * @return True if it's a string literal, false otherwise.
	 */
	public static boolean isStringLiteral(String str)
	{
		return (str.matches("\".*\""));		
	}
	
	/**
	 * Takes the given list of string literals and variable
	 * references and splits it out into its logical units.
	 * 
	 * Example:
	 * 	some list "of files" => ["some","list","\"of files\""]
	 * 
	 * @param content The list of string literals and variable references split by whitespace
	 * @return The list of the logical units, in original order.
	 */
	public static List<String> tokenizeTemplateStringList(String content)
	{		
		content = content.trim();
		
		List<String> l = new LinkedList<String>();
		
		String buffer = "";
		byte prev = ' ';
		boolean quoteState = false;
		for(byte b : content.getBytes())
		{
			if((char)b == ' ')
			{
				if(!quoteState) {
					l.add(buffer);
					buffer = "";
				}else{
					buffer += (char)b;
				}
			}else if(((char)b == '"')){				
				if(prev == '\\')
				{
					//buffer += (char)b;
				}else{
					quoteState = !quoteState;
				}
				buffer += (char)b;
			}else{
				buffer += (char)b;
			}
			prev = b;
		}
		l.add(buffer);

		
		return l;
	}
	
}
