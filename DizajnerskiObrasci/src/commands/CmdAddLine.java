package commands;

import mvc.Model.Line;
import mvc.Model.Model;

public class CmdAddLine implements Command{
	 
	Line line;
	Model model;
	
	public CmdAddLine(Line line, Model model) {
		this.line = line;
		this.model = model;
	}

	@Override
	public void execute() {
		model.add(line);
	}

	@Override
	public void unexecute() {
		model.remove(line);
	};
	
	
	
	

}
