package controller.commands;

public abstract class Command {

	/**Redo this command in both the data model and UI
	 * 
	 */
	public abstract void redo();
	
	/**Undo this command in both the data model and UI
	 * 
	 */
	public abstract void undo();
	
	/**Change the data only since change was triggered from the UI
	 * 
	 */
	public abstract void updateData();
}
