package commands;

import mvc.Model.Model;
import mvc.Model.Point;

public class CmdRemovePoint implements Command {
	
	private Point point;
	private Model model;
	
	public CmdRemovePoint(Point point, Model model) {
		this.point = point;
		this.model = model;
	}
	
	@Override
	public void execute() {
		model.remove(point);
		
	}
	@Override
	public void unexecute() {
		model.add(point);
		
	}

}
