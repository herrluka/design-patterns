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
	private Rectangle original = new Rectangle();
	
	
	public CmdUpdateRectangle(Rectangle oldRectangle, Rectangle newRectangle) {
		this.oldRectangle = oldRectangle;
		this.newRectangle = newRectangle;
		this.original.setUpperLeftPoint(new Point());
	}

	@Override
	public void execute() {
		
		original.getUpperLeftPoint().setX(oldRectangle.getUpperLeftPoint().getX());
		original.getUpperLeftPoint().setY(oldRectangle.getUpperLeftPoint().getY());
		try {
			original.setHeight(oldRectangle.getHeight());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			original.setWidth(oldRectangle.getWidth());
		} catch (Exception e) {
			e.printStackTrace();
		}
		original.setOutlineColor(oldRectangle.getOutlineColor());
		original.setInnerColor(oldRectangle.getInnerColor());
		
		
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

}
