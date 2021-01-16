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
 * 
 * Table Scheme:
 * 		Row: Integer
 * 		Location: String
 * 		Qty: Integer
 * 		Con Code: Integer
 *		App Jobe Code: Integer
 *		Description: String
 *		Rmv Job Code: Integer
 *		Why Made: Integer
 *		Resp Code: Integer
 *		Labor: BigDecimal
 *		Material: BigDecimal
 *		Total: BigDecimal
 * 
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
	
	private static final Class[] dataType = {
			Integer.class,
			String.class,
			Integer.class,
			Integer.class,
			Integer.class,
			String.class,
			Integer.class,
			Integer.class,
			Integer.class,
			BigDecimal.class,
			BigDecimal.class,
			BigDecimal.class};
	
	private static Object[][] DEFAULTDATA = {};
	
	class BRCModel extends DefaultTableModel{
		
		BRCModel(Object[][] data, String [] scheme ) { 
			super(data,scheme); 
		}
		
		@Override
		public Class<?> getColumnClass(int index) {
			return dataType[index];
		}
	};
	
	private JTable table;
	private DefaultTableModel model;

	//----Constructors----
	public BRCPanel(TableModelListener listener) {
		
		//Table Element
		this.model = new BRCModel(DEFAULTDATA, SCHEME);
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
	
	/**
	 * @param newRow
	 */
	public void addRow(Object[] newRow) {
		model.addRow(newRow);
	}

	//Getters & Setters
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	
}





















