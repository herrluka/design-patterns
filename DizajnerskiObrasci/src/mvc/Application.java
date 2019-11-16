package mvc;

import javax.swing.JFrame;

import mvc.Controler.DrawingController;
import mvc.Model.DrawingModel;
import mvc.View.DrawingFrame;


public class Application {

	public static void main(String[] args) {
		
		DrawingModel drawingModel = new DrawingModel();
		DrawingFrame drawingFrame = new DrawingFrame();
		drawingFrame.getDrawingView().setDrawingModel(drawingModel);
		DrawingController drawingControler = new DrawingController(drawingModel,drawingFrame);
		drawingFrame.setDrawingControler(drawingControler);
		drawingFrame.setSize(600,400);
		drawingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawingFrame.setVisible(true);

	}

}
