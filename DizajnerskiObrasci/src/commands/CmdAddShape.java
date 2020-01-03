package commands;

import mvc.Model.Model;
import mvc.Model.Shape;

public class CmdAddShape implements Command{
	
	Shape shape;
	Model model;

	public CmdAddShape(Shape s, Model model) {
		this.shape = s;
		this.model = model;
	}

	@Override
	public void execute() {
		model.add(shape);
		
	}

	@Override
	public void unexecute() {
		model.remove(shape);
	}
	
	@Override
	public String toString() {
		return "Add_" + shape.toString();
	}

}
