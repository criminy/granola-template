package granola.template.exec;

import granola.template.context.Context;

import java.io.Writer;
import java.util.List;

public interface TemplateCommand {

	public List<INodeRunner> exec(Writer wr,Context ctx,String buffer);
	
	public String getSupportedSlots();
}
