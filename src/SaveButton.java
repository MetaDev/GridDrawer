import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;

public class SaveButton extends JButton implements ActionListener {
	private Drawer drawer;

	public SaveButton(Drawer drawer) {
		super("save");
		this.drawer = drawer;

		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// save drawing as txt;
		FileDialog fc = new FileDialog(new Frame(), "Save", FileDialog.SAVE);
		fc.setFile("*.txt");
		fc.setVisible(true);
		String saveString = drawer.drawingToString(drawer.getDrawing());
		String fn = fc.getFile();
		String path = fc.getDirectory();
		if (fn != null && fn != "") {
			try {
				FileWriter fw = new FileWriter(path + "/" + fn);
				fw.write(saveString);
				fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}
