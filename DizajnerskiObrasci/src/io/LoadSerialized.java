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

	public List<Shape> load() {
		JFileChooser jFileChooser = new JFileChooser(new File("C:\\"));
		jFileChooser.setDialogTitle("Otvorite datoteku");
		int result = jFileChooser.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			String path = jFileChooser.getSelectedFile().getAbsolutePath();
			ObjectInputStream is;
			try {
				is = new ObjectInputStream(new FileInputStream(path));
				list = (ArrayList<Shape>)is.readObject();
				is.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,"Datoteka nije pronaðena","GREŠKA!",JOptionPane.WARNING_MESSAGE);
				return null;
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"Datoteka nije pronaðena","GREŠKA!",JOptionPane.WARNING_MESSAGE);
				return null;
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null,"Nije moguæe da se uèita datoteka.","GREŠKA!",JOptionPane.WARNING_MESSAGE);
				return null;
			}
		}
		return list;
	}
}
