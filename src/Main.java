import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main {
	


	  public static void main(String[] a) {
	    JFrame window = new JFrame();
	    JPanel mainframe=new JPanel(new GridLayout(0,2));
	    JPanel buttonFrame = new JPanel(new GridLayout(4,0));
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(30, 30, 800, 400);
	    
	    Drawer drawer = new Drawer();
	    drawer.setFocusable(true);
	    window.getContentPane().addMouseListener(drawer);
	    buttonFrame.add(new PrintButton(drawer));
	    buttonFrame.add(new SaveButton(drawer));
	    buttonFrame.add(new LoadButton(drawer));
             buttonFrame.add(new LoadHexButton(drawer,window));
	    mainframe.add(drawer);

	    mainframe.add(buttonFrame);
	    
	    window.getContentPane().add(mainframe);
	    
	    window.setVisible(true);
	  }
	
}
