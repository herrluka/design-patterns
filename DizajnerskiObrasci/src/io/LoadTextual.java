package io;

import java.util.ArrayList;
import java.util.List;
import commands.CmdRedo;
import commands.CmdUndo;
import commands.Command;
import mvc.Model.Model;


public class LoadTextual {
	
	private List<Command> undoList;
	private List<Command> redoList;
	private List<Command> fullList;
	private CommandParser parser;
	private Model model;
	private int currentUndoPostiton = -1;
	
	public LoadTextual(Model model) {
		this.model = model;
		this.parser = new CommandParser(this.model);
		this.fullList = new ArrayList<Command>();
		this.undoList = new ArrayList<Command>();
		this.redoList = new ArrayList<Command>();
		
	}

	public void load(String line) throws Exception {
		Command command;
		if(line.equals("Undo")) {
			command = new CmdUndo(undoList.get(currentUndoPostiton));
			command.unexecute();
			fullList.add(command);
			redoList.add(command);
			currentUndoPostiton--;
		} else if(line.equals("Redo")) {
			command = new CmdRedo(redoList.get(redoList.size() - 1));
			fullList.add(command);
			command.execute();
			redoList.remove(redoList.size() - 1);
			currentUndoPostiton++;
		} else {
			command = parser.parseCommand(line);
			fullList.add(command);
			command.execute();
			undoList.add(command);
			redoList.clear();
			currentUndoPostiton = undoList.size() - 1;
		}
	}

	public List<Command> getFullList() {
		return fullList;
	}
	
	public List<Command> getRedoList() {
		return redoList;
	}
	
	public List<Command> getUndoList() {
		return undoList;
	}

	
	

}
