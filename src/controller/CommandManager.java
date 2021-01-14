package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.CRBDataModel.CRBDataIngestor;
import model.CRBDataModel.CRBGeneralData;
import model.CRBDataModel.CRBLine;
import view.implimentations.BRCPanel;

public class CommandManager {

	/**Import a BRC from a new data file.
	 * @param address
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<CRBLine> importNewBRC(String address) throws IOException {
		
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(address));
		
		ArrayList<CRBLine> brc = new ArrayList<CRBLine>();
		
		String line;
		line = reader.readLine();
		while (line != null) {
			brc.add(CRBDataIngestor.readDataLine(line));
			line = reader.readLine();
		}
		
		reader.close();
		return brc;
	}
	
	/**Push Data Lines to the selected table.
	 * @param dataSet
	 * @param brcTable
	 */
	public static void pushDataToTable(ArrayList<CRBLine> dataSet, BRCPanel brcTable) {
		
		//Extract actual data lines
		ArrayList<CRBGeneralData> generalLines = extractDataLines(dataSet);
		
		//Check for invalid data
		if (generalLines.size() < 1) throw new IllegalArgumentException("No Data lines detected");
		
		int lineCount = 0;
		
		for (CRBGeneralData dataLine: generalLines) {
			
			lineCount++;
			
			//Format for data table
			Object[] tableLine = {
					new Integer(lineCount),
					dataLine.getLocation(),
					new Integer(dataLine.getQuantity()),
					dataLine.getConditionCode(),
					new Integer(dataLine.getAppliedJobCode()),
					dataLine.getNarrative(),
					new Integer(dataLine.getRemovedJobCode()),
					new Integer(dataLine.getWhyMadeCode()),
					new Integer(dataLine.getResponsabilityCode()),
					dataLine.getLaborCharge(),
					dataLine.getMaterialCharge(),
					dataLine.getLaborCharge().add(dataLine.getMaterialCharge())
					};
			
			//Push lines to table
			brcTable.addRow(tableLine);
			
		}
		
		
		
	}
	
	/**Extract the actual data lines from the set removing the summary and billing lines
	 * @param dataSet
	 * @return
	 */
	private static ArrayList<CRBGeneralData> extractDataLines(ArrayList<CRBLine> dataSet) {
		
		ArrayList<CRBGeneralData> repairLines = new ArrayList<CRBGeneralData>();
		
		for (CRBLine line: dataSet) {
			if (line.getRecordFormat() == 1) {
				repairLines.add((CRBGeneralData)line);
			}
		}
		
		System.out.println(repairLines.size() + " Records extracted");
		
		return repairLines;
	}
	
}











