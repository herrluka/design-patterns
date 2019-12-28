package commands;



import mvc.Model.Point;
import mvc.Model.Rectangle;

/**
 * 
 * Is it better to use try catch and is it performance expensive or to
 * implement without that? 
 * @author Serinho
 *
 */

public class CmdUpdateRectangle implements Command{
	
	private Rectangle oldRectangle;
	private Rectangle newRectangle;
	private Rectangle original;
	
	
	public CmdUpdateRectangle(Rectangle oldRectangle, Rectangle newRectangle) {
		this.oldRectangle = oldRectangle;
		this.newRectangle = newRectangle;
	}

	@Override
	public void execute() {
		
		original = oldRectangle.clone();
		
		
		oldRectangle.getUpperLeftPoint().setX(newRectangle.getUpperLeftPoint().getX());
		oldRectangle.getUpperLeftPoint().setY(newRectangle.getUpperLeftPoint().getY());
		try {
			oldRectangle.setHeight(newRectangle.getHeight());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			oldRectangle.setWidth(newRectangle.getWidth());
		} catch (Exception e) {
			e.printStackTrace();
		}
		oldRectangle.setOutlineColor(newRectangle.getOutlineColor());
		oldRectangle.setInnerColor(newRectangle.getInnerColor());
		
		
		
	}

	@Override
	public void unexecute() {
		
		oldRectangle.getUpperLeftPoint().setX(original.getUpperLeftPoint().getX());
		oldRectangle.getUpperLeftPoint().setY(original.getUpperLeftPoint().getY());
		try {
			oldRectangle.setHeight(original.getHeight());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			oldRectangle.setWidth(original.getWidth());
		} catch (Exception e) {
			e.printStackTrace();
		}
		oldRectangle.setOutlineColor(original.getOutlineColor());
		oldRectangle.setInnerColor(original.getInnerColor());
		
	}
	
	@Override
	public String toString() {
		return "Update rectangle:" + oldRectangle.toString() + "|" + newRectangle.toString();
	}

}
