package commands;

import mvc.Model.Point;

public class CmdUpdatePoint implements Command {

	Point oldPoint;
	Point original;
	Point newPoint;
	
	
	public CmdUpdatePoint(Point oldPoint,Point newPoint) {
		this.oldPoint = oldPoint;
		this.newPoint = newPoint;
	}

	@Override
	public void execute() {
		original = oldPoint.clone();
		oldPoint.moveTo(newPoint.getX(), newPoint.getY());
		oldPoint.setOutlineColor(newPoint.getOutlineColor());
		
		
	}

	@Override
	public void unexecute() {
		oldPoint.moveTo(original.getX(), original.getY());
		oldPoint.setOutlineColor(original.getOutlineColor());
		
	}

}
