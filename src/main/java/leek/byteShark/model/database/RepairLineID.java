package leek.byteShark.model.database;

import java.io.Serializable;

import lombok.Data;

@Data
public class RepairLineID implements Serializable{

	private static final long serialVersionUID = 1L;
	private int shoppingId;
	private int lineNumber;
	
	public RepairLineID() {
		
	}
	
	public RepairLineID(int shoppingId, int lineNumber) {
		super();
		this.shoppingId = shoppingId;
		this.lineNumber = lineNumber;
	}
	
}
