package controller;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.event.*;
import javax.swing.table.TableModel;

import model.CRBDataModel.CRBBase;

/**Handles updates coming from the associated JTable
 * @author klee8
 *
 */
public class TableListener implements TableModelListener{
	
	private CommandManager manager;
	
	public TableListener(CommandManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public void tableChanged(TableModelEvent event) {
		
		switch(event.getType()) {
		case TableModelEvent.INSERT:
			break;
			
		case TableModelEvent.UPDATE:
			
			int row = event.getFirstRow();
			int col = event.getColumn();
			
			TableModel model = (TableModel)event.getSource();
	        Object data = model.getValueAt(row, col);
	        
	        System.out.println("UPDATE \nRow: " + row + "\nColumn: " + col + "\nValue: " + data);
	        
	        row = ((int) model.getValueAt(row, 0)) - 1;

	        manager.changeData(col, row, data, model);
	        
	        break;
	        
		case TableModelEvent.DELETE:
			
			break;
			
		default:
			throw new IllegalArgumentException("Table event type not suppported.");
		}

	}
	
}
