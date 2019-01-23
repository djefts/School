/* LineArt.java
 * 
 * Adapted as a swing application from an
 * awt applet by unknown author
 * 
 * Nick Brixius, July 2005
*/
import javax.swing.*;
import java.awt.*;

// Primary drawing surface for line art

public class LineArt extends JPanel implements Runnable
{
	private final int LNUM1 = 64;
	private final int CNUM2 = 32;
	private final int CNUM3 = 180;
	private final int CNUM4 = 32;
	private final int LNUM4 = 64;
	private int colorCount;
	private int mode = 0;
	private int xa, ya, xb, yb;
	private int dxa, dya, dxb, dyb;
	private boolean keepRunning = true;

	private Thread kicker = null;
	private Container offImage;
	private Dimension mySize;
	private ControlPanel control;
	private Graphics g;
	private Line[] line = new Line[LNUM1];
	private Kaleido[] kaleido = new Kaleido[LNUM4];
	private Color firstColor;
	private Color lastColor;
	private Color[] lineColor = new Color[LNUM1];

	public LineArt() {
		setBackground(Color.black);
	}

	void drawMode1() {
		g.setColor(randomColor());
		int m = (int)(Math.random()*8+2);
		int n = (int)(Math.random()*8+2);
		int o = (int)(Math.random()*8+2);
		int p = (int)(Math.random()*8+2);
		double q, r, s, t;
		do {
			q = Math.random()*2;
			r = Math.random()*2;
			s = Math.random()*2;
			t = Math.random()*2;
		} while ((q < 1 && s < 1) || (r < 1 && t < 1));
		for (int i = 0; i < 720; i++) {
			double d = (double)i/360*Math.PI;
			int x1 = (int)(mySize.width/2 + mySize.width/4*(Math.sin(d*m))*q);
			int y1 = (int)(mySize.height/2 + mySize.height/4*(Math.cos(d*n))*r);
			int x2 = (int)(mySize.width/2 + mySize.width/4*(Math.cos(d*o))*s);
			int y2 = (int)(mySize.height/2 + mySize.height/4*(Math.sin(d*p))*t);
			g.drawLine(x1, y1, x2, y2);
		}
	}

	void drawMode2() {
		g.setXORMode(Color.black);
		xa = 0;
		dxa = (int)(Math.random()*8+2);
		ya = (int)(mySize.height*Math.random());
		dya = (int)(Math.random()*8+2);
		xb = (int)(mySize.width*Math.random());
		dxb = (int)(Math.random()*8+2);
		yb = 0;
		dyb = (int)(Math.random()*8+2);
		for (int i = 0; i < line.length; i++) {
			line[i] = new Line(-1, -1, -1, -1, Color.black);
		}
		firstColor = randomColor();
		lastColor = randomColor();
		lineColor = blendColor(lastColor, firstColor, CNUM2);
		colorCount = 0;
	}

	void drawMode3() {
		int m = (int)(Math.random()*8+2);
		int n = (int)(Math.random()*8+2);
		int o = (int)(Math.random()*8+2);
		int p = (int)(Math.random()*8+2);
		double q, r, s, t;
		do {
			q = Math.random()*2;
			r = Math.random()*2;
			s = Math.random()*2;
			t = Math.random()*2;
		} while ((q < 1 && s < 1) || (r < 1 && t < 1));
		firstColor = randomColor();
		lastColor = randomColor();
		lineColor = blendColor(lastColor, firstColor, CNUM3);
		colorCount = 0;
		for (int i = 0; i < 360; i++) {
			double d = (double)i/360*Math.PI;
			int x1 = (int)(mySize.width/2 + mySize.width/4*(Math.sin(d*m))*q);
			int y1 = (int)(mySize.height/2 + mySize.height/4*(Math.cos(d*n))*r);
			int x2 = (int)(mySize.width/2 + mySize.width/4*(Math.cos(d*o))*s);
			int y2 = (int)(mySize.height/2 + mySize.height/4*(Math.sin(d*p))*t);
			g.setColor(lineColor[colorCount]);
			g.drawLine(x1, y1, x2, y2);
			if (++colorCount >= CNUM3) {
				firstColor = lastColor;
				lastColor = randomColor();
				lineColor = blendColor(lastColor, firstColor, CNUM3);
				colorCount = 0;
			}
		}
	}
 
	void drawMode4() {
		g.setXORMode(Color.black);
		xa = 0;
		dxa = (int)(Math.random()*5+1);
		ya = (int)(mySize.height*Math.random());
		dya = (int)(Math.random()*5+1);
		xb = (int)(mySize.width*Math.random());
		dxb = (int)(Math.random()*5+1);
		yb = 0;
		dyb = (int)(Math.random()*5+1);
		for (int i = 0; i < kaleido.length; i++) {
			kaleido[i] = new Kaleido(-1, -1, -1, -1, Color.black, mySize);
		}
		firstColor = randomColor();
		lastColor = randomColor();
		lineColor = blendColor(lastColor, firstColor, CNUM4);
		colorCount = 0;
	}

	void changeMode(int mode) {
		if (kicker != null) {
			keepRunning = false;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
			kicker = null;
		}
		this.mode = mode;
		mySize = getSize();
		g = getGraphics();
		g.setPaintMode();
		g.setColor(Color.black);
		g.fillRect(0, 0, mySize.width, mySize.height);
		if (mode == 1) {
			drawMode1();
		} else if (mode == 2) {
			drawMode2();
			if (kicker == null) {
				kicker = new Thread(this);
			}
			keepRunning = true;
			kicker.start();
		} else if (mode == 3) {
			drawMode3();
		} else if (mode == 4) {
			mySize = getSize();
			if(mySize.height > mySize.width){
				int translateY = (mySize.height - mySize.width)/2;
				setSize(new Dimension(mySize.width, mySize.width));
				g.translate(0,translateY);
				
			}else{
				int translateX = (mySize.width - mySize.height)/2;
				setSize(new Dimension(mySize.height, mySize.height));
				g.translate(translateX,0);
			}
			revalidate();
			mySize = getSize();
			drawMode4();
			if (kicker == null) {
				kicker = new Thread(this);
			}
			keepRunning = true;
			kicker.start();
		}
	}


	void updateParam(int a, int b) {
		xa += dxa;
		if (xa < 0) 
			dxa = (int)(Math.random()*a+b);
		else if (xa >= mySize.width) 
			dxa = -(int)(Math.random()*a+b);
		xb += dxb;
		if (xb < 0) 
			dxb = (int)(Math.random()*a+b);
		else if (xb >= mySize.width) 
			dxb = -(int)(Math.random()*a+b);
		ya += dya;
		if (ya < 0) 
			dya = (int)(Math.random()*a+b);
		else if (ya >=mySize.height) 
			dya = -(int)(Math.random()*a+b);
		yb += dyb;
		if (yb < 0) 
			dyb = (int)(Math.random()*a+b);
		else if (yb >= mySize.height) 
			dyb = -(int)(Math.random()*a+b);
	}

	public void paintArt() {
		switch (mode) {
		case 2:
			updateParam(8, 2);
			for (int i = 1; i < line.length; i++) {
				line[i-1] = line[i];
			}
			line[line.length-1] = new Line(xa, ya, xb, yb, lineColor[colorCount]);
			if (++colorCount >= CNUM2) {
				firstColor = lastColor;
				lastColor = randomColor();
				lineColor = blendColor(lastColor, firstColor, CNUM2);
				colorCount = 0;
			}
			line[0].draw(g);
			line[line.length-1].draw(g);
			break;
		case 4:
			updateParam(5, 1);
			for (int i = 1; i < kaleido.length; i++) {
				kaleido[i-1] = kaleido[i];
			}
			kaleido[kaleido.length-1] = new Kaleido(xa, ya, xb, yb, lineColor[colorCount], mySize);
			if (++colorCount >= CNUM4) {
				firstColor = lastColor;
				lastColor = randomColor();
				lineColor = blendColor(lastColor, firstColor, CNUM4);
				colorCount = 0;
			}
			kaleido[0].draw(g);
			kaleido[kaleido.length-1].draw(g);
			break;
		}
	}

	public void run() {
		while (true) {
			if(!keepRunning)
				break;
			paintArt();
			try	{
				Thread.sleep(30);
			} catch (InterruptedException e) {}
		}
	}

	Color randomColor() {
		int r = (int)(Math.random()*256);
		int g = (int)(Math.random()*256);
		int b = (int)(Math.random()*256);
		return new Color(r, g, b);
	}

	Color[] blendColor(Color a, Color b, int n) {
		Color[] col = new Color[n];
		for (int i = 0; i < col.length; i++) {
			int red = a.getRed()*i/col.length + b.getRed()*(col.length-i)/col.length;
			int green = a.getGreen()*i/col.length + b.getGreen()*(col.length-i)/col.length;
			int blue = a.getBlue()*i/col.length + b.getBlue()*(col.length-i)/col.length;
			col[i] = new Color(red, green, blue);
		}
		return col;
	}
	
	public void panelResize(){
		changeMode(mode);
	}
}
