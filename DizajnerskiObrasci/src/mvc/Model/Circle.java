package mvc.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.StyledEditorKit.BoldAction;

public class Circle extends ArealShape implements Serializable{
	private Point center;
	private int radius;
	
	public Circle() {

	}

	public Circle(Point center, int radius) throws Exception {
		setCenter(center);
		setRadius(radius);
	}

	public Circle(Point center, int radius, boolean selected) throws Exception {
		this(center, radius);
		setSelected(selected);
	}
	
	public Circle(Point center, int radius, boolean selected, Color outlineColor, Color innerColor) throws Exception {
		this(center,radius, selected);
		setOutlineColor(outlineColor);
		setInnerColor(innerColor);
	}

	@Override
	public void draw(Graphics g) {
		fill(g);
		Color outlineColor = getOutlineColor();
		
		if(outlineColor!=null)
		g.setColor(outlineColor);
		else
			g.setColor(Color.BLACK);
		g.drawOval(this.getCenter().getX() - this.radius, getCenter().getY() - getRadius(), this.getRadius()*2, this.getRadius()*2);
		g.setColor(Color.BLACK);
		
		
		if (isSelected()) {
			drawSelectedSquare(g);
		}
	}
	
	@Override
	public void drawSelectedSquare(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
		g.drawRect(getCenter().getX() + getRadius() - 3, getCenter().getY() - 3, 6, 6);
		g.drawRect(getCenter().getX() - getRadius() - 3, getCenter().getY() - 3, 6, 6);
		g.drawRect(getCenter().getX() - 3, getCenter().getY() + getRadius() - 3, 6, 6);
		g.drawRect(getCenter().getX() - 3, getCenter().getY() - getRadius() - 3, 6, 6);
		g.setColor(Color.BLACK);
	}
		
	public boolean contains(Point p) {
		return center.distance(p.getX(), p.getY()) <= radius;
	}
	
	public boolean equals(Circle circle) {
		return center.equals(circle.getCenter())
				&& radius == circle.getRadius()
				&& getOutlineColor().equals(circle.getOutlineColor())
				&& getInnerColor().equals(circle.getInnerColor());
	}

	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) throws Exception{
		if(radius>0)
			this.radius = radius;
		else
			throw new Exception();
	}

	@Override
	public void fill(Graphics g) {
		Color innerCol = getInnerColor();
		if(innerCol!=null)
		{
			g.setColor(innerCol);
			g.fillOval(this.getCenter().getX() - this.radius, getCenter().getY() - getRadius(), this.getRadius()*2, this.getRadius()*2);
		}
		g.setColor(Color.BLACK);
		
	}

	@Override
	public double area() {
		
		return radius*radius * Math.PI;
	}

	@Override
	public void moveTo(int x, int y) {
		center.setX(x);
		center.setY(y);
	}
	
	@Override
	public Circle clone() {
		Circle circle = new Circle();
		circle.setCenter(new Point(getCenter().getX(),getCenter().getY()));
		try {
			circle.setRadius(radius);
		} catch (Exception e) {
			e.printStackTrace();
		}
		circle.setOutlineColor(getOutlineColor());
		circle.setInnerColor(getInnerColor());
		circle.setSelected(isSelected());
		return circle;
	}

	public String toString() {
		return "Circle:Center-" + center + ", radius=" + radius + ",outline color=" + getOutlineColor().getRGB() + ",inner color=" + getInnerColor().getRGB() + ",selected=" + isSelected(); 
	}

}
