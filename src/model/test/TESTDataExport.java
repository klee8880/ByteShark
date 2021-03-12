package model.test;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.CRBDataModel.CRBDataIngestor;

public class TESTDataExport {

	static CRBDataIngestor ingestor;
	
	//Tests are run below assuming these inputs remain unchanged.
	private static final String TESTDATALINE = 		"1PROXUTLX2010PR-I0081677     USH20200709       UTLX678133TE200917047600P77 PR-I0081677     20200917       CSC   00010  8008   09  8008   3          033376200000000DN CLEAN TANK - LUB/CRUDE OIL                        12640047600000                                                    20031600001                                                                                                                                                                                                               ";
	private static final String TESTCONTACTLINE = 	"6PROXUTLX2010PR-I0081677     UIQPROCOR LIMITED                                                                       BILLING INQUIRES                   905-827-4111             905-469-5208             RBUReceivables@Procor.com                                   585 Michigan Drive, Unit 2                                                                                                                                                          OAKVILLE                           ONCAL6L 0G1            ";
	private static final String TESTSUMMARYLINE8 = 	"8PROXUTLX2010PR-I0081677     USH000003100014739410000000000333640D201005               30201104                                                                                                                                                                                                                                                                                                                                                                                                                     ";
	private static final String TESTSUMMARYLINE9 = 	"9PROXZZZZ2010ZZZZZZZZZZZZZZZZZZZ000003100014739410000000000333640D                                                                                                                                                                                                                                                                                                                                                                                                                                                  ";
	
	@BeforeEach
	public void initialize() {
		ingestor = new CRBDataIngestor();
	}
	
	@Test
	public void recordFormat1Output () throws IOException {
		ingestor.appendFromString(TESTDATALINE);
		Assertions.assertTrue(TESTDATALINE.equals(ingestor.iterator().next().toString()));
	}
	
	@Test
	public void recordFormat6Output () throws IOException {
		ingestor.appendFromString(TESTCONTACTLINE);
		Assertions.assertTrue(TESTCONTACTLINE.equals(ingestor.iterator().next().toString()));
	}
	
	@Test
	public void recordFormat8Output () throws IOException {
		ingestor.appendFromString(TESTSUMMARYLINE8);
		Assertions.assertTrue(TESTSUMMARYLINE8.equals(ingestor.iterator().next().toString()));
	}
	
	@Test
	public void recordFormat9Output () throws IOException {
		ingestor.appendFromString(TESTSUMMARYLINE9);
		Assertions.assertTrue(TESTSUMMARYLINE9.equals(ingestor.iterator().next().toString()));
	}
}
