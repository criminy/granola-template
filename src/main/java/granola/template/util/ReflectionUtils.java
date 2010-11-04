package granola.template.util;

/**
 * Utility class for common runtime reflection commands.
 * @author criminy
 *
 */
public class ReflectionUtils {
	/**
	 * No Exception casting: tries to cast the source object to the destination class,
	 * returning null if it can not do it.
	 * 
	 * <code>
	 *  Class1 c1 = ...;
	 * 	Class2 c2 = attemptCast(c1);
	 *  if(c2 != null) {
	 *  	... use c2
	 *  }
	 *  
	 *  or 
	 *  
	 *  Class1 c1 = ...;
	 *  Class2 c2;
	 *  if( (c2 = attemptCast(c1)) != null) {
	 *  	... use c2
	 *  }
	 * </code>
	 * 
	 * @param <T> The source type
	 * @param <E> The destination type
	 * @param src The source object
	 * @param dst The destination class class
	 * @return The source object, as the destination class.
	 */
	@SuppressWarnings("unchecked")
	public static <T,E> E attemptCast(T src,Class<E> dst)
	{
		if(dst.isInstance(src))
		{
			return (E) src;
		}else{
			return null;
		}
	}

	
	/**
	 * Takes in the path of the object, converts each
	 * node to a javabean 'get' method to get the final result.
	 * 
	 * Example:
	 * 	$object,some.object.variable => $object.getSome().getObject().getVariable()
	 * 
	 * @param <T>
	 * @param object The initial object to start at.
	 * @param path The path of the child objects to look at.
	 * @return The value of the result object.
	 * @throws RuntimeException if the getter can't be found.
	 */
	//TODO: fix unchecked cast
	
	public static <T> T lookupJavabeanProperty(Object object,String path)
	{
		String[] args = path.split("\\.");
		if(args.length == 1){
			try {
				@SuppressWarnings("unchecked")
				T t = (T) object.getClass().getMethod("get" + 
						
						args[0].substring(0,1).toUpperCase() + 
						args[0].substring(1,args[0].length())
				).invoke(object);
				return t;
			}catch(Exception exn)
			{
				throw new RuntimeException(exn);
			}			
		}else{
			Object o = object;
			for(String x : args)
			{
				o = lookupJavabeanProperty(o,x);				
			}
			@SuppressWarnings("unchecked")
			T t = (T) o;
			return t;
		}
	}
	
	/**
	 * Takes in the path of the object, converts each
	 * node to a javabean 'get' method to get the final result.
	 * 
	 * Example:
	 * 	$object,some.object.variable => $object.getSome().getObject().getVariable()
	 *  
	 * 
	 * @param object The initial object to start at.
	 * @param path The path of the child objects to look at.
	 * @return The value of the result object.
	 * @throws RuntimeException if the getter can't be found.
	 */
	public static Object lookupJavabeanPropertyAsObject(Object object,String path)
	{
		return lookupJavabeanProperty(object,path);
	}
	
}
