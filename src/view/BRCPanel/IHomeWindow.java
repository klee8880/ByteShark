package view.BRCPanel;

import javax.swing.JButton;

public interface IHomeWindow {

	/**Connect the button to a given event 
	 * @param eventName
	 * @param event - Lambda functions assignable
	 */
	public void connectButtons(BRCEvent eventName, IEventCallback event);

}
