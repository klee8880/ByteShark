package view.implimentations;

import javax.swing.JTable;

public class BRCTable extends JTable{

	private static final String [] SCHEME= {
			"Location",
			"Qty",
			"Con Code",
			"App Job Code",
			"Description",
			"Rmv Job Code",
			"Why Made",
			"Resp",
			"Labor",
			"Materials",
			"TotalCharge"
	};
	
	private static final Object[][] DEFAULTDATA = {};
	
	public BRCTable() {
		super(DEFAULTDATA, SCHEME);
	}
	
}
