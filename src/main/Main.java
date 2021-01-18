package main;

import java.util.ArrayList;
import javax.swing.event.TableModelListener;

import controller.CommandManager;
import controller.TableListener;
import model.CRBDataModel.CRBLine;
import view.HomeWindow;
import view.BRCPanel.BRCPanel;
import view.BRCPanel.IHomeWindow;

public class Main {

	private static String TESTFILE= "./InvoiceTestData.txt"; 
	
	public static void main (String[] args) throws Exception {

		//Start Event Handlers
		TableModelListener tableListener = new TableListener();
		
		//assemble the UI
		BRCPanel brcTable = new BRCPanel(tableListener);
		IHomeWindow homeWindow = new HomeWindow(brcTable);
		
		//TODO: TEST FILE INPUT
		ArrayList<CRBLine> brc = CommandManager.importNewBRC(TESTFILE);
		
		//TODO: TEST ADD DATA TO MODEL
		CommandManager.pushDataToTable(brc, brcTable);
	}
	
}
