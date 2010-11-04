package granola.template.internal.model.template;

import granola.template.internal.model.commands.BlockCommandSpecification;
import granola.template.internal.model.template.tree.Slot;

import java.util.Collection;

/**
 * The abstract representation of a template token.
 * 
 * These are all individual token objects:
 * 
 * 	ex1. This is a token
 * 
 * 	ex2. {% token %}
 * 
 *  ex3. {{ token }}
 *  
 *  ex4. {% endtoken %}
 * 
 * @author criminy
 *
 */
public interface Token {
	
	/**
	 * Adds a token as a child to a specific slot. 
	 * 
	 * @param slotName The name of the slot.
	 * @param t The token object.
	 */
	public void addToSlot(String slotName,Token t);
	
	/**
	 * Gets a slot object for the given slot name.
	 * 
	 * @param str The slot name.
	 * @return The slot object.
	 */
	public Slot<Token,String> getSlot(String str);
	
	/**
	 * The list of the stored slots on this token object.
	 * 
	 * @return The slot list.
	 */
	public Collection<Slot<Token,String> > getSlots();
	
	/**
	 * Tests whether the slot name is supported by this token. 
	 * @param slotName The name of the slot.
	 * @return True if the slot is supported, False otherwise.
	 */
	public boolean supportsSlot(String slotName);
	
	/**
	 * Locks the slot from modification.
	 * @param slotName The name of the slot.
	 */
	public void lockSlot(String slotName);
	
	/**
	 * Unlocks the slot to allow modification.
	 * @param slotName The name of the slot.
	 */
	public void unlockSlot(String slotName);
	
	/**
	 * Gets the containing parent token.
	 * @return The parent token, or null if it is a root node.
	 */
	public Token getParent();
	
	/**
	 * Sets the parent of this token. 
	 * @param t The parent to be set.
	 */
	public void setParent(Token t);	
	
	/**
	 * Gets the command specification of this object, if it is a BlockCommand.
	 * @return The BlockCommandSpecification instance.
	 */
	public BlockCommandSpecification getCommandSpecification();
	
	/**
	 * Sets the command specification of this object.
	 * @param cmd The BlockCommandSpecification instance.
	 */
	public void setCommandSpecification(BlockCommandSpecification cmd);
}
