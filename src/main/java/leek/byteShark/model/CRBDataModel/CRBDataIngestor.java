package leek.byteShark.model.CRBDataModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import leek.byteShark.model.interfaces.IDataFormatter;

public class CRBDataIngestor implements IDataFormatter<CRBBase>, Iterable<CRBBase>{
	
	ArrayList<CRBBase> dataLines = new ArrayList<CRBBase>();

	//Methods
	@Override
	public Iterator<CRBBase> iterator() {
		return dataLines.iterator();
	}

	@Override
	public void appendFromString(String input) throws IllegalArgumentException {
		//Check argument length for incorrect data file.
		if (input.length() != 500) throw new IllegalArgumentException("Incorrect format: Not 500 characters per line");
		
		//Switch based on the detected record type.
		int recordType = Integer.parseInt(input.substring(0, 1));
		CRBBase crb;
		
		switch (recordType) {
		case 1:
			 crb = new CRBData(input);
			break;
		case 6:
			crb = new CRBContactInfo(input);
			break;
		case 8:
			crb = new CRBSummary(input);
			break;
		case 9:
			crb = new CRBTotal(input);
			break;
		default:
			throw new IllegalArgumentException("Incorrect format: Unexpected record format");
		}
		
		dataLines.add(crb);
		
	}

	@Override
	public void appendFromString(List <String> input) throws IllegalArgumentException {
		for (String line: input) {
			this.appendFromString(line);
		}
	}
	
	@Override
	public void appendFromDataLine(CRBBase input) throws IllegalArgumentException{
		dataLines.add(input);
	}

	@Override
	public void appendFromDataLine(List<CRBBase> input) throws IllegalArgumentException{
		for (CRBBase line: input) {
			this.appendFromDataLine(line);;
		}
	}
	
	@Override
	public List<CRBBase> outputData() {
		
		ArrayList<CRBBase> output = new ArrayList<CRBBase> ();
		
		for (CRBBase c: dataLines) output.add(c);
		
		return output;
	}

	@Override
	public void clear() {dataLines.clear();}
	
}
