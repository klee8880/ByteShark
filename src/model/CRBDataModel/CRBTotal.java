package model.CRBDataModel;

import java.math.BigDecimal;

/**Record Format 9: The final line of a CRB data set that summarizes all the lines above it. Acts as the checksum on the file.
 * @author klee8
 *
 */
public class CRBTotal extends CRBBase{

	protected int recordCount;				//33-39
	protected BigDecimal laborCharge;		//40-49
	protected BigDecimal materialCharge;	//50-65
	protected char sign;					//66
	
	public CRBTotal(String input) {
		super(input);
		
		this.recordCount = Integer.parseInt(input.substring(32, 39));
		
		BigDecimal divider = new BigDecimal("100");
		this.laborCharge = new BigDecimal(input.substring(39,49)).divide(divider);
		this.materialCharge = new BigDecimal(input.substring(49,65)).divide(divider);
		
		this.sign = input.charAt(65);
	}

	//Getters & Setters
	
	public CRBTotal() {
		super();
		this.recordFormat = 9;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public BigDecimal getLaborCharge() {
		return laborCharge;
	}

	public void setLaborCharge(BigDecimal laborCharge) {
		this.laborCharge = laborCharge;
	}

	public BigDecimal getMaterialCharge() {
		return materialCharge;
	}

	public void setMaterialCharge(BigDecimal materialCharge) {
		this.materialCharge = materialCharge;
	}

	public char getSign() {
		return sign;
	}

	public void setSign(char sign) {
		this.sign = sign;
	}
	
	//Methods
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.recordFormat);
		
		sb.append(this.billingParty);
		
		//Padding
		sb.append("ZZZZ");
		
		sb.append(this.accountDate.getYear() - 2000);
		sb.append(String.format("%02d", this.accountDate.getMonthValue()));
		
		//Padding
		sb.append("ZZZZZZZZZZZZZZZZZZZ");
		
		sb.append(String.format("%07d", this.recordCount));
		
		sb.append(convertBigDecimal("%010d", this.laborCharge));
		sb.append(convertBigDecimal("%016d", this.materialCharge));
		sb.append(this.sign);
		
		//Padding
		for (int i = 0; i < 434; i++) sb.append(' ');
		
		return sb.toString();
	}
	
}
