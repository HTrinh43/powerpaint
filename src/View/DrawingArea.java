package View;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import Model.ToolType;
import Model.UWColors;
//import toolbar.DrawingArea.MyMouseInputAdapter;
//import toolbar.DrawingArea;
import View.DrawingArea.MyMouseInputAdapter;
import ControllerTools.EllipseTool;
import ControllerTools.LineTool;
import ControllerTools.PaintTool;
import ControllerTools.PencilTool;
import ControllerTools.RectangleTool;

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
    
    private PaintTool myCurrentTool;
//    private LineTool myLineTool;
//    private PencilTool myPencilTool;
//    private EllipseTool myEllipseTool;
//    private RectangleTool myRectangleTool;
    private Color myPrimaryColor;
    private int count;
    private final List<Shape> myPreviousShapes;
    
    static {
        START_X = WIDTH / 2 - SQUARE_SIDE / 2;
        START_Y = HEIGHT / 2 - SQUARE_SIDE / 2;
    }
    private Point mySquareCenterPoint;
    
    public DrawingArea() {
        super();
        setBackground(Color.white);
        setOpaque(true);
        //setup tools
//        myPencilTool = new PencilTool(ToolType.PENCIL, KeyEvent.VK_P);
//        myLineTool = new LineTool(ToolType.LINE, KeyEvent.VK_L);
//        myEllipseTool = new EllipseTool(ToolType.ELLIPSE, KeyEvent.VK_E);
//        myRectangleTool = new RectangleTool(ToolType.RECTANGLE, KeyEvent.VK_R);
        //
        myPreviousShapes = new ArrayList<>();

        mySquareCenterPoint = new Point(START_X, START_Y);  
        
        myCurrentTool = null;
        
        myPrimaryColor = UWColors.PURPLE.getColor();
        final MouseInputAdapter mia = new MyMouseInputAdapter();
        addMouseListener(mia);
        addMouseMotionListener(mia);
        count = 0;
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

        //line thickness
    	g2d.setStroke(new BasicStroke(3));
    	for (Shape s : myPreviousShapes) {
    	g2d.setColor(myPrimaryColor);
        g2d.draw(s);
        System.out.println(myPreviousShapes.indexOf(s));
    	}
        g2d.draw(myCurrentTool.getShape());
//        count++;
    }
    
    @Override
    public Dimension getMinimumSize() {
        return MIN_SIZE;
    }
 
    @Override
    public Dimension getPreferredSize() {
        return MIN_SIZE;
    }
    
    public void setCurrentTool(final PaintTool theTool) {
        myCurrentTool = theTool;
    }
    
    public void setPrimaryColor(final Color theColor) {
    	myPrimaryColor = theColor;
    }
    /**
     * Mouse Adapter to handle Mouse Events and move the square around. 
     */
    class MyMouseInputAdapter extends MouseInputAdapter {
        

        @Override
        public void mousePressed(final MouseEvent theEvent) {
            mySquareCenterPoint = getCenterPoint(theEvent);
            myCurrentTool.setStartPoint(theEvent.getPoint());
            repaint();
        }

        
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            mySquareCenterPoint = getCenterPoint(theEvent);
            myCurrentTool.setEndPoint(theEvent.getPoint());
            
            if (myCurrentTool.getName() == ToolType.PENCIL.getTool()) {
            	myPreviousShapes.add(myCurrentTool.getShape());
            	myCurrentTool.setStartPoint(theEvent.getPoint());
            }
            repaint();
        }
        
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            mySquareCenterPoint = getCenterPoint(theEvent);
            
            if (myCurrentTool.getName() == ToolType.LINE.getTool() 
            		|| myCurrentTool.getName() == ToolType.ELLIPSE.getTool()
            		|| myCurrentTool.getName() == ToolType.RECTANGLE.getTool()) {
            	myPreviousShapes.add(myCurrentTool.getShape());
        }
//            repaint();
        }
        
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
