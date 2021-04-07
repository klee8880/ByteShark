package leek.byteShark;

import java.io.IOException;
import java.util.List;

import leek.byteShark.controller.CommandManager;
import leek.byteShark.model.CRBDataModel.CRBBase;
import leek.byteShark.model.CRBDataModel.CRBData;

/**Temporary singleton holder used to test spring page editting.
 * @author klee8
 *
 */
public class TEMPBRCSingleton {

	private static List<CRBData> brc = null;
	private static String ADDRESS= "./InvoiceTestData.txt";
	
	public static List<CRBData> getBRC(){
		
		//Create table if not already created
		if (brc == null) {
			try {
				List<CRBBase> extract = CommandManager.importNewBRC(ADDRESS);
				brc = CommandManager.extractDataLines(extract);
			} catch (IOException e) {e.printStackTrace();}
		}
		
		return brc;
		
	}
	
}
