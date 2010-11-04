package granola.template.context;

import java.util.HashMap;
import java.util.Map;

public class Context {
	private Map<String,Object> variables = buildMap();
	
	protected Map<String,Object> buildMap()
	{
		return new HashMap<String,Object>();
	}
	
	public void addObject(String str,Object o)
	{
		variables.put(str,o);
	}
	
	public void removeObject(String str)
	{
		variables.remove(str);
	}
	
	public Object getObject(String str)
	{
		return this.variables.get(str);
	}
	
	
	public Object getObject(String str,Object def)
	{
		Object ret = this.variables.get(str);
		if(ret == null) return def;
		return ret;
	}
	
	
	
}
