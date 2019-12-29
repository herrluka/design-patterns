package commands;

import mvc.Model.Line;
import mvc.Model.Point;

/**
 * It is a good practice to edit values of point's coordinates because some users
 * can edit point coordinate and our line value shouldn't be changed
 * 
 * 
 */

public class CmdUpdateLine implements Command {
	
	Line oldLine;
	Line newLine;
	Line original;
	
	

	public CmdUpdateLine(Line oldLine, Line newLine) {
		this.oldLine = oldLine;
		this.newLine = newLine;
	}

	@Override
	public void execute() {
		
		original = oldLine.clone();
		
		oldLine.getStartPoint().setX(newLine.getStartPoint().getX());
		oldLine.getStartPoint().setY(newLine.getStartPoint().getY());
		oldLine.getEndPoint().setX(newLine.getEndPoint().getX());
		oldLine.getEndPoint().setY(newLine.getEndPoint().getY());
		oldLine.setOutlineColor(newLine.getOutlineColor());
	}

	@Override
	public void unexecute() {
		
		oldLine.getStartPoint().setX(original.getStartPoint().getX());
		oldLine.getStartPoint().setY(original.getStartPoint().getY());
		oldLine.getEndPoint().setX(original.getEndPoint().getX());
		oldLine.getEndPoint().setY(original.getEndPoint().getY());
		oldLine.setOutlineColor(original.getOutlineColor());
	}
	
	@Override
	public String toString() {
		return "Update line:" + original.toString() + "|" + oldLine.toString();
	}

}
