package leek.byteShark.view;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import leek.byteShark.TEMPBRCSingleton;
import leek.byteShark.controller.CommandManager;
import leek.byteShark.model.CRBDataModel.CRBBase;
import leek.byteShark.model.CRBDataModel.CRBData;

@Controller
@RequestMapping("/brc")
public class BRCController {
	
	/**Display an entire brc table as a web page
	 * @param model
	 * @return
	 */
	@GetMapping
	public String showBRC(Model model){
		
		List<CRBData> data = TEMPBRCSingleton.getBRC();
		
		model.addAttribute("BRCLines",data);
		
		return "BRCTable";
	}
	
	
	//TODO: Edit a brc line effectively
	/**
	 * @param model
	 * @return
	 */
	@GetMapping("/edit/{lineNumber}")
	public String editLine(@PathVariable(value="lineNumber") final String lineNumber, Model model){
		
		List<CRBData> data = TEMPBRCSingleton.getBRC();
		
		for (CRBData line: data) {
			model.addAttribute("BRCData", line);
			break;
		}
		
		return "BRCDetail";
	}
	
	
	
}
