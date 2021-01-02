package model.CRBDataModel;

/**Record Format 1: Normal AAR compliant Dataset line.
 * @author klee8
 *
 */
public class CRBGeneralData extends CRBData {

	protected String narrative;					//167-216

	//Constructors
	public CRBGeneralData() {
		super();
	}
	
	public CRBGeneralData(String input) {
		super(input);
		this.narrative = input.substring(166,216).trim();
	}

	//Getters & Setters
	public String getNarrative() {
		return narrative;
	}

	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}
	
}
