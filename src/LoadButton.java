import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LoadButton extends JButton implements ActionListener {
	private Drawer drawer;

	public LoadButton(Drawer drawer) {
		super("Load");
		this.drawer = drawer;
		addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// load string from file and save as in[][] drawing
		FileDialog fc = new FileDialog(new Frame(), "Save", FileDialog.LOAD);
		fc.setFile("*.txt");
		fc.setVisible(true);
		
		String fn = fc.getFile();
		String path = fc.getDirectory();
		if (fn != null && fn!="") {
			
			try {
				System.out.println(path+"/"+fn);
				String drawingString = readFile(path+"/"+fn);
				drawer.setDrawing(drawer.stringToDrawing(drawingString));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		drawer.repaint();

	}
	private String readFile(String pathname) throws IOException {

	    File file = new File(pathname);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
	    Scanner scanner = new Scanner(file);
	    String lineSeparator = System.getProperty("line.separator");

	    try {
	        while(scanner.hasNextLine()) {        
	            fileContents.append(scanner.nextLine() + lineSeparator);
	        }
	        return fileContents.toString();
	    } finally {
	        scanner.close();
	    }
	}
}
