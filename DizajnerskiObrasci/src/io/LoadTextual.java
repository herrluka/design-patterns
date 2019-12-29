package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import commands.Command;
import mvc.Model.Model;
import mvc.Model.Shape;

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

	public void load(String line) throws IOException {
		if(line.equals("Undo")) {
			actualCommand--;
		} else if(line.equals("Redo")) {
			actualCommand++;
		} else {
			if(actualCommand < list.size() - 1) {
				cleanList();
			}
			list.add(parser.parseCommand(line));
			actualCommand++;
		} 
	}
	
	private void cleanList() {
		for(int i = actualCommand + 1; i < list.size(); i++) {
			list.remove(i);
		}
	}

	public List<Command> getList() {
		return list;
	}

	public int getActualCommand() {
		return actualCommand;
	}
	
	

}
