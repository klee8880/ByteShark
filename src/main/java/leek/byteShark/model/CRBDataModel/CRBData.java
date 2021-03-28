package leek.byteShark.model.CRBDataModel;

import java.math.BigDecimal;
import java.time.LocalDate;

/**Record Format 1: A line from a billing repair card containing billing data and coding.
 * @author klee8
 *
 */
public class CRBData extends CRBBase{
	
	protected String detailSource;				//31-32
	protected String docRefNum;					//33-47
	protected String carInitial; 				//48-51
	protected int carNumber; 					//52-57
	protected char carType;						//58
	protected char loadIndicator;				//59
	protected LocalDate repairDate;				//60-65
	protected int SPLC;							//66-71
	protected String repairParty;				//72-75
	protected String repairPartyInvoiceNum;		//76-91
	protected String repairPartyDocRef;			//92-106
	protected String facilityType;				//107-108
	protected String location;					//109-110
	protected int quantity;						//113-116
	protected short conditionCode;				//117
	protected String appliedJobCode;			//120-123
	protected String appliedQualifier;			//124-125
	protected short whyMadeCode;				//127-128
	protected String removedJobCode; 			//131-134
	protected String removedQualifier;			//135-136
	protected short responsabilityCode;			//138
	protected String defectParty;				//139-142
	protected LocalDate defectDate;				//143-148
	protected BigDecimal laborCharge;			//149-155
	protected BigDecimal materialCharge;		//156-163
	protected char materialSign;				//164
	protected char machinePriceable;			//165
	protected char wrongRepairIndicator;		//166
	
	protected String narrative;	
	
	protected BigDecimal laborRate;				//217-221
	protected int expandedSPLC;					//222-230

	protected LocalDate arrivalDate;			//283-288
	protected int lineNumber;					//289-293
	protected int railLinkInboundDate;			//294-299
	protected int railLinkOutboundDate;			//300-305
	protected char resubmittedInvoice;			//306
	protected String originalInvoiceNumber;		//307-322		*Required if resubmittedInvoice
	protected LocalDate originalAccountDate;			//323-326
	protected String AARComponentID;			//327-340
	protected String freeUseArea;				//401-500
	
	
	//Constructor

	public CRBData(String input) {
		super(input);
		
		this.detailSource = input.substring(30,32).trim();
		this.docRefNum = input.substring(32,47).trim();
		this.carInitial = input.substring(47,51).trim();
		this.carNumber = Integer.parseInt(input.substring(51,57));
		this.carType = input.charAt(57);
		this.loadIndicator = input.charAt(58);
		this.repairDate = LocalDate.of(
				2000 + Integer.parseInt(input.substring(59,61)), 
				Integer.parseInt(input.substring(61,63)), 
				Integer.parseInt(input.substring(63,65)));
		this.SPLC = Integer.parseInt(input.substring(65,71));
		this.repairParty = input.substring(71,75).trim();
		this.repairPartyInvoiceNum = input.substring(75, 91).trim();
		this.repairPartyDocRef = input.substring(91, 106).trim();
		this.facilityType = input.substring(106, 108).trim();
		this.location = input.substring(108, 110).trim();
		this.quantity = Integer.parseInt(input.substring(112, 116));
		this.conditionCode = Short.parseShort(input.substring(116, 117));
		this.appliedJobCode = input.substring(119, 123).trim();
		this.appliedQualifier = input.substring(123, 125).trim();
		this.whyMadeCode = Short.parseShort(input.substring(126, 128));
		this.removedJobCode = input.substring(130, 134).trim();
		this.removedQualifier = input.substring(134, 136).trim();
		this.responsabilityCode = Short.parseShort(input.substring(137,138));
		
		//Check if Defect Card is present
		this.defectParty = input.substring(138, 142).trim();
		try {
		this.defectDate = LocalDate.of(
				2000 + Integer.parseInt(input.substring(142,144)), 
				Integer.parseInt(input.substring(144,146)), 
				Integer.parseInt(input.substring(146,148)));
		} catch (NumberFormatException ex) {
			this.defectDate = null;
		}
		
		BigDecimal divider = new BigDecimal("100");
		
		this.laborCharge = new BigDecimal(input.substring(148,155)).divide(divider);
		this.materialCharge = new BigDecimal(input.substring(155,163)).divide(divider);
		this.materialSign = input.charAt(163);
		this.machinePriceable = input.charAt(164);
		this.wrongRepairIndicator = input.charAt(165);
		this.narrative = input.substring(166,216).trim();
		this.laborRate = new BigDecimal(input.substring(216,221)).divide(divider);
		this.expandedSPLC = Integer.parseInt(input.substring(221, 230));
		
		this.arrivalDate = LocalDate.of(
				2000 + Integer.parseInt(input.substring(282,284)), 
				Integer.parseInt(input.substring(284,286)), 
				Integer.parseInt(input.substring(286,288)));
		this.lineNumber = Integer.parseInt(input.substring(288,293));
		
		this.resubmittedInvoice = input.charAt(306);
		this.originalInvoiceNumber = input.substring(306,322);
		try {
		this.originalAccountDate = LocalDate.of(
				2000 + Integer.parseInt(input.substring(322,324)), 
				Integer.parseInt(input.substring(324,326)), 
				1);
		} catch(NumberFormatException ex) {
			this.originalAccountDate = null;
		}
		AARComponentID = input.substring(326,340);
		this.freeUseArea = "";
	}

	public CRBData() {
		super();
		this.recordFormat = 1;
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

	public char getCarType() {
		return carType;
	}

	public void setCarType(char carType) {
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

	public short getConditionCode() {
		return conditionCode;
	}

	public void setConditionCode(short conditionCode) {
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

	public short getWhyMadeCode() {
		return whyMadeCode;
	}

	public void setWhyMadeCode(Short whyMadeCode) {
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

	public short getResponsabilityCode() {
		return responsabilityCode;
	}

	public void setResponsabilityCode(short responsabilityCode) {
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

	public String getNarrative() {
		return narrative;
	}

	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}
	
	public BigDecimal getLaborRate() {
		return laborRate;
	}

	public void setLaborRate(BigDecimal laborRate) {
		this.laborRate = laborRate;
	}

	public int getExpandedSPLC() {
		return expandedSPLC;
	}

	public void setExpandedSPLC(int expandedSPLC) {
		this.expandedSPLC = expandedSPLC;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
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

	public LocalDate getOriginalAccountDate() {
		return originalAccountDate;
	}

	public void setOriginalAccountDate(LocalDate originalAccountDate) {
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
	
	//Methods
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(super.toString());
		sb.append(this.detailSource);
		sb.append(String.format("%-15s", this.docRefNum));
		sb.append(this.carInitial);
		sb.append(String.format("%06d", this.carNumber));
		sb.append(this.carType);
		sb.append(this.loadIndicator);
		
		sb.append(covertDateYYMMDD(this.repairDate));
		
		sb.append(String.format("%06d", this.SPLC));
		sb.append(String.format("%-4s", this.repairParty));
		sb.append(String.format("%-16s", this.repairPartyInvoiceNum));
		sb.append(String.format("%-15s", this.repairPartyDocRef));
		sb.append(this.facilityType);
		
		sb.append(String.format("%-4s", this.location));
		sb.append(String.format("%04d", this.quantity));
		sb.append(this.conditionCode);
		sb.append("  ");
		sb.append(this.appliedJobCode);
		sb.append(String.format("%-3s", this.appliedQualifier));
		sb.append(String.format("%02d", this.whyMadeCode));
		sb.append("  ");
		sb.append(this.removedJobCode);
		sb.append(String.format("%-3s", this.removedQualifier));
		sb.append(this.responsabilityCode);
		sb.append(String.format("%-4s", this.defectParty));
		
		sb.append(covertDateYYMMDD(this.defectDate));
		
		sb.append(convertBigDecimal("%07d", this.laborCharge));
		sb.append(convertBigDecimal("%08d", this.materialCharge));
		
		sb.append(this.materialSign);
		sb.append(this.machinePriceable);
		sb.append(this.wrongRepairIndicator);
		sb.append(String.format("%-50s", this.narrative));
		
		sb.append(convertBigDecimal("%05d", this.laborRate));
		
		sb.append(String.format("%09d", this.expandedSPLC));
		
		//TODO: CIF areas not implemented
		for (int i = 0; i < 13 * 4; i++) {
			sb.append(' ');
		}
		
		sb.append(covertDateYYMMDD(this.arrivalDate));
		
		sb.append(String.format("%05d", this.lineNumber));
		
		//Only to be filled by railinc
		for (int i = 0; i < 12; i++) {
			sb.append(' ');
		}
		
		sb.append(this.resubmittedInvoice);
		sb.append(String.format("%-16s", this.originalInvoiceNumber));
		sb.append(covertDateYYMM(this.originalAccountDate));
		
		sb.append(String.format("%-14s", this.AARComponentID));
		
		//Reserved for other uses
		for (int i = 0; i < 160; i++) {
			sb.append(' ');
		}
		
		return sb.toString();
	}
	
}

















