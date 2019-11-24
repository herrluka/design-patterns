package commands;

public interface Command {
	
	/**
	 * Interface that has to be implemeted to be able to provide UNDO-REDO function
	 * 
	 */
	
	void execute();
	void unexecute();

}
