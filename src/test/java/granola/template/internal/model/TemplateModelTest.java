package granola.template.internal.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import granola.template.BaseTest;
import granola.template.internal.model.template.BlockCommand;
import granola.template.internal.model.template.Echo;
import granola.template.internal.model.template.InlineCommand;
import granola.template.internal.model.template.TemplateImpl;
import granola.template.internal.model.template.Token;
import granola.template.internal.model.template.tree.BaseToken;
import granola.template.internal.model.template.tree.Slot;

import org.junit.Test;

public class TemplateModelTest extends BaseTest{

	public List<? extends BaseToken> createRandomIfElse(boolean el)
	{
		int x = new Random().nextInt() % 100;
		BlockCommand ifCmd = new BlockCommand("if " + x);
		BlockCommand elseCmd = new BlockCommand("else");
		BlockCommand endCmd = new BlockCommand("endif");
		BlockCommand block = new BlockCommand("block");
		BlockCommand endblock = new BlockCommand("endblock");
		InlineCommand inl = new InlineCommand("cmd");
		BlockCommand autoEscape = new BlockCommand("autoescape");
		BlockCommand endAutoescape =new BlockCommand("endautoescape");
		Echo xe = new Echo(x + " is true");
		Echo xe2 = new Echo(x + " is very true!");
		Echo xt = new Echo(x + " is false");
		Echo xt2 = new Echo(x + " is very false!");
		if(el)
			return Arrays.asList(
					block,
					ifCmd,
					xe,inl,xe2,
					elseCmd,
					autoEscape,
					xt,					
					endAutoescape,
					xt2,
					endCmd,endblock);
		else {
			List<BaseToken> l = new ArrayList<BaseToken>();
			l.add(ifCmd);
			l.add(xe);			
			l.addAll(createRandomIfElse(!el));
			l.add(xe2);
			l.add(endCmd);
			return l;
		}
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
		
		printTokens("",t);
		t = t.normalize(this.gctx);
		printTokens("",t);
	}
	
	@Test
	public void test1()
	{		
		TemplateImpl t = new TemplateImpl();		
		Echo e = new Echo("This is some text");
		InlineCommand cmd = new InlineCommand("command1");
		
		t.addToSlot("main",e);
		t.addToSlot("main",cmd);
		
		for(BaseToken xt : createRandomIfElse(false))
		{
			t.addToSlot("main",xt);
		}	
		for(BaseToken xt : createRandomIfElse(true))
		{
			t.addToSlot("main",xt);
		}	
		printTokens("",t);
		t = t.normalize(this.gctx);
				
		assertEquals(t.getSlots().size(),1);
		assertNull(t.getSlot("empty"));
		assertNotNull(t.getSlot("main"));
		assertEquals(t.getSlot("main").getChildren().get(0),e);
		assertEquals(t.getSlot("main").getChildren().get(1),cmd);
		printTokens("",t);
	}
	
	public static void printTokens(String padding,Token t)
	{
		System.out.println(padding + t.toString());
		for(Slot<Token,String> x : t.getSlots())
		{
			System.out.println(padding + " Slot(" + x.getName() + ")");
			for(Token tx : x.getChildren() )
			{							
				printTokens(padding + "  ",tx);
			}			
		}
	}
}
