package commands;

import mvc.Model.Circle;

public class CmdUpadeCircle implements Command{

	Circle oldCircle;
	Circle newCircle;
	Circle original = new Circle();
	
	public CmdUpadeCircle(Circle oldCircle, Circle newCircle, Circle original) {
		this.oldCircle = oldCircle;
		this.newCircle = newCircle;
		this.original = original;
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
