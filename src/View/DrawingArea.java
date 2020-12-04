package View;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
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

import Model.ShapeModel;
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
    private static int myCursorThickness;
    
    /** The minimum size this component should be. */
    private static final Dimension MIN_SIZE = new Dimension(WIDTH, HEIGHT);

    /** The squares starting center x. */
    private static final int START_X;
        
    /** The squares starting center y. */
    private static final int START_Y;
    
    private static final String PROPERTY_CLEAR = "CLEAR";
    
    private int myThickness;
    
    private Color myBackgroundColor;
    
    private PaintTool myCurrentTool;

    private Color myPrimaryColor;
    private final List<ShapeModel> myPreviousShapes;
    
    static {
        START_X = WIDTH / 2 - myCursorThickness / 2;
        START_Y = HEIGHT / 2 - myCursorThickness / 2;
    }

	private Color mySecondaryColor;
	
	private Color myCurrentColor;
    
    public DrawingArea() {
        super();
        setBackground(Color.white);
        setOpaque(true);

        myPreviousShapes = new ArrayList<>();

        
        myCurrentTool = null;
        
        myThickness = 10;
        
        myBackgroundColor = this.getBackground();
        
        myCurrentColor = UWColors.PURPLE.getColor();
        myPrimaryColor = UWColors.PURPLE.getColor();
        mySecondaryColor = UWColors.GOLD.getColor();
        
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

		g2d.setColor(myCurrentColor);
        //line thickness
    	if (myCurrentTool.toString() != ToolType.ERASER.toString()) {
    		g2d.setColor(myPrimaryColor);
    	}
    	for (ShapeModel s : myPreviousShapes) {
    		g2d.setStroke(new BasicStroke(s.getThickness()));
    		g2d.setColor(s.getColor());
    		g2d.draw(s.getShape());
    	}
        g2d.draw(myCurrentTool.getShape());
        
        firePropertyChange(PROPERTY_CLEAR, 
        		(myPreviousShapes.size() == 0), !(myPreviousShapes.size() == 0));
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
    
	public void setSecondaryColor(final Color theColor) {
		mySecondaryColor = theColor;
	}
    
    public void setThickness(final int theThickness) {
    	myThickness = theThickness;

    }
    
    public void clear() {
    	myPreviousShapes.clear();
    	repaint();
    }

    /**
     * Mouse Adapter to handle Mouse Events and move the square around. 
     */
    class MyMouseInputAdapter extends MouseInputAdapter {
        
    	
    	private boolean zeroThickness() {
    		return myThickness == 0;
    	}
    	/**
    	 * 
    	 * @param theEvent: Mouse Event
    	 * @return color depending on right or left mouse
    	 */
    	private void detectMouseButton(final MouseEvent theEvent) {

            //detect left or right mouse
             if (theEvent.getButton() == MouseEvent.BUTTON3) {
             	myCurrentColor = mySecondaryColor;
             }
             else
            	 myCurrentColor = myPrimaryColor;

    	}

        @Override
        public void mousePressed(final MouseEvent theEvent) {  
        	if (zeroThickness())
        		return;
            myCurrentTool.setStartPoint(theEvent.getPoint());
            repaint();
        }
        
        @Override
        public void mouseMoved(final MouseEvent theEvent) {
        	setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            repaint();
        }
        
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
        	if (zeroThickness())
        		return;
            myCurrentTool.setEndPoint(theEvent.getPoint());
            detectMouseButton(theEvent);
            Color color = myCurrentColor;
//change == to Equals
            if (myCurrentTool.getName() == ToolType.PENCIL.getTool().getName()) {
            	myPreviousShapes.add(new ShapeModel(myCurrentTool.getShape(), color, myThickness));
            	myCurrentTool.setStartPoint(theEvent.getPoint());
            }
            else if (myCurrentTool.getName() == ToolType.ERASER.getTool().getName()) {
            	myPreviousShapes.add(new ShapeModel(myCurrentTool.getShape(), myBackgroundColor, myThickness));
            	myCurrentTool.setStartPoint(theEvent.getPoint());
            }
            repaint();
        }
        
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
        	if (zeroThickness())
        		return;
            detectMouseButton(theEvent);
            Color color = myCurrentColor;
            if (myCurrentTool.getName() == ToolType.LINE.getTool().getName() 
            		|| myCurrentTool.getName() == ToolType.ELLIPSE.getTool().getName()
            		|| myCurrentTool.getName() == ToolType.RECTANGLE.getTool().getName()) {
            	myPreviousShapes.add(new ShapeModel(myCurrentTool.getShape(), color, myThickness));
        }
        }
        
        /**
         * Returns a new Point that is the center of the square based
         * on the theEvent's point being the top left corner of the square. 
         * @param theEvent the mouse event with the Point of the top left 
         * corner of the square
         * @return a new Point that is the center of the square
         */
        private Point getCenterPoint(final MouseEvent theEvent) {
            final int x = theEvent.getX() - myCursorThickness / 2; 
            final int y = theEvent.getY() - myCursorThickness / 2; 
            return new Point(x, y);
        }
    }

}
