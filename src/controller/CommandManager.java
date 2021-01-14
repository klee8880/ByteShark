package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.CRBDataModel.CRBDataIngestor;
import model.CRBDataModel.CRBLine;

public class CommandManager {

	public static ArrayList<CRBLine> importNewBRC(String address) throws IOException {
		
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(address));
		
		ArrayList<CRBLine> brc = new ArrayList<CRBLine>();
		
		String line;
		line = reader.readLine();
		while (line != null) {
			brc.add(CRBDataIngestor.readDataLine(line));
			line = reader.readLine();
		}
		
		reader.close();
		return brc;
	}
	
}
