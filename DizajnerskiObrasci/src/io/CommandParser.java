package io;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
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
import mvc.Model.Circle;
import mvc.Model.Donut;
import mvc.Model.HexagonAdapter;
import mvc.Model.Line;
import mvc.Model.Model;
import mvc.Model.Point;
import mvc.Model.Rectangle;
import mvc.Model.Shape;

public class CommandParser {
	
	private String lastString = null;
	private Model model;
	
	public CommandParser(Model model) {
		this.model = model;
	}

	public void parseCommandType(String type){
		
	}
	
	public Command parseCommand(String line) {
		String command = line.split("_")[0];
		String withoutCommand = line.split("_")[1];
		if(command.equals("Add")) {
			return parseAdd(withoutCommand);
		} else if(command.equals("Remove")) {
			return parseRemove(withoutCommand);
		} else if (command.equals("Update")) {
			return parseUpdate(withoutCommand);
		} else if (command.equals("Bring back")) {
			return parseBringBack(withoutCommand);
		} else if (command.equals("Bring front")) {
			return parseBringFront(withoutCommand);
		} else if (command.equals("Bring to end")) {
			return parseBringToEnd(withoutCommand);
		} else if (command.equals("Bring to front")) {
			return parseBringToFront(withoutCommand);
		}
		return null;
	}
	
	private Command parseBringToFront(String oldIndex) {
		return new CmdBringToFront(model, Integer.parseInt(oldIndex));
	}
		
	private Command parseBringToEnd(String oldIndex) {
		return new CmdBringToEnd(model, Integer.parseInt(oldIndex));	
	}
	
	private Command parseBringBack(String oldIndex) {
		return new CmdToBack(model, Integer.parseInt(oldIndex));
	}
	
	private Command parseBringFront(String oldIndex) {
		return new CmdToFront(model, Integer.parseInt(oldIndex));
	}
	
	private Command parseUpdate(String text) {
		Command command = null;
		Shape oldShape = parseShape(text.split(";")[0]);
		Shape newShape = parseShape(text.split(";")[1]);
		if(oldShape instanceof Point && newShape instanceof Point) {
			command = new CmdUpdatePoint((Point)oldShape, (Point)newShape);
		} else if(oldShape instanceof Line && newShape instanceof Line) {
			command = new CmdUpdateLine((Line)oldShape, (Line)newShape);
		} else if(oldShape instanceof Rectangle && newShape instanceof Rectangle) {
			command = new CmdUpdateRectangle((Rectangle)oldShape, (Rectangle)newShape);
		} else if(oldShape instanceof Donut && newShape instanceof Donut) {
			command = new CmdUpdateDonut((Donut)oldShape, (Donut)newShape);
		} else if(oldShape instanceof Circle && newShape instanceof Circle) {
			command = new CmdUpdateCircle((Circle)oldShape, (Circle)newShape);
		} else if(oldShape instanceof HexagonAdapter && newShape instanceof HexagonAdapter) {
			command = new CmdUpdateHexagon((HexagonAdapter)oldShape,(HexagonAdapter)newShape);
		}
		return command;
	}
	
	private Command parseRemove(String text) {
		String[] shapeStrings = text.split(";");
		List<Shape> helperList = new ArrayList<Shape>();
		for(String row : shapeStrings) {
			Shape shape = parseShape(row);
			helperList.add(shape);
		}
		return new CmdRemoveShape(helperList, model);
	}
	
	private Command parseAdd(String text) {
		Shape shape = parseShape(text);
		Command command = new CmdAddShape(shape, model);
		return command;
	}
	
	private Shape parseShape(String text) {
		String shape = text.split(":")[0];
		String[] props = text.split(",");
		if(shape.equals("Point")) {
			Point point = new Point();
			point.setX(Integer.parseInt(props[0].split("=")[1]));
			point.setY(Integer.parseInt(props[1].split("=")[1]));
			point.setOutlineColor(new Color(Integer.parseInt(props[2].split("=")[1])));
			return point;
		} else if(shape.equals("Line")) {
			Line line = new Line();
			line.setStartPoint(new Point(Integer.parseInt(props[0].split("=")[1]),Integer.parseInt(props[1].split("=")[1])));
			line.setEndPoint(new Point(Integer.parseInt(props[2].split("=")[1]),Integer.parseInt(props[3].split("=")[1])));
			line.setOutlineColor(new Color(Integer.parseInt(props[4].split("=")[1])));
			return line;
		} else if(shape.equals("Rectangle")) {
			Rectangle rectangle = new Rectangle();
			rectangle.setUpperLeftPoint(new Point(Integer.parseInt(props[0].split("=")[1]),Integer.parseInt(props[1].split("=")[1])));
			try {
				rectangle.setHeight(Integer.parseInt(props[2].split("=")[1]));
				rectangle.setWidth(Integer.parseInt(props[3].split("=")[1]));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"Visina i širina ne mogu da budu negativne","GREŠKA!",JOptionPane.WARNING_MESSAGE);
			}
			rectangle.setOutlineColor(new Color(Integer.parseInt(props[4].split("=")[1])));
			rectangle.setInnerColor(new Color(Integer.parseInt(props[5].split("=")[1])));
			return rectangle;
		} else if(shape.equals("Circle")) {
			Circle circle = new Circle();
			circle.setCenter(new Point(Integer.parseInt(props[0].split("=")[1]),Integer.parseInt(props[1].split("=")[1])));
			try {
				circle.setRadius(Integer.parseInt(props[2].split("=")[1]));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"Polupreènik mora da bude veæi od 0","GREŠKA!",JOptionPane.WARNING_MESSAGE);
			}
			circle.setOutlineColor(new Color(Integer.parseInt(props[3].split("=")[1])));
			circle.setInnerColor(new Color(Integer.parseInt(props[4].split("=")[1])));
			return circle;
		} else if(shape.equals("Donut")) {
			Donut donut = new Donut();
			donut.setCenter(new Point(Integer.parseInt(props[0].split("=")[1]),Integer.parseInt(props[1].split("=")[1])));
			try {
				donut.setRadius(Integer.parseInt(props[2].split("=")[1]));
				donut.setInnerRadius(Integer.parseInt(props[3].split("=")[1]));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"Polupreènici moraju da budu veæi od 0","GREŠKA!",JOptionPane.WARNING_MESSAGE);
			}
			donut.setOutlineColor(new Color(Integer.parseInt(props[4].split("=")[1])));
			donut.setInnerColor(new Color(Integer.parseInt(props[5].split("=")[1])));
			return donut;
		} else if(shape.equals("Hexagon")) {
			HexagonAdapter hexagonAdapter = new HexagonAdapter(
					new Point(Integer.parseInt(props[0].split("=")[1]),Integer.parseInt(props[1].split("=")[1])),
					Integer.parseInt(props[2].split("=")[1]));
			hexagonAdapter.setOutlineColor(new Color(Integer.parseInt(props[3].split("=")[1])));
			hexagonAdapter.setInnerColor(new Color(Integer.parseInt(props[4].split("=")[1])));
			return hexagonAdapter;
		}
		return null;
	}
}
