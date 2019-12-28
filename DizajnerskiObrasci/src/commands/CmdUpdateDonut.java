package commands;

import mvc.Model.Donut;
import mvc.Model.Point;

public class CmdUpdateDonut implements Command{

	Donut oldDonut;
	Donut newDonut;
	Donut original;
	
	
	public CmdUpdateDonut(Donut oldDonut, Donut newDonut) {
		this.oldDonut = oldDonut;
		this.newDonut = newDonut;
	}

	@Override
	public void execute() {
		original = oldDonut.clone();
		
		oldDonut.getCenter().setX(newDonut.getCenter().getX());
		oldDonut.getCenter().setY(newDonut.getCenter().getY());
		try {
			oldDonut.setRadius(newDonut.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			oldDonut.setInnerRadius(newDonut.getInnerRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		oldDonut.setInnerColor(newDonut.getInnerColor());
		oldDonut.setOutlineColor(newDonut.getOutlineColor());
	}

	@Override
	public void unexecute() {
		
		oldDonut.getCenter().setX(original.getCenter().getX());
		oldDonut.getCenter().setY(original.getCenter().getY());
		try {
			oldDonut.setRadius(original.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			oldDonut.setInnerRadius(original.getInnerRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		oldDonut.setInnerColor(original.getInnerColor());
		oldDonut.setOutlineColor(original.getOutlineColor());
	}
	
	@Override
	public String toString() {
		return "Update donut:" + oldDonut.toString() + "|" + newDonut.toString();
	}

}
