package controller.commands;

import java.util.Stack;

public class CommandHistory {
	
	public Stack<Command> redoStack = new Stack<Command> ();
	public Stack<Command> undoStack = new Stack<Command> ();
	
	public void redoLast() {
		if (redoStack.size() > 0) {
			redoStack.pop().redo();
		}
	}
	
	public void undoLast() {
		if (undoStack.size() > 0) {
			undoStack.pop().redo();
		}
	}
	
	public void pushCommandToHistory(Command cmd) {
		undoStack.add(cmd);
		redoStack.clear();
	}
	
}
