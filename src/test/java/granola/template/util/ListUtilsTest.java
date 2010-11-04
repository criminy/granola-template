package granola.template.util;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class ListUtilsTest {
	
	public List<String> getSimpleList()
	{
		return Arrays.asList(
				"one","two","three","four","five");
	}

	@Test
	public void testCdr()
	{
		List<String> original = getSimpleList();
		List<String> c = ListUtils.cdr(original);
		assertEquals(c.size(),original.size()-1);
		assertFalse(c.contains("one"));				
	}
	
	@Test
	public void testJoin()
	{
		assertEquals(ListUtils.join(Arrays.asList(
			"one","two","three","four","five"),","),
			"one,two,three,four,five");
		
		assertEquals(ListUtils.join(Arrays.asList(
				"one\"","t\"wo","th re!e","fou@r","fiv'e"),","),
				"one\",t\"wo,th re!e,fou@r,fiv'e");
		
		//this is basically gibberish....
		assertEquals(ListUtils.join(Arrays.asList(
				"中","日本","美","加拿大"),"。"),
				"中。日本。美。加拿大");
	}
	
}
