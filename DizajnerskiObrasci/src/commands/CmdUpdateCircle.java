package commands;

import mvc.Model.Circle;
import mvc.Model.Point;

public class CmdUpdateCircle implements Command{

	Circle oldCircle;
	Circle newCircle;
	Circle original;
	
	public CmdUpdateCircle(Circle oldCircle, Circle newCircle) {
		this.oldCircle = oldCircle;
		this.newCircle = newCircle;
	}

	@Override
	public void execute() {
		original = oldCircle.clone();
		
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
	
	@Override
	public String toString() {
		return "Update_" + original.toString() + ";" + newCircle.toString();
	}

}
