package granola.template.context;

import granola.template.util.ListUtils;
import granola.template.util.ReflectionUtils;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class ContextMap implements Map<String,Object>
{
	Context ctx;
	public ContextMap(Context ctx,Map<String,Object> b)
	{
		this.ctx = ctx;
		this.proxy = b;
	}
	
	private Map<String,Object> proxy;
	
	public void clear() {
		proxy.clear();
	}

	public boolean containsKey(Object arg0) {
		return proxy.containsKey(arg0);
	}

	public boolean containsValue(Object arg0) {
		return proxy.containsValue(arg0);
	}

	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return proxy.entrySet();
	}

	public Object get(Object _str) {
		String str = _str.toString();
		Object ret = proxy.get(str);
		if(ret == null && str.split("\\.").length > 1) {
			String name = str.split("\\.")[0];
			Object o = ctx.getObject(name);			
			return ReflectionUtils.lookupJavabeanPropertyAsObject(
				o,ListUtils.join(ListUtils.cdr(str.split("\\.")),"."));
		}
		return ret;
	}

	public boolean isEmpty() {
		return this.proxy.isEmpty();
	}

	public Set<String> keySet() {
		return this.proxy.keySet();
	}

	public Object put(String arg0, Object arg1) {
		return this.proxy.put(arg0,arg1);
	}

	public void putAll(Map<? extends String, ? extends Object> arg0) {
		this.proxy.putAll(arg0);
	}

	public Object remove(Object arg0) {
		return this.proxy.remove(arg0);
	}

	public int size() {
		return this.proxy.size();
	}

	public Collection<Object> values() {
		return this.proxy.values();
	}
	
}

public class Context {
	ContextMap m = new ContextMap(this, buildMap());
	
	public Map<String, Object> asMap() {
		return m;
	}
	
	protected Map<String,Object> buildMap()
	{
		return new HashMap<String,Object>();
	}
	
	public void addObject(String str,Object o)
	{
		m.put(str,o);
	}
	
	public void removeObject(String str)
	{
		m.remove(str);
	}
	
	public Object getObject(String str)
	{
		return this.m.get(str);
	}
	
	public Object getObject(String str,Object def)
	{
		Object ret = getObject(str);
		if(ret == null) return def;
		return ret;
	}
	
	public <T> T getObjectAs(String str)
	{
		return (T) this.getObject(str);
	}
	
	
}
