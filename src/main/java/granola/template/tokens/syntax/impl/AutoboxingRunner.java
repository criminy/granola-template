package granola.template.tokens.syntax.impl;

import java.io.Writer;

import granola.template.context.Context;
import granola.template.tokens.syntax.objects.Children;
import granola.template.tokens.syntax.objects.InvocationModifyingValue;
import granola.template.tokens.syntax.objects.Value;
import granola.template.tokens.syntax.objects.ValueList;
import granola.template.tokens.syntax.rules.exec.CallInvocation;

public class AutoboxingRunner {

	public void invokeMethod(Writer wr,Context ctx,Object instance,Value v,Children children)
	{		
		CallInvocation inv = new CallInvocation();
		inv.setContext(ctx);
		inv.setInstance(instance);
		
		
		if(v instanceof ValueList) {
			ValueList vl = ((ValueList)v);
			
			
			for(Value rx : vl.getList())
			{
				if(rx instanceof InvocationModifyingValue)
				{
					((InvocationModifyingValue)rx).exec(inv);
				}		
			}
		}else{
			((InvocationModifyingValue)v).exec(inv);
		}
		inv.invoke(wr,children);		
	}
	
}