package granola.template.internal.model.template;

import granola.template.internal.model.template.tree.LeafNodeImpl;

public class InlineCommand extends LeafNodeImpl {

	private String content;
	
	public InlineCommand(String content) {
		this.content = content.trim();
	}
	
	public String getContent() {
		return content;
	}
	
	@Override
	public String toString() {
		return "InlineCommand(" + content + ")";
	}

	public boolean supportsTag(String slotName) {
		throw new UnsupportedOperationException();
	}

	
}

