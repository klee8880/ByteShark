package view.implimentations;

import java.awt.Dimension;
import java.math.BigDecimal;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import view.interfaces.IBRCPanel;

/**Table Used to display basic BRC data
 * @author Kevin Lee
 */
public class BRCPanel extends JPanel implements IBRCPanel{
	private static final long serialVersionUID = -5657231907813507325L;
	private static String [] SCHEME= {
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
			{"C",new Integer(0),"09",new Integer(0),"NEW DESCRIPTION",new Integer(0),new Integer(0),new Integer(0),new BigDecimal("50.00"),new BigDecimal("50.00"),new BigDecimal("100.00")}
		};

	//Data model subclass
	private class BRCTableModel extends AbstractTableModel{
		private static final long serialVersionUID = -3496274303177205770L;
		private Object[][] data;
		
		public BRCTableModel(Object[][] data) {this.data = data;}
		
		@Override
		public int getColumnCount() {return SCHEME.length;}

		@Override
		public int getRowCount() {return data.length;}
		
		@Override
		public String getColumnName(int col) {return SCHEME[col];}

		@Override
		public Object getValueAt(int row, int col) {return data[row][col];}
		
		@Override
		public boolean isCellEditable(int row, int col) {return true;}
	}
	
	//----Constructors----
	public BRCPanel() {
		
        //Table Element
        JTable table = new JTable(new BRCTableModel(DEFAULTDATA));
        table.setPreferredScrollableViewportSize(new Dimension(1500, 800));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        
        //Decorate with Scroll Bar
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        this.setOpaque(true);
	}
	
	public BRCPanel(Object[][] data) {
		
		//Validate Data Model
		if (data[0].length != SCHEME.length) 
			throw new IllegalArgumentException("Provided data does not match the data scheme of this table");
		
        //Table Element
        JTable table = new JTable(new BRCTableModel(data));
        table.setPreferredScrollableViewportSize(new Dimension(1500, 800));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        
        //Decorate with Scroll Bar
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        this.setOpaque(true);
	}
	
}





















