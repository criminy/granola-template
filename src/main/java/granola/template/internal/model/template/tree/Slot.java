package granola.template.internal.model.template.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Slot object node, which stores a list
 * of objects tagged with a specific value.
 * 
 * @author criminy
 *
 * @param <T> The type of object to enumerate.
 * @param <Key> The type of key to tag this slot with
 */
public class Slot<T,Key> {

	/**
	 * The list of children.
	 */
	private List<T> children;
	/**
	 * The key value of this slot.
	 */
	private Key name;
	
	/**
	 * Whether this slot is locked or not.
	 */
	private boolean locked = false;
	
	/**
	 * Method which constructs the children list, defaults
	 * to ArrayList and can be overriden 
	 * to support other list types.
	 * @return The list instance.
	 */
	protected List<T> getList(){
		return new ArrayList<T>();
	}
	
	/**
	 * Constructs a slot, given the key value.
	 * @param name The key value.
	 */
	public Slot(Key name) {
		this.name = name;
		this.children = getList();
	}

	/**
	 * Returns the value.
	 * @return The value of the key.
	 */
	public Key getName()
	{
		return this.name;
	}
	
	/**
	 * Gets the list of children.
	 * @return The list of children.
	 */
	public List<T> getChildren()
	{
		return this.children;
	}

	/**
	 * Sets whether the slot is locked from modification.
	 * @param locked The value of the locked property.
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * Returns whether the slot is locked.
	 * @return The value of the slot.
	 */
	public boolean isLocked() {
		return locked;
	}
	
}
