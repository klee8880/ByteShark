package leek.byteShark.view;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import leek.byteShark.model.database.ShoppingRepository;
import leek.byteShark.model.CRBDataModel.CRBData;
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
		
		//shoppings = shoppingRepo.findByCarNumber(carNumber);
		shoppings = new Stack<Shopping>();
		shoppings.add(new Shopping("ABCD", 123456));
		shoppings.add(new Shopping("ABCD", 654321));
		shoppings.add(new Shopping("ABCD", 987654));
		shoppings.add(new Shopping("ABCD", 456789));
		
		ShoppingList wrapper = new ShoppingList(shoppings);
		
		System.out.println(shoppings.size() + " entries found");
		
		model.addAttribute("update",wrapper);
		
		return "ShoppingSummary";
	}
}
