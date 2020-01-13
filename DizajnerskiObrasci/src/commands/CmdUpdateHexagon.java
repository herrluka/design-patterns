package commands;


import mvc.Model.HexagonAdapter;
import mvc.Model.Point;

public class CmdUpdateHexagon implements Command{

	private HexagonAdapter oldHexagon;
	private HexagonAdapter newHexagon;
	private HexagonAdapter original;
	
	
	public CmdUpdateHexagon(HexagonAdapter oldHexagon, HexagonAdapter newHexagon) {
		this.oldHexagon = oldHexagon;
		this.newHexagon = newHexagon;
	}

	@Override
	public void execute() {
		original = oldHexagon.clone();
		
		oldHexagon.setX(newHexagon.getX());
		oldHexagon.setY(newHexagon.getY());
		oldHexagon.setRadius(newHexagon.getRadius());
		oldHexagon.setInnerColor(newHexagon.getInnerColor());
		oldHexagon.setOutlineColor(newHexagon.getOutlineColor());
	}

	@Override
	public void unexecute() {
		oldHexagon.setX(original.getX());
		oldHexagon.setY(original.getY());
		oldHexagon.setRadius(original.getRadius());
		oldHexagon.setInnerColor(original.getInnerColor());
		oldHexagon.setOutlineColor(original.getOutlineColor());
	}
	
	@Override
	public String toString() {
		return "Update_" + original.toString() + ";" + oldHexagon.toString();
	}

}
