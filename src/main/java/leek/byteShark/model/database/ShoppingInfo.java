package leek.byteShark.model.database;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "SHOPPING")
public class ShoppingInfo {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String carInitial;
	private int number;
	private char carType;
	
	private String invoiceNumber = "";
	private LocalDate invoiceDate;
	private String detailSource = "BR";
	private String billingParty = "";
	
	private LocalDate accountDate;
	private LocalDate arrivalDate;
	private LocalDate repairDate;
	
	private int SPLC;
	private char loadindicator;
	
	private String defectCard;
	private LocalDate defectDate;
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCarInitial() {
		return carInitial;
	}
	public void setCarInitial(String carInitial) {
		this.carInitial = carInitial;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public char getCarType() {
		return carType;
	}
	public void setCarType(char carType) {
		this.carType = carType;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getDetailSource() {
		return detailSource;
	}
	public void setDetailSource(String detailSource) {
		this.detailSource = detailSource;
	}
	public String getBillingParty() {
		return billingParty;
	}
	public void setBillingParty(String billingParty) {
		this.billingParty = billingParty;
	}
	public LocalDate getAccountDate() {
		return accountDate;
	}
	public void setAccountDate(LocalDate accountDate) {
		this.accountDate = accountDate;
	}
	public LocalDate getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public LocalDate getRepairDate() {
		return repairDate;
	}
	public void setRepairDate(LocalDate repairDate) {
		this.repairDate = repairDate;
	}
	public int getSPLC() {
		return SPLC;
	}
	public void setSPLC(int sPLC) {
		SPLC = sPLC;
	}
	public char getLoadindicator() {
		return loadindicator;
	}
	public void setLoadindicator(char loadindicator) {
		this.loadindicator = loadindicator;
	}
	public String getDefectCard() {
		return defectCard;
	}
	public void setDefectCard(String defectCard) {
		this.defectCard = defectCard;
	}
	public LocalDate getDefectDate() {
		return defectDate;
	}
	public void setDefectDate(LocalDate defectDate) {
		this.defectDate = defectDate;
	}
	
	
	
}
