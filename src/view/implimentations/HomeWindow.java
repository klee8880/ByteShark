package view.implimentations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import view.interfaces.IHomeWindow;

/** Home window that shows after startup is complete Begins with an empty table
 * @author klee8
 *
 */
public class HomeWindow extends JFrame implements IHomeWindow{
	
    private final int defaultWidth = 1250;
    private final int defaultHeight = 800;
    private final String defaultTitle = "Byte Shark";
    private final Insets defaultButtonDimensions = new Insets(5, 8, 5, 8);

	public HomeWindow(JPanel BRCTable) {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(defaultTitle);
        this.setSize(defaultWidth, defaultHeight);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //TODO: Control Bar
        
        this.setContentPane(BRCTable);
        
        this.pack();
        this.setVisible(true);
        
	}

	private JPanel createWindow() {
		JPanel contentPane = createBackgroundPanel();
        //JPanel buttonPanel = createMenu();
        //contentPane.add(buttonPanel, BorderLayout.NORTH);
        return contentPane;
	}
	
	private JPanel createBackgroundPanel() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        return contentPane;
    }

}
