package io;

public class SaveManager implements Save{

	private Save saver;
	
	public SaveManager(Save saver) {
		this.saver = saver;
	}
	
	public void setSaver(Save saver) {
		this.saver = saver;
	}
	
	@Override
	public String saveAs() {
		return saver.saveAs();
	}

}
