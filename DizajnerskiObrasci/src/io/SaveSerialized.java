package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import mvc.Model.Shape;

public class SaveSerialized implements Save {
	
	private List<Shape> list;
	private String path;

	public SaveSerialized(List<Shape> list) {
		this.list = list;
	}

	@Override
	public String saveAs() {
		JFileChooser jFileChooser = new JFileChooser(new File("c:\\"));
		jFileChooser.setDialogTitle("Saèuvajte datoteku");
		int result = jFileChooser.showSaveDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			path = jFileChooser.getSelectedFile().getAbsolutePath();
			try {
				ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(path));
				os.writeObject(list);	
				os.close();
				return path;
			}catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,"Datoteka nije pronaðena","GREŠKA!",JOptionPane.WARNING_MESSAGE);
				return null;
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"Datoteka nije pronaðena","GREŠKA!",JOptionPane.WARNING_MESSAGE);
				return null;
			}
		} else {
			return null;
		}
	}
	
	public void setList(List<Shape> list) {
		this.list = list;
	}

//	@Override
//	public void save() {
//
//		try {
//			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(path));
//			os.writeObject(list);
//		} catch (FileNotFoundException e) {
//			JOptionPane.showMessageDialog(null,"Datoteka nije pronaðena","GREŠKA!",JOptionPane.WARNING_MESSAGE);
//		} catch (IOException e) {
//			JOptionPane.showMessageDialog(null,"Datoteka nije pronaðena","GREŠKA!",JOptionPane.WARNING_MESSAGE);
//		}
//	}


	
	

}
