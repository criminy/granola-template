package granola.template.parser;

import granola.template.context.GranolaContext;
import granola.template.internal.model.commands.InlineCommandRegistry;
import granola.template.internal.model.template.BlockCommand;
import granola.template.internal.model.template.Echo;
import granola.template.internal.model.template.Expression;
import granola.template.internal.model.template.InlineCommand;
import granola.template.internal.model.template.TemplateImpl;
import granola.template.internal.model.template.Token;
import granola.template.model.Template;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

public class TemplateParser {

	public Template fromInputStream(GranolaContext ctx,InputStream f) throws IOException	
	{
		return fromString(ctx,IOUtils.toString(f),newBlockMap());
	}
	
	public Template fromInputStream(GranolaContext ctx,InputStream f,Map<String,BlockCommand> blocks) throws IOException	
	{
		return fromString(ctx,IOUtils.toString(f),blocks);
	}
	
	public Template fromFile(GranolaContext ctx,File f) throws FileNotFoundException, IOException	
	{
		return fromString(ctx,IOUtils.toString(new FileInputStream(f)));
	}
	
	public Template fromString(GranolaContext ctx,String str) throws FileNotFoundException, IOException
	{
		return fromString(ctx,str,newBlockMap());
	}
	
	public Template fromString(GranolaContext ctx,String str,Map<String,BlockCommand> blocks) throws FileNotFoundException, IOException
	{
		TemplateImpl template = new TemplateImpl();
		InlineCommandRegistry inlineRegistry = ctx.getInlines();
		Pattern p;
		Matcher m;
		boolean r;	
		Token t = template;
		
		InlineCommand extendsCommand = null;
		
		do {
			p = Pattern.compile(".*\\{(\\{|%)(.+)(\\}|%)\\}.*",Pattern.DOTALL);
			m = p.matcher(str);
			if(r = m.matches()) 
			{
				if(m.group(1).equalsIgnoreCase("{") && m.group(3).equalsIgnoreCase("}"))
				{										
					t.addToSlot("main",new Echo(str.substring(m.end(2)+2,str.length())));
					t.addToSlot("main",new Expression(str.substring(m.start(2),m.end(2))));					
				}else if(m.group(1).equalsIgnoreCase("%") && m.group(3).equalsIgnoreCase("%"))
				{		
					t.addToSlot("main",new Echo(str.substring(m.end(2)+2,str.length())));
					
					String commandName = str.substring(m.start(2),m.end(2)).trim();
					if(inlineRegistry.isInlineCommand(commandName.split(" ")[0]))
					{
						InlineCommand cmd = new InlineCommand(str.substring(m.start(2),m.end(2)));
						if(commandName.split(" ")[0].equals("extends"))							
						{
							extendsCommand = cmd;
						}
						t.addToSlot("main",cmd);
					}else{
						BlockCommand cmd = new BlockCommand(str.substring(m.start(2),m.end(2)));
											
						if(cmd.getCommandName().equals("block")){
							
							String blockName = cmd.getCommand().split(" ")[1];
														
							
							BlockCommand bl = blocks.get(blockName);
							
							
							
							if(bl == null) {
								blocks.put(blockName,cmd);
							
							}
							else {
								cmd = bl;
							}
						}
						
						t.addToSlot("main",cmd);
					}
				}
				str = str.substring(0,m.start(2)-2);
			}else{
				t.addToSlot("main",new Echo(str));
			}
		}while(r);
		assert(t == template);		
		Collections.reverse(t.getSlot("main").getChildren());
		Template ret = new Template(template.normalize(ctx));		
		
		if(extendsCommand != null)
		{
			for(String key : blocks.keySet())
			{
				blocks.get(key).lockSlot("main");
			}
			String filename = extendsCommand.getContent().split(" ")[1].split("\"")[1];			
			Template base = fromInputStream(ctx,
				TemplateParser.class.getClassLoader().
					getResourceAsStream(filename),
						blocks);
			return base;			
		}	
		return ret;		
	}

	protected Map<String,BlockCommand> newBlockMap() {
		return new HashMap<String,BlockCommand>();
	}
}
