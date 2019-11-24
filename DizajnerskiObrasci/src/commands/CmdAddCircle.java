package commands;

import mvc.Model.Circle;
import mvc.Model.Model;

public class CmdAddCircle implements Command{

	Circle circle;
	Model model;
	
	
	public CmdAddCircle(Circle circle, Model model) {
		this.circle = circle;
		this.model = model;
	}

	@Override
	public void execute() {
		model.add(circle);
	}

	@Override
	public void unexecute() {
		model.remove(circle);
	}

}
