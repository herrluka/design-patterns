package mvc.Controler;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


import commands.CmdAddShape;
import commands.CmdBringToEnd;
import commands.CmdBringToFront;
import commands.CmdRemoveShape;
import commands.CmdToBack;
import commands.CmdToFront;
import commands.CmdUpdateCircle;
import commands.CmdUpdateDonut;
import commands.CmdUpdateHexagon;
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
import mvc.Model.HexagonAdapter;
import mvc.Model.Line;
import mvc.Model.Model;
import mvc.View.DialogCircle;
import mvc.View.DialogDonut;
import mvc.View.DialogHexagon;
import mvc.View.DialogLine;
import mvc.View.DialogPoint;
import mvc.View.DialogRectangle;
import mvc.View.Frame;




public class Controller extends Observable {

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
		boolean flag = false;
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
			dialogPoint.setPnlColor(getOutlineColor());
			dialogPoint.setVisible(true);
			if(dialogPoint.isOk()) {
				newPoint.setOutlineColor(dialogPoint.getPnlColor());
				setOutlineColor(dialogPoint.getPnlColor());
				CmdAddShape cmd = new CmdAddShape(newPoint, model);
				commandExecuteHelper(cmd);
//				cleanCommandList();
				deselectAll();
			}
		}
		else if(mode == Constants.LINE)
		{
			if(startPoint==null) {
				startPoint = new Point(e.getX(),e.getY());
				flag = true;
			}
			else if(startPoint.equals(new Point(e.getX(),e.getY()))) {
				JOptionPane.showMessageDialog(new JFrame(), "Nije moguæe nacrtati liniju koja sadrži dve iste taèke. Izaberite drugu taèku.", "Greška", JOptionPane.WARNING_MESSAGE);
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
				dialogLine.setPnlLineColor(getOutlineColor());
				dialogLine.setVisible(true);
				if(dialogLine.isOk()) {
					newLine.setOutlineColor(dialogLine.getPnlLineColor());
					setOutlineColor(dialogLine.getPnlLineColor());
					CmdAddShape cmd = new CmdAddShape(newLine, model);
					commandExecuteHelper(cmd);
//					cleanCommandList();
					startPoint=null;
					deselectAll();
				}
			}
		}
		else if(mode == Constants.RECTANGLE) {
			Point p = new Point(e.getX(),e.getY());
			DialogRectangle dialogRectangle = new DialogRectangle();
			dialogRectangle.setTxtXKoordinata(Integer.toString(p.getX()));
			dialogRectangle.setTxtYKoordinata(Integer.toString(p.getY()));
			dialogRectangle.setTxtXKoordinataEnabled(false);
			dialogRectangle.setTxtYKoordinataEnabled(false);
			dialogRectangle.setPnlRectangleInnerColor(getInnerColor());
			dialogRectangle.setPnlRectangleOutlineColor(getOutlineColor());
			dialogRectangle.setVisible(true);
			if(dialogRectangle.isOk()) {
			try {
				if(dialogRectangle.isOk()) {
					int width = Integer.parseInt(dialogRectangle.getTxtSirina());
					int height = Integer.parseInt(dialogRectangle.getTxtVisina());
					Rectangle rct = new Rectangle(p,width,height);
					rct.setOutlineColor(dialogRectangle.getPnlRectangleOutlineColor());
					rct.setInnerColor(dialogRectangle.getPnlRectangleInnerColor());
					setInnerColor(dialogRectangle.getPnlRectangleInnerColor());
					setOutlineColor(dialogRectangle.getPnlRectangleOutlineColor());
					CmdAddShape cmd = new CmdAddShape(rct, model);
					commandExecuteHelper(cmd);
//					cleanCommandList();
					deselectAll();
				}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Visina i sirina moraju da budu pozitivne!", "Greška", JOptionPane.WARNING_MESSAGE);
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
			dialogCircle.setPnlCircleInnerColor(getInnerColor());
			dialogCircle.setPnlCircleOutlineColor(getOutlineColor());
			dialogCircle.setVisible(true);
			try
			{
			if(dialogCircle.isOk())
			{
				int radius=Integer.parseInt(dialogCircle.getTextPoluprecnik());
				Circle circle = new Circle(center,radius);
				circle.setOutlineColor(dialogCircle.getPnlCircleOutlineColor());
				circle.setInnerColor(dialogCircle.getPnlCircleInnerColor());
				setInnerColor(dialogCircle.getPnlCircleInnerColor());
				setOutlineColor(dialogCircle.getPnlCircleOutlineColor());
				CmdAddShape cmd = new CmdAddShape(circle, model);
				commandExecuteHelper(cmd);
//				cleanCommandList();
				deselectAll();
			
			}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Vrednost poluprecnika mora da bude pozitivna!", "Greška", JOptionPane.WARNING_MESSAGE);	
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
			dialogDonut.setPnlDonutInnerColor(getInnerColor());
			dialogDonut.setPnlDonutOutlineColor(getOutlineColor());
			dialogDonut.setVisible(true);
			try
			{
			if(dialogDonut.isOk())
			{
				int innerRadius=Integer.parseInt(dialogDonut.getTxtUnut());
				int outerRadius=Integer.parseInt(dialogDonut.getTxtSpolj());
				Donut donut = new Donut(center,outerRadius,innerRadius);
				donut.setOutlineColor(dialogDonut.getPnlDonutOutlineColor());
				donut.setInnerColor(dialogDonut.getPnlDonutInnerColor());
				setInnerColor(dialogDonut.getPnlDonutInnerColor());
				setOutlineColor(dialogDonut.getPnlDonutOutlineColor());
				CmdAddShape cmd = new CmdAddShape(donut, model);
				commandExecuteHelper(cmd);
//				cleanCommandList();
				deselectAll();
			}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Poluprecnici moraju da budu veci od nule i poluprecnik unutrasnjeg kruga mora da bude manji od poluprecnika velikog kruga!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		else if(mode == Constants.HEXAGON) {
			Point center = new Point(e.getX(),e.getY());
			DialogHexagon dialogHexagon = new DialogHexagon();
			dialogHexagon.setTxtCoordX(Integer.toString(center.getX()));
			dialogHexagon.setTxtCoordY(Integer.toString(center.getY()));
			dialogHexagon.setTxtCoordXEditable(false);
			dialogHexagon.setTxtCoordYEditable(false);
			dialogHexagon.setPnlInnerColor(getInnerColor());
			dialogHexagon.setPnlOutlineColor(getOutlineColor());
			dialogHexagon.setVisible(true);
			if(dialogHexagon.isOk()) {
				HexagonAdapter hexagon = new HexagonAdapter(center, Integer.parseInt(dialogHexagon.getTxtRadius()));
				hexagon.setInnerColor(dialogHexagon.getPnlInnerColor());
				hexagon.setOutlineColor(dialogHexagon.getPnlOutlineColor());
				setOutlineColor(dialogHexagon.getPnlOutlineColor());
				setInnerColor(dialogHexagon.getPnlInnerColor());
				CmdAddShape cmd = new CmdAddShape(hexagon, model);
				commandExecuteHelper(cmd);
//				cleanCommandList();
				deselectAll();
				
			}
		}
		if(flag == false) {
			startPoint = null;
		}
		frame.getView().repaint();
		sendChanges();
	}

	private void handleSelectMode(MouseEvent e) {
		boolean flag = false;
		List<Shape> list = model.getShapes();
		for(int i= list.size() - 1; i >= 0; i--) {
			Shape shape = list.get(i);
			if(shape.contains(new Point(e.getX(),e.getY()))) {
				flag = true;
				if(shape.isSelected() == false) {
					shape.setSelected(true);
					break;
				}
				else {
					shape.setSelected(false);
					break;
				}
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
			dialogPoint.setPnlColor(((Point)selectedShape).getOutlineColor());
			dialogPoint.setVisible(true);
			try {
			if(dialogPoint.isOk())
			{
				newPoint = new Point();
				newPoint.setX(Integer.parseInt(dialogPoint.getTbX()));
				newPoint.setY(Integer.parseInt(dialogPoint.getTxtY()));
				newPoint.setOutlineColor(dialogPoint.getPnlColor());
				setOutlineColor(dialogPoint.getPnlColor());
				
			CmdUpdatePoint cmd = new CmdUpdatePoint((Point)selectedShape, newPoint);
			commandExecuteHelper(cmd);
			}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Greška", JOptionPane.WARNING_MESSAGE);
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
			dialogLine.setPnlLineColor(((Line) selectedShape).getOutlineColor());
			dialogLine.setVisible(true);
			try
			{
			if(dialogLine.isOk())
			{
				newLine = new Line();
				newLine.setStartPoint(new Point((Integer.parseInt(dialogLine.getTxtPocKoordX())),(Integer.parseInt(dialogLine.getTxtPocKoordY()))));
				newLine.setEndPoint(new Point((Integer.parseInt(dialogLine.getTxtKrKoordX())),(Integer.parseInt(dialogLine.getTxtKrKoordY()))));
				newLine.setOutlineColor(dialogLine.getPnlLineColor());
				setOutlineColor(dialogLine.getPnlLineColor());
				CmdUpdateLine cmd = new CmdUpdateLine((Line)selectedShape, newLine);
				commandExecuteHelper(cmd);
			}
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Greška", JOptionPane.WARNING_MESSAGE);
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
			dialogRectangle.setPnlRectangleInnerColor(((Rectangle)selectedShape).getInnerColor());
			dialogRectangle.setPnlRectangleOutlineColor(((Rectangle)selectedShape).getOutlineColor());
			dialogRectangle.setVisible(true);
			try
			{
			if(dialogRectangle.isOk())
			{
				newRectangle = new Rectangle();
				newRectangle.setUpperLeftPoint(new Point(Integer.parseInt(dialogRectangle.getTxtXKoordinata()),Integer.parseInt(dialogRectangle.getTxtYKoordinata())));
				newRectangle.setHeight(Integer.parseInt(dialogRectangle.getTxtVisina()));
				newRectangle.setWidth(Integer.parseInt(dialogRectangle.getTxtSirina()));
				newRectangle.setOutlineColor(dialogRectangle.getPnlRectangleOutlineColor());
				newRectangle.setInnerColor(dialogRectangle.getPnlRectangleInnerColor());
				setInnerColor(dialogRectangle.getPnlRectangleInnerColor());
				setOutlineColor(dialogRectangle.getPnlRectangleOutlineColor());
				
				CmdUpdateRectangle cmd = new CmdUpdateRectangle((Rectangle)selectedShape, newRectangle);
				commandExecuteHelper(cmd);
			}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Visina i sirina moraju da budu pozitivni brojevi!", "Greška", JOptionPane.WARNING_MESSAGE);
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
			dialogDonut.setPnlDonutOutlineColor((((Donut)selectedShape).getOutlineColor()));
			dialogDonut.setPnlDonutInnerColor((((Donut)selectedShape).getInnerColor()));
			dialogDonut.setVisible(true);
			try {
			if(dialogDonut.isOk())
			{
				newDonut = new Donut();
				newDonut.setCenter(new Point(Integer.parseInt(dialogDonut.getTxtKoordX()),Integer.parseInt(dialogDonut.getTxtKoordY())));
				newDonut.setRadius(Integer.parseInt(dialogDonut.getTxtSpolj()));
				newDonut.setInnerRadius(Integer.parseInt(dialogDonut.getTxtUnut()));
				newDonut.setOutlineColor(dialogDonut.getPnlDonutOutlineColor());
				newDonut.setInnerColor(dialogDonut.getPnlDonutInnerColor());
				setInnerColor(dialogDonut.getPnlDonutInnerColor());
				setOutlineColor(dialogDonut.getPnlDonutOutlineColor());
				CmdUpdateDonut cmd = new CmdUpdateDonut((Donut)selectedShape, newDonut);
				commandExecuteHelper(cmd);
			}
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Poluprecnici moraju da budu veci od nule i poluprecnik unutrasnjeg kruga mora da bude manji od poluprecnika velikog kruga!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
			
		}else if(selectedShape instanceof Circle)
		{
			Circle newCircle;
			DialogCircle dialogCircle=new DialogCircle();
			dialogCircle.setTxtKoordX(Integer.toString(((Circle)selectedShape).getCenter().getX()));
			dialogCircle.setTxtKoordY(Integer.toString(((Circle)selectedShape).getCenter().getY()));
			dialogCircle.setPoluprecnik(Integer.toString(((Circle)selectedShape).getRadius()));
			dialogCircle.setPnlCircleInnerColor(((Circle)selectedShape).getInnerColor());
			dialogCircle.setPnlCircleOutlineColor(((Circle)selectedShape).getOutlineColor());
			dialogCircle.setVisible(true);
			try
			{
			if(dialogCircle.isOk())
			{
				newCircle = new Circle();
				newCircle.setCenter(new Point(Integer.parseInt(dialogCircle.getTxtKoordX()),Integer.parseInt(dialogCircle.getTxtKoordY())));
				newCircle.setRadius(Integer.parseInt(dialogCircle.getTextPoluprecnik()));
				newCircle.setOutlineColor(dialogCircle.getPnlCircleOutlineColor());
				newCircle.setInnerColor(dialogCircle.getPnlCircleInnerColor());
				setInnerColor(dialogCircle.getPnlCircleInnerColor());
				setOutlineColor(dialogCircle.getPnlCircleOutlineColor());
				
				CmdUpdateCircle cmd = new CmdUpdateCircle((Circle)selectedShape, newCircle);
				commandExecuteHelper(cmd);
			}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Vrednost poluprecnika mora da bude pozitivna!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(selectedShape instanceof HexagonAdapter) {
			HexagonAdapter newHexagon;
			DialogHexagon dialogHexagon = new DialogHexagon();
			dialogHexagon.setTxtCoordX(Integer.toString(((HexagonAdapter)selectedShape).getX()));
			dialogHexagon.setTxtCoordY(Integer.toString(((HexagonAdapter)selectedShape).getY()));
			dialogHexagon.setTxtRadius(Integer.toString(((HexagonAdapter)selectedShape).getRadius()));
			dialogHexagon.setPnlInnerColor(((HexagonAdapter)selectedShape).getInnerColor());
			dialogHexagon.setPnlOutlineColor(((HexagonAdapter)selectedShape).getOutlineColor());
			dialogHexagon.setVisible(true);
			if(dialogHexagon.isOk()) {
				int coordX = Integer.parseInt(dialogHexagon.getTxtCoordX());
				int coordY = Integer.parseInt(dialogHexagon.getTxtCoordY());
				int radius = Integer.parseInt(dialogHexagon.getTxtRadius());
				newHexagon = new HexagonAdapter(new Point(coordX,coordY), radius);
				newHexagon.setInnerColor(dialogHexagon.getPnlInnerColor());
				newHexagon.setOutlineColor(dialogHexagon.getPnlOutlineColor());
				setInnerColor(dialogHexagon.getPnlOutlineColor());
				setOutlineColor(dialogHexagon.getPnlInnerColor());
				CmdUpdateHexagon cmd = new CmdUpdateHexagon((HexagonAdapter)selectedShape, newHexagon);
				commandExecuteHelper(cmd);
			}
		}
		frame.getView().repaint();
		
	}
	
	private void commandExecuteHelper(Command command) {
		command.execute();
		if(actualCommand == commandList.size() - 1) {
			commandList.add(command);
			actualCommand++;
		} else {
			commandList.add(actualCommand + 1, command);
			actualCommand++;
			cleanCommandList();
		}
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
		sendChanges();
	}
	
	public void redo() {
		actualCommand++;
		commandList.get(actualCommand).execute();
		enableButtons();
		frame.getView().repaint();
		sendChanges();
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
			if(JOptionPane.showConfirmDialog(new JFrame(), "Da li ste sigurni da želite da obrišete selektovane oblike?","Potvrda",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				CmdRemoveShape cmd = new CmdRemoveShape(list, model);
				commandExecuteHelper(cmd);
				frame.getView().repaint();
				sendChanges();
			}
		}
	}
	
	public ArrayList<Shape> getSelectedShapes() {
		Iterator<Shape> iterator = model.getShapes().listIterator();
		ArrayList<Shape> helperList = new ArrayList<Shape>();
		while(iterator.hasNext()) {
			Shape s = iterator.next();
			if(s.isSelected())
				helperList.add(s);
		}
		return helperList;
	}
	
	public void bringToFront() {
		ArrayList<Shape> shapes = getSelectedShapes();
		Shape selectedShape = shapes.get(0);
		int currentIndex = model.getShapes().indexOf(selectedShape);
		CmdBringToFront cmd = new CmdBringToFront(model, currentIndex);
		commandExecuteHelper(cmd);
		frame.getView().repaint();
	}
	
	public void bringToEnd() {
		ArrayList<Shape> shapes = getSelectedShapes();
		Shape selectedShape = shapes.get(0);
		int currentIndex = model.getShapes().indexOf(selectedShape);
		CmdBringToEnd cmd = new CmdBringToEnd(model, currentIndex);
		commandExecuteHelper(cmd);
		frame.getView().repaint();
	}
	
	public void toFront() {
		ArrayList<Shape> shapes = getSelectedShapes();
		Shape selectedShape = shapes.get(0);
		int currentIndex = model.getShapes().indexOf(selectedShape);
		CmdToFront cmd = new CmdToFront(model, currentIndex);
		commandExecuteHelper(cmd);
		frame.getView().repaint();
		
		//TODO Obezbediti da se ne poziva kad je na vrhu
	}
	
	public void toBack() {
		ArrayList<Shape> shapes = getSelectedShapes();
		Shape selectedShape = shapes.get(0);
		int currentIndex = model.getShapes().indexOf(selectedShape);
		CmdToBack cmd = new CmdToBack(model, currentIndex);
		commandExecuteHelper(cmd);
		frame.getView().repaint();
		
		//TODO Obezbediti da se ne poziva kad je na dnu
	}
	
	public void sendChanges() {
		this.setChanged();
		this.notifyObservers(model.getShapes());
	}
	
	private void setOutlineColor(Color color) {
		frame.setOutlineColor(color);
	}
	
	private Color getOutlineColor() {
		return frame.getPnlOutlineColor();
	}
	
	private void setInnerColor(Color color) {
		frame.setInnerColor(color);
	}
	
	private Color getInnerColor() {
		return frame.getPnlInnerColor();
	}

	
	
	
}
