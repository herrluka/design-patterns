package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import commands.Command;
import mvc.Model.Shape;

public class LoadSerialized {
	
	private ArrayList<Shape> list;
	private String path;

	public List<Shape> load() throws FileNotFoundException, IOException, ClassNotFoundException {
		JFileChooser jFileChooser = new JFileChooser(new File("C:\\"));
		jFileChooser.setDialogTitle("Open file");
		int result = jFileChooser.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			path = jFileChooser.getSelectedFile().getAbsolutePath();
			ObjectInputStream is;
			is = new ObjectInputStream(new FileInputStream(path));
			list = (ArrayList<Shape>)is.readObject();
			is.close();
		}
		return list;
	}

	public String getPath() {
		return path;
	}
	
	
}
