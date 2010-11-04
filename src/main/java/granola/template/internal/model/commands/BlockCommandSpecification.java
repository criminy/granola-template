package granola.template.internal.model.commands;

/**
 * The read-only specification model for the {% ... %} {% end...%} style commands.
 * 
 * Examples:
 * 
 * if-command:
 * 	slots = 'main,else'
 *  name = 'if'
 *  endname = 'endif'
 *  
 * filter command
 *  slots = 'main'
 *  name = 'filter'
 *  endname = 'endfilter' 
 * 
 * @author criminy
 *
 */
public class BlockCommandSpecification {

	/**
	 * List of slots, as a comma-separated list of words [a-zA-Z].
	 */
	private String slots;
	
	/**
	 * The name of the command.
	 */
	private String name;
	
	/**
	 * The terminating name of the command.
	 */
	private String endname;

	/**
	 * Creates a new block level command.
	 * @param name The name of the command.
	 * @param slots The comma separated values of the child slots.
	 * @param endname The name that terminates the command.
	 * @return The BlockCommandSpecification instance.
	 */
	public static BlockCommandSpecification block(String name,String slots,String endname)
	{
		return new BlockCommandSpecification(name, slots,endname);
	}
	
	/**
	 * Internal constructor.
	 * @param name The name of the command.
	 * @param slots The comma separated values of the child slots.
	 * @param endname The name that terminates the command.
	 */
	private BlockCommandSpecification(String name,String slots,String endname)
	{
		this.slots = slots;
		this.name = name;
		this.endname = endname;
	}
	
	/**
	 * Gets the name of the command.
	 * @return Gets the name of the command.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Checks whether the given command has a slot.
	 * @param str The slotname to test.
	 * @return True if the command can support the given slot and False otherwise.
	 */
	public boolean hasSlot(String str)
	{
		return slots.contains(str);
	}
	
	/**
	 * Does the given name a terminating name.
	 * @param str The name of the command.
	 * @return True if it terminates and False otherwise.
	 */
	public boolean isEndName(String str)
	{
		return this.endname.equals(str);
	}
}
