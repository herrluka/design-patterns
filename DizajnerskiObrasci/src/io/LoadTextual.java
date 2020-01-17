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
	private int currentUndo = -1;
	private int currentRedo = -1;
	
	public LoadTextual(Model model) {
		this.model = model;
		this.parser = new CommandParser(this.model);
		this.fullList = new ArrayList<Command>();
		this.undoList = new ArrayList<Command>();
		this.redoList = new ArrayList<Command>();
		
	}

	public void load(String line) {// throws Exception {
		Command command;
		if(line.equals("Undo")) {
			fullList.add(new CmdUndo());
			undoList.get(undoList.size() - 1).unexecute();
			redoList.add(undoList.get(undoList.size() - 1));
			undoList.remove(undoList.size() - 1);
			
		} else if(line.equals("Redo")) {
			fullList.add(new CmdRedo());
			redoList.get(redoList.size() - 1).execute();
			undoList.add(redoList.get(redoList.size() - 1));
			redoList.remove(redoList.size() - 1);
			
		} else {
			command = parser.parseCommand(line);
			fullList.add(command);
			command.execute();
			undoList.add(command);
			redoList.clear();
			currentUndo = undoList.size() - 1;
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
