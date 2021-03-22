package model.interfaces;

import java.time.LocalDate;

import model.CRBDataModel.CRBBase;
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
	public IDataExporter<CRBBase> setInvoiceDate(LocalDate invoiceDate);

	public IDataExporter<CRBBase> setBillingParty(String billingParty);

	public IDataExporter<CRBBase> setBilledParty(String billedParty);

	public IDataExporter<CRBBase> setAccountDate(LocalDate accountDate);

	public IDataExporter<CRBBase> setInvoiceNumber(String invoiceNumber);

	public IDataExporter<CRBBase> setPriceMaster(char priceMaster);

	public IDataExporter<CRBBase> setTaxPayerId(String taxPayerId);

	public IDataExporter<CRBBase> setPaymentTerms(int paymentTerms);

	public IDataExporter<CRBBase> setPaymentDueDate(LocalDate paymentDueDate);
	
	public IDataExporter<CRBBase> setInquiryContact(CRBContactInfo inquiryContact);

	public IDataExporter<CRBBase> setRemitToContact(CRBContactInfo remitToContact);

	public IDataExporter<CRBBase> setExceptionsContact(CRBContactInfo exceptionsContact);

	public IDataExporter<CRBBase> setBillingContact(CRBContactInfo billingContact);

	public IDataExporter<CRBBase> setBilledContact(CRBContactInfo billedContact);

	void formatForPlainText() throws IllegalArgumentException;
}
