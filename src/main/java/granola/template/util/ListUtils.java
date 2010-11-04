package granola.template.util;

import java.util.Arrays;
import java.util.List;

/**
 * Utilities for working with lists.
 * 
 * @author criminy
 *
 */
public class ListUtils {

	/**
	 * lisp-style cdr command.
	 * @param <T> The type of the list elements.
	 * @param t The original list
	 * @return A new list, with the original list minus the fist element.
	 */
	public static <T> List<T> cdr(List<T> t){
		return t.subList(1,t.size());		
	}
	
	/**
	 * lisp-style cdr command.
	 * @param <T> The type of the list elements.
	 * @param t The original list
	 * @return A new list, with the original list minus the fist element.
	 */
	public static <T> List<T> cdr(T[] t){
		return cdr(Arrays.asList(t));	
	}
	
	/**
	 * Join a list into a String, using delim as the
	 * joining token and the toString method to 
	 * serialize the list items to string.
	 * 
	 * @param t The list of items.
	 * @param delim The token to join each string with
	 * @return The string.
	 */
	public static String join(List<?> t,String delim)
	{
		String ret = "";
		for(Object x : t)
		{
			ret += x + delim;
		}
		return ret.substring(0,ret.length()-delim.length());
	}
}
