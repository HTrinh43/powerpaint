package ControllerTools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import Model.ToolType;

public class RectangleTool extends AbstractPaintTool {

    public RectangleTool() {
		super(ToolType.RECTANGLE, KeyEvent.VK_R);
	}

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
