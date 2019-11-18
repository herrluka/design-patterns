package mvc.View;

import javax.swing.JFrame;

import mvc.Controler.DrawingController;
import mvc.Model.DrawingModel;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingFrame extends JFrame{
	

	private DrawingView view = new DrawingView(); //dodato new zbog window buildera
	private DrawingController controler;
	
	public DrawingFrame() {
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("KLIIIK");
				controler.mouseClicked(e);
			}
		});
		
		getContentPane().add(view, BorderLayout.CENTER);
	}
	
	public DrawingView getDrawingView() {
		return view;
	}

	public void setDrawingControler(DrawingController drawingControler) {
		this.controler = drawingControler;
		
	}
}
