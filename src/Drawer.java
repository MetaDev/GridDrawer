import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

class Drawer extends JPanel implements MouseListener {
	private int nrOfColumns = 8;
	private int nrOfRows = 8;
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

	public Drawer(int nrOfColumns, int nrOfRows) {
		super();
		
		this.nrOfColumns = nrOfColumns;
		this.nrOfRows = nrOfRows;
		drawing = new int[nrOfColumns][nrOfRows];
		addMouseListener(this);
	}

	public Drawer() {
		super();
		drawing = new int[nrOfColumns][nrOfRows];
	}

	public void paint(Graphics g) {
		int size = this.getHeight();

		int width = size / nrOfColumns;
		int height = size / nrOfRows;
		for (int x = 0; x < nrOfColumns; x++) {
			for (int y = 0; y < nrOfRows; y++) {
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

	public void printDrawing() {
		System.out.println();
		System.out.print("{");
		for (int x = 0; x < nrOfColumns; x++) {
			System.out.print("{");
			//axis of MetaChess is in lower left corner
			for (int y = nrOfRows-1; y >= 0; y--) {
				System.out.print(drawing[x][y]);
				// seperate with comma, except last
				if (y != 0)
					System.out.print(',');
			}
			System.out.print("}");
			if (x != nrOfColumns - 1) {
				System.out.print(',');
				System.out.println();
			}
		}
		System.out.print("}");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int sizeOfTile = this.getPreferredSize().height/nrOfColumns;
		int x = e.getX();
		int y = e.getY();
		// convert coordinates to column and row
		int i = (int)Math.floor((float)x/(float)sizeOfTile);
		int j =(int) Math.floor((float)y/(float)sizeOfTile);
		//flip color
		if(i<nrOfColumns && j<nrOfRows&& i>=0 && j>=0)
		drawing[i][j]=(drawing[i][j]+1)%2;
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