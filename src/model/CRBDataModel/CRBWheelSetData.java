package model.CRBDataModel;

/**Record Format 1: Raillinc compliant dataset line for data involving wheel replacements.
 * @author klee8
 *
 */
public class CRBWheelSetData extends CRBData {
	
	protected String WheelNarrative;			//167-194
	protected String appliedWheelDate;			//195-198
	protected String manufacturingCode;			//199-200
	protected char wheelClassCode;				//201
	protected int appliedSideReading;			//202-203
	protected float appliedFingerReading;		//204-205
	protected String removedWheelDate;			//206-209
	protected String removedWheelManufacture;	//210-211
	protected char removedWheelClass;			//212
	protected int removedSideReadings;			//213-214
	protected int removedFingerReadings;		//215-216
	
}
