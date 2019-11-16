package mvc.Model;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Donut extends Circle {
private int innerRadius;
private Color bojaManjeIvice;


	public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius) throws Exception {
		super(center, radius);
			setInnerRadius(innerRadius);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) throws Exception {
		this(center, radius, innerRadius);
		setSelected(selected);
	}
	
	@Override
	public void draw(Graphics g) {
		
		System.out.println("USaO");
		  g.drawLine(55, 55, 55, 55);
	}
	
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return dFromCenter > innerRadius &&
				super.contains(p);
	}
	
	public int getInnerRadius() {
		return innerRadius;
	}
	public void setInnerRadius(int innerRadius) throws Exception {
		if(innerRadius>=0 && innerRadius<this.getRadius())
		{
		this.innerRadius = innerRadius;
		}
		else
			throw new Exception();
	}
	
	public Color getBojaManjeIvice() {
		return bojaManjeIvice;
	}

	public void setBojaManjeIvice(Color bojaManjeIvice) {
		this.bojaManjeIvice = bojaManjeIvice;
	}
	
	public String toString() {
		return super.toString() + ", inner radius=" + innerRadius ;
	}
	
//	private static Shape createRing() {
//		Ellipse2D outer = new Ellipse2D.Double(
//	            this.getCenter().getX() - this.getRadius(), 
//	            this.getCenter().getY() - this.getRadius(),
//	            getRadius() + getRadius(), 
//	            getRadius() + getRadius());
//	        Ellipse2D inner = new Ellipse2D.Double(
//	            this.getCenter().getX() - innerRadius, 
//	            this.getCenter().getY() - innerRadius,
//	            innerRadius + innerRadius, 
//	            innerRadius + innerRadius);
//	        Area area = new Area(outer);
//	        area.subtract(new Area(inner));
//	        
//	}
}
