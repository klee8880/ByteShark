package main;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JPanel;
import javax.swing.event.TableModelListener;

import controller.TableListener;
import view.implimentations.*;
import view.interfaces.*;

public class Main {

	private static String TESTFILE= "/500ByteReader/InvoiceTestData.txt"; 
	
	public static void main (String[] args) throws Exception {

		//Start Event Handlers
		TableModelListener tableListener = new TableListener();
		
		//assemble the UI
		JPanel brcTable = new BRCPanel(tableListener);
		IHomeWindow homeWindow = new HomeWindow(brcTable);
		
		//TODO: Activate Control Structure
		
	}
	
}
