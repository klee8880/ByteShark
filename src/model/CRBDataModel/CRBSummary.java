package model.CRBDataModel;

import java.math.BigDecimal;
import java.time.LocalDate;

/**Record Format 8: Data line that summary of a set of detail lines
 * Detail source ZZ is the summary of the total invoice
 * All other detail sources is the summary of the detail source.
 * @author klee8
 *
 */
public class CRBSummary extends CRBBase{


	protected String detailSource;				//31-32
	protected int recordCount;					//33-39
	protected BigDecimal laborCharge;			//40-49
	protected BigDecimal materialCharge;		//50-65
	protected char sign;						//66
	protected LocalDate invoiceDate;			//67-72
	protected String taxPayerId;				//73-87
	protected int paymentTerms;					//88-89
	protected LocalDate paymentDueDate;			//90-95
	
	//Constructor
	public CRBSummary(String input) {
		super(input);
		
		
		
		this.detailSource = input.substring(30, 32);
		this.recordCount = Integer.parseInt(input.substring(32, 39));
		
		BigDecimal divider = new BigDecimal("100");
		this.laborCharge = new BigDecimal(input.substring(39,49)).divide(divider);
		this.materialCharge = new BigDecimal(input.substring(49,65)).divide(divider);
		
		this.sign = input.charAt(65);
		
		try {
		this.invoiceDate = LocalDate.of(
				2000 + Integer.parseInt(input.substring(66, 68)), 
				Integer.parseInt(input.substring(68, 70)), 
				Integer.parseInt(input.substring(70, 72)));
		} catch(NumberFormatException ex) {
			this.invoiceDate = null;
		}
		
		this.taxPayerId = input.substring(72, 87);
		this.paymentTerms = Integer.parseInt(input.substring(87, 89));
		
		try {
		this.paymentDueDate = LocalDate.of(
				2000 + Integer.parseInt(input.substring(89, 91)), 
				Integer.parseInt(input.substring(91, 93)), 
				Integer.parseInt(input.substring(93, 95)));
		} catch(NumberFormatException ex) {
			this.paymentDueDate = null;
		}
		
		
	}

	public CRBSummary() {
	}
	
	//Methods
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		
		sb.append(this.detailSource)
		.append(String.format("%07d", this.recordCount))
		.append(convertBigDecimal("%010d", this.laborCharge))
		.append(convertBigDecimal("%016d", this.materialCharge))
		.append(sign)
		.append(covertDateYYMMDD(this.invoiceDate))
		.append(String.format("%-15s", this.taxPayerId))
		.append(String.format("%2d", this.paymentTerms))
		.append(covertDateYYMMDD(this.paymentDueDate));
		
		for (int i = 0; i < 405; i++) {
			sb.append(' ');
		}
		
		return sb.toString();
	}
}
