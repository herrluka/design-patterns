package mvc.Controler;

import java.awt.Color;
import java.awt.event.MouseEvent;

import mvc.Model.Point;
import mvc.Model.DrawingModel;
import mvc.View.DrawingFrame;

public class DrawingController {

	private DrawingFrame frame;
	private DrawingModel model;
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {

		this.frame = frame;
		this.model = model;
	}

	public void mouseClicked(MouseEvent e) {
		Point p = new Point(e.getX(),e.getY());
		p.setOutlineColor(Color.RED);
		model.add(p);
		frame.getDrawingView().repaint();
	}
	
	
}
