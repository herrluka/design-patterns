package app;



import java.awt.Dimension;

import javax.swing.JFrame;

import mvc.Controler.Controller;
import mvc.Model.Model;
import mvc.View.DialogHexagon;
import mvc.View.Frame;

public class Application {

	public static void main(String[] args) {
		Model model = new Model();
		Frame frame = new Frame();
		frame.getView().setModel(model);
		Controller controler = new Controller(model,frame);
		frame.setControler(controler);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
