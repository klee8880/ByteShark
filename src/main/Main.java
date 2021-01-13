package main;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JPanel;
import view.implimentations.*;
import view.interfaces.*;

public class Main {

	private static String TESTFILE= "/500ByteReader/InvoiceTestData.txt"; 
	
	public static void main (String[] args) throws Exception {

		//assemble the UI
		JPanel brcTable = new BRCPanel();
		IHomeWindow homeWindow = new HomeWindow(brcTable);
		
		//TODO: Activate Control Structure
		
	}
	
}
