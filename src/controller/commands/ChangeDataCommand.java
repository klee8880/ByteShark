package controller.commands;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import model.CRBDataModel.CRBData;
import view.interfaces.IBRCPanel;

public class ChangeDataCommand extends Command{

	int col;
	int row;
	CRBData data;
	Object oldValue;
	Object newValue;
	List<IBRCPanel> panels;

	
	public ChangeDataCommand(int col, int row, CRBData data, Object newValue, List<IBRCPanel> panels) {
		super();
		this.col = col;
		this.row = row;
		this.data = data;
		this.newValue = newValue;
		this.panels = panels;
		
		//Determine the old value based on the column that was changed.
		switch(col) {
        	//TODO: row #s should not be editable
        case 1:
        	oldValue = data.getLocation();
        	break;
        case 2:
        	oldValue = data.getQuantity();
        	break;
        case 3:
        	oldValue = data.getConditionCode();
        	break;
        case 4:
        	oldValue = data.getAppliedJobCode();
        	break;
        case 5:
        	oldValue = data.getNarrative();
        	break;
        case 6:
        	oldValue = data.getRemovedJobCode();
        	break;
        case 7:
        	oldValue = data.getWhyMadeCode();
        	break;
        case 8:
        	oldValue = data.getResponsabilityCode();
        	break;
        case 9:
        	oldValue = data.getLaborCharge();
        	break;
        case 10:
        	oldValue = data.getMaterialCharge();
        	break;
        case 11:
        	//TODO: figure out how to handle changes to total since it is affected by 8 & 9
        	break;
        default:
        	throw new IllegalArgumentException("Unsupported row edit requested");
        }
	}

	@Override
	public void redo() {
		setTable(col, newValue);
		setModel(row, col, newValue);
		totalRowUpdate(row,col);
	}

	@Override
	public void undo() {
		setTable(col, oldValue);
		setModel(row, col, oldValue);
		totalRowUpdate(row,col);
	}

	@Override
	public void update() {
		setTable(col, newValue);
		totalRowUpdate(row,col);
	}
	
	private void setModel(int row, int col, Object value) {
		for (IBRCPanel panel: panels) {
			panel.updateModel(col, row, value);
		}
	}
	
	private void totalRowUpdate(int row, int col) {
		if (col == 9 || col == 10) {
			BigDecimal total = data.getMaterialCharge().add(data.getLaborCharge());
			setModel(row, 11, total);
		}
	}
	
	/**Changes the values on the data model based on the translation from the UI
	 * 
	 */
	private void setTable(int col, Object value) {
		
        switch(col) {
        //TODO: row #s should not be editable
        case 1:
        	data.setLocation((String) value);
        	break;
        case 2:
        	data.setQuantity((int) value);
        	break;
        case 3:
        	data.setConditionCode((short) value);
        	break;
        case 4:
        	data.setAppliedJobCode(value.toString());
        	break;
        case 5:
        	data.setNarrative(value.toString());
        	break;
        case 6:
        	data.setRemovedJobCode(value.toString());
        	break;
        case 7:
        	data.setWhyMadeCode((short) value);
        	break;
        case 8:
        	data.setResponsabilityCode((short) value);
        	break;
        case 9:
        	data.setLaborCharge((BigDecimal) value);
        	break;
        case 10:
        	data.setMaterialCharge((BigDecimal) value);
        	break;
        default:
        	throw new IllegalArgumentException("Unsupported row edit requested");
        }

	}

}

















