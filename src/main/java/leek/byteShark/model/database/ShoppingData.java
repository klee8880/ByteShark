package leek.byteShark.model.database;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "SHOPPINGDATA")
public class ShoppingData {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int row;
	
	private String location;
	private int quantity;
	private short conditionCode;
	private String applied;
	private String Description;
	private String removed;
	private short whyMade;
	private short responsability;
	private BigDecimal laborCharge;
	private BigDecimal materialCharge;
	private char materialSign;
	
	
}
