package granola.template.util;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilsTest{

	@Test public void testIsLiteral()
	{
		assertTrue(StringUtils.isStringLiteral("\"a string literal\""));
		assertTrue(StringUtils.isStringLiteral("\"a str\\\"ing literal\""));
		assertFalse(StringUtils.isStringLiteral("variableReference"));
	}
	
	@Test public void testStringTokenize()
	{
		List<String> l = StringUtils.tokenizeTemplateStringList("var1 var2 var3");
		
		assertEquals(l.size(),3);
		assertEquals(l.get(0),"var1");
		assertEquals(l.get(1),"var2");
		assertEquals(l.get(2),"var3");
	}
	
	@Test public void testStringTokenizeWithLiterals()
	{
		List<String> l = StringUtils.tokenizeTemplateStringList("\"v\\\"ar1\" \"var2\" \"var3\"");
		
		assertEquals(l.size(),3);
		assertEquals(l.get(0),"\"v\\\"ar1\"");
		assertEquals(l.get(1),"\"var2\"");
		assertEquals(l.get(2),"\"var3\"");
	}
	
	@Test public void testStringTokenizeWithLiteralsAndSpaces()
	{
		List<String> l = StringUtils.tokenizeTemplateStringList("\"var 1\" \"var2\" \"var 3\"");
		
		assertEquals(l.size(),3);
		assertEquals(l.get(0),"\"var 1\"");
		assertEquals(l.get(1),"\"var2\"");
		assertEquals(l.get(2),"\"var 3\"");
	}
	
	
}
