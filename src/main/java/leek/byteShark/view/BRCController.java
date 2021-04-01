package leek.byteShark.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BRCController {

	@GetMapping("/brc")
	public String showAll() {
		
		//TODO: Populate the table
		
		return "BRCTable";
	}
	
}
