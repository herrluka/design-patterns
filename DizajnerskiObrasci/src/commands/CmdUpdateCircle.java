package commands;

import mvc.Model.Circle;
import mvc.Model.Point;

public class CmdUpdateCircle implements Command{

	Circle oldCircle;
	Circle newCircle;
	Circle original = new Circle();
	
	public CmdUpdateCircle(Circle oldCircle, Circle newCircle) {
		this.oldCircle = oldCircle;
		this.newCircle = newCircle;
		this.original.setCenter(new Point());
	}

	@Override
	public void execute() {
		original.getCenter().setX(oldCircle.getCenter().getX());
		original.getCenter().setY(oldCircle.getCenter().getY());
		try {
			original.setRadius(oldCircle.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		original.setInnerColor(oldCircle.getInnerColor());
		original.setOutlineColor(oldCircle.getOutlineColor());
		
		oldCircle.getCenter().setX(newCircle.getCenter().getX());
		oldCircle.getCenter().setY(newCircle.getCenter().getY());
		try {
			oldCircle.setRadius(newCircle.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		oldCircle.setInnerColor(newCircle.getInnerColor());
		oldCircle.setOutlineColor(newCircle.getOutlineColor());
		
	}

	@Override
	public void unexecute() {
		oldCircle.getCenter().setX(original.getCenter().getX());
		oldCircle.getCenter().setY(original.getCenter().getY());
		try {
			oldCircle.setRadius(original.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		oldCircle.setInnerColor(original.getInnerColor());
		oldCircle.setOutlineColor(original.getOutlineColor());
		
	}

}
