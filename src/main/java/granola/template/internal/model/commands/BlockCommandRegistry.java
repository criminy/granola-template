package granola.template.internal.model.commands;

import java.util.HashMap;
import java.util.Map;

/**
 * The storage system for the block-style commands, used only for parsing.
 * 
 * Examples: 
 * 
 * {% if %}
 *   	
 * {% else %}
 * 		
 * {% endif %}
 * 
 * {% filter filtername %}
 * 
 * {% endfilter %}
 * 
 * {% for x %}
 * 
 * {% endfor %}
 * 
 * @author criminy
 *
 */
public class BlockCommandRegistry {

	/**
	 * Command database.
	 */
	private Map<String,BlockCommandSpecification> commands = new HashMap<String,BlockCommandSpecification>();
	
	/**
	 * Gets the CommandSpecification object.
	 * @param cmd The name of the command
	 * @return The commandspecification object.
	 */
	public BlockCommandSpecification getCommand(String cmd)
	{
		return this.commands.get(cmd);
	}
	
	/**
	 * Constructs a command specification object.
	 * @param a The name of the command.
	 * @param b The list of child slots.
	 * @return The command specification object.
	 */
	private BlockCommandSpecification x(String a,String b)
	{
		return BlockCommandSpecification.block(a,b,"end"+a);
	}
	
	
	public void addCommand(String name,String slots)
	{
		this.commands.put(name,x(name,slots));
	}
		
	/**
	 * Constructs the block command registry
	 * and adds the default block commands.
	 */
	public BlockCommandRegistry() {
		/*
		this.commands.put("if",x("if","main,else"));
		this.commands.put("for",x("for","main,empty"));
		this.commands.put("ifchanged",x("ifchanged","main"));
		this.commands.put("ifequal",x("ifequal","main"));
		this.commands.put("ifnotequal",x("ifnotequal","main"));
		
		this.commands.put("autoescape",x("autoescape","main"));
		this.commands.put("block",x("block","main"));
		this.commands.put("comment",x("comment","main"));
		this.commands.put("filter",x("filter","main"));
		this.commands.put("with",x("with","main"));	
		*/
	}
	
}
