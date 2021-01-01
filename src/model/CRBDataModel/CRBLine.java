package model.CRBDataModel;

import java.sql.Date;

/**A line from a billing repair card containing billing data and coding
 * @author klee8
 *
 */
public abstract class CRBLine {
	protected int recordFormat;					//1
	protected String partyInitial;				//2-5
	protected String billedParty;				//6-9
	protected Date accountDate;					//10-13
	protected String invoiceNumber;				//14-29
	protected char priceMaster;					//30
	protected String detailSource;				//31-32
	protected String docRefNum;					//33-47
	protected String carInitial; 				//48-51
	protected int carNumber; 					//52-57
	protected String carType;					//58
	protected char loadIndicator;				//59
	protected Date repairDate;					//60-65
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
	protected String defectDate;				//143-148
	protected int laborCharge;					//149-155
	protected int materialCharge;				//156-163
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
}

















