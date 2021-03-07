package view.Interfaces;

import java.io.File;

public interface IBRCPanel {

	/**
	 * @param newRow - Object[] following the table's schema
	 */
	public void addRow(Object[] newRow);
	
	/**Prompts the UI to ask the user to select a new file path
	 * @return File - The file path of the selected file
	 */
	public File getFilePath(String prompt);

	/**Set the value at the given coordinate in the UI's model
	 * @param data
	 * @param row
	 * @param col
	 */
	public void setValueAt(Object data, int row, int col);

	
	/**Update the model so that it reflects the specified change.
	 * @param col
	 * @param rowNum
	 * @param data
	 */
	public void updateModel(int col, int rowNum, Object data);
	
}
