/* Line.java
 * 
 * Adapted as a swing application from an
 * awt applet by unknown author
 * 
 * Nick Brixius, July 2005
*/

import java.awt.*;

// Represents a line in a drawing

class Line {
	protected int x1, y1, x2, y2;
	protected Color col;

	Line(int x1, int y1, int x2, int y2, Color col) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.col = col;
	}

	void draw(Graphics g) {
		g.setColor(col);
		g.drawLine(x1, y1, x2, y2);
	}
}

