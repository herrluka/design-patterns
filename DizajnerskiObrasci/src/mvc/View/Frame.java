package mvc.View;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import mvc.Controler.Controller;
import mvc.Model.Model;
import mvc.Model.Shape;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import constants.Constants;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.print.attribute.standard.NumberOfDocuments;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class Frame extends JFrame implements Observer{
	
	
	private View view = new View(); //dodato new zbog window buildera
	private Controller controler;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JToggleButton tglBtnPoint;
	private JToggleButton tglBtnLine;
	private JToggleButton tglbtnRectangle;
	private JToggleButton tglBtnCircle;
	private JToggleButton tglBtnDonut;
	private JToggleButton tglBtnHexagon;
	private JToggleButton tglBtnSelect;
	private JToggleButton tglBtnEdit;
	private JToggleButton tglBtnDelete;
	private JToggleButton tglBtnUndo;
	private JToggleButton tglBtnRedo;
	private JPanel innerColorPanel;
	private JPanel outlineColorPanel;
	private JButton tglBtnBringTo;
	
	
	public Frame() {
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controler.mouseClicked(e);
			}
		});
		setExtendedState(MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(900,950));
		getContentPane().add(view, BorderLayout.CENTER);
		JToolBar toolBar = new JToolBar(null, JToolBar.VERTICAL);
		toolBar.setEnabled(false);
		getContentPane().add(toolBar, BorderLayout.WEST);
		
		JPanel colorsParentPanel = new JPanel();
		colorsParentPanel.setBackground(Color.GRAY);
		getContentPane().add(colorsParentPanel,BorderLayout.SOUTH);
		
		innerColorPanel = new JPanel();
		innerColorPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setInnerColor(JColorChooser.showDialog(null, "Izaberite boju unutrašnjosti", getPnlInnerColor()));
			}
		});
		innerColorPanel.setBackground(Color.WHITE);
		
		outlineColorPanel = new JPanel();
		outlineColorPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setOutlineColor(JColorChooser.showDialog(null, "Izaberite boju ivice", getPnlOutlineColor()));
			}
		});
		outlineColorPanel.setBackground(Color.BLACK);
		GroupLayout gl_innerColorPanel = new GroupLayout(innerColorPanel);
		gl_innerColorPanel.setHorizontalGroup(
			gl_innerColorPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 36, Short.MAX_VALUE)
		);
		gl_innerColorPanel.setVerticalGroup(
			gl_innerColorPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 36, Short.MAX_VALUE)
		);
		innerColorPanel.setLayout(gl_innerColorPanel);
		GroupLayout gl_colorsParentPanel = new GroupLayout(colorsParentPanel);
		gl_colorsParentPanel.setHorizontalGroup(
			gl_colorsParentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_colorsParentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_colorsParentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(innerColorPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(outlineColorPanel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(642, Short.MAX_VALUE))
		);
		gl_colorsParentPanel.setVerticalGroup(
			gl_colorsParentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_colorsParentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(innerColorPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(outlineColorPanel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(8))
		);
		colorsParentPanel.setLayout(gl_colorsParentPanel);
		
		JPopupMenu menu = new JPopupMenu("menu");
		JMenuItem bringToFront = new JMenuItem("Bring to Front");
		JMenuItem bringToEnd = new JMenuItem("Bring to end");
		JMenuItem toBack= new JMenuItem("Move to back");
		JMenuItem toFront= new JMenuItem("Move to front");
		menu.add(bringToFront);
		menu.add(bringToEnd);
		menu.add(toBack);
		menu.add(toFront);
		
		
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
		
		tglBtnHexagon = new JToggleButton("");
		buttonGroup.add(tglBtnHexagon);
		tglBtnHexagon.setIcon(new ImageIcon(getClass().getResource("/hexagon.png")));
		toolBar.add(tglBtnHexagon);
		
		tglBtnSelect = new JToggleButton("");
		buttonGroup.add(tglBtnSelect);
		tglBtnSelect.setIcon(new ImageIcon(getClass().getResource("/select.png")));
		tglBtnSelect.setEnabled(false);
		toolBar.add(tglBtnSelect);
		
		tglBtnEdit = new JToggleButton("");
		buttonGroup.add(tglBtnEdit);
		tglBtnEdit.setIcon(new ImageIcon(getClass().getResource("/edit.png")));
		tglBtnEdit.setEnabled(false);
		toolBar.add(tglBtnEdit);
		
		tglBtnDelete = new JToggleButton("");
		buttonGroup.add(tglBtnDelete);
		tglBtnDelete.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
		tglBtnDelete.setEnabled(false);
		toolBar.add(tglBtnDelete);
		
		tglBtnBringTo = new JButton("");
		tglBtnBringTo.setIcon(new ImageIcon(getClass().getResource("/bringToFront.png")));
		tglBtnBringTo.setEnabled(false);
		toolBar.add(tglBtnBringTo);
		
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
		
		tglBtnHexagon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controler.setMode(Constants.HEXAGON);
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
		
		tglBtnBringTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tglBtnBringTo.isEnabled() == true)
					menu.show(tglBtnBringTo, e.getX(), e.getY());
			}
		});
		
		bringToFront.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controler.bringToFront();
			}
		});
		
		bringToEnd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controler.bringToEnd();
			}
		});
		
		toFront.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controler.toFront();
				
			}
		});
		
		toBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controler.toBack();
			}
		});
	}
	
	@Override
	public void update(Observable o, Object arg) {
		ArrayList<Shape> list = (ArrayList<Shape>)arg;
		int numberOfShapes = list.size();
		int selectedShapes = controler.getSelectedShapes().size();
		tglBtnSelect.setEnabled(numberOfShapes> 0);
		tglBtnEdit.setEnabled(selectedShapes == 1);
		tglBtnDelete.setEnabled(selectedShapes > 0);
		tglBtnBringTo.setEnabled(numberOfShapes > 1 && selectedShapes == 1);
		
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
	
	public void setOutlineColor(Color color) {
		outlineColorPanel.setBackground(color);
	}
	
	public Color getPnlOutlineColor() {
		return outlineColorPanel.getBackground();
	}
	
	public void setInnerColor(Color color) {
		innerColorPanel.setBackground(color);
	}
	
	public Color getPnlInnerColor() {
		return innerColorPanel.getBackground();
	}

	
	
}
