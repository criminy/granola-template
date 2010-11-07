package granola.template.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import granola.template.BaseTest;
import granola.template.internal.model.TemplateModelTest;
import granola.template.internal.model.template.BlockCommand;
import granola.template.internal.model.template.Echo;
import granola.template.internal.model.template.TemplateImpl;
import granola.template.model.Template;
import granola.template.parser.TemplateParser;

import org.junit.Test;

public class TemplateParserTest extends BaseTest{

	@Test 
	public void testConstruction() throws IOException
	{		
		Template t = this.gctx.getParser().fromString(this.gctx,"hello {% if x %} x {{ y }} {% cycle 'one' 'two' %} {% if y %} {% else %} a {% endif %} b {% endif %} asdf");
		OutputStreamWriter wr = new OutputStreamWriter(System.out);
		this.gctx.getRunner().runTemplate(this.gctx, 
				wr,
				t,
				ctx);
		wr.flush();
		wr.close();
	}
	
	@Test
	public void testTemplateInheritence() throws IOException
	{
		Template t = this.gctx.getParser().fromInputStream(this.gctx,
			TemplateParserTest.class.getClassLoader().getResourceAsStream(
				"testTemplateInheritence/file.html"));
		
		this.ctx.addObject("message","testTemplateInheritenceMessage");
		
		OutputStreamWriter wr = new OutputStreamWriter(System.out);
		this.gctx.getRunner().runTemplate(this.gctx,wr,t,ctx);
		wr.flush();
		
		
		TemplateModelTest.printTokens("",t.getImpl());
		wr.close();
	}
	
	@Test
	public void test2()
	{
		TemplateImpl t = new TemplateImpl();		
		Echo e = new Echo("hello");
		BlockCommand cmd = new BlockCommand("if");
		Echo ex = new Echo("x");
		BlockCommand cmd2 = new BlockCommand("endif");
		Echo ex2 = new Echo("asdf");
		
		t.addToSlot("main",e);
		t.addToSlot("main",cmd);
		t.addToSlot("main",ex);
		t.addToSlot("main",cmd2);
		t.addToSlot("main",ex2);

		t = t.normalize(this.gctx);
		TemplateModelTest.printTokens("",t);
	}
	
	@Test
	public void forXTest() throws FileNotFoundException, IOException
	{
		OutputStreamWriter wr = new OutputStreamWriter(System.out);
		String x = "{% for x in y %} {{ x }} {% endfor %}";
		Template t = this.gctx.getParser().fromString(
				this.gctx,x);
		
			
		this.ctx.addObject("y",Arrays.asList(1,2,3,4,5));
			
		
		this.gctx.getRunner().runTemplate(this.gctx,wr,t,ctx);
		wr.flush();
			
			
		TemplateModelTest.printTokens("",t.getImpl());
		wr.close();

	}
}
