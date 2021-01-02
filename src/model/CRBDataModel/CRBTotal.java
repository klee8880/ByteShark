package model.CRBDataModel;

import java.math.BigDecimal;

/**Record Format 9: The final line of a CRB data set that summarizes all the lines above it. Acts as the checksum on the file.
 * @author klee8
 *
 */
public class CRBTotal extends CRBLine{

	protected int recordCount;				//33-39
	protected BigDecimal laborCharge;		//40-49
	protected BigDecimal materialCharge;	//50-65
	protected char sign;					//66
	
	public CRBTotal(String input) {
		super(input);
		
	}
}
