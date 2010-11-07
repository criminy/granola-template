package granola.template.internal.model.template;

import java.util.Stack;

import granola.template.context.GranolaContext;
import granola.template.internal.model.commands.BlockCommandRegistry;
import granola.template.internal.model.commands.BlockCommandSpecification;
import granola.template.internal.model.template.tree.Slot;
import granola.template.internal.model.template.tree.SlotTreeImpl;

/**
 * The template object.
 * @author criminy
 *
 */
public class TemplateImpl extends SlotTreeImpl {

	private static final String DEFAULT = "main";

	public String toString() {
		return "Template()";
	}
	
	/**
	 * Modifies the given template object
	 * by re-arranging the attached child nodes to be
	 * logically arranged.
	 * 
	 * @param parent A new template object.
	 */
	public TemplateImpl normalize(GranolaContext ctx)
	{								
		TemplateImpl newParent = new TemplateImpl();		
		Token cmd = newParent;	
		BlockCommandRegistry cmdRegistry = ctx.getBlocks();
		Stack<String> slots = new Stack<String>();
		slots.add(DEFAULT);
		for(Slot<Token,String> s : this.getSlots())
		{
			for(Token t : s.getChildren())
			{				
				if(t instanceof BlockCommand) {
					BlockCommand tblock = ((BlockCommand)t);
					BlockCommandSpecification cmdSpec = cmdRegistry.getCommand(tblock.getCommandName());	
										
					if(cmdSpec != null)
					{								
						cmd.addToSlot(slots.lastElement(), t);	
						slots.add(DEFAULT);
						cmd = t;
						tblock.setCommandSpecification(cmdSpec);
					}else {
						
						
						
						cmdSpec = cmd.getCommandSpecification();					
						if(cmdSpec.hasSlot(tblock.getCommandName()))
						{
							slots.pop();							
							slots.add(tblock.getCommandName());
						}else if(cmd.getCommandSpecification().isEndName(tblock.getCommandName()))//s.equals("end" + cmd.getCommandSpecification().getName()))
						{														
							slots.pop();
							if(cmd.getParent() != null) {
								cmd = cmd.getParent();	
							}
						}
					}
				}else{
					cmd.addToSlot(slots.lastElement(), t);
				}
			}
		}
		//printTokens("",newParent);
		return newParent;
	}
	public static void printTokens(String padding,Token t)
	{
		System.out.println(padding + t.toString());
		for(Slot<Token,String> x : t.getSlots())
		{
			System.out.println(padding + " Slot(" + x.getName() + ")");
			for(Token tx : x.getChildren() )
			{							
				printTokens(padding + "  ",tx);
			}			
		}
	}
	public boolean supportsTag(String slotName) {
		return false;
	}
	
	@Override
	public BlockCommandSpecification getCommandSpecification() {
		return BlockCommandSpecification.block("template","main","endtemplate");
	}
	
	@Override
	public void setCommandSpecification(BlockCommandSpecification cmd) {	
		super.setCommandSpecification(cmd);
	}
}
