package granola.template.tokens.syntax.objects;

import granola.template.context.Context;
import granola.template.util.Runnable1F;

import java.io.Writer;

public interface Children {
	
	public void exec(Writer os,String node,Runnable1F<Context> runnable);
}
