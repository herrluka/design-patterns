package commands;

import mvc.Model.Model;
import mvc.Model.Shape;

public class CmdBringToFront implements Command{

	private Model model;
	private Shape shape;
	private int currentIndex;
	
	
	public CmdBringToFront(Model model, int currentIndex) {
		super();
		this.model = model;
		this.currentIndex = currentIndex;
		this.shape = model.getShapes().get(currentIndex);
	}

	@Override
	public void execute() {
		model.getShapes().remove(shape);
		model.getShapes().add(shape);
	}

	@Override
	public void unexecute() {
		model.getShapes().remove(shape);
		model.getShapes().add(currentIndex,shape);
		
	}

}
