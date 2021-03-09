package model.CRBDataModel;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**Testing array for ingestion of data lines
 * @author klee8
 *
 */
public class TESTCRBInjestion {
	
	static CRBDataIngestor ingestor;
	
	@BeforeEach
	public void initialize() {
		ingestor = new CRBDataIngestor();
	}
	
	//Tests are run below assuming these inputs remain unchanged.
	private static final String TESTDATALINE = 		"1PROXUTLX2010PR-I0081677     USH20200709       UTLX678133TE200917047600P77 PR-I0081677     20200917       CSC   00010  8008   09  8008   3          033376200000000DN CLEAN TANK - LUB/CRUDE OIL                        12640047600000                                                    20031600001                                                                                                                                                                                                               ";
	private static final String TESTCONTACTLINE = 	"6PROXUTLX2010PR-I0081677     UIQPROCOR LIMITED                                                                       BILLING INQUIRES                   905-827-4111             905-469-5208             RBUReceivables@Procor.com                                   585 Michigan Drive, Unit 2                                                                                                                                                          OAKVILLE                           ONCAL6L 0G1            ";
	private static final String TESTSUMMARYLINE8 = 	"8PROXUTLX2010PR-I0081677     USH000003100014739410000000000333640D201005               30201104                                                                                                                                                                                                                                                                                                                                                                                                                     ";
	private static final String TESTSUMMARYLINE9 = 	"9PROXZZZZ2010ZZZZZZZZZZZZZZZZZZZ000003100014739410000000000333640D                                                                                                                                                                                                                                                                                                                                                                                                                                                  ";
	
	@Test
	public void basicLineData() {
		CRBLine data;
		try {
			
			//Ingest all lines
			ingestor.appendFromString(TESTDATALINE);
			ingestor.appendFromString(TESTSUMMARYLINE8);
			ingestor.appendFromString(TESTCONTACTLINE);
			
			//Spit out all lines & test
			Iterator<CRBLine> iterator = ingestor.iterator();
			
			data = iterator.next();
			Assertions.assertEquals(1, data.recordFormat);
			data = iterator.next();
			Assertions.assertEquals(8, data.recordFormat);
			data = iterator.next();
			Assertions.assertEquals(6, data.recordFormat);
			
		} catch (IOException e) {
			Assertions.fail("Exception Hit");
			return;
		}
		
		Assertions.assertEquals("PROX", data.billingParty);
		Assertions.assertEquals("UTLX", data.billedParty);
		LocalDate date = LocalDate.of(2020, 10, 1);
		Assertions.assertTrue(date.equals(data.accountDate));
		Assertions.assertEquals("PR-I0081677", data.invoiceNumber);
		Assertions.assertEquals('U', data.priceMaster);
		
	}
	
	@Test
	public void dataLineappend() {
		
		CRBLine data;
		
		try {
			
			ingestor.appendFromString(TESTDATALINE);
			Iterator<CRBLine> iterator = ingestor.iterator();
			data = iterator.next();
		} catch (IOException e) {
			Assertions.fail("Exception Hit");
			return;
		}
		
		Assertions.assertEquals(1, data.recordFormat);
		
		CRBData line = (CRBData) data;
		
		Assertions.assertEquals("C ", line.location);
		Assertions.assertEquals(1, line.quantity);
		Assertions.assertEquals(0, line.conditionCode);
		Assertions.assertEquals("8008", line.appliedJobCode);
		Assertions.assertEquals("CLEAN TANK - LUB/CRUDE OIL", ((CRBGeneralData)line).narrative);
		Assertions.assertEquals("8008", line.removedJobCode);
		Assertions.assertEquals("09", line.whyMadeCode);
		Assertions.assertEquals(3, line.responsabilityCode);
		Assertions.assertTrue(new BigDecimal("3337.62").equals(line.laborCharge));
		Assertions.assertTrue(new BigDecimal("0").equals(line.materialCharge));
		Assertions.assertTrue(new BigDecimal("3337.62").equals(((CRBGeneralData)line).getTotalCharge()));
		
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
			
		} catch (IOException ex) {
			return;
		}
		
		Assertions.fail("Illegal Argument not caught: Incorrect file length");
	}
	
	//TODO: Incorrect summary lines
	@Test
	public void incorrectSummariesCheck() {}
	
}




















