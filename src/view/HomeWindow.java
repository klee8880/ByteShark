package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import view.BRCPanel.BRCEvent;
import view.BRCPanel.IHomeWindow;

/** Home window that shows after startup is complete Begins with an empty table
 * @author klee8
 *
 */
public class HomeWindow extends JFrame implements IHomeWindow{
	
    private final int defaultWidth = 1250;
    private final int defaultHeight = 800;
    private static final String DEFAULTTITLE = "Byte Shark";
    private final Insets defaultButtonDimensions = new Insets(5, 8, 5, 8);

	public HomeWindow(JPanel BRCTable) {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(DEFAULTTITLE);
        this.setSize(defaultWidth, defaultHeight);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //TODO: Control Bar
        JPanel controlPane = createButtonPanel();
        
        controlPane.add(BRCTable, BorderLayout.CENTER);
        
        this.pack();
        this.setVisible(true);
        
	}
	
	private JPanel createButtonPanel() {
		
		//Background
		JPanel backgroundPanel = new JPanel();
		backgroundPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		backgroundPanel.setLayout(new BorderLayout(0, 0));
		backgroundPanel.setBackground(Color.WHITE);
        setContentPane(backgroundPanel);
        
        //Button Panel
        JPanel buttons = new JPanel();
        FlowLayout flowLayout = (FlowLayout) buttons.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        buttons.setBackground(Color.lightGray);
        
        //Add Buttons to Panel
        for (BRCEvent event: BRCEvent.values()) {
        	
        	JButton nextButton = new JButton(event.toString());
        	nextButton.setForeground(Color.BLACK);
        	nextButton.setBackground(Color.WHITE);
        	nextButton.setBorder(new LineBorder(Color.BLACK));
        	
        	buttons.add(nextButton);
        }
        
        //Assemble button panel
        backgroundPanel.add(buttons, BorderLayout.NORTH);
		
		return backgroundPanel;
	}

}
