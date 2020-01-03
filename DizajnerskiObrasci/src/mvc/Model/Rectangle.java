package mvc.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Rectangle extends ArealShape implements Serializable{

	private Point upperLeftPoint;
	private int width;
	private int height;
	
	
	public Rectangle() {

	}

	public Rectangle(Point upperLeftPoint, int height, int width) throws Exception {
		this.upperLeftPoint = upperLeftPoint;
		setHeight(height);
		setWidth(width);
	}

	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected) throws Exception {
		this(upperLeftPoint, height, width);
		setSelected(selected);
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, Color color) throws Exception {
		this(upperLeftPoint, height, width);
		setOutlineColor(color);
	}

	@Override
	public void draw(Graphics g) {
		fill(g);
		Color outCol = getOutlineColor();
		if(outCol!=null)
			g.setColor(outCol);
		g.drawRect(this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY(), this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
		
		if (isSelected()) {
			drawSelectedSquare(g);
		}
	}
	
	@Override
	public void drawSelectedSquare(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(getUpperLeftPoint().getX() - 3, getUpperLeftPoint().getY() - 3, 6, 6);
		g.drawRect(this.getUpperLeftPoint().getX() - 3 + getWidth(), this.getUpperLeftPoint().getY() - 3, 6, 6);
		g.drawRect(this.getUpperLeftPoint().getX() - 3, this.getUpperLeftPoint().getY() - 3 + getHeight(), 6, 6);
		g.drawRect(this.getUpperLeftPoint().getX() + getWidth() - 3, this.getUpperLeftPoint().getY() + getHeight() - 3, 6, 6);
		g.setColor(Color.BLACK);
	}

	public boolean contains(Point p) {
		if (this.getUpperLeftPoint().getX() <= p.getX() 
				&& p.getX()<= this.getUpperLeftPoint().getX() + width
				&& this.getUpperLeftPoint().getY() <= p.getY()
				&& p.getY() <= this.getUpperLeftPoint().getY() + height) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean equals(Rectangle rectangle) {
		return upperLeftPoint.equals(rectangle.getUpperLeftPoint())
				&& height == rectangle.getHeight()
				&& width == rectangle.getWidth()
				&& getOutlineColor().equals(rectangle.getOutlineColor())
				&& getInnerColor().equals(rectangle.getInnerColor());
	}
	
	public double area() {
		return width * height;
	}
	
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) throws Exception {
		if(width>0)
		this.width = width;
		else
			throw new Exception();
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) throws Exception {
		if(height>0)
		this.height = height;
		else
			throw new Exception();
	}

	@Override
	public void fill(Graphics g) {
		Color innerCol = getInnerColor();
		if(innerCol != null){
			g.setColor(innerCol);
			g.fillRect(this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY(), this.getWidth(), this.height);
		}
		g.setColor(Color.BLACK);
		
	}

	@Override
	public void moveTo(int x, int y) {
		upperLeftPoint.setX(x);
		upperLeftPoint.setY(y);
		
	}
	
	@Override
	public Rectangle clone() {
		Rectangle rectangle = new Rectangle();
		rectangle.setUpperLeftPoint(new Point(upperLeftPoint.getX(),upperLeftPoint.getY()));
		try {
			rectangle.setHeight(height);
			rectangle.setWidth(width);
		} catch (Exception e) {
			e.printStackTrace();
		}
		rectangle.setOutlineColor(getOutlineColor());
		rectangle.setInnerColor(getInnerColor());
		return rectangle;
	}
	
	public String toString() {
		return "Rectangle:Upper left point-" + upperLeftPoint + ",height=" + height + ",width=" + width + ",outline color=" + getOutlineColor().getRGB() + ",inner color=" + getInnerColor().getRGB();
	}
}
