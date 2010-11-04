package granola.template.internal.model.template;

import granola.template.internal.model.template.tree.SlotTreeImpl;

public class BlockCommand extends SlotTreeImpl {

	private String command;
	private String commandName;
	
	public BlockCommand(String command) {
		this.command = command.trim();
		this.commandName = command.trim().split(" ")[0];
	}
	
	public String toString() {
		return "BlockCommand(" + command + ")";
	}
	
	public String getCommandName() {
		return commandName;
	}
	
	public String getCommand()
	{
		return this.command;
	}

	public boolean supportsTag(String slotName) {
		return true;
	}

}
