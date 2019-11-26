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

import constants.Constants;

import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;

public class Frame extends JFrame{
	
	
	private View view = new View(); //dodato new zbog window buildera
	private Controller controler;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JToggleButton tglBtnPoint;
	private JToggleButton tglBtnLine;
	private JToggleButton tglbtnRectangle;
	private JToggleButton tglBtnCircle;
	private JToggleButton tglBtnDonut;
	private JToggleButton tglBtnSelect;
	private JToggleButton tglBtnEdit;
	private JToggleButton tglBtnDelete;
	private JToggleButton tglBtnUndo;
	private JToggleButton tglBtnRedo;
	
	
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
		
		tglBtnPoint = new JToggleButton("");
		buttonGroup.add(tglBtnPoint);
		tglBtnPoint.setIcon(new ImageIcon(getClass().getResource("/point.png")));
		toolBar.add(tglBtnPoint);
		
		tglBtnLine = new JToggleButton("");
		buttonGroup.add(tglBtnLine);
		tglBtnLine.setIcon(new ImageIcon(getClass().getResource("/line.png")));
		toolBar.add(tglBtnLine);
		
		tglbtnRectangle = new JToggleButton("");
		buttonGroup.add(tglbtnRectangle);
		tglbtnRectangle.setIcon(new ImageIcon(getClass().getResource("/rectangle.png")));
		toolBar.add(tglbtnRectangle);
		
		tglBtnCircle =  new JToggleButton("");
		buttonGroup.add(tglBtnCircle);
		tglBtnCircle.setIcon(new ImageIcon(getClass().getResource("/circle.png")));
		toolBar.add(tglBtnCircle);
		
	
		tglBtnDonut = new JToggleButton("");
		buttonGroup.add(tglBtnDonut);
		tglBtnDonut.setIcon(new ImageIcon(getClass().getResource("/donut.png")));
		toolBar.add(tglBtnDonut);
		
		tglBtnSelect = new JToggleButton("");
		buttonGroup.add(tglBtnSelect);
		tglBtnSelect.setIcon(new ImageIcon(getClass().getResource("/select.png")));
		toolBar.add(tglBtnSelect);
		
		tglBtnEdit = new JToggleButton("");
		buttonGroup.add(tglBtnEdit);
		tglBtnEdit.setIcon(new ImageIcon(getClass().getResource("/edit.png")));
		toolBar.add(tglBtnEdit);
		
		tglBtnDelete = new JToggleButton("");
		buttonGroup.add(tglBtnDelete);
		tglBtnDelete.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
		toolBar.add(tglBtnDelete);
		
		tglBtnUndo = new JToggleButton("");
		buttonGroup.add(tglBtnUndo);
		tglBtnUndo.setIcon(new ImageIcon(getClass().getResource("/undo.png")));
		tglBtnUndo.setEnabled(false);
		toolBar.add(tglBtnUndo);
		
		tglBtnRedo = new JToggleButton("");
		buttonGroup.add(tglBtnRedo);
		tglBtnRedo.setIcon(new ImageIcon(getClass().getResource("/redo.png")));
		tglBtnRedo.setEnabled(false);
		toolBar.add(tglBtnRedo);
		
		tglBtnPoint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controler.setMode(Constants.POINT);
			}
		});
		
		tglBtnLine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controler.setMode(Constants.LINE);
			}
		});
		
		tglbtnRectangle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controler.setMode(Constants.RECTANGLE);
			}
		});
		
		tglBtnCircle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controler.setMode(Constants.CIRCLE);
			}
		});

		tglBtnDonut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controler.setMode(Constants.DONUT);
			}
		});

		tglBtnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controler.setMode(Constants.SELECT);
			}
		});

		tglBtnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controler.handleEdit();
			}
		});
		
		tglBtnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controler.handleDelete();
			}
		});
		
		tglBtnUndo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tglBtnUndo.isEnabled())
					controler.undo();
			}
		});
		
		tglBtnRedo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tglBtnRedo.isEnabled()) {
					controler.redo();
				}
			}
		});
		

	}
	
	public void setUndoButtonEnabled(boolean bool) {
		this.tglBtnUndo.setEnabled(bool);
	}
	
	public void setRedoButtonEnabled(boolean bool) {
		this.tglBtnRedo.setEnabled(bool);
	}
	
	public View getView() {
		return view;
	}

	public void setControler(Controller controler) {
		this.controler = controler;
		
	}
}
