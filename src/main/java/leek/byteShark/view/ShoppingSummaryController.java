package leek.byteShark.view;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import leek.byteShark.model.database.ShoppingRepository;
import leek.byteShark.model.database.Shopping;

@Controller
public class ShoppingSummaryController {
	
	@Autowired
	private ShoppingRepository shoppingRepo;
	
	@GetMapping("/search")
	public String showAllShoppings(Model model) {
		
		return "ShoppingSummary";
	}
	
	
	@GetMapping("/search/{carNumber}")
	public String showShoppingSubset(Model model, @PathVariable(value="carNumber") final Integer carNumber) {
		
		List<Shopping> shoppings;
		
		shoppings = shoppingRepo.findByCarNumber(carNumber);
		
		return "ShoppingSummary";
	}
}
