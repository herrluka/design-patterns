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
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Donut extends Circle implements Serializable{
private int innerRadius;


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
		
		Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape outer = new Ellipse2D.Double(getCenter().getX() - getRadius(), getCenter().getY() - getRadius(), 2*getRadius(),2*getRadius());
        Shape inner = new Ellipse2D.Double(getCenter().getX() - innerRadius, getCenter().getY() - innerRadius, 2*getInnerRadius(),2*getInnerRadius());

        Area donut = new Area( outer );
        donut.subtract( new Area(inner) );

        g2d.setColor(getInnerColor());
        g2d.fill(donut);
        g2d.setColor(getOutlineColor());
        g2d.draw(donut);

        g2d.dispose();
        
        if(isSelected()) {
        	drawSelectedSquare(g);
        }
	}
	
	@Override
	public void drawSelectedSquare(Graphics g) {
		super.drawSelectedSquare(g);
		g.setColor(Color.BLUE);
		g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
		g.drawRect(getCenter().getX() + getInnerRadius() - 3, getCenter().getY() - 3, 6, 6);
		g.drawRect(getCenter().getX() - getInnerRadius() - 3, getCenter().getY() - 3, 6, 6);
		g.drawRect(getCenter().getX() - 3, getCenter().getY() + getInnerRadius() - 3, 6, 6);
		g.drawRect(getCenter().getX() - 3, getCenter().getY() - getInnerRadius() - 3, 6, 6);
		g.setColor(Color.BLACK);
	}
	
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return dFromCenter > innerRadius && super.contains(p);
	}
	
	public boolean equals(Donut donut) {
		return getCenter().equals(donut.getCenter())
				&& getRadius() == donut.getRadius()
				&& getInnerRadius() == donut.getInnerRadius()
				&& getOutlineColor().equals(donut.getOutlineColor())
				&& getInnerColor().equals(donut.getInnerColor());
	}
	
	public int getInnerRadius() {
		return innerRadius;
	}
	public void setInnerRadius(int innerRadius) throws Exception {
		if(innerRadius > 0 && innerRadius < this.getRadius())
		{
		this.innerRadius = innerRadius;
		}
		else
			throw new Exception("There has to be set Radius to be able to compare with him in that if statement");
	}
	
	
	@Override 
	public Donut clone() {
		Donut donut = new Donut();
		donut.setCenter(new Point(getCenter().getX(),getCenter().getY()));
		try {
			donut.setRadius(getRadius());
			donut.setInnerRadius(getInnerRadius());
		} catch(Exception e) {
			e.printStackTrace();
		}
		donut.setOutlineColor(getOutlineColor());
		donut.setInnerColor(getInnerColor());
		donut.setSelected(isSelected());
		return donut;
	}
	
	public String toString() {
		return "Donut:Center-" + getCenter() + ", radius=" + getRadius() + ",inner radius=" + innerRadius + ",outline color=" + getOutlineColor().getRGB() + ",inner color=" + getInnerColor().getRGB() + ",selected=" + isSelected(); 
	}
	
}
