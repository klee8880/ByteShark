package leek.byteShark.view;

import java.util.List;

import leek.byteShark.model.database.Shopping;
import lombok.Data;

@Data
public class ShoppingList {
	List<Shopping> data;
	
	public ShoppingList (List<Shopping> data){
		super();
		this.data = data;
	}
}
