package model.CRBDataModel;

import java.io.IOException;
import java.util.List;

import model.interfaces.IDataExporter;

/**Modification of Data ingestor that only takes type 1 CRB lines.
 * Additional functionality to add summary, sub total, and contact information lines to prepare file for output.
 * @author klee8
 * TODO: (Technical Debt) Change CRB line imports to deep copy data instead of references. 
 */
public class CRBDataExporter extends CRBDataIngestor implements IDataExporter <CRBBase>{

	private CRBContactInfo inquiryContact;
	private CRBContactInfo remitToContact;
	private CRBContactInfo exceptionsContact;
	private CRBContactInfo billingContact;
	private CRBContactInfo billedContact;
	
	//Getters & setters
	
	public void setInquiryContact(CRBContactInfo inquiryContact) {
		this.inquiryContact = inquiryContact;
	}

	public void setRemitToContact(CRBContactInfo remitToContact) {
		this.remitToContact = remitToContact;
	}

	public void setExceptionsContact(CRBContactInfo exceptionsContact) {
		this.exceptionsContact = exceptionsContact;
	}

	public void setBillingContact(CRBContactInfo billingContact) {
		this.billingContact = billingContact;
	}

	public void setBilledContact(CRBContactInfo billedContact) {
		this.billedContact = billedContact;
	}

	//Methods
	
	@Override
	public void appendFromString(String input) throws IllegalArgumentException {
		
		//Check argument length for incorrect data file.
		if (input.length() != 500) throw new IllegalArgumentException("Incorrect format: Not 500 characters per line");
		
		//Check to ensure only record type 1
		if (input.substring(0, 1).equals("1")) throw new IllegalArgumentException("Incorrect format: Not record type 1");

		super.appendFromString(input);
		
	}

	@Override
	public void appendFromString(List <String> input) throws IllegalArgumentException {
		
		for (String line: input) {
			this.appendFromString(line);
		}
	}
	
	@Override
	public void appendFromDataLine(CRBBase input) throws IllegalArgumentException{
		
		//Check to ensure only record type 1
		if (input.getRecordFormat() != 1) throw new IllegalArgumentException("Incorrect format: Not record type 1");
		
		super.appendFromDataLine(input);
	}

	@Override
	public void appendFromDataLine(List<CRBBase> input) throws IllegalArgumentException{
		for (CRBBase line: input) {
			this.appendFromDataLine(line);;
		}
	}
	
	@Override
	public void formatForPlainText() {
		
		//Check that all lines are record type 1
		
		//Check that all contact information is included
		
		//Sum all data lines
		
		//Add subtotal lines
		
		//Add total Line
		
		//Add summary line
		
		
		
	}


}
