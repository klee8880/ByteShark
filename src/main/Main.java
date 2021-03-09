package main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;

import controller.*;
import model.CRBDataModel.*;
import view.BRCPanel.*;
import view.interfaces.IHomeWindow;

public class Main {

	private static String TESTFILE= "./InvoiceTestData.txt"; 
	
	public static void main (String[] args) throws Exception {

		//TODO: TEST FILE INPUT
		List<CRBLine> brc = CommandManager.importNewBRC(TESTFILE);
		List<CRBGeneralData> generalLines = CommandManager.extractDataLines(brc);
		
		//Start Event Handlers
		CommandManager manager = new CommandManager(generalLines);
		TableModelListener tableListener = new TableListener(manager);
		
		//assemble the UI
		BRCPanel brcTable = new BRCPanel(tableListener);
		IHomeWindow homeWindow = new HomeWindow(brcTable);
		
		//Controller -> UI connections
		manager.addUI(brcTable);
		manager.connectEvents(homeWindow);
		
		
		//TODO: TEST ADD DATA TO MODEL
		CommandManager.pushDataToTable(brc, brcTable);
	}
	
}
