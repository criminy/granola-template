package granola.template.model;

import granola.template.internal.model.template.TemplateImpl;

public class Template {

	TemplateImpl impl;
	
	public Template(TemplateImpl impl) {
		this.impl = impl;
	}
	
	public TemplateImpl getImpl() {
		return impl;
	}
	
}
