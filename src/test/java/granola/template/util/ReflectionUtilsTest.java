package granola.template.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReflectionUtilsTest {

	public class A {
		private B b;

		public void setB(B b) {
			this.b = b;
		}

		public B getB() {
			return b;
		}
	}
	
	public class B {
		private C c;

		public void setC(C c) {
			this.c = c;
		}

		public C getC() {
			return c;
		}
	}
	
	public class C
	{
		private String content;

		public void setContent(String content) {
			this.content = content;
		}

		public String getContent() {
			return content;
		}
		
	}

	@Test public void testLookupJavabeanProperty()
	{
		A a = new A();
		B b = new B();
		C c = new C();
		c.setContent("ASDF");
		b.setC(c);
		a.setB(b);
		
		String  str = ReflectionUtils.lookupJavabeanProperty(a,"b.c.content");
		assertEquals(str,"ASDF");
	}
		
	@Test(expected=ClassCastException.class) public void testLookupFailedCast()
	{
		A a = new A();
		B b = new B();
		C c = new C();
		c.setContent("ASDF");
		b.setC(c);
		a.setB(b);
		
		C obj = ReflectionUtils.lookupJavabeanProperty(a,"b.c.content");		
	}
	
	
	@Test public void testLookupAsObject()
	{
		A a = new A();
		B b = new B();
		C c = new C();
		c.setContent("ASDF");
		b.setC(c);
		a.setB(b);
		
		String  str = ReflectionUtils.lookupJavabeanPropertyAsObject(a,"b.c.content").toString();
		assertEquals(str,"ASDF");	
	}
	
}
