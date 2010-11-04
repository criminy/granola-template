package granola.template.internal.model.commands;

import java.util.ArrayList;
import java.util.List;

/**
 * The storage system for the inline-style commands, used only for parsing.
 * 
 * @author criminy
 *
 */
public class InlineCommandRegistry {

	List<String> inlineCommands = newList();
	
	protected List<String> newList()
	{
		return new ArrayList<String>();
	}
	
	public InlineCommandRegistry() {
		inlineCommands.add("cycle");
		inlineCommands.add("firstof");
		inlineCommands.add("include");
		inlineCommands.add("load");
		inlineCommands.add("now");
		inlineCommands.add("regroup");
		inlineCommands.add("ssi");
		inlineCommands.add("url");
		inlineCommands.add("widthratio");
		inlineCommands.add("templatetag");
		inlineCommands.add("extends");
	}
	
	public boolean isInlineCommand(String cmd)
	{
		return inlineCommands.contains(cmd);
	}
	
	
}
