package controller.commands;

import java.math.BigDecimal;
import java.util.ArrayList;

import model.CRBDataModel.CRBGeneralData;
import view.BRCPanel.IBRCPanel;

public class ChangeDataCommand extends Command{

	int columnUpdated;
	CRBGeneralData data;
	Object oldValue;
	Object newValue;
	ArrayList<IBRCPanel> panels;

	
	public ChangeDataCommand(int columnUpdated, CRBGeneralData data,ArrayList<IBRCPanel> panels , Object newValue) {
		super();
		this.columnUpdated = columnUpdated;
		this.data = data;
		this.newValue = newValue;
		this.panels = panels;
		
		//Determine the old value based on the column that was changed.
		switch(columnUpdated) {
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
		setTable(columnUpdated, newValue);
		//TODO: Update the UI(s)
	}

	@Override
	public void undo() {
		setTable(columnUpdated, oldValue);
		//TODO: Update the UI(s)
	}

	@Override
	public void updateData() {
		setTable(columnUpdated, newValue);
		
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
        	data.setWhyMadeCode(value.toString());
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
        case 11:
        	//TODO: figure out how to handle changes to total since it is affected by 8 & 9
        	break;
        default:
        	throw new IllegalArgumentException("Unsupported row edit requested");
        }

	}

}

















