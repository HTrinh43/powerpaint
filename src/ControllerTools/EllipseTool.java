package ControllerTools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import ControllerTools.AbstractPaintTool;
import Model.ToolType;

public class EllipseTool extends AbstractPaintTool {

    public EllipseTool(ToolType theName, int theMnemonic) {
		super(theName, theMnemonic);
	}

	@Override
    public Shape getShape() {
		Point startPoint = getStartPoint();
		Point endPoint = getEndPoint();
		Point centerPoint = new Point((int)endPoint.getX()
				,(int)(startPoint.getY()));
        final Ellipse2D.Double circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(centerPoint, endPoint);
//        circle.setFrameFromDiagonal(startPoint, endPoint);

        return circle;
    }
}
