package mvc.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Moveable, Cloneable, Serializable {

	private boolean selected;
	private Color outlineColor;

	
	public Shape() {
		
	}
	
	public abstract boolean contains(Point p);
	public abstract void draw(Graphics g);
	public abstract void drawSelectedSquare(Graphics g);
	
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
