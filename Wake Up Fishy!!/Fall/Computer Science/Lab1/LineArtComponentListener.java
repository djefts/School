/* LineArtComponentListener.java
 * 
 * Adapted as a swing application from an
 * awt applet by unknown author
 * 
 * Nick Brixius, July 2005
*/

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

//  Listener that reacts to resizing of application window

public class LineArtComponentListener  implements ComponentListener {
	private LineArt la;
	public LineArtComponentListener(LineArt la){
		this.la = la;
	}
	public void componentHidden(ComponentEvent arg0) {}
	public void componentMoved(ComponentEvent arg0) {	}
	public void componentResized(ComponentEvent arg0) {
		la.panelResize();
	}
	public void componentShown(ComponentEvent arg0) {}
}
