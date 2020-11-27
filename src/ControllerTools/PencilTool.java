package ControllerTools;

import java.awt.Shape;
import java.awt.geom.Line2D;

import Model.ToolType;

public class PencilTool extends AbstractPaintTool {
	
    public PencilTool(ToolType theName, int theMnemonic) {
		super(theName, theMnemonic);
		
	}

	@Override
    public Shape getShape() {
        return new Line2D.Double(getStartPoint(), getEndPoint());
    }

}
