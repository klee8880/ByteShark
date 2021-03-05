package main;

import java.util.ArrayList;
import javax.swing.event.TableModelListener;

import controller.*;
import model.CRBDataModel.*;
import view.BRCPanel.*;

public class Main {

	private static String TESTFILE= "./InvoiceTestData.txt"; 
	
	public static void main (String[] args) throws Exception {

		//TODO: TEST FILE INPUT
		ArrayList<CRBLine> brc = CommandManager.importNewBRC(TESTFILE);
		ArrayList<CRBGeneralData> generalLines = CommandManager.extractDataLines(brc);
		
		//Start Event Handlers
		TableModelListener tableListener = new TableListener(new CommandManager(generalLines));
		
		//assemble the UI
		BRCPanel brcTable = new BRCPanel(tableListener);
		IHomeWindow homeWindow = new HomeWindow(brcTable);
		
		//TODO: TEST ADD DATA TO MODEL
		CommandManager.pushDataToTable(brc, brcTable);
	}
	
}
