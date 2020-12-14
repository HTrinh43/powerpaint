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
import java.util.Stack;

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
/**
 * A Drawing Board that has 
 * 
 * @author Alex Trinh, Natalie Nguyen Hong
 * @version Fall 2020
 */
public class DrawingArea extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 844017799742180066L;

    /** The default width value. */
    private static final int WIDTH = 500; 
    
    /** The default height value. */
    private static final int HEIGHT = 500;

    /** The minimum size this component should be. */
    private static final Dimension MIN_SIZE = new Dimension(WIDTH, HEIGHT);

    
    private static final String PROPERTY_IS_CLEAR = "CLEAR";
    
    /**
     * Drawing line thickness
     */
    private int myThickness;
    /**
     * Drawing board background color
     */
    private Color myBackgroundColor;
    
    /**
     * Current Using Drawing Tool
     */
    private PaintTool myCurrentTool;

    /**
     * Primary Color (UW Purple by default), can be changed
     */
    private Color myPrimaryColor;
    
    /**
     * Secondary Color (UW Gold by default), can be changed
     */
    private Color mySecondaryColor;
    /**
     * Shapes List contains drawing shapes
     */
    
    private boolean myClickedClear;
    
    private final Stack<ShapeModel> myTempShapes;
    
    private final Stack<ShapeModel> myCurrentShapes;
    
    private final Stack<ShapeModel> myPreviousShapes;
    
    /**
     * Current Color for drawing tool (depending on left/right mouse click)
     */
	private Color myCurrentColor;
    
    public DrawingArea() {
        super();
        setBackground(Color.white);
        setOpaque(true);

        myCurrentShapes = new Stack<>();

        myPreviousShapes = new Stack<>();
        
        myTempShapes = new Stack<>();
        
        myCurrentTool = null;
        
        myThickness = 10;
        
        myClickedClear = false;
        
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
    	if(myCurrentShapes.size() == 0)
    		return;
    	else {
    		
		g2d.setColor(myCurrentColor);
        //line thickness
    	if (myCurrentTool.toString() != ToolType.ERASER.toString()) {
    		g2d.setColor(myPrimaryColor);
    	}
    	for (ShapeModel s : myCurrentShapes) {
    		g2d.setStroke(new BasicStroke(s.getThickness()));
    		g2d.setColor(s.getColor());
    		g2d.draw(s.getShape());
    	}
        g2d.draw(myCurrentTool.getShape());

        firePropertyChange(PROPERTY_IS_CLEAR, 
        		(myCurrentShapes.size() == 0), !(myCurrentShapes.size() == 0));
    	}
    	
        firePropertyChange("REDO_ENABLE", 
        		(myPreviousShapes.size() == 0), !(myPreviousShapes.size() == 0));
    	
        firePropertyChange("UNDO_IS_ENABLE", 
        		(myCurrentShapes.size() == 0 && myTempShapes.size() == 0), 
        		(myCurrentShapes.size() != 0 || myTempShapes.size() != 0));
//        
//        firePropertyChange("UNDO_IS_DISABLE", 
//        		(myCurrentShapes.size() != 0 || myTempShapes.size() != 0), 
//        		(myCurrentShapes.size() == 0 && myTempShapes.size() == 0));
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
     * 
     * @param theTool, set theTool to the current drawing tool
     */
    public void setCurrentTool(final PaintTool theTool) {
        myCurrentTool = theTool;

    }
    
    /**
     * 
     * @param set theColor to the Primary Color
     */
    public void setPrimaryColor(final Color theColor) {
    	myPrimaryColor = theColor;
    }
    
    /**
     * 
     * @param set theColor to the Secondary Color
     */
	public void setSecondaryColor(final Color theColor) {
		mySecondaryColor = theColor;
	}
    
    /**
     * 
     * @param set theThickness to the Current Line Thickness
     */
    public void setThickness(final int theThickness) {
    	myThickness = theThickness;
    }
    
    /**
     * A method that clear the drawing board
     */
    public void clear() {
    	myTempShapes.addAll(myCurrentShapes);
    	myCurrentShapes.clear();
    	myClickedClear = true;
    	repaint();
    }
    
    public void undo() {
    	if(myClickedClear) {
    		myCurrentShapes.addAll(myTempShapes);
    		myTempShapes.clear();
    		myClickedClear = !myClickedClear;
    	}
    	else {
    	myPreviousShapes.push(myCurrentShapes.pop());
    	repaint();
    	System.out.println(myCurrentShapes.size());
    	}
    }
    
    public void redo() {
    	myCurrentShapes.push(myPreviousShapes.pop());
    	repaint();
    }

    
    /**
     * Get the Shapes List
     */
    public List<ShapeModel> getList(){
    	return myCurrentShapes;
    }
    /**
     * 
     * @param theList, set the current list to the new list
     */
    public void setList(final List<ShapeModel> theList){
    	myCurrentShapes.clear();
    	myCurrentShapes.addAll(theList);
    	repaint();
    }
    
    /**
     * Mouse Adapter to handle Mouse Events and move the square around. 
     */
    class MyMouseInputAdapter extends MouseInputAdapter {
        
    	/**
    	 * Check if the Current Line thickness is 0
    	 * @return
    	 */
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

            if (myCurrentTool.getName().equals(ToolType.PENCIL.getTool().getName())) {
            	myCurrentShapes.push(new ShapeModel(myCurrentTool.getShape(), color, myThickness));
            	myCurrentTool.setStartPoint(theEvent.getPoint());
            }
            else if (myCurrentTool.getName().equals(ToolType.ERASER.getTool().getName())) {
            	myCurrentShapes.push(new ShapeModel(myCurrentTool.getShape(), myBackgroundColor, myThickness));
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
            if (myCurrentTool.getName().equals(ToolType.LINE.getTool().getName()) 
            		|| myCurrentTool.getName().equals(ToolType.ELLIPSE.getTool().getName())
            		|| myCurrentTool.getName().equals(ToolType.RECTANGLE.getTool().getName())) {
            	myCurrentShapes.push(new ShapeModel(myCurrentTool.getShape(), color, myThickness));
        }
        }
    }

}
