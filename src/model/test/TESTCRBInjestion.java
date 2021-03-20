package model.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.CRBDataModel.CRBBase;
import model.CRBDataModel.CRBContactInfo;
import model.CRBDataModel.CRBData;
import model.CRBDataModel.CRBDataIngestor;
import model.interfaces.IDataFormatter;

/**Testing array for ingestion of data lines
 * @author klee8
 *
 */
public class TESTCRBInjestion {
	
	static IDataFormatter <CRBBase> ingestor;
	
	@BeforeEach
	public void initialize() {
		ingestor = new CRBDataIngestor ();
	}
	
	//Tests are run below assuming these inputs remain unchanged.
	private static final String TESTDATALINE = 		"1PROXUTLX2010PR-I0081677     USH20200709       UTLX678133TE200917047600P77 PR-I0081677     20200917       CSC   00010  8008   09  8008   3          033376200000000DN CLEAN TANK - LUB/CRUDE OIL                        12640047600000                                                    20031600001                                                                                                                                                                                                               ";
	private static final String TESTCONTACTLINE = 	"6PROXUTLX2010PR-I0081677     UIQPROCOR LIMITED                                                                       BILLING INQUIRES                   905-827-4111             905-469-5208             RBUReceivables@Procor.com                                   585 Michigan Drive, Unit 2                                                                                                                                                          OAKVILLE                           ONCAL6L 0G1            ";
	private static final String TESTSUMMARYLINE8 = 	"8PROXUTLX2010PR-I0081677     USH000003100014739410000000000333640D201005               30201104                                                                                                                                                                                                                                                                                                                                                                                                                     ";
	private static final String TESTSUMMARYLINE9 = 	"9PROXZZZZ2010ZZZZZZZZZZZZZZZZZZZ000003100014739410000000000333640D                                                                                                                                                                                                                                                                                                                                                                                                                                                  ";
	
	@Test
	public void basicLineData() {
		CRBBase data;
		try {
			
			//Ingest all lines
			ingestor.appendFromString(TESTDATALINE);
			ingestor.appendFromString(TESTSUMMARYLINE8);
			ingestor.appendFromString(TESTCONTACTLINE);
			
			//Spit out all lines & test
			Iterator<CRBBase> iterator = ingestor.iterator();
			
			data = iterator.next();
			Assertions.assertEquals(1, data.getRecordFormat());
			data = iterator.next();
			Assertions.assertEquals(8, data.getRecordFormat());
			data = iterator.next();
			Assertions.assertEquals(6, data.getRecordFormat());
			
		} catch (IllegalArgumentException e) {
			Assertions.fail("Exception Hit");
			return;
		}
		
		Assertions.assertEquals("PROX", data.getBillingParty());
		Assertions.assertEquals("UTLX", data.getBilledParty());
		LocalDate date = LocalDate.of(2020, 10, 1);
		Assertions.assertTrue(date.equals(data.getAccountDate()));
		Assertions.assertEquals("PR-I0081677", data.getInvoiceNumber());
		Assertions.assertEquals('U', data.getPriceMaster());
		
	}
	
	@Test
	public void dataLineappend() {
		
		CRBData data;
		
		try {
			
			ingestor.appendFromString(TESTDATALINE);
			Iterator<CRBBase> iterator = ingestor.iterator();
			data = (CRBData) iterator.next();
		} catch (IllegalArgumentException e) {
			Assertions.fail("Exception Hit");
			return;
		}
		
		Assertions.assertEquals(1, data.getRecordFormat());
		
		CRBData line = data;
		
		Assertions.assertEquals("C", line.getLocation());
		Assertions.assertEquals(1, line.getQuantity());
		Assertions.assertEquals(0, line.getConditionCode());
		Assertions.assertEquals("8008", line.getAppliedJobCode());
		Assertions.assertEquals("CLEAN TANK - LUB/CRUDE OIL", ((CRBData)line).getNarrative());
		Assertions.assertEquals("8008", line.getRemovedJobCode());
		Assertions.assertEquals(9, line.getWhyMadeCode());
		Assertions.assertEquals(3, line.getResponsabilityCode());
		Assertions.assertTrue(new BigDecimal("3337.62").equals(line.getLaborCharge()));
		Assertions.assertTrue(new BigDecimal("0").equals(line.getMaterialCharge()));
		Assertions.assertTrue(new BigDecimal("3337.62").equals(((CRBData)line).getTotalCharge()));
		
	}
	
	@Test
	public void wheelsetLineappend() {
		//TODO: get an example of wheelset data and test.
	}
	
	@Test
	public void summary8Lineappend() {
		
	}
	
	@Test
	public void summary9Lineappend() {
		
	}
	
	@Test
	public void contactInfoLineappend() {
		
	}
	
	@Test
	public void incorrectArgLengthCheck() {
		
		//Incorrect argument length
		try {
			//0 length
			ingestor.appendFromString("");
			//1 length
			ingestor.appendFromString(" ");
			//501 length
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 501; i++) {
				sb.append(' ');
			}
			ingestor.appendFromString(sb.toString());
			
		} catch (IllegalArgumentException ex) {
			return;
		}
		
		Assertions.fail("Illegal Argument not caught: Incorrect file length");
	}
	
	//TODO: Incorrect summary lines
	@Test
	public void incorrectSummariesCheck() {}
	
}




















