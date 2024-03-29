package leek.byteShark.view;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import leek.byteShark.TEMPBRCSingleton;
import leek.byteShark.model.CRBDataModel.CRBData;

@Controller
public class BRCController {

	/**Display an entire brc table as a web page
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@GetMapping("/brc")
	public String showBRC(Model model) throws IOException{
		
		List<CRBData> data = TEMPBRCSingleton.getBRC();
		
		//Title
		model.addAttribute("carNumber", data.get(0).getCarInitial() + " " + data.get(1).getCarNumber());
		
		//Edit line format
		BRCWrapper update = new BRCWrapper(data);
		model.addAttribute("update",update);
		
		return "BRCTable";
	}

	@GetMapping("/brc/edit/{lineNumber}")
	public String editLine(@PathVariable(value="lineNumber") final Integer lineNumber, Model model) throws IOException{
		
		List<CRBData> data = TEMPBRCSingleton.getBRC();	
		
		//Title
		model.addAttribute("carNumber", data.get(0).getCarInitial() + " " + data.get(1).getCarNumber());
		
		for (CRBData line: data) {
			if (line.getLineNumber() == lineNumber) {
				model.addAttribute("Row", lineNumber);
				model.addAttribute("BRCData", line);
				return "EditDataLine";
			}
		}
		
		return "EditDataLine";
	}
	
	@GetMapping("/brc/edit/new")
	public String addLine(Model model) throws IOException{
		
		List<CRBData> data = TEMPBRCSingleton.getBRC();	
		
		//Title
		model.addAttribute("carNumber", data.get(0).getCarInitial() + " " + data.get(1).getCarNumber());
		
		model.addAttribute("Row", "new");
		
		CRBData line = new CRBData();
		line.setLineNumber(data.size() + 1);
		
		model.addAttribute("BRCData", line);
		
		return "EditDataLine";
	}
	
	@PostMapping("/brc/edit/{lineNumber}")
	public String editLine(@PathVariable(value="lineNumber") final Integer lineNumber, @ModelAttribute("BRCData")CRBData dataLine, BindingResult bindingResult) throws IOException {
		
		List<CRBData> data = TEMPBRCSingleton.getBRC();

		for(CRBData line: data) {
			if (line.getLineNumber() == lineNumber) {
				int index = data.indexOf(line);
				data.set(index, dataLine);
				
				return "redirect:/brc";
			}
		}

		return "redirect:/brc";
	}
	
	@PostMapping("/brc/edit/new")
	public String addLine(@ModelAttribute("BRCData")CRBData dataLine, BindingResult bindingResult) throws IOException {
		
		List<CRBData> data = TEMPBRCSingleton.getBRC();

		data.add(dataLine);

		return "redirect:/brc";
	}
	
	@PostMapping("/brc/update")
    public String saveBRC(@ModelAttribute("update") BRCWrapper wrapper, BindingResult bindingResult) {

        TEMPBRCSingleton.setBRC(wrapper.getData());

        return "redirect:/brc";
    }
}
