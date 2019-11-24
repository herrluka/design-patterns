package commands;

import mvc.Model.Line;
import mvc.Model.Model;

public class CmdRemoveLine implements Command{

	Line line;
	Model model;
	
	public CmdRemoveLine(Line line, Model model) {
		this.line = line;
		this.model = model;
	}
	
	@Override
	public void execute() {
		model.remove(line);
		
	}

	@Override
	public void unexecute() {
		model.add(line);
		
	}

}
