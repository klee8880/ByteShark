package controller.commands;

public abstract class Command {

	public abstract void undo();
	public abstract void redo();
	public abstract void run();
	
}
