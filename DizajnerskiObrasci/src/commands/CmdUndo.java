package commands;

public class CmdUndo implements Command{

	Command command;
	
	public CmdUndo() {
	
	}

	public CmdUndo(Command command) {
		this.command = command;
	}

	@Override
	public void execute() {
		command.unexecute();
		
	}

	@Override
	public void unexecute() {
		command.execute();
		
	}
	
	@Override
	public String toString() {
		return "Undo";
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	
}
