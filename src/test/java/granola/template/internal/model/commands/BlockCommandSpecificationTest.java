package granola.template.internal.model.commands;

import static org.junit.Assert.*;
import granola.template.internal.model.commands.BlockCommandSpecification;

import org.junit.Test;

public class BlockCommandSpecificationTest {

	@Test
	public void testConstruction()
	{
		@SuppressWarnings("unused")
		BlockCommandSpecification spec
		 = BlockCommandSpecification.block("sample","main,extra", "endsample");						
	}
	
	@Test
	public void testHasSlots()
	{
		BlockCommandSpecification spec
		 = BlockCommandSpecification.block("sample","main,extra", "endsample");
		
		assertTrue(spec.hasSlot("main"));
		assertTrue(spec.hasSlot("extra"));
		assertFalse(spec.hasSlot("bad"));
	}
	
	@Test
	public void testName()
	{
		BlockCommandSpecification spec
		 = BlockCommandSpecification.block("sample","main,extra", "endsample");
		
		assertEquals(spec.getName(),"sample");
	}
	
	@Test
	public void testEndName()
	{
		BlockCommandSpecification spec
		 = BlockCommandSpecification.block("sample","main,extra", "endsample");
		
		assertTrue(spec.isEndName("endsample"));
		assertFalse(spec.isEndName("bad"));
	}
}
