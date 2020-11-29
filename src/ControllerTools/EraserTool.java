package ControllerTools;

import java.awt.Shape;
import java.awt.geom.Line2D;

import Model.ToolType;

public class EraserTool extends AbstractPaintTool {
	
    public EraserTool(ToolType theName, int theMnemonic) {
		super(theName, theMnemonic);
		
	}

	@Override
    public Shape getShape() {
        return new Line2D.Double(getStartPoint(), getEndPoint());
    }
}
