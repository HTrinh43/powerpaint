package ControllerTools;

import java.awt.Point;
import java.awt.Shape;
import Model.ToolType;

public class AbstractPaintTool implements PaintTool {
    public static final Point NO_POINT = new Point(-50, -50);
    private final ToolType myName;
    private final int myMnemonic;
	private Point myStartPoint;
	private Point myEndPoint;

    public AbstractPaintTool(final ToolType theName, final int theMnemonic) {
		myName = theName;
		myMnemonic = theMnemonic;
		myStartPoint = NO_POINT;
        myEndPoint = NO_POINT;
    }
    	/**
	 * 
	 */
	@Override
	public String getName() {
		return myName.getTool();
	}

	/**
	 * 
	 */
	@Override
	public int getMnemonic() {
		return myMnemonic;
	}

	/**
	 * 
	 */
	@Override
	public void setStartPoint(Point thePoint) {
		myStartPoint = thePoint;
        myEndPoint = thePoint;
	}

	/**
	 * 
	 */
	@Override
	public Point getStartPoint() {
		return myStartPoint;
	}

	/**
	 * 
	 */
	@Override
	public void reset() {
		setStartPoint(NO_POINT);
    }
    
    @Override
    public Shape getShape() {
        return null;
    }

    @Override
    public void setEndPoint(Point thePoint){
        myEndPoint = thePoint;
    }
    
    @Override
    public Point getEndPoint(){
    	return myEndPoint;
    }

}
