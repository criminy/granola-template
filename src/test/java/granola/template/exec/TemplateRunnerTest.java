package granola.template.exec;

import static org.junit.Assert.*;
import granola.template.BaseTest;
import granola.template.context.Context;
import granola.template.context.GranolaContext;
import granola.template.internal.model.TemplateModelTest;
import granola.template.model.Template;
import granola.template.parser.TemplateParser;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;

public class TemplateRunnerTest extends BaseTest{

	@Test
	public void testRunner() throws IOException
	{
		
		GranolaContext granola = this.gctx;
		
		Template t = granola.getParser().fromString(this.gctx,"hello {% if x %} x {{ y }} {% if z == true %} _ {% else %} a {%endif %} b {% endif %} asdf");
		
		Context ctx = new Context();
		ctx.addObject("y","value");
		ctx.addObject("x",true);
		ctx.addObject("z",false);
		
		String val = "hello  x value  a  b  asdf";
		
		Writer wr = new StringWriter();
		granola.getRunner().runTemplate(this.gctx,wr, t, ctx);
		wr.flush();		
		String val2 = wr.toString();		
		wr.close();
		TemplateModelTest.printTokens("",t.getImpl());			
		assertEquals(val,val2);		
	}
	
	@Test
	public void testRunner2() throws IOException
	{
		
		GranolaContext granola = this.gctx;
		
		Template t = granola.getParser().fromString(this.gctx,"hello {% for 4 %}a{% if x > 4 %}y{% if x %}z{% endif %}{% endif %},{% endfor %}");
		
		Context ctx = new Context();
		
		ctx.addObject("x", 5);
		String val = "hello ayz,ayz,ayz,ayz,";
		
		Writer wr = new StringWriter();
		granola.getRunner().runTemplate(this.gctx,wr, t, ctx);
		wr.flush();		
		String val2 = wr.toString();		
		wr.close();
		TemplateModelTest.printTokens("",t.getImpl());			
		assertEquals(val,val2);		
	}
	
	@Test
	public void testRunner3() throws IOException
	{
		
		GranolaContext granola = this.gctx;
		
		Template t = granola.getParser().fromString(this.gctx,"hello {% for 4 %}a{% if x > 4 %}y{% if x %}z{% endif %}{% endif %},{% endfor %}");
		
		Context ctx = new Context();
		
		ctx.addObject("x", 3);
		String val = "hello a,a,a,a,";
		
		Writer wr = new StringWriter();
		granola.getRunner().runTemplate(this.gctx,wr, t, ctx);
		wr.flush();		
		String val2 = wr.toString();		
		wr.close();
		TemplateModelTest.printTokens("",t.getImpl());			
		assertEquals(val,val2);		
	}
}
