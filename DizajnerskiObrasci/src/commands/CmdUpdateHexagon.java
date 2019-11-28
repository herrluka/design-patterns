package commands;


import mvc.Model.HexagonAdapter;
import mvc.Model.Point;

public class CmdUpdateHexagon implements Command{

	private HexagonAdapter oldHexagon;
	private HexagonAdapter newHexagon;
	private HexagonAdapter original = new HexagonAdapter(new Point(0,0),0);
	
	
	public CmdUpdateHexagon(HexagonAdapter oldHexagon, HexagonAdapter newHexagon) {
		this.oldHexagon = oldHexagon;
		this.newHexagon = newHexagon;
	}

	@Override
	public void execute() {
		original.setX(oldHexagon.getX());
		original.setY(oldHexagon.getY());
		original.setRadius(oldHexagon.getRadius());
		original.setInnerColor(oldHexagon.getInnerColor());
		original.setOutlineColor(oldHexagon.getOutlineColor());
		
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

}
