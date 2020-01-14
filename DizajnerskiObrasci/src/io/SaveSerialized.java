package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import mvc.Model.Shape;

public class SaveSerialized implements Save {
	
	private List<Shape> list;
	
	public SaveSerialized() {
		
	}

	public SaveSerialized(List<Shape> list) {
		this.list = list;
	}

	@Override
	public String saveAs() {
		JFileChooser jFileChooser = new JFileChooser(new File("D:\\"));
		jFileChooser.setDialogTitle("Save file");
		int result = jFileChooser.showSaveDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			String path = jFileChooser.getSelectedFile().getAbsolutePath();
			try {
				ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(path));
				os.writeObject(list);	
				os.close();
				return path;
			}catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,"File not saved","Error",JOptionPane.WARNING_MESSAGE);
				return null;
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"File not saved","Error",JOptionPane.WARNING_MESSAGE);
				return null;
			}
		} else {
			return null;
		}
	}
	
	public void setList(List<Shape> list) {
		this.list = list;
	}

	@Override
	public void save(String path, List<Object> listOfObjects) {

		List<Shape> listOfShapes = new ArrayList<Shape>();
		for(Object o : listOfObjects) {
			listOfShapes.add((Shape)o);
		}
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(path));
			os.writeObject(listOfShapes);
			os.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"File not found","Error",JOptionPane.WARNING_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"File not found","Error",JOptionPane.WARNING_MESSAGE);
		}
	}


	
	

}
