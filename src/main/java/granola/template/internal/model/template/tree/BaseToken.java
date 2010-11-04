package granola.template.internal.model.template.tree;

import granola.template.internal.model.commands.BlockCommandSpecification;
import granola.template.internal.model.template.Token;

/**
 * The abstract BaseToken object.
 * 
 * @author criminy
 *
 */
public abstract class BaseToken implements Token{
	BlockCommandSpecification cmd;
	Token parent;
	
	public Token getParent() {
		return parent;
	}

	public void setParent(Token t) {
		this.parent = t;
	}
	
	public BlockCommandSpecification getCommandSpecification() {
		return this.cmd;
	}
	
	public void setCommandSpecification(BlockCommandSpecification cmd) {
		this.cmd = cmd;
	}
}
