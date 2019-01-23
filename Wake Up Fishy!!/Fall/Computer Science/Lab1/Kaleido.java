/* Kaleido.java
 * 
 * Adapted as a swing application from an
 * awt applet by unknown author
 * 
 * Nick Brixius, July 2005
*/

import java.awt.*;

//  Implements line algorithm for kaleidoscope drawing

class Kaleido
{
	private int x1, y1, x2, y2;
	private Color col;
	private Dimension size;

	Kaleido(int x1, int y1, int x2, int y2, Color col, Dimension size) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.col = col;
		this.size = size;
	}

	void draw(Graphics g) {
		g.setColor(col);
		g.drawLine(x1, y1, x2, y2);
		g.drawLine(size.width-x1, y1, size.width-x2, y2);
		g.drawLine(x1, size.height-y1, x2, size.height-y2);
		g.drawLine(size.width-x1, size.height-y1, size.width-x2, size.height-y2);
		g.drawLine(y1, x1, y2, x2);
		g.drawLine(y1, size.width-x1, y2, size.width-x2);
		g.drawLine(size.height-y1, x1, size.height-y2, x2);
		g.drawLine(size.height-y1, size.width-x1, size.height-y2, size.width-x2);
	}
}

