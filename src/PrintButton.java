import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;


public class PrintButton extends JButton implements MouseListener{
	private Drawer drawer;
public PrintButton(Drawer drawer){
	super("print");
	this.drawer=drawer;
	addMouseListener(this);
}

@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent e) {
	drawer.printDrawing();
}

@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
} 
}
