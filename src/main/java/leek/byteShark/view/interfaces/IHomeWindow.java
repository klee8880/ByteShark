package leek.byteShark.view.interfaces;

import leek.byteShark.view.BRCPanel.BRCEvent;

public interface IHomeWindow {

	/**Connect the button to a given event 
	 * @param eventName
	 * @param event - Lambda functions assignable
	 */
	public void connectButtons(BRCEvent eventName, IEventCallback event);

}
