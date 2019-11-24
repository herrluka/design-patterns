package commands;

import mvc.Model.Model;
import mvc.Model.Rectangle;

public class CmdAddRectangle implements Command{
	
	Rectangle rectangle;
	Model model;

	public CmdAddRectangle(Rectangle rectangle, Model model) {
		this.rectangle = rectangle;
		this.model = model;
	}

	@Override
	public void execute() {
		
		model.add(rectangle);
		
	}

	@Override
	public void unexecute() {
		model.remove(rectangle);
		
	}

}
