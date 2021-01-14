package main;

import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.event.TableModelListener;

import controller.CommandManager;
import controller.TableListener;
import model.CRBDataModel.CRBLine;
import view.implimentations.*;
import view.interfaces.*;

public class Main {

	private static String TESTFILE= "/Eclipse/ByteShark/InvoiceTestData.txt"; 
	
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
