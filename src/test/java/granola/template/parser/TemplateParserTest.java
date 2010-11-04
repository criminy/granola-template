package granola.template.parser;

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
	public void testConstruction()
	{
		TemplateParser parser = new TemplateParser();
		Template t = parser.fromString(this.gctx,"hello {% if %} x {{ y }} {% cycle 'one' 'two' %} {% if y %} {% else %} a {%endif %} b {% endif %} asdf");
		TemplateModelTest.printTokens("", t.getImpl());				
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
}
