package model.CRBDataModel;

public class CRBDataIngestor {

	public static CRBLine readDataLine (String input) {
		
		//Check argument length for incorrect data file.
		if (input.length() != 500) throw new IllegalArgumentException("Incorrect format: Not 500 characters per line");
		
		//Switch based on the detected record type.
		int recordType = Integer.parseInt(input.substring(0, 1));
		CRBLine crb;
		
		switch (recordType) {
		case 1:
			 crb = new CRBGeneralData(input);
			return crb;
		case 6:
			crb = new CRBContactInfo(input);
			return crb;
		case 8:
			crb = new CRBSummary(input);
			return crb;
		case 9:
			crb = new CRBTotal(input);
			return crb;
		default:
			throw new IllegalArgumentException("Incorrect format: Unexpected record format");
		}
		
	}
	
}
