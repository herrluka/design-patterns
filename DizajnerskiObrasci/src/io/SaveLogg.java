package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SaveLogg implements Save {

	private List<String> list;

	public SaveLogg() {
	
	}
	
	public SaveLogg(List<String> list) {
		this.list = list;
	}

	@Override
	public String saveAs() {
		JFileChooser jFileChooser = new JFileChooser(new File("c:\\"));
		jFileChooser.setDialogTitle("Saèuvajte datoteku");
		int result = jFileChooser.showSaveDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			String path = jFileChooser.getSelectedFile().getAbsolutePath() + ".txt";
			try {
				FileWriter fw = new FileWriter(path);
				for(String s: list) {
					fw.write(s + System.lineSeparator());
				}
				fw.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"Neuspešno èuvanje datoteke","GREŠKA!",JOptionPane.WARNING_MESSAGE);
				return null;
			}
			return path;
		} else {
			return null;
		}
		
	}
	
	public void setList(List<String> list) {
		this.list = list;
	}


	@Override
	public void save(String path, List<Object> list) {
		try {
			FileWriter fw = new FileWriter(path);
			for(Object o: list) {
				fw.write((String)o + System.lineSeparator());
			}
			fw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Datoteka nije pronaðena","GREŠKA!",JOptionPane.WARNING_MESSAGE);
		}
		
	}


	
	
	

}
