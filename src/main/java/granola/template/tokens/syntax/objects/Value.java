package granola.template.tokens.syntax.objects;

public class Value  {

	private Object value;
	
	public Value(Object l) {
		this.value = l;
	}

	public Object getValue() {
		return value;
	}
	
	public void setValue(Object val)
	{
		this.value = val;
	}
}
