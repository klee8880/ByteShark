package model.interfaces;

import model.CRBDataModel.CRBContactInfo;

/**Data Formatter with extended functionality to format data for output as a standardized file.
 * @author klee8
 *
 * @param <T>
 */
public interface IDataExporter <T> extends IDataFormatter <T>{

	/**Format the data so that it can be written to a plain text file appropriate to the datatype
	 * 
	 */
	public void formatForPlainText();
	
	public void setInquiryContact(CRBContactInfo inquiryContact);

	public void setRemitToContact(CRBContactInfo remitToContact);

	public void setExceptionsContact(CRBContactInfo exceptionsContact);

	public void setBillingContact(CRBContactInfo billingContact);

	public void setBilledContact(CRBContactInfo billedContact);
}
