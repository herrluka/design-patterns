package commands;

import java.util.List;

import mvc.Model.Model;
import mvc.Model.Shape;

public class CmdRemoveShape implements Command {

	List<Shape> shapes;
	Model model;
	
	public CmdRemoveShape(List<Shape> s, Model model) {
		this.shapes = s;
		this.model = model;
	}
	
	@Override
	public void execute() {
		for(Shape shape : shapes) {
			model.remove(shape);
		}
	}

	@Override
	public void unexecute() {
		for(Shape shape : shapes) {
			model.add(shape);
		}
		
	}

}
