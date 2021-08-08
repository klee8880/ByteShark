package com.example.servingwebcontent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import leek.byteShark.controller.CommandManager;
import leek.byteShark.model.CRBDataModel.CRBBase;
import leek.byteShark.model.CRBDataModel.CRBContactInfo;
import leek.byteShark.model.CRBDataModel.CRBDataExporter;
import leek.byteShark.model.CRBDataModel.CRBDataIngestor;
import leek.byteShark.model.CRBDataModel.CRBSummary;
import leek.byteShark.model.interfaces.IDataExporter;
import leek.byteShark.model.interfaces.IDataFormatter;


public class ReadWriteTest {
	
	static IDataFormatter <CRBBase> ingestor;
	static IDataExporter <CRBBase> exporter;
	
	@BeforeEach
	public void initialize() {
		ingestor = new CRBDataIngestor ();
		exporter = new CRBDataExporter ();
	}
	
	private static String TESTFILE = "./InvoiceTestData.txt";
	private static String DEBUGOUTPUT = "./TESTReadWriteOutput.txt";
	
	//Tests are run below assuming these inputs remain unchanged.
	private static final String TESTDATALINE = 		"1PROXUTLX2010PR-I0081677     USH20200709       UTLX678133TE200917047600P77 PR-I0081677     20200917       CSC   00010  8008   09  8008   3          033376200000000DN CLEAN TANK - LUB/CRUDE OIL                        12640047600000                                                    20031600001                                                                                                                                                                                                               ";
	private static final String TESTCONTACTLINE = 	"6PROXUTLX2010PR-I0081677     UIQPROCOR LIMITED                                                                       BILLING INQUIRES                   905-827-4111             905-469-5208             RBUReceivables@Procor.com                                   585 Michigan Drive, Unit 2                                                                                                                                                          OAKVILLE                           ONCAL6L 0G1            ";
	
	@Test
	public void TESTFullDataSetExport() throws IOException {
		//Import the Example file
		List<CRBBase> brc = CommandManager.importNewBRC(TESTFILE);
		
		//Extract relevant lines & push them to the exporter
	
		IDataExporter <CRBBase>exp = new CRBDataExporter();
		CRBSummary summary = null;
		
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
				
			case 8: 
				//Record billing information for testing later
				summary = (CRBSummary) line;
				break;
			default: break;
			}
		}
		
		if (summary == null) Assertions.fail("No summary line detected in original file");
		
		//Add necessary info to the exporter
		exp.setBilledParty(summary.getBilledParty());
		exp.setBillingParty(summary.getBillingParty());
		exp.setAccountDate(summary.getAccountDate());
		exp.setInvoiceNumber(summary.getInvoiceNumber());
		exp.setPriceMaster(summary.getPriceMaster());
		exp.setInvoiceDate(summary.getInvoiceDate());
		exp.setTaxPayerId(summary.getTaxPayerId());
		exp.setPaymentTerms(summary.getPaymentTerms());
		exp.setPaymentDueDate(summary.getPaymentDueDate());
		
		//Trigger formatting operation
		exp.formatForPlainText();
		
		//Test exported lines against original data file
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(TESTFILE));
		Iterator <CRBBase> exportedLines = exp.iterator();
		
		String fromExporter, fromFile;
		
		int lineCount = 0;
		
		fromFile = reader.readLine();
		
		while (fromFile != null || exportedLines.hasNext()) {
			
			lineCount++;
			
			fromExporter = exportedLines.next().toString();

			//Check file for discrepancies and write to log file if found
			if (!fromFile.equals(fromExporter)) {
				File debugFile = new File(DEBUGOUTPUT);
				debugFile.delete();
				debugFile = new File(DEBUGOUTPUT);
				FileWriter debugWriter = new FileWriter(debugFile);
				
				debugWriter.write(fromFile);
				debugWriter.write('\n');
				debugWriter.write(fromExporter);
				debugWriter.write('\n');
				
				debugWriter.flush();
				debugWriter.close();
				
				Assertions.fail("Matching export checks failed at line " + lineCount);
			}
			
			fromFile = reader.readLine();
		} 
		

		
		reader.close();
		
	}
	
	@Test
	public void TESTIncompleteRecord1() {
		
		CRBContactInfo data;
		
		try {
			
			ingestor.appendFromString(TESTCONTACTLINE);
			Iterator<CRBBase> iterator = ingestor.iterator();
			data = (CRBContactInfo) iterator.next();
		} catch (IllegalArgumentException e) {
			Assertions.fail("Exception Hit");
			return;
		}
		
		exporter.setBilledContact(data)
		.setBillingContact(data)
		.setExceptionsContact(data)
		.setInquiryContact(data)
		.setRemitToContact(data);
		
		try {
			exporter.formatForPlainText();
		} catch (Exception e) {
			return;
		}
		
		Assertions.fail("Incomplete data did not cause expected exception");
	}
	
	@Test
	public void TESTIncompleteContacts() {
		
		CRBBase data;
		
		try {
			
			ingestor.appendFromString(TESTDATALINE);
			Iterator<CRBBase> iterator = ingestor.iterator();
			data = iterator.next();
		} catch (IllegalArgumentException e) {
			Assertions.fail("Exception Hit");
			return;
		}
		
		exporter.appendFromDataLine(data);
		
		try {
			exporter.formatForPlainText();
		} catch (Exception e) {
			return;
		}
		Assertions.fail("Incomplete contact data did not cause expected exception");
	}
	
}

