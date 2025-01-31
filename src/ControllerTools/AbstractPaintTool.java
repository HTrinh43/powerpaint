package ControllerTools;

import java.awt.Point;
import java.awt.Shape;
import Model.ToolType;

/**
 * Provides common implements of some paint tool behaviors.
 * 
 * @author Alex Trinh, Natalie Nguyen Hong
 * @version Fall 2020
 */
public class AbstractPaintTool implements PaintTool {
	
	/** A point outside the drawing area. */
    public static final Point NO_POINT = new Point(-50, -50);
    
    /** The tool name. */
    protected final ToolType myName;
    
    /** The mnemonic for the tool. */
    private final int myMnemonic;
    
    /** The initial anchor point for the shape drawn by this tool. */
	private Point myStartPoint;
	
	/** The end point for the shape drawn by this tool. */
	private Point myEndPoint;

	/**
	 * Constructs a paint tool.
	 * 
	 * @param theName the name of the tool
	 * @param theMnemonic the mnemonic for the tool
	 */
    public AbstractPaintTool(final ToolType theName, final int theMnemonic) {
		myName = theName;
		myMnemonic = theMnemonic;
		myStartPoint = NO_POINT;
        myEndPoint = NO_POINT;
    }

    /**
     * Returns the name of this tool.
     * 
     * @return the name of this tool
     */
	@Override
	public String getName() {
		return myName.toString();
	}

	/**
	 * Returns the mnemonic for this tool.
	 * 
	 * @return the mnemonic for this tool
	 */
	@Override
	public int getMnemonic() {
		return myMnemonic;
	}

	/**
	 * Sets the start point for this tool.
	 * 
	 * @param thePoint the start point for this tool
	 */
	@Override
	public void setStartPoint(Point thePoint) {
		myStartPoint = thePoint;
        myEndPoint = thePoint;
	}

	/**
	 * Returns the start point for this tool.
	 * 
	 * @return the start point for this tool
	 */
	@Override
	public Point getStartPoint() {
		return myStartPoint;
	}

	/**
	 * Resets the start point for this tool.
	 */
	@Override
	public void reset() {
		setStartPoint(NO_POINT);
    }
    
	/**
	 * Returns the shape drawn by this tool.
	 * 
	 * @return the shape draw by this tool
	 */
    @Override
    public Shape getShape() {
        return null;
    }

    /**
     * Sets the end point for this tool
     * 
     * @param thePoint the end point for this tool
     */
    @Override
    public void setEndPoint(Point thePoint){
        myEndPoint = thePoint;
    }
    
    /**
     * Returns the end point for this tool.
	 * 
	 * @return the end point for this tool
     */
    @Override
    public Point getEndPoint(){
    	return myEndPoint;
    }

}
