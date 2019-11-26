package mvc.View;

import java.awt.Graphics;

import javax.swing.JPanel;
import mvc.Model.Model;
import mvc.Model.Shape;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ListIterator;

public class View extends JPanel {

	private Model model = new Model();
	
	public void paint(Graphics g) {
			super.paint(g);
			ListIterator<Shape> it = model.getShapes().listIterator();
			while (it.hasNext()) {
				it.next().draw(g);
			}
	}

	public void setModel(Model Model) {
		this.model = Model;	
	}
	
}
