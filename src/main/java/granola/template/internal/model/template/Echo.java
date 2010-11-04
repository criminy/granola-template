package granola.template.internal.model.template;

import granola.template.internal.model.template.tree.LeafNodeImpl;

public class Echo extends LeafNodeImpl {

	private String content;
	
	public Echo(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public String toString() {
		return "Echo(" + this.content + ")";
	}
}
