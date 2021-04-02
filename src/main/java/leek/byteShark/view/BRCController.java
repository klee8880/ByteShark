package leek.byteShark.view;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import leek.byteShark.controller.CommandManager;
import leek.byteShark.model.CRBDataModel.CRBBase;
import leek.byteShark.model.CRBDataModel.CRBData;

@Controller
public class BRCController {

	private static String ADDRESS= "./InvoiceTestData.txt";
	
	@GetMapping("/brc")
	public String showBRC(Model model){
		
		
		//TODO: Populate the table
		try {
			List<CRBBase> brc = CommandManager.importNewBRC(ADDRESS);
			List<CRBData> generalLines = CommandManager.extractDataLines(brc);
			
			model.addAttribute("BRCLines",generalLines);
			
		} catch (IOException e) {e.printStackTrace();}
		
		return "BRCTable";
	}
	
}
