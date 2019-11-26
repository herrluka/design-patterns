package commands;

import mvc.Model.Donut;
import mvc.Model.Point;

public class CmdUpdateDonut implements Command{

	Donut oldDonut;
	Donut newDonut;
	Donut original = new Donut();
	
	
	public CmdUpdateDonut(Donut oldDonut, Donut newDonut) {
		this.oldDonut = oldDonut;
		this.newDonut = newDonut;
		this.original.setCenter(new Point());;
	}

	@Override
	public void execute() {
		System.out.print(oldDonut.getCenter().getX());
		original.getCenter().setX(oldDonut.getCenter().getX());
		System.out.print("OVDE");
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
