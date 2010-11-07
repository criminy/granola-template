package granola.template.exec;

import granola.template.context.*;
import granola.template.util.Runnable1F;

public class INodeRunner
{
	private String name;
	private Runnable1F<Context> onInvoke;
	
	public INodeRunner(String name,Runnable1F<Context> onInvoke) {
		this.name = name;
		this.onInvoke = onInvoke;
	}

	public String getName() {
		return name;
	}
	public Runnable1F<Context> getOnInvoke() {
		return onInvoke;
	}
	
}