package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import Model.UWColors;
//import toolbar.DrawingArea.MyMouseInputAdapter;
//import toolbar.DrawingArea;
import View.DrawingArea.MyMouseInputAdapter;

public class DrawingArea extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 844017799742180066L;

    /** The default width value. */
    private static final int WIDTH = 500; 
    
    /** The default height value. */
    private static final int HEIGHT = 500;

    /** The length of the square's sides. */
    private static final int SQUARE_SIDE = 5;
    
    /** The minimum size this component should be. */
    private static final Dimension MIN_SIZE = new Dimension(WIDTH, HEIGHT);

    /** The squares starting center x. */
    private static final int START_X;
    
    
    /** The squares starting center y. */
    private static final int START_Y;
    
    
    static {
        START_X = WIDTH / 2 - SQUARE_SIDE / 2;
        START_Y = HEIGHT / 2 - SQUARE_SIDE / 2;
    }
    private Point mySquareCenterPoint;
    
    public DrawingArea() {
        super();
        setBackground(Color.white);
        setOpaque(true);
        mySquareCenterPoint = new Point(START_X, START_Y);
        
        final MouseInputAdapter mia = new MyMouseInputAdapter();
        addMouseListener(mia);
        addMouseMotionListener(mia);
    }
    
    @Override
    protected void paintComponent(final Graphics theGraphics) {
        //MAKE SURE you call the parent paintComponenet
        super.paintComponent(theGraphics);
        
        //cast Graphics object to the "newer-ish" Graphics2D class.
        final Graphics2D g2d = (Graphics2D) theGraphics;
        
        // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        //Starting Painting things!
        final Rectangle2D square = new Rectangle2D.Double(
                      mySquareCenterPoint.getX(), mySquareCenterPoint.getY(), 
                      SQUARE_SIDE, SQUARE_SIDE);
        
        g2d.fill(square);

    }
    @Override
    public Dimension getMinimumSize() {
        return MIN_SIZE;
    }
 
    @Override
    public Dimension getPreferredSize() {
        return MIN_SIZE;
    }
    
    /**
     * Mouse Adapter to handle Mouse Events and move the square around. 
     */
    class MyMouseInputAdapter extends MouseInputAdapter {
        

        @Override
        public void mousePressed(final MouseEvent theEvent) {
            mySquareCenterPoint = getCenterPoint(theEvent);
            repaint();
        }

        
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            mySquareCenterPoint = getCenterPoint(theEvent);
            repaint();
        }
        
//        @Override
//        public void mouseReleased(final MouseEvent theEvent) {
//            mySquareCenterPoint = getCenterPoint(theEvent);
//            repaint();
//        }
        
        /**
         * Returns a new Point that is the center of the square based
         * on the theEvent's point being the top left corner of the square. 
         * @param theEvent the mouse event with the Point of the top left 
         * corner of the square
         * @return a new Point that is the center of the square
         */
        private Point getCenterPoint(final MouseEvent theEvent) {
            final int x = theEvent.getX() - SQUARE_SIDE / 2; 
            final int y = theEvent.getY() - SQUARE_SIDE / 2; 
            return new Point(x, y);
        }
    }
}