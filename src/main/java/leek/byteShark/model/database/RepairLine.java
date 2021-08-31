package leek.byteShark.model.database;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;

@Data
@Entity
@IdClass(RepairLineID.class)
public class RepairLine {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer shoppingId;
	@Id
	private Integer lineNumber;
	
	private String location;
	private Integer quantity;
	private Short conditionCode;
	private String applied;
	private String Description;
	private String removed;
	private Short whyMade;
	private Short responsability;
	private BigDecimal laborCharge;
	private BigDecimal materialCharge;
	private Character materialSign;

}
