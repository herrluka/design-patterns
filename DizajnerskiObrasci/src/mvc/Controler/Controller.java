package mvc.Controler;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


import commands.CmdAddShape;
import commands.CmdRemoveShape;
import commands.CmdUpdateCircle;
import commands.CmdUpdateDonut;
import commands.CmdUpdateLine;
import commands.CmdUpdatePoint;
import commands.CmdUpdateRectangle;
import commands.Command;
import constants.Constants;
import mvc.Model.Point;
import mvc.Model.Rectangle;
import mvc.Model.Shape;
import mvc.Model.Circle;
import mvc.Model.Donut;
import mvc.Model.Line;
import mvc.Model.Model;
import mvc.View.DialogCircle;
import mvc.View.DialogDonut;
import mvc.View.DialogLine;
import mvc.View.DialogPoint;
import mvc.View.DialogRectangle;
import mvc.View.Frame;




public class Controller {

	private Frame frame;
	private Model model;
	private String mode = Constants.NORMAL; //mode where nothing happens
	private Point startPoint = null; //for line to save startPoint
	private List<Command> commandList = new ArrayList<Command>(); 
	private int actualCommand = -1;
	
	public Controller(Model model, Frame frame) {

		this.frame = frame;
		this.model = model;
	}

	public void mouseClicked(MouseEvent e) {
		if(mode == Constants.SELECT) {
			this.handleSelectMode(e);
		}
		else if(mode == Constants.POINT) {
			Point newPoint = new Point(e.getX(),e.getY());
			DialogPoint dialogPoint=new DialogPoint();
			dialogPoint.setTbXEdt(false);
			dialogPoint.setTxtYEdt(false);
			dialogPoint.setTbX(Integer.toString(newPoint.getX()));
			dialogPoint.setTxtY(Integer.toString(newPoint.getY()));
			dialogPoint.setVisible(true);
			newPoint.setOutlineColor(dialogPoint.getBoja());
			CmdAddShape cmd = new CmdAddShape(newPoint, model);
			commandExecuteHelper(cmd);
			cleanCommandList();
			deselectAll();
		}
		else if(mode == Constants.LINE)
		{
			if(startPoint==null) {
				startPoint = new Point(e.getX(),e.getY());
			}
			else if(startPoint.equals(new Point(e.getX(),e.getY()))) {
				JOptionPane.showMessageDialog(new JFrame(), "Nije mogu�e nacrtati liniju koja sadr�i dve iste ta�ke. Izaberite drugu ta�ku.", "Gre�ka", JOptionPane.WARNING_MESSAGE);
			}
			else {
			Line newLine = new Line(startPoint,new Point(e.getX(),e.getY()));
			DialogLine dialogLine = new DialogLine();
			dialogLine.setTxtKrKoordXEdt(false);
			dialogLine.setTxtKrKoordYEdt(false);
			dialogLine.setTxtPocKoordXEdt(false);
			dialogLine.setTxtPocKoordYEdt(false);
			dialogLine.setTxtPocKoordX(Integer.toString(newLine.getStartPoint().getX()));
			dialogLine.setTxtPocKoordY(Integer.toString(newLine.getStartPoint().getY()));
			dialogLine.setTxtKrKoordX(Integer.toString(newLine.getEndPoint().getX()));
			dialogLine.setTxtKrKoordY(Integer.toString(newLine.getEndPoint().getY()));
			dialogLine.setVisible(true);
			newLine.setOutlineColor(dialogLine.getCol());
			CmdAddShape cmd = new CmdAddShape(newLine, model);
			commandExecuteHelper(cmd);
			cleanCommandList();
			startPoint=null;
			deselectAll();
			}
		}
		else if(mode == Constants.RECTANGLE) {
			Point p = new Point(e.getX(),e.getY());
			DialogRectangle dialogRectangle = new DialogRectangle();
			dialogRectangle.setTxtXKoordinata(Integer.toString(p.getX()));
			dialogRectangle.setTxtYKoordinata(Integer.toString(p.getY()));
			dialogRectangle.setTxtXKoordinataEnabled(false);
			dialogRectangle.setTxtYKoordinataEnabled(false);
			dialogRectangle.setVisible(true);
			if(dialogRectangle.isOk()) {
			try {
			int width = Integer.parseInt(dialogRectangle.getTxtSirina());
			int height = Integer.parseInt(dialogRectangle.getTxtVisina());
			Rectangle rct = new Rectangle(p,width,height);
			rct.setOutlineColor(dialogRectangle.getBojaIvice());
			rct.setInnerColor(dialogRectangle.getBojaUnut());
			CmdAddShape cmd = new CmdAddShape(rct, model);
			commandExecuteHelper(cmd);
			cleanCommandList();
			deselectAll();
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Gre�ka", JOptionPane.WARNING_MESSAGE);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Visina i sirina moraju da budu pozitivne!", "Gre�ka", JOptionPane.WARNING_MESSAGE);
			}
			}
		}
		else if(mode == Constants.CIRCLE)
		{
			Point center = new Point(e.getX(),e.getY());
			DialogCircle dialogCircle = new DialogCircle();
			dialogCircle.setTxtKoordXEdt(false);
			dialogCircle.setTxtKoordYEdt(false);
			dialogCircle.setTxtKoordX(Integer.toString(center.getX()));
			dialogCircle.setTxtKoordY(Integer.toString(center.getY()));
			dialogCircle.setVisible(true);
			try
			{
			if(dialogCircle.isOk())
			{
				int radius=Integer.parseInt(dialogCircle.getTextPoluprecnik());
				Circle circle = new Circle(center,radius);
				circle.setOutlineColor(dialogCircle.getBojaIvice());
				circle.setInnerColor(dialogCircle.getBojaUnut());
				CmdAddShape cmd = new CmdAddShape(circle, model);
				commandExecuteHelper(cmd);
				cleanCommandList();
				deselectAll();
			
			}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!!", "Gre�ka", JOptionPane.WARNING_MESSAGE);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Vrednost poluprecnika mora da bude pozitivna!", "Gre�ka", JOptionPane.WARNING_MESSAGE);	
			}
		}
		else if(mode == Constants.DONUT)
		{
			Point center = new Point(e.getX(),e.getY());
			DialogDonut dialogDonut = new DialogDonut();
			dialogDonut.setTxtKoordX(Integer.toString(center.getX()));
			dialogDonut.setTxtKoordY(Integer.toString(center.getY()));
			dialogDonut.setTxtKoordXEditable(false);
			dialogDonut.setTxtKoordYEditable(false);
			dialogDonut.setVisible(true);
			try
			{
			if(dialogDonut.isOk())
			{
				int innerRadius=Integer.parseInt(dialogDonut.getTxtUnut());
				int outerRadius=Integer.parseInt(dialogDonut.getTxtSpolj());
				Donut donut = new Donut(center,outerRadius,innerRadius);
				donut.setOutlineColor(dialogDonut.getBojaIvice());
				donut.setInnerColor(dialogDonut.getBojaUnut());
				CmdAddShape cmd = new CmdAddShape(donut, model);
				commandExecuteHelper(cmd);
				cleanCommandList();
				deselectAll();
			}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Gre�ka", JOptionPane.WARNING_MESSAGE);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Poluprecnici moraju da budu veci od nule i poluprecnik unutrasnjeg kruga mora da bude manji od poluprecnika velikog kruga!", "Gre�ka", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		frame.getView().repaint();
	}

	private void handleSelectMode(MouseEvent e) {
		boolean flag = false;
		ListIterator<Shape> iterator = model.getShapes().listIterator();
		while(iterator.hasNext()) {
			Shape shape = iterator.next();
			if(shape.contains(new Point(e.getX(),e.getY()))) {
				if(shape.isSelected() == false) {
					shape.setSelected(true);
				}
				flag = true;
			}
		}
		if(flag == false) {
			deselectAll();
		}
		
	}



	public void handleEdit() {
		Shape selectedShape = getSelectedShapes().get(0);
		if( selectedShape instanceof Point)
		{
			Point newPoint;
			DialogPoint dialogPoint = new DialogPoint();
			dialogPoint.setTbX(Integer.toString(((Point) selectedShape).getX()));
			dialogPoint.setTxtY(Integer.toString(((Point) selectedShape).getY()));
			dialogPoint.setBoja(((Point)selectedShape).getOutlineColor());
			dialogPoint.setVisible(true);
			try {
			if(dialogPoint.isOk())
			{
				newPoint = new Point();
				newPoint.setX(Integer.parseInt(dialogPoint.getTbX()));
				newPoint.setY(Integer.parseInt(dialogPoint.getTxtY()));
				newPoint.setOutlineColor(dialogPoint.getBoja());
				
			CmdUpdatePoint cmd = new CmdUpdatePoint((Point)selectedShape, newPoint);
			commandExecuteHelper(cmd);
			}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Gre�ka", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(selectedShape instanceof Line)
		{
			Line newLine;
			DialogLine dialogLine=new DialogLine();
			dialogLine.setTxtPocKoordX(Integer.toString(((Line) selectedShape).getStartPoint().getX()));
			dialogLine.setTxtPocKoordY(Integer.toString(((Line) selectedShape).getStartPoint().getY()));
			dialogLine.setTxtKrKoordX(Integer.toString(((Line) selectedShape).getEndPoint().getX()));
			dialogLine.setTxtKrKoordY(Integer.toString(((Line) selectedShape).getEndPoint().getY()));
			dialogLine.setCol(((Line) selectedShape).getOutlineColor());
			dialogLine.setVisible(true);
			try
			{
			if(dialogLine.isOk())
			{
				newLine = new Line();
				newLine.setStartPoint(new Point((Integer.parseInt(dialogLine.getTxtPocKoordX())),(Integer.parseInt(dialogLine.getTxtPocKoordY()))));
				newLine.setEndPoint(new Point((Integer.parseInt(dialogLine.getTxtKrKoordX())),(Integer.parseInt(dialogLine.getTxtKrKoordY()))));
				newLine.setOutlineColor(dialogLine.getCol());
				CmdUpdateLine cmd = new CmdUpdateLine((Line)selectedShape, newLine);
				commandExecuteHelper(cmd);
			}
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Gre�ka", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(selectedShape instanceof Rectangle)
		{
			Rectangle newRectangle;
			DialogRectangle dialogRectangle=new DialogRectangle();
			dialogRectangle.setTxtXKoordinata(Integer.toString(((Rectangle)selectedShape).getUpperLeftPoint().getX()));
			dialogRectangle.setTxtYKoordinata(Integer.toString(((Rectangle)selectedShape).getUpperLeftPoint().getY()));
			dialogRectangle.setTxtSirina(Integer.toString(((Rectangle)selectedShape).getWidth()));
			dialogRectangle.setTxtVisina(Integer.toString(((Rectangle)selectedShape).getHeight()));
			dialogRectangle.setBojaUnut(((Rectangle)selectedShape).getInnerColor());
			dialogRectangle.setBojaIvice(((Rectangle)selectedShape).getOutlineColor());
			dialogRectangle.setVisible(true);
			try
			{
			if(dialogRectangle.isOk())
			{
				newRectangle = new Rectangle();
				newRectangle.setUpperLeftPoint(new Point(Integer.parseInt(dialogRectangle.getTxtXKoordinata()),Integer.parseInt(dialogRectangle.getTxtYKoordinata())));
				newRectangle.setHeight(Integer.parseInt(dialogRectangle.getTxtVisina()));
				newRectangle.setWidth(Integer.parseInt(dialogRectangle.getTxtSirina()));
				newRectangle.setOutlineColor(dialogRectangle.getBojaIvice());
				newRectangle.setInnerColor(dialogRectangle.getBojaUnut());
				
				CmdUpdateRectangle cmd = new CmdUpdateRectangle((Rectangle)selectedShape, newRectangle);
				commandExecuteHelper(cmd);
			}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Gre�ka", JOptionPane.WARNING_MESSAGE);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Visina i sirina moraju da budu pozitivni brojevi!", "Gre�ka", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		else if(selectedShape instanceof Donut)
		{
			Donut newDonut;
			DialogDonut dialogDonut = new DialogDonut();
			dialogDonut.setTxtKoordX(Integer.toString(((Donut)selectedShape).getCenter().getX()));
			dialogDonut.setTxtKoordY(Integer.toString(((Donut)selectedShape).getCenter().getY()));
			dialogDonut.setTxtUnut(Integer.toString(((Donut)selectedShape).getInnerRadius()));
			dialogDonut.setTxtSpolj(Integer.toString(((Donut)selectedShape).getRadius()));
			dialogDonut.setBojaIvice((((Donut)selectedShape).getOutlineColor()));
			dialogDonut.setBojaUnut((((Donut)selectedShape).getInnerColor()));
			dialogDonut.setVisible(true);
			try {
			if(dialogDonut.isOk())
			{
				newDonut = new Donut();
				newDonut.setCenter(new Point(Integer.parseInt(dialogDonut.getTxtKoordX()),Integer.parseInt(dialogDonut.getTxtKoordY())));
				newDonut.setRadius(Integer.parseInt(dialogDonut.getTxtSpolj()));
				newDonut.setInnerRadius(Integer.parseInt(dialogDonut.getTxtUnut()));
				newDonut.setOutlineColor(dialogDonut.getBojaIvice());
				newDonut.setInnerColor(dialogDonut.getBojaUnut());
				
				CmdUpdateDonut cmd = new CmdUpdateDonut((Donut)selectedShape, newDonut);
				commandExecuteHelper(cmd);
			}
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Gre�ka", JOptionPane.WARNING_MESSAGE);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Poluprecnici moraju da budu veci od nule i poluprecnik unutrasnjeg kruga mora da bude manji od poluprecnika velikog kruga!", "Gre�ka", JOptionPane.WARNING_MESSAGE);
			}
			
		}else if(selectedShape instanceof Circle)
		{
			Circle newCircle;
			DialogCircle dialogCircle=new DialogCircle();
			dialogCircle.setTxtKoordX(Integer.toString(((Circle)selectedShape).getCenter().getX()));
			dialogCircle.setTxtKoordY(Integer.toString(((Circle)selectedShape).getCenter().getY()));
			dialogCircle.setPoluprecnik(Integer.toString(((Circle)selectedShape).getRadius()));
			dialogCircle.setBojaUnut(((Circle)selectedShape).getInnerColor());
			dialogCircle.setBojaIvice(((Circle)selectedShape).getOutlineColor());
			dialogCircle.setVisible(true);
			try
			{
			if(dialogCircle.isOk())
			{
				newCircle = new Circle();
				newCircle.setCenter(new Point(Integer.parseInt(dialogCircle.getTxtKoordX()),Integer.parseInt(dialogCircle.getTxtKoordY())));
				newCircle.setRadius(Integer.parseInt(dialogCircle.getTextPoluprecnik()));
				newCircle.setOutlineColor(dialogCircle.getBojaIvice());
				newCircle.setInnerColor(dialogCircle.getBojaUnut());
				
				CmdUpdateCircle cmd = new CmdUpdateCircle((Circle)selectedShape, newCircle);
				commandExecuteHelper(cmd);
			}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Gre�ka", JOptionPane.WARNING_MESSAGE);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Vrednost poluprecnika mora da bude pozitivna!", "Gre�ka", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		frame.getView().repaint();
		
	}
	
	private void commandExecuteHelper(Command command) {
		command.execute();
		if(actualCommand == commandList.size() - 1) {
			commandList.add(command);
		} else {
			commandList.add(actualCommand + 1, command);
		}
		actualCommand++;
		enableButtons();
	}
	
	private void cleanCommandList() {
		for(int i = commandList.size() - 1; i > actualCommand; i--) {
			commandList.remove(i);
		}
		enableButtons();
	}
	
	public void undo() {
		commandList.get(actualCommand).unexecute();
		actualCommand--;
		enableButtons();
		frame.getView().repaint();
	}
	
	public void redo() {
		actualCommand++;
		commandList.get(actualCommand).execute();
		enableButtons();
		frame.getView().repaint();
	}
	
	private void enableButtons() {
		this.frame.setUndoButtonEnabled(actualCommand > -1);
		this.frame.setRedoButtonEnabled(actualCommand < (commandList.size() - 1));
	}
	
	private void deselectAll() {
		for(Shape s : model.getShapes()) {
			if(s.isSelected())
				s.setSelected(false);
		}
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void handleDelete() {
		ArrayList<Shape> list = getSelectedShapes();
		if(list.size() != 0) {
			if(JOptionPane.showConfirmDialog(new JFrame(), "Da li ste sigurni da �elite da obri�ete selektovane oblike?","Potvrda",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				for(int i = 0; i < list.size(); i++) {
					CmdRemoveShape cmd = new CmdRemoveShape(list.get(i), model);
					commandExecuteHelper(cmd);
				}
				frame.getView().repaint();
			}
		}
	}
	
	private ArrayList<Shape> getSelectedShapes() {
		Iterator<Shape> iterator = model.getShapes().listIterator();
		ArrayList<Shape> helperList = new ArrayList<Shape>();
		while(iterator.hasNext()) {
			Shape s = iterator.next();
			if(s.isSelected())
				helperList.add(s);
		}
		return helperList;
	}

	
	
	
}