package ControllerTools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import Model.ToolType;

/**
 * A tool for drawing Rectangle.
 * 
 * @author Alex Trinh, Natalie Nguyen Hong
 * @version Fall 2020
 */
public class RectangleTool extends AbstractPaintTool {

	/**
	 * Constructs a rectangle tool.
	 */
    public RectangleTool() {
		super(ToolType.RECTANGLE, KeyEvent.VK_R);
	}

    /**
     * Returns the shape drawn by this tool.
     * 
     * @return the shape drawn by this tool.
     */
	@Override
    public Shape getShape() {
		Point startPoint = getStartPoint();
		Point endPoint = getEndPoint();
		Point centerPoint = new Point((int)(startPoint.getX()+endPoint.getX())/2,(int)(startPoint.getY()+endPoint.getY())/2);
        final Rectangle2D.Double rectangle = new Rectangle2D.Double();
        rectangle.setFrameFromCenter(centerPoint, endPoint);
        return rectangle;
    }
}
