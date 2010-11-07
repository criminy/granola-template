package granola.template.internal.model.template.tree;


import granola.template.internal.model.template.Token;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SlotTreeImpl extends BaseToken
{
	private Map<String,Slot<Token,String> > children = new HashMap<String,Slot<Token,String>>();

	/**
	 * Adds a token object as a child under a specific slot.
	 */
	public void addToSlot(String slotName, Token t) {		
		Slot<Token,String> slot = null;
		if((slot = children.get(slotName)) == null)
		{
			slot = new Slot<Token,String>(slotName);
			children.put(slotName,slot);
		}
		if(!slot.isLocked())
		{
			slot.getChildren().add(t);
			t.setParent(this);
		}
	}
	
	/* (non-Javadoc)
	 * @see granola.template.model.template.tree.SlotTree#getSlot(java.lang.String)
	 */
	public Slot<Token,String> getSlot(String str) {
		return children.get(str);
	}

	/* (non-Javadoc)
	 * @see granola.template.model.template.tree.SlotTree#getSlots()
	 */
	public Collection<Slot<Token,String> > getSlots() {
		return children.values();
	}

	/* (non-Javadoc)
	 * @see granola.template.model.template.tree.SlotTree#supportsSlot(java.lang.String)
	 */
	public boolean supportsSlot(String slotName) {
		Slot<Token,String> s;
		return ((s = getSlot(slotName)) != null && s.isLocked());			
	}

	/* (non-Javadoc)
	 * @see granola.template.model.template.tree.SlotTree#lockSlot(java.lang.String)
	 */
	public void lockSlot(String slotName) {
		Slot s = getSlot(slotName);
		if(s == null)
		{
			s = new Slot<Token,String>(slotName);
			this.children.put(slotName,s);			
		}
		getSlot(slotName).setLocked(true);
	}

	/* (non-Javadoc)
	 * @see granola.template.model.template.tree.SlotTree#unlockSlot(java.lang.String)
	 */
	public void unlockSlot(String slotName) {
		getSlot(slotName).setLocked(false);
	}
	

}
