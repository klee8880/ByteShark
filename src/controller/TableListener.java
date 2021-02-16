package controller;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.event.*;
import javax.swing.table.TableModel;

import model.CRBDataModel.CRBGeneralData;
import model.CRBDataModel.CRBLine;

/**Handles updates coming from the associated JTable
 * @author klee8
 *
 */
public class TableListener implements TableModelListener{
	
	private ArrayList<CRBGeneralData> brc;

	public TableListener(ArrayList<CRBGeneralData> brc) {
		super();
		this.brc = brc;
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
	        String columnName = model.getColumnName(col);
	        Object data = model.getValueAt(row, col);
	        
	        //TODO: DEBUG
	        System.out.println("UPDATE \nColumn: " + col + "\nValue: " + data);
	        
	        //TODO: Change the background data to match the view
	        row = ((int) model.getValueAt(row, 0)) - 1;
	        
	        switch(col) {
	        case 1:
	        	brc.get(row).setLocation((String) data);
	        	break;
	        case 2:
	        	brc.get(row).setQuantity((int) data);
	        	break;
	        case 3:
	        	brc.get(row).setConditionCode((short) data);
	        	break;
	        case 4:
	        	brc.get(row).setAppliedJobCode(data.toString());
	        	break;
	        case 5:
	        	brc.get(row).setNarrative(data.toString());
	        	break;
	        case 6:
	        	brc.get(row).setRemovedJobCode(data.toString());
	        	break;
	        case 7:
	        	brc.get(row).setWhyMadeCode(data.toString());
	        	break;
	        case 8:
	        	brc.get(row).setResponsabilityCode((short) data);
	        	break;
	        case 9:
	        	brc.get(row).setLaborCharge((BigDecimal) data);
	        	break;
	        case 10:
	        	brc.get(row).setMaterialCharge((BigDecimal) data);
	        	break;
	        case 11:
	        	//TODO: figure out how to handle changes to total since it is affected by 8 & 9
	        	break;
	        default:
	        	throw new IllegalArgumentException("Unsupported row edit requested");
	        }
	        
	        break;
	        
		case TableModelEvent.DELETE:
			break;
			
		default:
			throw new IllegalArgumentException("Table event type not suppported.");
		}

	}
	
}
