package leek.byteShark.model.CRBDataModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import leek.byteShark.model.interfaces.IDataExporter;

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
	
	private String billingParty;
	private String billedParty;
	private LocalDate accountDate;
	private String invoiceNumber;
	private char priceMaster;
	
	private LocalDate invoiceDate;
	private String taxPayerId;
	private int paymentTerms;
	private LocalDate paymentDueDate;


	/**Keeps track of how many lines of data and the total material/labor charges added to this object
	 * @author klee8
	 *
	 */
	private class TrackedTotal {
		private BigDecimal material = new BigDecimal("0");
		private BigDecimal labor = new BigDecimal("0");
		private int lineCount = 0;
		
		//Getters
		public BigDecimal getMaterial() {
			return material;
		}
		public BigDecimal getLabor() {
			return labor;
		}
		public int getLineCount() {
			return lineCount;
		}
		
		//Methods
		public void addLine(BigDecimal material, BigDecimal labor) throws IllegalArgumentException{
			
			if (material == null || labor == null) 
				throw new IllegalArgumentException("Material or Labor line is missing");
			
			lineCount++;
			
			this.material = this.material.add(material);
			this.labor = this.labor.add(labor);
		}
		
		public void addLine(CRBData line) throws IllegalArgumentException{
			
			
			if (line.materialSign == 'C') {
				BigDecimal reverse = new BigDecimal("-1");
				addLine(line.getMaterialCharge().multiply(reverse), line.getLaborCharge().multiply(reverse));
			}
			else {
				addLine(line.getMaterialCharge(), line.getLaborCharge());
			}
			
		}
	}
	
	//Getters & setters
	
	
	
	public IDataExporter<CRBBase> setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
		return this;
	}

	public IDataExporter<CRBBase> setBillingParty(String billingParty) {
		this.billingParty = billingParty;
		return this;
	}

	public IDataExporter<CRBBase> setBilledParty(String billedParty) {
		this.billedParty = billedParty;
		return this;
	}

	public IDataExporter<CRBBase> setAccountDate(LocalDate accountDate) {
		this.accountDate = accountDate;
		return this;
	}

	public IDataExporter<CRBBase> setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
		return this;
	}

	public IDataExporter<CRBBase> setPriceMaster(char priceMaster) {
		this.priceMaster = priceMaster;
		return this;
	}

	public IDataExporter<CRBBase> setTaxPayerId(String taxPayerId) {
		this.taxPayerId = taxPayerId;
		return this;
	}

	public IDataExporter<CRBBase> setPaymentTerms(int paymentTerms) {
		this.paymentTerms = paymentTerms;
		return this;
	}

	public IDataExporter<CRBBase> setPaymentDueDate(LocalDate paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
		return this;
	}
	
	public IDataExporter<CRBBase> setInquiryContact(CRBContactInfo inquiryContact) {
		this.inquiryContact = inquiryContact;
		return this;
	}

	public IDataExporter<CRBBase> setRemitToContact(CRBContactInfo remitToContact) {
		this.remitToContact = remitToContact;
		return this;
	}

	public IDataExporter<CRBBase> setExceptionsContact(CRBContactInfo exceptionsContact) {
		this.exceptionsContact = exceptionsContact;
		return this;
	}

	public IDataExporter<CRBBase> setBillingContact(CRBContactInfo billingContact) {
		this.billingContact = billingContact;
		return this;
	}

	public IDataExporter<CRBBase> setBilledContact(CRBContactInfo billedContact) {
		this.billedContact = billedContact;
		return this;
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
	public void formatForPlainText() throws IllegalArgumentException {
		
		//Clear any existing non data type 1 lines from the collection
		for (CRBBase line: this.dataLines) {
			if(line.getRecordFormat() != 1) {
				this.dataLines.remove(line);
			}
		}
		
		if (this.dataLines.size() <= 0 ) throw new IllegalArgumentException("No Record format 1 lines in this collection");
		
		//Check that all contact information is included
		if 		(this.inquiryContact == null) 		throw new IllegalArgumentException("inquiryContact for this object has not been set");
		else if (this.remitToContact == null) 		throw new IllegalArgumentException("remitToContact for this object has not been set");
		else if (this.billedContact == null) 		throw new IllegalArgumentException("billedContact for this object has not been set");
		else if (this.billingContact == null) 		throw new IllegalArgumentException("billingContact for this object has not been set");
		else if (this.exceptionsContact == null) 	throw new IllegalArgumentException("exceptionsContact for this object has not been set");
		
		//Sum all data lines
		Map <String, TrackedTotal> subTotals = new HashMap<String, TrackedTotal>();
		TrackedTotal grandTotal = new TrackedTotal();
		
		for (CRBBase line: this.dataLines) {
			
			CRBData dataLine = (CRBData) line;
			String detailSource = (dataLine).getDetailSource();
			
			//if not already defined add a sub total to the map
			if (!subTotals.containsKey(detailSource)) {
				subTotals.put(detailSource, new TrackedTotal());
			}

			/*TODO: DEBUG
			StringBuilder sb = new StringBuilder("Line: ");
			sb.append(dataLine.lineNumber);
			sb.append(" Material: ");
			sb.append(dataLine.getMaterialCharge());
			sb.append(" Labor: ");
			sb.append(dataLine.getLaborCharge());
			System.out.println(sb);
			*/
			
			//Record the next record to the appropriate sub total
			subTotals.get(detailSource).addLine(dataLine);
			
			//Record overall sums
			grandTotal.addLine(dataLine);
		}
		
		//Add sub total lines to object
		for (String key: subTotals.keySet()) {
			
			CRBSummary record8 = new CRBSummary();
			
			TrackedTotal total = subTotals.get(key);
			
			record8.setDetailSource(key);
			record8.setRecordCount(total.getLineCount());
			record8.setLaborCharge(total.getLabor());
			record8.setMaterialCharge(total.getMaterial());
			
			if (total.getMaterial().compareTo(BigDecimal.ZERO) < 0 || total.getLabor().compareTo(BigDecimal.ZERO) < 0) 
				record8.setSign('C');
			else 
				record8.setSign('D');
			
			record8.setInvoiceDate(invoiceDate);;
			record8.setPaymentTerms(paymentTerms);
			record8.setPaymentDueDate(paymentDueDate);
			
			this.dataLines.add(record8);
		}
		
		//Add Contact Lines to object
		this.dataLines.add (inquiryContact);
		this.dataLines.add (remitToContact);
		this.dataLines.add (exceptionsContact);
		this.dataLines.add (billingContact);
		this.dataLines.add (billedContact);
		
		//Add total Line to object
		CRBSummary record8 = new CRBSummary();
		
		record8.setDetailSource("ZZ");
		record8.setRecordCount(grandTotal.getLineCount());
		record8.setLaborCharge(grandTotal.getLabor());
		record8.setMaterialCharge(grandTotal.getMaterial());
		
		if (grandTotal.getMaterial().compareTo(BigDecimal.ZERO) < 0 || grandTotal.getLabor().compareTo(BigDecimal.ZERO) < 0) 
			record8.setSign('C');
		else 
			record8.setSign('D');
		
		record8.setInvoiceDate(invoiceDate);;
		record8.setTaxPayerId(taxPayerId);
		record8.setPaymentTerms(paymentTerms);
		record8.setPaymentDueDate(paymentDueDate);
		
		this.dataLines.add (record8);
		
		//Add summary line to object
		CRBTotal record9 = new CRBTotal();
		
		record9.setRecordCount(grandTotal.getLineCount());
		record9.setLaborCharge(grandTotal.getLabor());
		record9.setMaterialCharge(grandTotal.getMaterial());
		
		if (grandTotal.getMaterial().compareTo(BigDecimal.ZERO) < 0 || grandTotal.getLabor().compareTo(BigDecimal.ZERO) < 0) 
			record9.setSign('C');
		else 
			record9.setSign('D');
		
		this.dataLines.add(record9);
		
		//Standardize line attributes from base
		for (CRBBase line: this.dataLines) {
			line.setAccountDate(accountDate);
			line.setBilledParty(billedParty);
			line.setBillingParty(billingParty);
			line.setInvoiceNumber(invoiceNumber);
			line.setPriceMaster(priceMaster);
		}
		
	}


}