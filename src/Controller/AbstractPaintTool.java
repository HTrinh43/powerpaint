package Controller;

import java.awt.Point;
import java.awt.Shape;

public class AbstractPaintTool implements PaintTool {
    public static final Point NO_POINT = new Point(-50, -50);
    private final String myName;
    private final int myMnemonic;
	private Point myStartPoint;

    public AbstractPaintTool(final String theName, final int theMnemonic) {
		myName = theName;
		myMnemonic = theMnemonic;
		myStartPoint = NO_POINT;
    }
    	/**
	 * 
	 */
	@Override
	public String getName() {
		return myName;
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
    public void setNextPoint(Point thePoint){}

}
