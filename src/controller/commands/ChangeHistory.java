package controller.commands;


import java.util.Stack;

public class ChangeHistory {
	
	private Stack<Command> undoStack = new Stack<Command>();
	private Stack<Command> redoStack = new Stack<Command>();
	
	/**Adds a command to the undo/redo structure. Adding a new item to the undo stack clears any commands in the redo stack since they no longer apply to the current timeline.
	 * @param nextCommand - Command that was just performed
	 */
	public void queueCommand(Command nextCommand) {
		undoStack.add(nextCommand);
		redoStack.clear();
	}
	
	/**Prompts the latest command on the undo log to be reversed in both the data model and the UI if applicable. Commands that are undone are then added to the redo stack in case the user wishes to fast forward later
	 * 
	 */
	public void undo() {
		if (undoStack.empty()) return;
		Command cmd = undoStack.pop();
		
		cmd.undo();
		
		redoStack.add(cmd);
	}
	
	public void redo() {
		if (redoStack.empty()) return;
		Command cmd = redoStack.pop();
		
		cmd.redo();
		
		undoStack.add(cmd);
	}
	
}
