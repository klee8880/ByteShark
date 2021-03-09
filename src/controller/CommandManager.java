package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import javax.swing.table.TableModel;

import controller.commands.*;
import model.CRBDataModel.CRBDataIngestor;
import model.CRBDataModel.CRBGeneralData;
import model.CRBDataModel.CRBLine;
import model.interfaces.IDataFormatter;
import view.BRCPanel.BRCEvent;
import view.BRCPanel.BRCPanel;
import view.interfaces.IBRCPanel;
import view.interfaces.IHomeWindow;

public class CommandManager {

	private ChangeHistory history = new ChangeHistory();
	private List<CRBGeneralData> brc;
	private List<IBRCPanel> panels = new ArrayList<IBRCPanel>();
	private Semaphore updateFlag = new Semaphore(1);
	
	public CommandManager(List<CRBGeneralData> generalLines) {
		super();
		this.brc = generalLines;
	}
	
	public void addUI(IBRCPanel panel){
		panels.add(panel);
	}

	private void undo() {
		try {
			updateFlag.acquire();
			history.undo();
			updateFlag.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void redo(){
		try {
			updateFlag.acquire();
			history.redo();
			updateFlag.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**Change the background data based on the changes made in the UI
	 * @param col
	 * @param row
	 * @param data
	 * @param model
	 */
	void changeData(int col, int row, Object data, TableModel model) {
        
		//Flag to check that the change is not spawned from the command manager
		if (!updateFlag.tryAcquire()) return;
		
		Command cmd = new ChangeDataCommand(col, row, brc.get(row), data, panels);
		
		cmd.update();
		updateFlag.release();
		
		history.queueCommand(cmd);
	}
	
	/**Import a BRC from a new data file.
	 * @param address
	 * @return
	 * @throws IOException
	 */
	public static List<CRBLine> importNewBRC(String address) throws IOException {
		
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(address));
		IDataFormatter formatter = new CRBDataIngestor();
		
		String line;
		line = reader.readLine();
		while (line != null) {
			formatter.appendFromString(line);
			line = reader.readLine();
		}
		
		reader.close();
		return formatter.outputData();
	}
	
	/**Push Data Lines to the selected table.
	 * @param brc2
	 * @param brcTable
	 */
	public static void pushDataToTable(List<CRBLine> brc2, BRCPanel brcTable) {
		
		//Extract actual data lines
		List<CRBGeneralData> generalLines = extractDataLines(brc2);
		
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
	 * @param brc2
	 * @return
	 */
	public static ArrayList<CRBGeneralData> extractDataLines(List<CRBLine> brc2) {
		
		ArrayList<CRBGeneralData> repairLines = new ArrayList<CRBGeneralData>();
		
		for (CRBLine line: brc2) {
			if (line.getRecordFormat() == 1) {
				repairLines.add((CRBGeneralData)line);
			}
		}
		
		System.out.println(repairLines.size() + " Records extracted");
		
		return repairLines;
	}
	
	public void connectEvents(IHomeWindow window) {
		window.connectButtons(BRCEvent.Redo, ()-> {redo();});
		window.connectButtons(BRCEvent.Undo, ()-> {undo();});
	}
	
}











