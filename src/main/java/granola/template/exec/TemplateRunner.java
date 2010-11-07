package granola.template.exec;

import granola.template.context.Context;
import granola.template.context.GranolaContext;
import granola.template.internal.model.template.BlockCommand;
import granola.template.internal.model.template.Echo;
import granola.template.internal.model.template.Expression;
import granola.template.internal.model.template.InlineCommand;
import granola.template.internal.model.template.Token;
import granola.template.internal.model.template.tree.Slot;
import granola.template.model.Template;
import granola.template.util.ListUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.List;


public class TemplateRunner {

	public void runTemplate(GranolaContext gctx,Writer os, Template template,Context ctx) throws IOException
	{
		for(Token t : template.getImpl().getSlot("main").getChildren())
		{
			runToken(gctx,os,t,ctx);
		}
	}
	
	protected void runToken(GranolaContext gctx,Writer os, Token tok,Context ctx) throws IOException
	{
		
		if(tok instanceof Echo)
		{
			os.write(((Echo)tok).getContent());
		}
		if(tok instanceof BlockCommand)
		{
			BlockCommand bc = ((BlockCommand)tok);
			
			TemplateCommand tc = gctx.getTemplateCommand(bc.getCommandName());
						
			
			List<INodeRunner> nodes = tc.exec(os, ctx, ListUtils.join(ListUtils.cdr(bc.getCommand().split(" "))," ") );
						
			for(INodeRunner node : nodes) {
				String nameName = node.getName();
				Slot<Token,String> sl = bc.getSlot(nameName);
				if(sl != null) {
					for(Token t : sl.getChildren())
					{
						if(node.getOnInvoke() != null) 
							node.getOnInvoke().run(ctx);
						runToken(gctx,os,t,ctx);
					}
				}
			}
		}	
		if(tok instanceof Expression)
		{
			os.write(ctx.getObject(((Expression)tok).getContent(),"").toString());
		}
		if(tok instanceof InlineCommand)
		{
			os.write(((InlineCommand)tok).toString());
		}
	}
	
}
