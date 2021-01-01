package model.CRBDataModel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TESTCRBInjestion {
	
	private static String TESTDATALINE = "1PROXUTLX2010PR-I0081677     USH20200709       UTLX678133TE200917047600P77 PR-I0081677     20200917       CSC   00010  8008   09  8008   3          033376200000000DN CLEAN TANK - LUB/CRUDE OIL                        12640047600000                                                    20031600001                                                                                                                                                                                                               ";
	private static String TESTSUMMARYLINE = "8PROXUTLX2010PR-I0081677     USH000003100014739410000000000333640D201005               30201104                                                                                                                                                                                                                                                                                                                                                                                                                     ";
	private static String TESTCONTACTLINE = "6PROXUTLX2010PR-I0081677     UIQPROCOR LIMITED                                                                       BILLING INQUIRES                   905-827-4111             905-469-5208             RBUReceivables@Procor.com                                   585 Michigan Drive, Unit 2                                                                                                                                                          OAKVILLE                           ONCAL6L 0G1            ";
	@Test
	public void RecordTypeDetection() {
		CRBData data = CRBDataFactory.readDataLine(TESTDATALINE);
		Assertions.assertTrue(data.recordFormat == 1);
		
		data = CRBDataFactory.readDataLine(TESTSUMMARYLINE);
		Assertions.assertTrue(data.recordFormat == 8);
		
		data = CRBDataFactory.readDataLine(TESTCONTACTLINE);
		Assertions.assertTrue(data.recordFormat == 6);
	
	}
	
	@Test
	public void dataLineRead() {
		
	}
	
	@Test
	public void summaryLineRead() {
		
	}
	
	@Test
	public void contactInfoLineRead() {
		
	}
	
	@Test
	public void incorrectDataFile() {
		
	}
	
}
