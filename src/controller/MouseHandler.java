package controller;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import model.Point;

public class MouseHandler implements MouseInputListener{

	private Point begin  = new Point();
	private Point end  = new Point();;
	
	//Constructors
	public MouseHandler() {
		super();
	}
	
	//Methods
	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		begin = recordPoint(arg0);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}
	
	/**
	 * @param MouseEvent e
	 * @return A point (x,y) extracted from the input mouse event
	 */
	private Point recordPoint(MouseEvent e) {
		return new Point(e.getX(), e.getY());
	}

}
