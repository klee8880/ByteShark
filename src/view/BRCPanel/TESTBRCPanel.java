package view.BRCPanel;

import java.math.BigDecimal;

import javax.swing.event.TableModelListener;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.TableListener;

public class TESTBRCPanel {

	private static BRCPanel brcTable;
	
	@BeforeAll
	public static void initialize() {
		
		//Start Event Handlers
		TableModelListener tableListener = new TableListener(null);
		
		//assemble the UI
		brcTable = new BRCPanel(tableListener);
		
	}
	
	@Test
	public void testNullInputs() {

		try {
			brcTable.addRow(null);
		} catch (IllegalArgumentException e) {return;}
		
		Assertions.fail("Invalid inputs not detected");
		
	}
	
	@Test
	public void testShortArrayLength() {
		
		Object[] input = {};
		
		try {
			brcTable.addRow(input);
		} catch (IllegalArgumentException e) {return;}
		
		Assertions.fail("Invalid inputs not detected");
	}
	
	@Test
	public void testLongArrayLength() {
		
		Object[] input = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		
		try {
			brcTable.addRow(input);
		} catch (IllegalArgumentException e) {return;}
		
		Assertions.fail("Invalid inputs not detected");
	}
	
	@Test
	public void testArgumentTypes() {
		
		boolean failed = false;
		
		Object[] tableLine = {
				1,"C",1,1,1111,"NARRATIVE",1111,11,1111,
				new BigDecimal("0.00"),new BigDecimal("0.00"),
				new BigDecimal("0.00")};
		
		//Base Case
		try {brcTable.addRow(tableLine);} catch (Exception e) {
			Assertions.fail("Valid Input marked as invalid");
		}
		
		//Mutations
		tableLine[0] = "A";
		try {brcTable.addRow(tableLine);} catch (Exception e) {failed = true;}
		tableLine[0] = 1;
		
		tableLine[1] = 1;
		try {brcTable.addRow(tableLine);} catch (Exception e) {failed = true;}
		tableLine[1] = "C";
		
		tableLine[2] = "A";
		try {brcTable.addRow(tableLine);} catch (Exception e) {failed = true;}
		tableLine[2] = 1;
		
		tableLine[3] = "A";
		try {brcTable.addRow(tableLine);} catch (Exception e) {failed = true;}
		tableLine[3] = 1;
		
		tableLine[4] = "A";
		try {brcTable.addRow(tableLine);} catch (Exception e) {failed = true;}
		tableLine[4] = 1111;
		
		tableLine[5] = 1;
		try {brcTable.addRow(tableLine);} catch (Exception e) {failed = true;}
		tableLine[5] = "NARRATIVE";
		
		tableLine[6] = "A";
		try {brcTable.addRow(tableLine);} catch (Exception e) {failed = true;}
		tableLine[6] = 1111;
		
		tableLine[7] = "A";
		try {brcTable.addRow(tableLine);} catch (Exception e) {failed = true;}
		tableLine[7] = 11;
		
		tableLine[8] = "A";
		try {brcTable.addRow(tableLine);} catch (Exception e) {failed = true;}
		tableLine[8] = 1111;
		
		tableLine[9] = "A";
		try {brcTable.addRow(tableLine);} catch (Exception e) {failed = true;}
		tableLine[9] = new BigDecimal("0.00");
		
		tableLine[10] = "A";
		try {brcTable.addRow(tableLine);} catch (Exception e) {failed = true;}
		tableLine[10] = new BigDecimal("0.00");
		
		tableLine[11] = "A";
		try {brcTable.addRow(tableLine);} catch (Exception e) {failed = true;}
		tableLine[11] = new BigDecimal("0.00");
		
		
		if (!failed) Assertions.fail("Invalid inputs not detected");
	}
	
}














