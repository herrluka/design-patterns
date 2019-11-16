package mvc.View;

import java.awt.Graphics;

import javax.swing.JPanel;
import mvc.Model.DrawingModel;
import mvc.Model.Shape;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ListIterator;

public class DrawingView extends JPanel {

	private DrawingModel model = new DrawingModel();

	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	public void paint(Graphics g) {
		
			ListIterator<Shape> it = model.getShapes().listIterator();
			while (it.hasNext()) {
				it.next().draw(g);
			}
	}

	public void setDrawingModel(DrawingModel drawingModel) {
		this.model = drawingModel;	
	}
	
}
