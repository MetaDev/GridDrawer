
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigInteger;

import javax.swing.JPanel;

class Drawer extends JPanel implements MouseListener {

    private int drawingSize = 8;
    private int[][] drawing;

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        Container c = getParent();
        if (c != null) {
            d = c.getSize();
        } else {
            return new Dimension(10, 10);
        }
        int w = (int) d.getWidth();
        int h = (int) d.getHeight();
        int s = (w < h ? w : h);
        return new Dimension(s, s);
    }

    public Drawer(int size) {
        super();

        this.drawingSize = size;
        drawing = new int[size][size];
        addMouseListener(this);
    }

    public Drawer() {
        super();
        drawing = new int[drawingSize][drawingSize];
    }

    public int[][] getDrawing() {
        return drawing;
    }

    public void setDrawing(int[][] drawing) {
        this.drawing = drawing;
    }

    public void paint(Graphics g) {
        int size = this.getHeight();

        int width = size / drawingSize;
        int height = size / drawingSize;
        for (int x = 0; x < drawingSize; x++) {
            for (int y = 0; y < drawingSize; y++) {
                // draw color
                if (drawing[x][y] == 0) {
                    g.setColor(Color.BLACK);
                }
                if (drawing[x][y] == 1) {
                    g.setColor(Color.WHITE);
                }

                g.fillRect(x * width, y * height, width, height);
                // draw grid
                g.setColor(Color.GRAY);
                g.drawRect(x * width, y * height, width, height);
            }
        }
    }

    public void setColorOfDrawing(int i, int j, int color) {
        drawing[i][j] = color;
    }

    public int[][] stringToDrawing(String text) {
        String[] rows = text.split("\n");
        int[][] drawing = null;
        for (int i = 0; i < rows.length; i++) {

            String row = rows[rows.length - 1 - i];
            for (int j = 0; j < rows.length; j++) {
                if (drawing == null) {
                    drawing = new int[rows.length][rows.length];
                }
                char c = row.toCharArray()[j];
                drawing[j][i] = Integer.parseInt(c + "");
            }

        }

        return drawing;
    }
    

    public String printDrawingToHex(int[][] drawing) {
        //for each 4 bits 1 hex symbol, so 8 rows gives 16 hex symbols
        String[] bin = new String[16];
        String temp="";
        String hex = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                temp += drawing[i][j];
            }
           
        }
        bin=temp.split("(?<=\\G.{4})");
        for(int i=0;i<16;i++){
            hex+=Integer.toHexString(Integer.parseInt(bin[i], 2));
        }
        //convert to hex
        return hex;
    }
 
    public String printDrawing(int[][] drawing) {
        String drawingString = "";
        drawingString += "{";
        for (int y = 0; y < drawingSize; y++) {

            drawingString += ("{");
            // axis of MetaChess is in lower left corner
            for (int x = 0; x < drawingSize; x++) {
                drawingString += (drawing[x][y]);
                // seperate with comma, except last
                if (x != drawingSize - 1) {
                    drawingString += (',');
                }
            }
            drawingString += ("}");
            if (y != drawingSize - 1) {
                drawingString += (',');
                drawingString += ('\n');
            }
        }
        drawingString += ("}");
        return drawingString;
    }

    public String drawingToString(int[][] drawing) {
        String drawingString = "";
        for (int y = 0; y < drawingSize; y++) {
            for (int x = 0; x < drawingSize; x++) {
                drawingString += (drawing[x][drawingSize - 1 - y]);
            }
            if (y != drawingSize - 1) {
                drawingString += '\n';
            }
        }
        return drawingString;
    }

    public void printDrawing() {
        System.out.println("New Drawing: \n");
        System.out.println(printDrawing(drawing));
    }

    public void printDrawingHex() {
        System.out.println("New Drawing: \n");
        System.out.println(printDrawingToHex(drawing));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int sizeOfTile = this.getPreferredSize().height / drawingSize;
        int x = e.getX();
        int y = e.getY();
        // convert coordinates to column and row
        int i = (int) Math.floor((float) x / (float) sizeOfTile);
        int j = (int) Math.floor((float) y / (float) sizeOfTile);
        // flip color
        if (i < drawingSize && j < drawingSize && i >= 0 && j >= 0) {
            drawing[i][j] = (drawing[i][j] + 1) % 2;
        }
        repaint();
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

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
