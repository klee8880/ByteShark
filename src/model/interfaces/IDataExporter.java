package model.interfaces;

import java.time.LocalDate;

import model.CRBDataModel.CRBBase;
import model.CRBDataModel.CRBContactInfo;

/**Data Formatter with extended functionality to format data for output as a standardized file.
 * @author klee8
 *
 * @param <T> - The data type you plan on exporting to.
 */
public interface IDataExporter <T> extends IDataFormatter <T>{

	/**Format a set of CRB data to be readable in 500 byte format on output.
	 * If any non record format 1 lines exist on this collection before running this function they will be removed and replaced.
	 * @throws IllegalArgumentException
	 */
	public void formatForPlainText()throws IllegalArgumentException;
	
	public IDataExporter <T> setInquiryContact(CRBContactInfo inquiryContact);

	public IDataExporter <T> setRemitToContact(CRBContactInfo remitToContact);

	public IDataExporter <T> setExceptionsContact(CRBContactInfo exceptionsContact);

	public IDataExporter <T> setBillingContact(CRBContactInfo billingContact);

	public IDataExporter <T> setBilledContact(CRBContactInfo billedContact);
	
	public IDataExporter <T> setInvoiceDate(LocalDate invoiceDate);

	public IDataExporter <T> setTaxPayerId(String taxPayerId);

	public IDataExporter <T> setPaymentTerms(int paymentTerms);

	public IDataExporter <T> setPaymentDueDate(LocalDate paymentDueDate);
	
	public IDataExporter <T> setBillingParty(String billingParty);

	public IDataExporter <T> setBilledParty(String billedParty);

	public IDataExporter <T> setAccountDate(LocalDate accountDate);
	
	public IDataExporter <T> setInvoiceNumber(String invoiceNumber);

	public IDataExporter <T> setPriceMaster(char priceMaster);
}
