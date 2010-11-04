package granola.template.tokens.syntax.rules.exec;

import granola.template.context.Context;
import granola.template.tokens.syntax.objects.Children;
import granola.template.tokens.syntax.objects.Value;
import granola.template.tokens.syntax.objects.VariableReference;

import java.io.Writer;
import java.lang.reflect.*;
import java.util.*;

/**
 * Stateful object which represents a call to an instance method
 * with parameters.
 * 
 *  
 * @author criminy
 *
 */
public class CallInvocation 
{
	private Context ctx;
	private Object instance;
	private Method method;
	private List<Object> arguments = new java.util.LinkedList<Object>();
	
	public void setContext(Context ctx) {
		this.ctx = ctx;
	}
	
	public void setInstance(Object instance) {
		this.instance = instance;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public Method getMethod() {
		return method;
	}

	public void addArgument(Value v)
	{
		if(v instanceof VariableReference)
		{
			arguments.add(ctx.getObject( (String)v.getValue(),null));
		}else{
			arguments.add(v.getValue());
		}
	}
	
	public Object invoke(Writer wr,Children children)
	{
		List<Object> args = new LinkedList<Object>();
		args.add(ctx);
		args.add(wr);
		args.addAll(this.arguments);
		args.add(children);
		try {
			for(Object o : args)
			{
				System.out.print("+ " + o + " ");
				if(o != null) 
					System.out.println(o.getClass());
				else
					System.out.println();
			}
			return method.invoke(instance, args.toArray());
		}catch(Exception exn)
		{
			throw new RuntimeException(exn);
		}
	}
	
	
	
}
