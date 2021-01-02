package model.CRBDataModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

/**Record Format 1: A line from a billing repair card containing billing data and coding. Further delineated with general and wheel set type formats based on need.
 * @author klee8
 *
 */
public abstract class CRBData extends CRBLine{
	
	protected String detailSource;				//31-32
	protected String docRefNum;					//33-47
	protected String carInitial; 				//48-51
	protected int carNumber; 					//52-57
	protected String carType;					//58
	protected char loadIndicator;				//59
	protected LocalDate repairDate;					//60-65
	protected int SPLC;							//66-71
	protected String repairParty;				//72-75
	protected String repairPartyInvoiceNum;		//76-91
	protected String repairPartyDocRef;			//92-106
	protected String facilityType;				//107-108
	protected String location;					//109-110
	protected int quantity;						//113-116
	protected int conditionCode;				//117
	protected String appliedJobCode;			//120-123
	protected String appliedQualifier;			//124-125
	protected String whyMadeCode;				//127-128
	protected String removedJobCode; 			//131-134
	protected String removedQualifier;			//135-136
	protected int responsabilityCode;			//138
	protected String defectParty;				//139-142
	protected LocalDate defectDate;				//143-148
	protected BigDecimal laborCharge;			//149-155
	protected BigDecimal materialCharge;		//156-163
	protected char materialSign;				//164
	protected char machinePriceable;			//165
	protected char wrongRepairIndicator;		//166
	
	//Lines 167-216 are either the narrative or the wheelset data for the car.
	
	protected int laborRate;					//217-221
	protected int expandedSPLC;					//222-230
	protected String CIFRepairingParty;			//231-243
	protected String CIFbillingInvoicingParty;	//244-256
	protected String CIFBilledParty;			//257-269
	protected String CIFJICParty;				//270-282
	protected int arrivalDate;					//283-288
	protected int lineNumber;					//289-293
	protected int railLinkInboundDate;			//294-299
	protected int railLinkOutboundDate;			//300-305
	protected char resubmittedInvoice;			//306
	protected String originalInvoiceNumber;		//307-322		*Required if resubmittedInvoice
	protected int originalAccountDate;			//323-326
	protected String AARComponentID;			//327-340
	protected String freeUseArea;				//401-500
	
	
	//Constructor
	public CRBData(String input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	public CRBData() {
		// TODO Auto-generated constructor stub
	}

	//Getters & Setters
	public String getDetailSource() {
		return detailSource;
	}



	public void setDetailSource(String detailSource) {
		this.detailSource = detailSource;
	}



	public String getDocRefNum() {
		return docRefNum;
	}



	public void setDocRefNum(String docRefNum) {
		this.docRefNum = docRefNum;
	}



	public String getCarInitial() {
		return carInitial;
	}



	public void setCarInitial(String carInitial) {
		this.carInitial = carInitial;
	}



	public int getCarNumber() {
		return carNumber;
	}



	public void setCarNumber(int carNumber) {
		this.carNumber = carNumber;
	}



	public String getCarType() {
		return carType;
	}



	public void setCarType(String carType) {
		this.carType = carType;
	}



	public char getLoadIndicator() {
		return loadIndicator;
	}



	public void setLoadIndicator(char loadIndicator) {
		this.loadIndicator = loadIndicator;
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



	public String getRepairParty() {
		return repairParty;
	}



	public void setRepairParty(String repairParty) {
		this.repairParty = repairParty;
	}



	public String getRepairPartyInvoiceNum() {
		return repairPartyInvoiceNum;
	}



	public void setRepairPartyInvoiceNum(String repairPartyInvoiceNum) {
		this.repairPartyInvoiceNum = repairPartyInvoiceNum;
	}



	public String getRepairPartyDocRef() {
		return repairPartyDocRef;
	}



	public void setRepairPartyDocRef(String repairPartyDocRef) {
		this.repairPartyDocRef = repairPartyDocRef;
	}



	public String getFacilityType() {
		return facilityType;
	}



	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public int getConditionCode() {
		return conditionCode;
	}



	public void setConditionCode(int conditionCode) {
		this.conditionCode = conditionCode;
	}



	public String getAppliedJobCode() {
		return appliedJobCode;
	}



	public void setAppliedJobCode(String appliedJobCode) {
		this.appliedJobCode = appliedJobCode;
	}



	public String getAppliedQualifier() {
		return appliedQualifier;
	}



	public void setAppliedQualifier(String appliedQualifier) {
		this.appliedQualifier = appliedQualifier;
	}



	public String getWhyMadeCode() {
		return whyMadeCode;
	}



	public void setWhyMadeCode(String whyMadeCode) {
		this.whyMadeCode = whyMadeCode;
	}



	public String getRemovedJobCode() {
		return removedJobCode;
	}



	public void setRemovedJobCode(String removedJobCode) {
		this.removedJobCode = removedJobCode;
	}



	public String getRemovedQualifier() {
		return removedQualifier;
	}



	public void setRemovedQualifier(String removedQualifier) {
		this.removedQualifier = removedQualifier;
	}



	public int getResponsabilityCode() {
		return responsabilityCode;
	}



	public void setResponsabilityCode(int responsabilityCode) {
		this.responsabilityCode = responsabilityCode;
	}



	public String getDefectParty() {
		return defectParty;
	}



	public void setDefectParty(String defectParty) {
		this.defectParty = defectParty;
	}



	public LocalDate getDefectDate() {
		return defectDate;
	}



	public void setDefectDate(LocalDate defectDate) {
		this.defectDate = defectDate;
	}



	public BigDecimal getLaborCharge() {
		return laborCharge;
	}



	public void setLaborCharge(BigDecimal laborCharge) {
		this.laborCharge = laborCharge;
	}



	public BigDecimal getMaterialCharge() {
		return materialCharge;
	}



	public void setMaterialCharge(BigDecimal materialCharge) {
		this.materialCharge = materialCharge;
	}



	public char getMaterialSign() {
		return materialSign;
	}



	public void setMaterialSign(char materialSign) {
		this.materialSign = materialSign;
	}



	public char getMachinePriceable() {
		return machinePriceable;
	}



	public void setMachinePriceable(char machinePriceable) {
		this.machinePriceable = machinePriceable;
	}



	public char getWrongRepairIndicator() {
		return wrongRepairIndicator;
	}



	public void setWrongRepairIndicator(char wrongRepairIndicator) {
		this.wrongRepairIndicator = wrongRepairIndicator;
	}



	public int getLaborRate() {
		return laborRate;
	}



	public void setLaborRate(int laborRate) {
		this.laborRate = laborRate;
	}



	public int getExpandedSPLC() {
		return expandedSPLC;
	}



	public void setExpandedSPLC(int expandedSPLC) {
		this.expandedSPLC = expandedSPLC;
	}



	public String getCIFRepairingParty() {
		return CIFRepairingParty;
	}



	public void setCIFRepairingParty(String cIFRepairingParty) {
		CIFRepairingParty = cIFRepairingParty;
	}



	public String getCIFbillingInvoicingParty() {
		return CIFbillingInvoicingParty;
	}



	public void setCIFbillingInvoicingParty(String cIFbillingInvoicingParty) {
		CIFbillingInvoicingParty = cIFbillingInvoicingParty;
	}



	public String getCIFBilledParty() {
		return CIFBilledParty;
	}



	public void setCIFBilledParty(String cIFBilledParty) {
		CIFBilledParty = cIFBilledParty;
	}



	public String getCIFJICParty() {
		return CIFJICParty;
	}



	public void setCIFJICParty(String cIFJICParty) {
		CIFJICParty = cIFJICParty;
	}



	public int getArrivalDate() {
		return arrivalDate;
	}



	public void setArrivalDate(int arrivalDate) {
		this.arrivalDate = arrivalDate;
	}



	public int getLineNumber() {
		return lineNumber;
	}



	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}



	public int getRailLinkInboundDate() {
		return railLinkInboundDate;
	}



	public void setRailLinkInboundDate(int railLinkInboundDate) {
		this.railLinkInboundDate = railLinkInboundDate;
	}



	public int getRailLinkOutboundDate() {
		return railLinkOutboundDate;
	}



	public void setRailLinkOutboundDate(int railLinkOutboundDate) {
		this.railLinkOutboundDate = railLinkOutboundDate;
	}



	public char getResubmittedInvoice() {
		return resubmittedInvoice;
	}



	public void setResubmittedInvoice(char resubmittedInvoice) {
		this.resubmittedInvoice = resubmittedInvoice;
	}



	public String getOriginalInvoiceNumber() {
		return originalInvoiceNumber;
	}



	public void setOriginalInvoiceNumber(String originalInvoiceNumber) {
		this.originalInvoiceNumber = originalInvoiceNumber;
	}



	public int getOriginalAccountDate() {
		return originalAccountDate;
	}



	public void setOriginalAccountDate(int originalAccountDate) {
		this.originalAccountDate = originalAccountDate;
	}



	public String getAARComponentID() {
		return AARComponentID;
	}



	public void setAARComponentID(String aARComponentID) {
		AARComponentID = aARComponentID;
	}



	public String getFreeUseArea() {
		return freeUseArea;
	}



	public void setFreeUseArea(String freeUseArea) {
		this.freeUseArea = freeUseArea;
	}



	public BigDecimal getTotalCharge() {
		return laborCharge.add(materialCharge);
	}
}

















