package granola.template.tokens.syntax.objects;

import java.util.LinkedList;
import java.util.List;

public class ValueList extends Value{

	public ValueList() {
		super(new LinkedList<Value>());
	}
	
	public void add(Value value)
	{
		this.getList().add(value);
	}
	
	@SuppressWarnings("unchecked")
	public List<Value> getList()
	{
		return (List<Value>)this.getValue();
	}

}
