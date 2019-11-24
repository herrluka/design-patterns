package commands;

import mvc.Model.Donut;
import mvc.Model.Model;

public class CmdAddDonut implements Command{

	Donut donut;
	Model model;
	
	
	public CmdAddDonut(Donut donut, Model model) {
		this.donut = donut;
		this.model = model;
	}

	@Override
	public void execute() {
		model.add(donut);
	}

	@Override
	public void unexecute() {
		model.remove(donut);
	}

}
