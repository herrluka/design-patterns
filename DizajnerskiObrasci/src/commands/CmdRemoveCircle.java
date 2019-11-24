package commands;

import mvc.Model.Circle;
import mvc.Model.Model;

public class CmdRemoveCircle implements Command{

	Circle circle;
	Model model;
	
	
	public CmdRemoveCircle(Circle circle, Model model) {
		this.circle = circle;
		this.model = model;
	}

	@Override
	public void execute() {
		model.remove(circle);
	}

	@Override
	public void unexecute() {
		model.add(circle);
	}

}
