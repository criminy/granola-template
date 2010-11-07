package granola.template.context;

import granola.template.commands.Block;
import granola.template.commands.For;
import granola.template.commands.If;
import granola.template.exec.SyntaxRulesTemplateCommand;
import granola.template.exec.TemplateCommand;
import granola.template.exec.TemplateRunner;
import granola.template.internal.model.commands.BlockCommandRegistry;
import granola.template.internal.model.commands.InlineCommandRegistry;
import granola.template.parser.TemplateParser;
import granola.template.tokens.syntax.rules.SyntaxRules;

import java.util.HashMap;
import java.util.Map;

/**
 * Primary context object.
 * 
 * @author criminy
 *
 */
public class GranolaContext {

	private Map<String,TemplateCommand> templateCommands = newTemplateCommandsMap();
	private Map<String,SyntaxRules> syntaxRules = newSyntaxRulesMap();
	private BlockCommandRegistry blocks = newBlockCommandRegistry();
	private InlineCommandRegistry inlines = newInlineCommandRegistry();
	private TemplateRunner runner = newTemplateRunner();
	private TemplateParser parser = new TemplateParser();
	
	public TemplateParser getParser() {
		return parser;
	}
	
	public TemplateRunner getRunner() {
		return runner;
	}
	
	protected InlineCommandRegistry newInlineCommandRegistry()
	{
		return new InlineCommandRegistry();
	}
	
	private TemplateRunner newTemplateRunner() {
		return new TemplateRunner();
	}

	protected Map<String,SyntaxRules> newSyntaxRulesMap()
	{
		return new HashMap<String,SyntaxRules>();
	}
	
	protected Map<String, TemplateCommand> newTemplateCommandsMap() {
		return new HashMap<String,TemplateCommand>();
	}
	
	private BlockCommandRegistry newBlockCommandRegistry() {
		return new BlockCommandRegistry();
	}
	
	public BlockCommandRegistry getBlocks() {
		return blocks;
	}
	
	public InlineCommandRegistry getInlines() {
		return inlines;
	}
	
	public Map<String, SyntaxRules> getSyntaxRules() {
		return syntaxRules;
	}
	
	public TemplateCommand getTemplateCommand(String name)
	{
		return this.templateCommands.get(name);
	}
	
	public void addCommand(String name,TemplateCommand cmd)
	{
		blocks.addCommand(name,cmd.getSupportedSlots());
		this.templateCommands.put(name,cmd);
		
		if(cmd instanceof SyntaxRulesTemplateCommand)
			this.syntaxRules.put(name,((SyntaxRulesTemplateCommand)cmd).getSyntax());				
	}
	
	public GranolaContext() {
		this.addCommand("if",new If());
		this.addCommand("for",new For());
		this.addCommand("block",new Block());
	}
	
}
