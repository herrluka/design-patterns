package commands;

import mvc.Model.Donut;

public class CmdUpdateDonut implements Command{

	Donut oldDonut;
	Donut newDonut;
	Donut original = new Donut();
	
	
	public CmdUpdateDonut(Donut oldDonut, Donut newDonut) {
		this.oldDonut = oldDonut;
		this.newDonut = newDonut;
	}

	@Override
	public void execute() {
		original.getCenter().setX(oldDonut.getCenter().getX());
		original.getCenter().setY(oldDonut.getCenter().getY());
		try {
			original.setRadius(oldDonut.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			original.setInnerRadius(oldDonut.getInnerRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		original.setInnerColor(oldDonut.getInnerColor());
		original.setOutlineColor(oldDonut.getOutlineColor());
		
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

}
