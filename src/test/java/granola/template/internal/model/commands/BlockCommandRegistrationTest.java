package granola.template.internal.model.commands;

import static org.junit.Assert.*;
import granola.template.BaseTest;
import granola.template.internal.model.commands.BlockCommandRegistry;

import org.junit.Test;

public class BlockCommandRegistrationTest extends BaseTest {

	@Test
	public void testConstruction()
	{
		@SuppressWarnings("unused")
		BlockCommandRegistry reg = new BlockCommandRegistry();
	}
	
	@Test
	public void testLookupCommands()
	{
		BlockCommandRegistry reg = this.gctx.getBlocks();
		assertNotNull(reg.getCommand("if"));
		assertNull(reg.getCommand("badcommand"));				
	}
	
}
