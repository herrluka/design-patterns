package commands;

import mvc.Model.Model;
import mvc.Model.Rectangle;

public class CmdRemoveRectangle implements Command {

	Rectangle rectangle;
	Model model;
	
	public CmdRemoveRectangle(Rectangle rectangle, Model model) {
		this.rectangle = rectangle;
		this.model = model;
	}
	
	@Override
	public void execute() {
		
		model.remove(rectangle);
	}

	@Override
	public void unexecute() {
		model.add(rectangle);
		
	}

}
