package mvc.Model;

import java.util.List;
import java.util.ArrayList;

import mvc.Model.Shape;

public class Model {

	private List<Shape> list = new ArrayList<Shape>();
	
	public List<Shape> getShapes(){
		return list;
	}
	
	public void add(Shape shape) {
		list.add(shape);
	}
	
	public void addOnPosition(Shape shape, int index) {
		list.add(index,shape);
	}
	
	public void remove(Shape shape) {
		list.remove(shape);
	}
	
	public void removeAll() {
		list.clear();
	}
	
	public Shape get(int index) {
		return list.get(index);
	}
	
	public void set(List<Shape> list) {
		this.list = list;
	}
	
	public void swap(int index1, int index2) {
		Shape helper = list.get(index1);
		list.set(index1,list.get(index2));
		list.set(index2,helper);
		for(Shape s : list) {
			System.out.println(s.toString());
		}
	}
}
