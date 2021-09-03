package leek.byteShark.view;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ShoppingWrapper {

	private String billedParty;
	private LocalDate accountDate;
	private String invoiceNumber;
	private char priceMaster;
	
	private LocalDate invoiceDate;
	private String taxPayerId;
	
	private String detailSource;
	private String carInitial;
	private int carNumber;
	private char carType;
	private char loadIndicator;
	private LocalDate repairDate;
	private int SPLC;
	
	private String defectParty;
	private LocalDate defectDate;
	private char resubmittedInvoice;
	private String originalInvoiceNumber;
	private LocalDate originalAccountDate;
	private LocalDate arrivalDate;
	
}
