package commands;

import mvc.Model.Donut;
import mvc.Model.Model;

public class CmdRemoveDonut implements Command{

	Donut donut;
	Model model;
	
	
	public CmdRemoveDonut(Donut donut, Model model) {
		this.donut = donut;
		this.model = model;
	}

	@Override
	public void execute() {
		model.remove(donut);
	}

	@Override
	public void unexecute() {
		model.add(donut);
	}
}
