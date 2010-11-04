package granola.template;


import granola.template.context.Context;
import granola.template.context.GranolaContext;
import granola.template.parser.TemplateParser;

import java.io.StringWriter;

import org.junit.Before;
import org.junit.Test;

public class BaseTest {
	protected StringWriter wr;
	protected GranolaContext gctx;
	protected Context ctx;
	protected TemplateParser loader;
	
	@Test
	public void emptyRunnable() {}
	
	@Before
	public void init()
	{
		loader = null;
		ctx = new Context();
		gctx = new GranolaContext();
		wr = new StringWriter();
	}
}
