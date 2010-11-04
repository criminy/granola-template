package granola.template.internal.model.template.tree;

import granola.template.internal.model.template.Token;

import java.util.Arrays;
import java.util.List;

public class LeafNodeImpl extends BaseToken {

	public void addToSlot(String slotName, Token t) {
		throw new UnsupportedOperationException();
	}

	public Slot<Token,String> getSlot(String str) {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	public List<Slot<Token,String> > getSlots() {
		return Arrays.asList();
	}

	public boolean supportsSlot(String slotName) {
		return false;
	}

	public void lockSlot(String slotName) {
		throw new UnsupportedOperationException();
	}

	public void unlockSlot(String slotName) {
		throw new UnsupportedOperationException();
	}	
}
