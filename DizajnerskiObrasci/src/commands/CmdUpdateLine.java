package commands;

import mvc.Model.Line;

/**
 * It is a good practice to edit values of point's coordinates because some users
 * can edit point coordinate and our line value shouldn't be changed
 * 
 * 
 */

public class CmdUpdateLine implements Command {
	
	Line oldLine;
	Line newLine;
	Line original = new Line();
	
	

	public CmdUpdateLine(Line oldLine, Line newLine) {
		this.oldLine = oldLine;
		this.newLine = newLine;
	}

	@Override
	public void execute() {
		
		original.getStartPoint().setX(oldLine.getStartPoint().getX());
		original.getStartPoint().setY(oldLine.getStartPoint().getY());
		original.getEndPoint().setX(oldLine.getEndPoint().getX());
		original.getEndPoint().setY(oldLine.getEndPoint().getY());
		original.setCol(oldLine.getCol());
		
		oldLine.getStartPoint().setX(newLine.getStartPoint().getX());
		oldLine.getStartPoint().setY(newLine.getStartPoint().getY());
		oldLine.getEndPoint().setX(newLine.getEndPoint().getX());
		oldLine.getEndPoint().setY(newLine.getEndPoint().getY());
		original.setCol(newLine.getCol());
	}

	@Override
	public void unexecute() {
		
		oldLine.getStartPoint().setX(original.getStartPoint().getX());
		oldLine.getStartPoint().setY(original.getStartPoint().getY());
		oldLine.getEndPoint().setX(original.getEndPoint().getX());
		oldLine.getEndPoint().setY(original.getEndPoint().getY());
		oldLine.setCol(original.getCol());
	}

}
