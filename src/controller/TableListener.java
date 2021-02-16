package controller;

import javax.swing.event.*;
import javax.swing.table.TableModel;

/**Handles updates coming from the associated JTable
 * @author klee8
 *
 */
public class TableListener implements TableModelListener{

	@Override
	public void tableChanged(TableModelEvent event) {
		
		switch(event.getType()) {
		case TableModelEvent.INSERT:
			break;
			
		case TableModelEvent.UPDATE:
			
			int row = event.getFirstRow();
			int col = event.getColumn();
			
			TableModel model = (TableModel)event.getSource();
	        String columnName = model.getColumnName(col);
	        Object data = model.getValueAt(row, col);
	        
	        //TODO: DEBUG
	        System.out.println("UPDATE \nColumn: " + columnName + "\nValue: " + data);
	        
	        break;
	        
		case TableModelEvent.DELETE:
			break;
			
		default:
			throw new IllegalArgumentException("Table event type not suppported.");
		}

	}
	
}
