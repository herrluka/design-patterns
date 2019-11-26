package mvc.Model;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends ArealShape {

	private int x;
	private int y;
	
	public Point() {
		
	}
	
	public Point(int x, int y) {
		this.x = x;
		setY(y);
	}
	
	public Point(int x, int y, boolean selected) {
		this(x, y);
		setSelected(selected);
	}
	
	

	public Point(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		setOutlineColor(color);
	}

	@Override
	public void draw(Graphics g) {
		
		Color col = getOutlineColor();
		if(col!=null)
			g.setColor(col);
		else
			g.setColor(Color.BLACK);
		g.drawLine(this.x-1, this.y - 1, this.x + 1, this.y + 1);
		g.drawLine(this.x - 1, this.y + 1, this.x + 1, this.y - 1);
		g.setColor(Color.BLACK);
		
		if (isSelected()) {
			drawSelectedSquare(g);
		}
	}
	
	@Override
	public void drawSelectedSquare(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(this.x-3, this.y-3, 6, 6);
		g.setColor(Color.BLACK);
	}
	
	@Override
	public void fill(Graphics g) {
	}

	
	public void moveBy(int byX, int byY) {
	    this.x = this.x + byX;
		this.y += byY;
	}

	public boolean contains(Point p) {
		return this.distance(p.getX(), p.getY()) <=3;
	}
	
	public double distance(int x2, int y2) {
		double dx = this.x - x2;
		double dy = this.y - y2;
		double d = Math.sqrt(dx*dx + dy*dy);
		return d;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public String toString() {
		return "(" + x + ", " + y + ")" ;
	}
	
	
	public boolean equals(Point p) {
	
		return this.getX() == p.getX() && this.getY() == p.getY();
	}

	@Override
	public double area() {
		return 0;
	}

	
}
