package commands;

import mvc.Model.Model;
import mvc.Model.Point;

public class CmdAddPoint implements Command {
	
	private Point point;
	private Model model;
	
	

	public CmdAddPoint(Point point, Model model) {
		this.point = point;
		this.model = model;
	}

	@Override
	public void execute() {
		
		model.add(point);
	}

	@Override
	public void unexecute() {
		
		model.remove(point);
		
	}

}
