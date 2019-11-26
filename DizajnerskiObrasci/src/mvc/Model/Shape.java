package mvc.Model;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {

	private boolean selected;
	private Color outlineColor;

	
	public Shape() {
		
	}
	
	public abstract boolean contains(Point p);
	public abstract void draw(Graphics g);
	
	public Shape(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public Color getOutlineColor() {
		return outlineColor;
	}

	public void setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
	}


	
}