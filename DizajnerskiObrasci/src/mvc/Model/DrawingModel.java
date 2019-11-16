package mvc.Model;

import java.util.List;
import java.util.ArrayList;

import mvc.Model.Shape;

public class DrawingModel {

	private List<Shape> list = new ArrayList<Shape>();
	
	public List<Shape> getShapes(){
		return list;
	}
	
	public void add(Shape shape) {
		list.add(shape);
	}
	
	public void remove(Shape shape) {
		list.remove(shape);
	}
	
	public Shape get(int index) {
		return list.get(index);
	}
}
