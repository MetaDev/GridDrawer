
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LoadHexButton extends JButton implements ActionListener {

    private Drawer drawer;
    private JFrame frame;

    public LoadHexButton(Drawer drawer, JFrame frame) {
        super("Load Hex");
        this.drawer = drawer;
        this.frame = frame;
        addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String hex = (String) JOptionPane.showInputDialog(
                frame,
                "Input HEX code",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE);
      
                int[][] drawing = convertHexToArray(hex);

        drawer.setDrawing(drawing);

        drawer.repaint();

    }

    public int isBitSet(String hex, int bitNumber) {
        long val = Long.valueOf(hex, 16);
        if ((val & (1 << bitNumber)) != 0) {
            return 1;
        }
        return 0;
    }

    public int[][] convertHexToArray(String hex) {
        String bin = String.format("%64s",new BigInteger(hex, 16).toString(2)).replace(' ', '0');
        int[][] drawing = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(bin.charAt(i*8+j)=='1'){
                     drawing[i][j]=1;
                }
            }

        }
        //convert hex to int array
        return drawing;
    }

}
