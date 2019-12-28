package mvc.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import hexagon.Hexagon;

public class HexagonAdapter extends ArealShape implements Serializable{
	
	private Hexagon hexagon;

	public HexagonAdapter(Point center, int radius) {
		hexagon = new Hexagon(center.getX(), center.getY(), radius);
	}


	@Override
	public void fill(Graphics g) {
	}

	@Override
	public double area() {
		return (3 * Math.sqrt(3) / 2) * Math.pow(hexagon.getR(), 2);
	}

	@Override
	public void drawSelectedSquare(Graphics g) {
	}

	@Override
	public boolean contains(Point p) {
		return hexagon.doesContain(p.getX(), p.getY());
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
		
	}
	
	public int getX() {
		return hexagon.getX();
	}
	
	public int getY() {
		return hexagon.getY();
	}
	
	public void setX(int x) {
		hexagon.setX(x);
	}
	
	public void setY(int y) {
		hexagon.setY(y);
	}
	
	public void setRadius(int radius) {
		hexagon.setR(radius);
	}
	
	public int getRadius() {
		return hexagon.getR();
	}
	
	public void setInnerColor(Color color) {
		hexagon.setAreaColor(color);
	}
	
	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}
	
	public void setOutlineColor(Color color) {
		hexagon.setBorderColor(color);
	}
	
	public Color getOutlineColor() {
		return hexagon.getBorderColor();
	}
	
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
	}
	
	public boolean isSelected() {
		return hexagon.isSelected();
	}


	@Override
	public void moveTo(int x, int y) {
		hexagon.setX(x);
		hexagon.setY(y);
	}
	
	@Override
	public HexagonAdapter clone() {
		HexagonAdapter hexagonAdapter = new HexagonAdapter(new Point(hexagon.getX(),hexagon.getY()), hexagon.getR());
		hexagonAdapter.setOutlineColor(getOutlineColor());
		hexagonAdapter.setInnerColor(getInnerColor());
		return hexagonAdapter;
	}


	@Override
	public String toString() {
		return "hexagon:" + hexagon.getX() + "," + hexagon.getX() + "," + hexagon.getR() + "," + hexagon.getBorderColor() + "," + hexagon.getAreaColor() ;
	}

}
