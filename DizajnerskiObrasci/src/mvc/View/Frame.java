package mvc.View;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mvc.Controler.Controller;
import mvc.Model.Model;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;

public class Frame extends JFrame{
	

	private View view = new View(); //dodato new zbog window buildera
	private Controller controler;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public Frame() {
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controler.mouseClicked(e);
			}
		});
		
		getContentPane().add(view, BorderLayout.CENTER);
		
		JToolBar toolBar = new JToolBar(null, JToolBar.VERTICAL);
		toolBar.setEnabled(false);
		getContentPane().add(toolBar, BorderLayout.WEST);
		
		JToggleButton tglBtnPoint = new JToggleButton("");
		buttonGroup.add(tglBtnPoint);
		tglBtnPoint.setIcon(new ImageIcon(getClass().getResource("/point.png")));
		toolBar.add(tglBtnPoint);
		
		JToggleButton tglBtnLine = new JToggleButton("");
		buttonGroup.add(tglBtnLine);
		tglBtnLine.setIcon(new ImageIcon(getClass().getResource("/line.png")));
		toolBar.add(tglBtnLine);
		
		JToggleButton tglbtnRectangle = new JToggleButton("");
		buttonGroup.add(tglbtnRectangle);
		tglbtnRectangle.setIcon(new ImageIcon(getClass().getResource("/rectangle.png")));
		toolBar.add(tglbtnRectangle);
		
		JToggleButton tglBtnCircle= new JToggleButton("");
		buttonGroup.add(tglBtnCircle);
		tglBtnCircle.setIcon(new ImageIcon(getClass().getResource("/circle.png")));
		toolBar.add(tglBtnCircle);
		
	
		JToggleButton tglBtnDonut = new JToggleButton("");
		buttonGroup.add(tglBtnDonut);
		tglBtnDonut.setIcon(new ImageIcon(getClass().getResource("/donut.png")));
		toolBar.add(tglBtnDonut);
		
		JToggleButton tglBtnSelect = new JToggleButton("");
		buttonGroup.add(tglBtnSelect);
		tglBtnSelect.setIcon(new ImageIcon(getClass().getResource("/select.png")));
		toolBar.add(tglBtnSelect);
		
		JToggleButton tglBtnEdit = new JToggleButton("");
		buttonGroup.add(tglBtnEdit);
		tglBtnEdit.setIcon(new ImageIcon(getClass().getResource("/edit.png")));
		toolBar.add(tglBtnEdit);
		
		JToggleButton tglBtnDelete = new JToggleButton("");
		buttonGroup.add(tglBtnDelete);
		tglBtnDelete.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
		toolBar.add(tglBtnDelete);
		
		

	}
	
	public View getView() {
		return view;
	}

	public void setControler(Controller controler) {
		this.controler = controler;
		
	}
}
