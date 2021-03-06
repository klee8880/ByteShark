package view.BRCPanel;

import java.awt.Dimension;
import java.io.File;
import java.math.BigDecimal;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**Table Used to display & manipulate basic BRC data for one repair event.
 * Installed Table Listener should be triggered whenever the user changes an element of the table.
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
			Short.class,
			Integer.class,
			String.class,
			Integer.class,
			Integer.class,
			Short.class,
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

	@Override
	public void addRow(Object[] newRow) {
		
		//Validation
		if (newRow == null) throw new IllegalArgumentException("Null passed as argument");
		if (newRow.length != dataType.length) throw new IllegalArgumentException("Incorrect number of arguments in array");
		
		for (int i = 0; i < newRow.length; i++) {
			if(newRow[i].getClass() != dataType[i]) {
				throw new IllegalArgumentException("Incorrect Datatype at column " + i + "{Expected " + dataType[i] + " received " + newRow[i].getClass() +"}");
			}
		}
		
		model.addRow(newRow);
	}

	@Override
	public File getFilePath(String prompt) {
		
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle(prompt);
		
		int result = chooser.showOpenDialog(this);
		
		switch(result) {
		
		case JFileChooser.APPROVE_OPTION:
			return chooser.getSelectedFile();
			
		case JFileChooser.CANCEL_OPTION:
			return null;

		default:
			throw new IllegalArgumentException();
		}

	}

	@Override
	public void setValueAt(Object data, int row, int col) {
		model.setValueAt(data, row, col);
	}
}





















