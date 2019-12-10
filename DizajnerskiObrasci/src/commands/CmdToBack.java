package commands;

import mvc.Model.Model;

public class CmdToBack implements Command{

	private Model model;
	private int index;
	
	
	public CmdToBack(Model model, int index) {
		super();
		this.model = model;
		this.index = index;
	}

	@Override
	public void execute() {
		model.swap(index, index - 1);	
	}

	@Override
	public void unexecute() {
		model.swap(index, index - 1);
	}

}
