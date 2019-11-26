package mvc.Model;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;

public class Line extends ArealShape {
	private Point startPoint;
	private Point endPoint;
	
	public Line() {
		
	}
	
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		setEndPoint(endPoint);
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		setSelected(selected);
	}

	@Override
	public void draw(Graphics g) {
		if(getOutlineColor() != null)
			g.setColor(getOutlineColor());
		else
			g.setColor(Color.BLACK);
		g.drawLine(this.getStartPoint().getX(), getStartPoint().getY(), this.getEndPoint().getX(), this.getEndPoint().getY());
		g.setColor(Color.BLACK);
		
		
		if (isSelected()) {
			drawSelectedSquare(g);
		}
		
	}
	
	@Override
	public void drawSelectedSquare(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(getStartPoint().getX()  - 3, getStartPoint().getY() - 3, 6, 6);
		g.drawRect(getEndPoint().getX() - 3, getEndPoint().getY() - 3, 6, 6);
		g.drawRect(middleOfLine().getX() - 3, middleOfLine().getY() - 3, 6, 6);
		g.setColor(Color.BLACK);
	}

	public Point middleOfLine() {
		int middleByX = (this.getStartPoint().getX() + this.getEndPoint().getX()) / 2;
		int middleByY = (this.getStartPoint().getY() + this.getEndPoint().getY()) / 2;
		Point p = new Point(middleByX, middleByY);
		return p;
	}
	
	public boolean contains(Point p) {
		if((startPoint.distance(p.getX(), p.getY()) + endPoint.distance(p.getX(), p.getY())) - length() <= 0.05)
			return true;
		return false;
	}
	
	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public String toString() {
		return startPoint + "-->" + endPoint;
	}

	@Override
	public void fill(Graphics g) {
		
	}

	@Override
	public double area() {
		return 0;
	}
}