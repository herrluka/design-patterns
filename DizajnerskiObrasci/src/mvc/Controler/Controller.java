package mvc.Controler;

import java.awt.Color;
import java.awt.event.MouseEvent;

import mvc.Model.Point;
import mvc.Model.Model;
import mvc.View.Frame;

public class Controller {

	private Frame frame;
	private Model model;
	
	public Controller(Model model, Frame frame) {

		this.frame = frame;
		this.model = model;
	}

	public void mouseClicked(MouseEvent e) {
		Point p = new Point(e.getX(),e.getY());
		p.setOutlineColor(Color.RED);
		model.add(p);
		frame.getView().repaint();
	}
	
	
}
