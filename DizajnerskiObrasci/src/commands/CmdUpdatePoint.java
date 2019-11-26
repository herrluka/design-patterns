package commands;

import mvc.Model.Point;

public class CmdUpdatePoint implements Command {

	Point oldPoint;
	Point original = new Point();
	Point newPoint;
	
	
	public CmdUpdatePoint(Point oldPoint,Point newPoint) {
		this.oldPoint = oldPoint;
		this.newPoint = newPoint;
	}

	@Override
	public void execute() {
		original.setX(oldPoint.getX());
		original.setY(oldPoint.getY());
		original.setOutlineColor(oldPoint.getOutlineColor());
		
		oldPoint.setX(newPoint.getX());
		oldPoint.setY(newPoint.getY());
		oldPoint.setOutlineColor(newPoint.getOutlineColor());
		
		
	}

	@Override
	public void unexecute() {
		oldPoint.setX(original.getX());
		oldPoint.setY(original.getY());
		oldPoint.setOutlineColor(original.getOutlineColor());
		
	}

}
