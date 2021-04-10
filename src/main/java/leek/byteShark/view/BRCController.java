package leek.byteShark.view;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import leek.byteShark.TEMPBRCSingleton;
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
		BRCWrapper wrapper = new BRCWrapper(data);
		
		model.addAttribute("carNumber", data.get(1).getCarInitial() + " " + data.get(1).getCarNumber());
		model.addAttribute("brcWrapper",wrapper);
		
		return "BRCTable";
	}
	
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
	
	/**Save incoming post data from a change to the general brc table
	 * @param wrapper
	 * @param bindingResult
	 * @return
	 */
	@PostMapping
    public String saveBRC(@ModelAttribute("brcWrapper") BRCWrapper wrapper, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
        	System.out.println("Error on Post Mapping");
            return "redirect:/brc";
        }

        TEMPBRCSingleton.setBRC(wrapper.getData());
        
        return "redirect:/brc";
    }
	
	@PostMapping("/AddLine")
    public String addLine(@ModelAttribute("newCRBData") CRBData data, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
        	System.out.println("Error on Post Mapping");
            return "redirect:/brc";
        }
        
        System.out.println("Hit the add line point.");
        
        return "redirect:/brc";
    }
	
}
