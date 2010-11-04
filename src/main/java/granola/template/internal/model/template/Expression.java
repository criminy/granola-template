package granola.template.internal.model.template;

import granola.template.internal.model.template.tree.LeafNodeImpl;


public class Expression extends LeafNodeImpl{

	private String content;
	
	public Expression(String content) {
		this.content = content.trim();
	}
	
	public String getContent() {
		return content;
	}
	
	public String toString() {
		return "Expression(" + this.content.trim() + ")";
	}
}
