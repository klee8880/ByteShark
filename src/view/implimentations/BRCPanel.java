package view.implimentations;

import java.awt.Dimension;
import java.math.BigDecimal;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import view.interfaces.IBRCPanel;

/**Table Used to display basic BRC data
 * @author Kevin Lee
 */
public class BRCPanel extends JPanel implements IBRCPanel{
	private static final long serialVersionUID = -5657231907813507325L;
	private static String [] SCHEME= {
			"Row",
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
	private static Object[][] DEFAULTDATA = {
			{new Integer(1),"C",new Integer(0),"09",new Integer(0),"NEW DESCRIPTION",new Integer(0),new Integer(0),new Integer(0),new BigDecimal("50.00"),new BigDecimal("50.00"),new BigDecimal("100.00")}
	};
	private JTable table;
	private DefaultTableModel model;

	//----Constructors----
	public BRCPanel(TableModelListener listener) {
		
		//Table Element
		this.model = new DefaultTableModel(DEFAULTDATA, SCHEME);
		this.model.addTableModelListener(listener);
		
        JTable table = new JTable(model);
        this.table = table;
        table.setPreferredScrollableViewportSize(new Dimension(1500, 800));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        
        //Decorate with Scroll Bar
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        this.setOpaque(true);
	}
	
	/**Update the entire table with new data.
	 * 
	 */
	public void refreshTable() {
		
	}

	
}





















