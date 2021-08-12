package leek.byteShark.model.database;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.Data;
import lombok.Data;

@Data
@Entity
@Table(name = "SHOPPING")
public class Shopping {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String carInitial;
	private int number;
	private char carType;
	
	private String invoiceNumber = "";
	private LocalDate invoiceDate;
	private String detailSource = "BR";
	private String billingParty = "";
	
	private LocalDate accountDate;
	private LocalDate arrivalDate;
	private LocalDate repairDate;
	
	private int SPLC;
	private char loadindicator;
	
	private String defectCard;
	private LocalDate defectDate;
	
}
