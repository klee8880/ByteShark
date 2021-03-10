package model.CRBDataModel;

import java.math.BigDecimal;
import java.time.LocalDate;

/**Record Format 8: Data line that summariezes data lines that appear above it in the data file up to the previous summary line.
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
	protected int paymentterms;					//88-89
	protected LocalDate paymentDueDate;			//90-95
	
	//Constructor
	public CRBSummary(String input) {
		super(input);
	}

	public CRBSummary() {
		// TODO Auto-generated constructor stub
	}
}
