package ControllerTools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

import ControllerTools.AbstractPaintTool;
import Model.ToolType;

/**
 * A tool for drawing Ellipses.
 * 
 * @author Alex Trinh, Natalie Nguyen Hong
 * @version Fall 2020
 */
public class EllipseTool extends AbstractPaintTool {

	/**
	 * Constructs an Ellipse tool.
	 */
    public EllipseTool() {
		super(ToolType.ELLIPSE, KeyEvent.VK_E);
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
//		Point centerPoint = new Point((int)endPoint.getX()
//				,(int)(startPoint.getY()));
        final Ellipse2D.Double circle = new Ellipse2D.Double();
//        circle.setFrameFromCenter(centerPoint, endPoint);
        circle.setFrameFromDiagonal(startPoint, endPoint);

        return circle;
    }
}
