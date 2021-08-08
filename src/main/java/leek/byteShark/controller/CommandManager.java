package leek.byteShark.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import javax.swing.table.TableModel;

import leek.byteShark.controller.commands.*;
import leek.byteShark.model.CRBDataModel.CRBBase;
import leek.byteShark.model.CRBDataModel.CRBData;
import leek.byteShark.model.CRBDataModel.CRBDataIngestor;
import leek.byteShark.model.interfaces.IDataFormatter;

public class CommandManager {

	private ChangeHistory history = new ChangeHistory();
	private List<CRBData> brc;
	private Semaphore updateFlag = new Semaphore(1);
	
	public CommandManager(List<CRBData> generalLines) {
		super();
		this.brc = generalLines;
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
		
		Command cmd = new ChangeDataCommand(col, row, brc.get(row), data);
		
		cmd.update();
		updateFlag.release();
		
		history.queueCommand(cmd);
	}
	
	/**Import a BRC from a new data file.
	 * @param address
	 * @return
	 * @throws IOException
	 */
	public static List<CRBBase> importNewBRC(String address) throws IOException {
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(address));
		IDataFormatter<CRBBase> formatter = new CRBDataIngestor ();
		
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
	 * @param brc
	 * @param brcTable
	 */
	public static void pushDataToTable(List<CRBBase> brc) {
		
		//Extract actual data lines
		List<CRBData> generalLines = extractDataLines(brc);
		
		//Check for invalid data
		if (generalLines.size() < 1) throw new IllegalArgumentException("No Data lines detected");
		
		for (CRBData dataLine: generalLines) {
			
			
			//Format for data table
			Object[] tableLine = {
					dataLine.getLineNumber(),//
					dataLine.getLocation(),//
					dataLine.getQuantity(),//
					dataLine.getConditionCode(),//
					Integer.parseInt(dataLine.getAppliedJobCode()),//
					dataLine.getNarrative(),//
					Integer.parseInt(dataLine.getRemovedJobCode()),//
					dataLine.getWhyMadeCode(),//
					dataLine.getResponsabilityCode(),//
					dataLine.getLaborCharge(),
					dataLine.getMaterialCharge(),
					dataLine.getLaborCharge().add(dataLine.getMaterialCharge())
					};
			
		}
		
	}
	
	/**Extract the actual data lines from the set removing the summary and billing lines
	 * @param brc2
	 * @return
	 */
	public static List<CRBData> extractDataLines(List<CRBBase> brc) {
		
		List<CRBData> repairLines = new ArrayList<CRBData>();
		
		for (CRBBase line: brc) {
			if (line.getRecordFormat() == 1) {
				repairLines.add((CRBData)line);
			}
		}
		
		System.out.println(repairLines.size() + " Records extracted");
		
		return repairLines;
	}

}











