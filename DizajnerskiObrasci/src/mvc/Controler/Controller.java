package mvc.Controler;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
import io.LoadSerialized;
import io.LoadTextual;
import io.Save;
import io.SaveLogg;
import io.SaveManager;
import io.SaveSerialized;
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
	private List<Command> commandList;
	private int actualCommand = -1;
	private String filePath;
	private SaveManager saveManager;
	
	public Controller(Model model, Frame frame) {

		this.frame = frame;
		this.model = model;
		commandList = new ArrayList<Command>();
		saveManager = new SaveManager();
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
				dialogLine.setTxtEndCoordXEdt(false);
				dialogLine.setTxtEndCoordYEdt(false);
				dialogLine.setTxtStartCoordXEdt(false);
				dialogLine.setTxtStartCoordYEdt(false);
				dialogLine.setTxtStartCoordX(Integer.toString(newLine.getStartPoint().getX()));
				dialogLine.setTxtStartCoordY(Integer.toString(newLine.getStartPoint().getY()));
				dialogLine.setTxtEndCoordX(Integer.toString(newLine.getEndPoint().getX()));
				dialogLine.setTxtEndCoordY(Integer.toString(newLine.getEndPoint().getY()));
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
			dialogRectangle.setTxtXCoord(Integer.toString(p.getX()));
			dialogRectangle.setTxtYCoord(Integer.toString(p.getY()));
			dialogRectangle.setTxtXCoordEnabled(false);
			dialogRectangle.setTxtYCoordEnabled(false);
			dialogRectangle.setPnlRectangleInnerColor(getInnerColor());
			dialogRectangle.setPnlRectangleOutlineColor(getOutlineColor());
			dialogRectangle.setVisible(true);
			if(dialogRectangle.isOk()) {
			try {
				if(dialogRectangle.isOk()) {
					int width = Integer.parseInt(dialogRectangle.getTxtWidth());
					int height = Integer.parseInt(dialogRectangle.getTxtHeight());
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
			dialogCircle.setTxtCoordXEdt(false);
			dialogCircle.setTxtCoordYEdt(false);
			dialogCircle.setTxtCoordX(Integer.toString(center.getX()));
			dialogCircle.setTxtCoordY(Integer.toString(center.getY()));
			dialogCircle.setPnlCircleInnerColor(getInnerColor());
			dialogCircle.setPnlCircleOutlineColor(getOutlineColor());
			dialogCircle.setVisible(true);
			try
			{
			if(dialogCircle.isOk())
			{
				int radius=Integer.parseInt(dialogCircle.getTextRadius());
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
			dialogDonut.setTxtCoordX(Integer.toString(center.getX()));
			dialogDonut.setTxtCoordY(Integer.toString(center.getY()));
			dialogDonut.setTxtCoordXEditable(false);
			dialogDonut.setTxtCoordYEditable(false);
			dialogDonut.setPnlDonutInnerColor(getInnerColor());
			dialogDonut.setPnlDonutOutlineColor(getOutlineColor());
			dialogDonut.setVisible(true);
			try
			{
			if(dialogDonut.isOk())
			{
				int innerRadius=Integer.parseInt(dialogDonut.getTxtInner());
				int outerRadius=Integer.parseInt(dialogDonut.getTxtOutter());
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
			dialogLine.setTxtStartCoordX(Integer.toString(((Line) selectedShape).getStartPoint().getX()));
			dialogLine.setTxtStartCoordY(Integer.toString(((Line) selectedShape).getStartPoint().getY()));
			dialogLine.setTxtEndCoordX(Integer.toString(((Line) selectedShape).getEndPoint().getX()));
			dialogLine.setTxtEndCoordY(Integer.toString(((Line) selectedShape).getEndPoint().getY()));
			dialogLine.setPnlLineColor(((Line) selectedShape).getOutlineColor());
			dialogLine.setVisible(true);
			try
			{
			if(dialogLine.isOk())
			{
				newLine = new Line();
				newLine.setStartPoint(new Point((Integer.parseInt(dialogLine.getTxtStartCoordX())),(Integer.parseInt(dialogLine.getTxtStartCoordY()))));
				newLine.setEndPoint(new Point((Integer.parseInt(dialogLine.getTxtEndCoordX())),(Integer.parseInt(dialogLine.getTxtEndCoordY()))));
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
			dialogRectangle.setTxtXCoord(Integer.toString(((Rectangle)selectedShape).getUpperLeftPoint().getX()));
			dialogRectangle.setTxtYCoord(Integer.toString(((Rectangle)selectedShape).getUpperLeftPoint().getY()));
			dialogRectangle.setTxtWidth(Integer.toString(((Rectangle)selectedShape).getWidth()));
			dialogRectangle.setTxtHeight(Integer.toString(((Rectangle)selectedShape).getHeight()));
			dialogRectangle.setPnlRectangleInnerColor(((Rectangle)selectedShape).getInnerColor());
			dialogRectangle.setPnlRectangleOutlineColor(((Rectangle)selectedShape).getOutlineColor());
			dialogRectangle.setVisible(true);
			try
			{
			if(dialogRectangle.isOk())
			{
				newRectangle = new Rectangle();
				newRectangle.setUpperLeftPoint(new Point(Integer.parseInt(dialogRectangle.getTxtXCoord()),Integer.parseInt(dialogRectangle.getTxtYCoord())));
				newRectangle.setHeight(Integer.parseInt(dialogRectangle.getTxtHeight()));
				newRectangle.setWidth(Integer.parseInt(dialogRectangle.getTxtWidth()));
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
			dialogDonut.setTxtCoordX(Integer.toString(((Donut)selectedShape).getCenter().getX()));
			dialogDonut.setTxtCoordY(Integer.toString(((Donut)selectedShape).getCenter().getY()));
			dialogDonut.setTxtInner(Integer.toString(((Donut)selectedShape).getInnerRadius()));
			dialogDonut.setTxtOutter(Integer.toString(((Donut)selectedShape).getRadius()));
			dialogDonut.setPnlDonutOutlineColor((((Donut)selectedShape).getOutlineColor()));
			dialogDonut.setPnlDonutInnerColor((((Donut)selectedShape).getInnerColor()));
			dialogDonut.setVisible(true);
			try {
			if(dialogDonut.isOk())
			{
				newDonut = new Donut();
				newDonut.setCenter(new Point(Integer.parseInt(dialogDonut.getTxtCoordX()),Integer.parseInt(dialogDonut.getTxtCoordY())));
				newDonut.setRadius(Integer.parseInt(dialogDonut.getTxtOutter()));
				newDonut.setInnerRadius(Integer.parseInt(dialogDonut.getTxtInner()));
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
			dialogCircle.setTxtCoordX(Integer.toString(((Circle)selectedShape).getCenter().getX()));
			dialogCircle.setTxtCoordY(Integer.toString(((Circle)selectedShape).getCenter().getY()));
			dialogCircle.setRadius(Integer.toString(((Circle)selectedShape).getRadius()));
			dialogCircle.setPnlCircleInnerColor(((Circle)selectedShape).getInnerColor());
			dialogCircle.setPnlCircleOutlineColor(((Circle)selectedShape).getOutlineColor());
			dialogCircle.setVisible(true);
			try
			{
			if(dialogCircle.isOk())
			{
				newCircle = new Circle();
				newCircle.setCenter(new Point(Integer.parseInt(dialogCircle.getTxtCoordX()),Integer.parseInt(dialogCircle.getTxtCoordY())));
				newCircle.setRadius(Integer.parseInt(dialogCircle.getTextRadius()));
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
		this.frame.addToLoggList(command.toString());
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
		this.frame.addToLoggList("Undo");
	}
	
	public void redo() {
		actualCommand++;
		commandList.get(actualCommand).execute();
		enableButtons();
		frame.getView().repaint();
		sendChanges();
		this.frame.addToLoggList("Redo");
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
		sendChanges();
		frame.getView().repaint();
	}
	
	public void bringToEnd() {
		ArrayList<Shape> shapes = getSelectedShapes();
		Shape selectedShape = shapes.get(0);
		int currentIndex = model.getShapes().indexOf(selectedShape);
		CmdBringToEnd cmd = new CmdBringToEnd(model, currentIndex);
		commandExecuteHelper(cmd);
		sendChanges();
		frame.getView().repaint();
	}
	
	public void toFront() {
		ArrayList<Shape> shapes = getSelectedShapes();
		Shape selectedShape = shapes.get(0);
		int currentIndex = model.getShapes().indexOf(selectedShape);
		CmdToFront cmd = new CmdToFront(model, currentIndex);
		commandExecuteHelper(cmd);
		sendChanges();
		frame.getView().repaint();
		
	}
	
	public void toBack() {
		ArrayList<Shape> shapes = getSelectedShapes();
		Shape selectedShape = shapes.get(0);
		int currentIndex = model.getShapes().indexOf(selectedShape);
		CmdToBack cmd = new CmdToBack(model, currentIndex);
		commandExecuteHelper(cmd);
		sendChanges();
		frame.getView().repaint();
	}
	
	private void saveFileAs(Save saveObj) {
		saveManager.setSaver(saveObj);
		filePath = saveManager.saveAs();
		this.frame.setSaveButtonEnabled(true);
	}
	
	public void openFileAsSerialized() {
		LoadSerialized loadManager = new LoadSerialized();
		List<Shape> shapeList = loadManager.load();
		saveManager.setSaver(new SaveSerialized());
		model.set(shapeList);
		frame.getView().repaint();
		actualCommand = -1;
		commandList = new ArrayList<Command>();
		sendChanges();
		enableButtons();
	}
	
	public void openFileAsTextual() {
		DefaultListModel<String> helperDlm = this.frame.getLoggList();
		LoadTextual loadManager = new LoadTextual(this.model);
		JFileChooser jFileChooser = new JFileChooser(new File("C:\\"));
		jFileChooser.setDialogTitle("Otvorite datoteku");
		jFileChooser.setFileFilter(new FileNameExtensionFilter("Text file", "txt"));
		int result = jFileChooser.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			 filePath = jFileChooser.getSelectedFile().getAbsolutePath();
			try {
				BufferedReader buffer = new BufferedReader(new FileReader(filePath));
				String line;
				this.frame.clearLoggList();
				while((line = buffer.readLine()) != null) {
					loadManager.load(line);
					this.frame.addToLoggList(line);
				}
				buffer.close();
				commandList = loadManager.getList();
				actualCommand = loadManager.getActualCommand();
				this.model.set(new ArrayList<Shape>());
				for(int i = 0;i <= actualCommand;i++) {
					commandList.get(i).execute();
				}
				this.frame.repaint();
				sendChanges();
				enableButtons();
				saveManager.setSaver(new SaveLogg());
			} catch (FileNotFoundException e) {
				this.frame.setLoggList(helperDlm);
				JOptionPane.showMessageDialog(null,"Datoteka nije pronaðena","GREŠKA!",JOptionPane.WARNING_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"Problem formata","GREŠKA!",JOptionPane.WARNING_MESSAGE);
				this.frame.setLoggList(helperDlm);
			}
		}	
	}
	
	public void saveFile() {
		List<Object> helperList = new ArrayList<>();
		DefaultListModel<String> dlmHelp = this.frame.getLoggList();
		for(int i = 0;i < dlmHelp.size();i++) {
			helperList.add(dlmHelp.get(i));
		}
		saveManager.save(filePath,helperList);
	
	}
	
	public void saveFileAsTextual() {
		List<String> helperList = new ArrayList<String>();
		DefaultListModel<String> helper = this.frame.getLoggList();
		for(int i = 0;i < helper.size();i++) {
			helperList.add(helper.get(i));
		}
		Save saveObj = new SaveLogg(helperList);
		saveFileAs(saveObj);
	}
	
	public void saveFileAsSerialized() {
		Save saveObj = new SaveSerialized(this.model.getShapes());
		saveFileAs(saveObj);
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
