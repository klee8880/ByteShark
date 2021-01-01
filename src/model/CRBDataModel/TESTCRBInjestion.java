package model.CRBDataModel;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**Testing array for ingestion of data lines
 * @author klee8
 *
 */
public class TESTCRBInjestion {
	
	//Tests are run below assuming these inputs remain unchanged.
	private static final String TESTDATALINE = 		"1PROXUTLX2010PR-I0081677     USH20200709       UTLX678133TE200917047600P77 PR-I0081677     20200917       CSC   00010  8008   09  8008   3          033376200000000DN CLEAN TANK - LUB/CRUDE OIL                        12640047600000                                                    20031600001                                                                                                                                                                                                               ";
	private static final String TESTSUMMARYLINE = 	"8PROXUTLX2010PR-I0081677     USH000003100014739410000000000333640D201005               30201104                                                                                                                                                                                                                                                                                                                                                                                                                     ";
	private static final String TESTCONTACTLINE = 	"6PROXUTLX2010PR-I0081677     UIQPROCOR LIMITED                                                                       BILLING INQUIRES                   905-827-4111             905-469-5208             RBUReceivables@Procor.com                                   585 Michigan Drive, Unit 2                                                                                                                                                          OAKVILLE                           ONCAL6L 0G1            ";
	
	@Test
	public void RecordTypeDetection() {
		CRBData data = CRBDataFactory.readDataLine(TESTDATALINE);
		Assertions.assertEquals(1, data.recordFormat);
		
		data = CRBDataFactory.readDataLine(TESTSUMMARYLINE);
		Assertions.assertEquals(8, data.recordFormat);
		
		data = CRBDataFactory.readDataLine(TESTCONTACTLINE);
		Assertions.assertEquals(6, data.recordFormat);
	
	}
	
	@Test
	public void dataLineRead() {
		
		CRBData data = CRBDataFactory.readDataLine(TESTDATALINE);
		
		Assertions.assertEquals("C", data.location);
		Assertions.assertEquals(1, data.quantity);
		Assertions.assertEquals(0, data.conditionCode);
		Assertions.assertEquals("8008", data.appliedJobCode);
		Assertions.assertEquals("CLEAN TANK - LUB/CRUDE OIL", ((CRBGeneralData)data).narrative);
		Assertions.assertEquals("8008", data.removedJobCode);
		Assertions.assertEquals("09", data.whyMadeCode);
		Assertions.assertEquals(3, data.responsabilityCode);
		Assertions.assertTrue(new BigDecimal("3337.62").equals(data.laborCharge));
		Assertions.assertTrue(new BigDecimal("0").equals(data.materialCharge));
		Assertions.assertTrue(new BigDecimal("3337.62").equals(((CRBGeneralData)data).totalCharge()));
		
	}
	
	@Test
	public void wheelsetLineRead() {
		//TODO: get an example of wheelset data and test.
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
