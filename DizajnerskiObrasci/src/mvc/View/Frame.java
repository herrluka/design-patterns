package mvc.View;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import mvc.Controler.Controller;
import mvc.Model.Shape;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import constants.Constants;
import io.Save;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Insets;

public class Frame extends JFrame implements Observer{
	
	
	private View view = new View(); 
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
	private JList list;
	private DefaultListModel<String> loggList;
	private JMenuBar menuBar;
	private JMenu mnOpen;
	private JMenuItem mntmOpenSerialized;
	private JMenuItem mntmSave;
	private JMenuItem mntmSaveAsSerialized;
	private JMenuItem mntmOpenTextual;
	private JMenuItem bringToFront; 
	private JMenuItem bringToEnd; 
	private JMenuItem toBack;
	private JMenuItem toFront;
	private JMenuItem mntmSaveAsTextual;
	private JMenu mnSave;
	private JMenu mnFile;
	private JMenuItem mntmNew;

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
		view.setBackground(Color.WHITE);
		
		JPanel colorsParentPanel = new JPanel();
		colorsParentPanel.setBackground(Color.GRAY);
		getContentPane().add(colorsParentPanel,BorderLayout.SOUTH);
		
		innerColorPanel = new JPanel();
		innerColorPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color color = JColorChooser.showDialog(null, "Izaberite boju unutrašnjosti", getPnlInnerColor());
				if(color != null) {
					setInnerColor(color);
				}
			}
		});
		innerColorPanel.setBackground(Color.WHITE);
		
		outlineColorPanel = new JPanel();
		outlineColorPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color color = JColorChooser.showDialog(null, "Izaberite boju ivice", getPnlOutlineColor());
				if(color != null) {
					setOutlineColor(color);
				}
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
		
		list = new JList();
		loggList = new DefaultListModel<String>();
		list.setModel(loggList);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_colorsParentPanel = new GroupLayout(colorsParentPanel);
		gl_colorsParentPanel.setHorizontalGroup(
			gl_colorsParentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_colorsParentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_colorsParentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(outlineColorPanel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(innerColorPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_colorsParentPanel.setVerticalGroup(
			gl_colorsParentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_colorsParentPanel.createSequentialGroup()
					.addGroup(gl_colorsParentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_colorsParentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_colorsParentPanel.createSequentialGroup()
							.addGap(18)
							.addComponent(innerColorPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(outlineColorPanel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
					.addGap(13))
		);
		colorsParentPanel.setLayout(gl_colorsParentPanel);
		
		scrollPane.setViewportView(list);
		
		JPopupMenu menu = new JPopupMenu("menu");
		bringToFront = new JMenuItem("Bring to Front");
		bringToEnd = new JMenuItem("Bring to end");
		toBack= new JMenuItem("Move to back");
		toFront= new JMenuItem("Move to front");
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
		tglBtnRedo.setFont(new Font("Tahoma", Font.PLAIN, 7));
		buttonGroup.add(tglBtnRedo);
		tglBtnRedo.setIcon(new ImageIcon(getClass().getResource("/redo.png")));
		tglBtnRedo.setEnabled(false);
		toolBar.add(tglBtnRedo);
		
		menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 0, 70, 0));
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		mnFile.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnFile);
		
		mntmNew = new JMenuItem("New");
		mntmNew.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnFile.add(mntmNew);
		
		mnOpen = new JMenu("Open");
		mnOpen.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnOpen);
		
		mntmOpenSerialized = new JMenuItem("Open serialized");
		mntmOpenSerialized.setHorizontalAlignment(SwingConstants.LEFT);
		mntmOpenSerialized.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnOpen.add(mntmOpenSerialized);
		
		mntmOpenTextual = new JMenuItem("Open textual");
		mntmOpenTextual.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnOpen.add(mntmOpenTextual);
		
		mnSave = new JMenu("Save");
		mnSave.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnSave);
		
		mntmSave = new JMenuItem("Save");
		mntmSave.setHorizontalAlignment(SwingConstants.LEFT);
		mnSave.add(mntmSave);
		mntmSave.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmSave.setEnabled(false);
		
		mntmSaveAsSerialized = new JMenuItem("Save as serialized...");
		mntmSaveAsSerialized.setHorizontalAlignment(SwingConstants.LEFT);
		mnSave.add(mntmSaveAsSerialized);
		mntmSaveAsSerialized.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		mntmSaveAsTextual = new JMenuItem("Save as textual...");
		mntmSaveAsTextual.setHorizontalAlignment(SwingConstants.LEFT);
		mnSave.add(mntmSaveAsTextual);
		mntmSaveAsTextual.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		mntmSaveAsTextual.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controler.saveFileAsTextual();
			}
		});
		
		mntmSaveAsSerialized.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controler.saveFileAsSerialized();
			}
		});
		
		mntmSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controler.saveFile();
				
			}
		});
		
		
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
				if(tglBtnEdit.isEnabled()) {
					controler.handleEdit();
					controler.setMode(Constants.NORMAL);
				}
			}
		});
		
		tglBtnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tglBtnDelete.isEnabled()) {
					controler.handleDelete();
					controler.setMode(Constants.NORMAL);
				}
			}
		});
		
		tglBtnUndo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tglBtnUndo.isEnabled()) {
					controler.undo();
					controler.setMode(Constants.NORMAL);
				}
			}
		});
		
		tglBtnRedo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tglBtnRedo.isEnabled()) {
					controler.redo();
					controler.setMode(Constants.NORMAL);
				}
			}
		});
		
		tglBtnBringTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tglBtnBringTo.isEnabled() == true) {
					menu.show(tglBtnBringTo, e.getX(), e.getY());
					controler.setMode(Constants.NORMAL);
				}
			}
		});
		
		bringToFront.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(bringToFront.isEnabled()) {
					controler.bringToFront();
					controler.setMode(Constants.NORMAL);
				}
			}
		});
		
		bringToEnd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(bringToEnd.isEnabled()) {
					controler.bringToEnd();
					controler.setMode(Constants.NORMAL);
				}
			}
		});
		
		toFront.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(toFront.isEnabled()) {
					controler.toFront();
					controler.setMode(Constants.NORMAL);
				}
				
			}
		});
		
		toBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(toBack.isEnabled()) {
					controler.toBack();
					controler.setMode(Constants.NORMAL);
				}
			}
		});
		
		mntmOpenSerialized.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controler.openFileAsSerialized();
				controler.setMode(Constants.NORMAL);
			}
		});
		
		mntmOpenTextual.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controler.openFileAsTextual();
				controler.setMode(Constants.NORMAL);
			}
		});
		
		mntmNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controler.newFile();
				controler.setMode(Constants.NORMAL);
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
		if(selectedShapes == 1 && list.get(0).isSelected()) {
			toBack.setEnabled(false);
			bringToEnd.setEnabled(false);
		} else {
			toBack.setEnabled(true);
			bringToEnd.setEnabled(true);
		}
		if(selectedShapes == 1 && list.get(numberOfShapes - 1).isSelected()) {
			toFront.setEnabled(false);
			bringToFront.setEnabled(false);
		} else {
			toFront.setEnabled(true);
			bringToFront.setEnabled(true);
		}
		
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
	
	public void setList(List<String> listOfCommands) {
		loggList = new DefaultListModel<String>();
		for(String c : listOfCommands) {
			loggList.addElement(c);
		}
	}
	
	public void addToLoggList(String element) {
		loggList.addElement(element);
	}

	public void setSaveButtonEnabled(boolean enabled) {
		this.mntmSave.setEnabled(enabled);
	}

	public DefaultListModel<String> getLoggList() {
		return loggList;
	}
	
	public void clearLoggList() {
		loggList.removeAllElements();
	}
	
	public void setLoggList(DefaultListModel<String> dlm) {
		loggList.removeAllElements();
		for(int i = 0;i < dlm.size();i++) {
			loggList.addElement(dlm.elementAt(i));
		}
	}	
	
}
