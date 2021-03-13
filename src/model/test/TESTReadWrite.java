package model.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.CommandManager;
import model.CRBDataModel.CRBBase;
import model.CRBDataModel.CRBContactInfo;
import model.CRBDataModel.CRBData;
import model.CRBDataModel.CRBDataExporter;
import model.CRBDataModel.CRBDataIngestor;
import model.interfaces.IDataExporter;
import model.interfaces.IDataFormatter;

public class TESTReadWrite {

	private static String TESTFILE= "./InvoiceTestData.txt"; 
	
	@Test
	public void TESTFullDataSetExport() throws IOException {
		//Import the Example file
		List<CRBBase> brc = CommandManager.importNewBRC(TESTFILE);
		
		//Extract relevant lines & push them to the exporter
	
		IDataExporter <CRBBase>exp = new CRBDataExporter();
		
		for (CRBBase line: brc) {
			switch (line.getRecordFormat()) {
			case 1: 
				exp.appendFromDataLine(line);
				break;
			case 6:
				switch (((CRBContactInfo) line).getContactType()) {
				case "IP":
					exp.setBillingContact((CRBContactInfo) line);
					break;
				case "BP":
					exp.setBilledContact((CRBContactInfo) line);
					break;
				case "RT":
					exp.setRemitToContact((CRBContactInfo) line);
					break;
				case "IQ":
					exp.setInquiryContact((CRBContactInfo) line);
					break;
				case "EX":
					exp.setExceptionsContact((CRBContactInfo) line);
					break;
				}
				break;
			default: break;
			}
		}
		
		//Test exported lines against original data file
		
		BufferedReader reader = new BufferedReader(new FileReader(TESTFILE));
		Iterator <CRBBase> exportedLines = exp.iterator();
		
		String fromExporter, fromFile;
		
		int lineCount = 0;
		
		try {
			do {
				lineCount++;

				fromFile = reader.readLine();
				fromExporter = exportedLines.next().toString();

				if (!fromFile.equals(fromExporter))
					Assertions.fail("Matching export checks failed at line " + lineCount);

			} while (fromFile != null || exportedLines.hasNext());
		} 
		catch (Exception ex) {
			Assertions.fail("An exception occured when trying to read the line after line " + lineCount);
		}
		finally {
			reader.close();
		}
		
	}
	
}
