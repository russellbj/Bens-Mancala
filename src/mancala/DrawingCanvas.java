/**
 * Created Nov. 2017.
 * Author: Darrah Chavey
 */

package mancala;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * This class will manage the display of the various Mancala games, any
 * status messages associated with them, and interpret clicks on the screen
 * as user actions.<P>
 * 
 * @author Coding by Darrah Chavey
 * 
 * @version 4 June 2011
 */
public class DrawingCanvas extends JPanel implements MouseListener  {
	
	/**
	 * Serial ID to allow implementation of Serializable. We don't actually
	 * implement saving the file this way, but it is expected by "JPanel".
	 */
	protected static final long serialVersionUID = 8095078020386417776L;
	
	/** The font used for drawing status messages on the screen. */
	protected Font drawFont = new Font( "Serif", Font.BOLD, 18 );

	// Local variables for drawing input on the canvas:
	
	/** The color that will be used in drawing to the screen. For now, it's
	 *  always black, but we leave open the possibility of other colors, or
	 *  using two different colors for actions of the two different players.
	 */
	private Color currentLineColor = Color.BLACK;
	
	/** Which of the BoardTypes is being used for this game?	*/
	private BoardTypes boardType = BoardTypes.TWO_ROW;
	
	/** How many columns are there in the game? Includes end bins for that form of game. */
	private int numberOfColumns = 0;
	
	/** How many columns are in this game? */
	public int getNumberOfColumns() {
		return numberOfColumns;
	}

	/** Images used in the game play and animation. */
	protected Image beans[], hand[];
	
	/** No action on a mouseExited() event; it is required to implement MouseMotionListener. */
	public void mouseExited(MouseEvent e) { }

	/** No action on a mouseEntered() event; it is required to implement MouseMotionListener. */
	public void mouseEntered(MouseEvent e) { }

	/** No action on a mouseReleased() event; it is required to implement MouseMotionListener. */
	public void mouseReleased(MouseEvent e) { }

	/** No action on a mousePressed() event; it is required to implement MouseMotionListener. */
	public void mousePressed(MouseEvent e) { }

	/** On a mouseclick, we have to identify where the user clicked, ignore it if it
	 *  doesn't correspond to a bin, and otherwise identify what bin was clicked.
	 *  @param e The event generated by mouse to which we must respond.
	 */
	public void mouseClicked(MouseEvent e) {
		
	}

	/**
	 * Construct the instance of the Drawing Canvas and set those default values 
	 * that don't require the construction of other program internals.
	 * 
	 *  @param beans An array of images used to draw the beans (for sowing) on the screen.
	 *  @param hand  An array of the two images used to draw a hand, holding beans, on the screen.
	 */
	public DrawingCanvas( Image beans[], Image hand[] ) {
		//super();
		super();
		// The largest screen size we can fit on one sheet of paper without scaling:
		setPreferredSize( new Dimension(800,400));
				
		// set initial values of other items
		this.setBackground( Color.WHITE );	// BUG? Doesn't work. Still uses grey background
				
		// Listen for key clicks, mouse clicks and mouse movements:
		setFocusable(true);		// So it can hear key clicks
		
		addMouseListener(this);
		
		this.beans = beans;		// Store these images
		this.hand = hand;
	}	

	/**
	 * Redraw the board, with the representation of the number of stones in each bin.
	 * We also redraw the "status message" at the bottom of the screen.
	 * 
	 * @param g The graphics context for the window into which we are drawing. 
	 */
	public void paint(Graphics g) {
		// set up the drawing tools and capabilities:
		BasicStroke penStroke;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setBackground(Color.WHITE);
		g2d.clearRect(0, 0, getWidth(), getHeight());	// Erase whatever was previously there.
		penStroke = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(penStroke);
		g2d.setColor( currentLineColor );
		g2d.setFont(drawFont);
		
		g2d.drawString("Wari", 200, 35);
		g2d.drawRect(75, 100, 400, 100);
		File file = new File("src/Bean-01.gif");
		Image img;
		try {
			img = ImageIO.read(file);
			g2d.drawImage(img, 75, 20, 20, 20, Color.WHITE, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Clear the canvas, then redraw the board and status information.
	 */
	public void redrawCanvas( ) {
		invalidate();
		repaint();
	}


}