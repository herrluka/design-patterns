package commands;

import java.util.List;

import mvc.Model.Shape;

public class CmdDeselect implements Command{

	private List<Shape> list;
	
	public CmdDeselect(List<Shape> list) {
		this.list = list;
	}

	@Override
	public void execute() {
	
		for(Shape shape : list) {
			shape.setSelected(false);
		}
		
	}

	@Override
	public void unexecute() {
		for(Shape shape : list) {
			shape.setSelected(true);
		}
		
	}
	
	@Override
	public String toString() {
		String str = "";
		for(Shape s : list) {
			if(!s.equals(list.get(list.size() - 1))) {
				str += s.toString() + ";";
			} else { 
				str += s.toString();
			}
			
		}
		return "Deselect_" + str;
	}

}
