package commands;

import java.util.Iterator;
import java.util.List;

import mvc.Model.Model;
import mvc.Model.Shape;

public class CmdRemoveShape implements Command {

	private List<Shape> shapes;
	private Model model;
	
	public CmdRemoveShape(List<Shape> s, Model model) {
		this.shapes = s;
		this.model = model;
	}
	
	@Override
	public void execute() {
		int i = 0;
		for(Shape shape : shapes) {
			shape.setPositionInList(model.getShapes().indexOf(shape) + i);
			model.remove(shape);
			i++;
		}
	}

	@Override
	public void unexecute() {
		for(Shape shape : shapes) {
			model.addOnPosition(shape,shape.getPositionInList());
		}
		
	}
	
	@Override
	public String toString() {
		String str= "";
		for(Shape s : shapes) {
			if(!s.equals(shapes.get(shapes.size() - 1))) {
				str += s.toString() + ";";
			} else { 
				str += s.toString();
			}
			
		}
		return "Remove_" + str;
	}

}
