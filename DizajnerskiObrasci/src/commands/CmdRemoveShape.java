package commands;

import mvc.Model.Model;
import mvc.Model.Shape;

public class CmdRemoveShape implements Command {

	Shape shape;
	Model model;
	
	public CmdRemoveShape(Shape s, Model model) {
		this.shape = s;
		this.model = model;
	}
	
	@Override
	public void execute() {
		
		model.remove(shape);
	}

	@Override
	public void unexecute() {
		model.add(shape);
		
	}

}
