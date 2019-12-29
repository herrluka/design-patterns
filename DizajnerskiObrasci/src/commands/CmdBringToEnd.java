package commands;

import mvc.Model.Model;
import mvc.Model.Shape;

public class CmdBringToEnd implements Command {

	private Model model;
	private Shape shape;
	private int currentIndex;
	
	public CmdBringToEnd(Model model, int index) {
		super();
		this.model = model;
		this.currentIndex = index;
	}

	@Override
	public void execute() {
		shape = model.get(currentIndex);
		model.getShapes().remove(shape);
		model.getShapes().add(0,shape);
	}

	@Override
	public void unexecute() {
		model.remove(shape);
		model.addOnPosition(shape,currentIndex);
	}
	
	@Override
	public String toString() {
		return "Bring to end_" + currentIndex;
	}

}
