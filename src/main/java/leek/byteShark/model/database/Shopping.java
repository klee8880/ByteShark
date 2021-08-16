package leek.byteShark.model.database;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.Data;
import lombok.Data;

@Data
@Entity
public class Shopping {
	
	protected Shopping() {}
	
	public Shopping(String carInitial, int carNumber) {
		super();
		this.carInitial = carInitial;
		this.carNumber = carNumber;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String carInitial;
	private Integer carNumber;
	private Character carType;
	
	private String invoiceNumber = "";
	private LocalDate invoiceDate;
	private String detailSource = "BR";
	private String billingParty = "";
	
	private LocalDate accountDate;
	private LocalDate arrivalDate;
	private LocalDate repairDate;
	
	private Integer SPLC;
	private Character loadIndicator = 'E';
	
	private String defectCard;
	private LocalDate defectDate;
	
}
