package io;

import java.util.ArrayList;
import java.util.List;
import commands.CmdRedo;
import commands.CmdUndo;
import commands.Command;
import mvc.Model.Model;


public class LoadTextual {
	
	private List<Command> list;
	private CommandParser parser;
	private Model model;
	private int actualCommand = -1;
	
	public LoadTextual(Model model) {
		this.model = model;
		this.parser = new CommandParser(this.model);
		this.list = new ArrayList<Command>();
	}

	public void load(String line) throws Exception {
		if(line.equals("Undo")) {
			Command cmd = new CmdUndo(list.get(actualCommand));
			cmd.execute();
			list.add(cmd);
			actualCommand--;
			
		} else if(line.equals("Redo")) {
			actualCommand++;
			Command cmd = new CmdRedo(list.get(actualCommand));
			cmd.execute();
			list.add(cmd);
			
		} else {
			actualCommand = list.size();
			list.add(parser.parseCommand(line));
			list.get(actualCommand).execute();

		}
	}

	public List<Command> getList() {
		return list;
	}

	public int getActualCommand() {
		return actualCommand;
	}
	
	

}
