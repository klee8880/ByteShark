package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.TableModel;

import controller.commands.*;
import model.CRBDataModel.CRBDataIngestor;
import model.CRBDataModel.CRBGeneralData;
import model.CRBDataModel.CRBLine;
import view.BRCPanel.BRCEvent;
import view.BRCPanel.BRCPanel;
import view.BRCPanel.IBRCPanel;
import view.BRCPanel.IHomeWindow;

public class CommandManager {

	private ChangeHistory history = new ChangeHistory();
	private ArrayList<CRBGeneralData> brc;
	private ArrayList<IBRCPanel> panels = new ArrayList<IBRCPanel>();
	
	public CommandManager(ArrayList<CRBGeneralData> brc) {
		super();
		this.brc = brc;
	}
	
	public void addUI(IBRCPanel panel){
		panels.add(panel);
	}

	private void undo() {
		history.undo();
	}
	
	private void redo() {
		history.redo();
	}
	
	/**Change the background data based on the changes made in the UI
	 * @param col
	 * @param row
	 * @param data
	 * @param model
	 */
	void changeData(int col, int row, Object data, TableModel model) {
        
		Command cmd = new ChangeDataCommand(row, brc.get(row), panels, data);
		
		cmd.updateData();
		history.queueCommand(cmd);
	}
	
	/**Import a BRC from a new data file.
	 * @param address
	 * @return
	 * @throws IOException
	 */
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
	
	/**Push Data Lines to the selected table.
	 * @param dataSet
	 * @param brcTable
	 */
	public static void pushDataToTable(ArrayList<CRBLine> dataSet, BRCPanel brcTable) {
		
		//Extract actual data lines
		ArrayList<CRBGeneralData> generalLines = extractDataLines(dataSet);
		
		//Check for invalid data
		if (generalLines.size() < 1) throw new IllegalArgumentException("No Data lines detected");
		
		int lineCount = 0;
		
		for (CRBGeneralData dataLine: generalLines) {
			
			lineCount++;
			
			//Format for data table
			Object[] tableLine = {
					lineCount,//
					dataLine.getLocation(),//
					dataLine.getQuantity(),//
					dataLine.getConditionCode(),//
					Integer.parseInt(dataLine.getAppliedJobCode()),//
					dataLine.getNarrative(),//
					Integer.parseInt(dataLine.getRemovedJobCode()),//
					Integer.parseInt(dataLine.getWhyMadeCode()),//
					dataLine.getResponsabilityCode(),//
					dataLine.getLaborCharge(),
					dataLine.getMaterialCharge(),
					dataLine.getLaborCharge().add(dataLine.getMaterialCharge())
					};
			//Push lines to table
			brcTable.addRow(tableLine);
			
		}
		
	}
	
	/**Extract the actual data lines from the set removing the summary and billing lines
	 * @param dataSet
	 * @return
	 */
	public static ArrayList<CRBGeneralData> extractDataLines(ArrayList<CRBLine> dataSet) {
		
		ArrayList<CRBGeneralData> repairLines = new ArrayList<CRBGeneralData>();
		
		for (CRBLine line: dataSet) {
			if (line.getRecordFormat() == 1) {
				repairLines.add((CRBGeneralData)line);
			}
		}
		
		System.out.println(repairLines.size() + " Records extracted");
		
		return repairLines;
	}
	
	public void connectEvents(IHomeWindow window) {
		window.connectButtons(BRCEvent.Redo, ()-> redo());
		window.connectButtons(BRCEvent.Undo, ()-> undo());
	}
	
}











