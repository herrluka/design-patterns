package io;

import java.awt.Color;

import javax.swing.JOptionPane;

import commands.CmdAddShape;
import commands.Command;
import mvc.Model.Circle;
import mvc.Model.Donut;
import mvc.Model.HexagonAdapter;
import mvc.Model.Line;
import mvc.Model.Model;
import mvc.Model.Point;
import mvc.Model.Rectangle;

public class CommandParser {
	
	private String lastString = null;
	private Model model;
	
	public CommandParser(Model model) {
		this.model = model;
	}

	public void parseCommandType(String type){
		
	}
	
	public Command parseCommand(String line) {
		String command = line.split(":")[0];
		if(command.equals("Add")) {
			return parseAdd(line);
		}
		System.out.println(command);
		return null;
	}
	
	private Command parseAdd(String text) {
		String shape = text.split(":")[1];
		String[] props = text.split(",");
		if(shape.equals("Point")) {
			Point point = new Point();
			point.setX(Integer.parseInt(props[0].split("=")[1]));
			point.setY(Integer.parseInt(props[1].split("=")[1]));
			point.setOutlineColor(new Color(Integer.parseInt(props[2].split("=")[1])));
			Command command = new CmdAddShape(point, model);
			System.out.println(command);
		} else if(shape.equals("Line")) {
			Line line = new Line();
			line.setStartPoint(new Point(Integer.parseInt(props[0].split("=")[1]),Integer.parseInt(props[1].split("=")[1])));
			line.setEndPoint(new Point(Integer.parseInt(props[2].split("=")[1]),Integer.parseInt(props[3].split("=")[1])));
			line.setOutlineColor(new Color(Integer.parseInt(props[4].split("=")[1])));
			Command command = new CmdAddShape(line, model);
			System.out.println(command);
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
			Command command = new CmdAddShape(rectangle, model);
			System.out.println(command);
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
			Command command = new CmdAddShape(circle, model);
			System.out.println(command);
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
			Command command = new CmdAddShape(donut, model);
			System.out.println(command);
		} else if(shape.equals("Hexagon")) {
			HexagonAdapter hexagonAdapter = new HexagonAdapter(
					new Point(Integer.parseInt(props[0].split("=")[1]),Integer.parseInt(props[1].split("=")[1])),
					Integer.parseInt(props[2].split("=")[1]));
			hexagonAdapter.setOutlineColor(new Color(Integer.parseInt(props[3].split("=")[1])));
			hexagonAdapter.setInnerColor(new Color(Integer.parseInt(props[4].split("=")[1])));
			Command command = new CmdAddShape(hexagonAdapter, model);
			System.out.println(command);
		}
		return null;
	}
}
