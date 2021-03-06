package leek.byteShark.model.CRBDataModel;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class CRBBase implements Comparable <CRBBase> {

	public int recordFormat; // position 1
	protected String billingParty;				//2-5
	protected String billedParty;				//6-9
	protected LocalDate accountDate;			//10-13
	protected String invoiceNumber;				//14-29
	protected char priceMaster;					//30
	
	//Constructor
	public CRBBase() {
		
	}
	
	public CRBBase(String input) {
		recordFormat = Integer.parseInt(input.substring(0, 1));
		billingParty = input.substring(1, 5);
		billedParty = input.substring(5, 9);
		accountDate = LocalDate.of(
				Integer.parseInt("20" + input.substring(9, 11)),
				Integer.parseInt(input.substring(11, 13)), 
				1);
		invoiceNumber = input.substring(13, 29).trim();
		priceMaster = input.charAt(29);
	}
	
	//Getters & Setters
	public int getRecordFormat() {
		return recordFormat;
	}

	public void setRecordFormat(int recordFormat) {
		this.recordFormat = recordFormat;
	}

	public String getBillingParty() {
		return billingParty;
	}

	public void setBillingParty(String billingParty) {
		this.billingParty = billingParty;
	}

	public String getBilledParty() {
		return billedParty;
	}

	public void setBilledParty(String billedParty) {
		this.billedParty = billedParty;
	}

	public LocalDate getAccountDate() {
		return accountDate;
	}

	public void setAccountDate(LocalDate accountDate) {
		this.accountDate = accountDate;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public char getPriceMaster() {
		return priceMaster;
	}

	public void setPriceMaster(char priceMaster) {
		this.priceMaster = priceMaster;
	}

	//Methods
	@Override
	public int compareTo(CRBBase o) {
		return o.getRecordFormat() - this.getRecordFormat();
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		//Record Format
		sb.append(this.recordFormat);
		
		//Billing party
		sb.append(this.billingParty);
		
		//Billed party
		sb.append(this.billedParty);
		
		//Account date
		sb.append(this.accountDate.getYear() - 2000);
		sb.append(String.format("%02d", this.accountDate.getMonthValue()));
		
		//Invoice Number
		sb.append(String.format("%-16s", this.invoiceNumber));
		
		//Price Master
		sb.append(this.priceMaster);
		
		return sb.toString();
	}
	
	/**Converts BigDecimal notation to string notation for an output file and applying the relevant string formmatting specified.
	 * @param format - A format string in the style of String.format( "...", Obj)
	 * @param decimal - Big Decimal to be converted
	 * @return
	 */
	protected static String convertBigDecimal(String format, BigDecimal decimal) {
		
		BigDecimal factor = decimal.multiply(new BigDecimal("100"));
		
		int result = factor.intValue();
		
		return String.format(format, result);
	}
	
	/**Converts Local date to a date of the format YYMMDD
	 * @param date
	 * @return String
	 */
	protected static StringBuilder covertDateYYMMDD(LocalDate date) {
		
		StringBuilder sb = new StringBuilder();
		
		if (date != null) {
			sb.append(String.format("%02d",date.getYear() - 2000));
			sb.append(String.format("%02d",date.getMonthValue()));
			sb.append(String.format("%02d",date.getDayOfMonth()));
		}
		else {
			sb.append("      ");
		}
		
		return sb;
		
	}
	
	/**Converts Local date to a date of the format YYMM
	 * @param date
	 * @return String
	 */
	protected static StringBuilder covertDateYYMM(LocalDate date) {
		
		StringBuilder sb = new StringBuilder();
		
		if (date != null) {
			sb.append(String.format("%02d",date.getYear() - 2000));
			sb.append(String.format("%02d",date.getMonthValue()));
		}
		else {
			sb.append("    ");
		}
		
		return sb;
		
	}
}
