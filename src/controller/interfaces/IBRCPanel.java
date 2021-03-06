package controller.interfaces;

public interface IBRCPanel {

	/**Set the value at the given coordinate in the UI's model
	 * @param data
	 * @param row
	 * @param col
	 */
	public void setValueAt(Object data, int row, int col);
	
}
