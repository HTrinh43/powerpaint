package ControllerTools;

import java.awt.Shape;
import java.awt.geom.Line2D;

import ControllerTools.AbstractPaintTool;
import Model.ToolType;

public class LineTool extends AbstractPaintTool {
	
    public LineTool(ToolType theName, int theMnemonic) {
		super(theName, theMnemonic);
		
	}

	@Override
    public Shape getShape() {
        return new Line2D.Double(getStartPoint(), getEndPoint());
    }

}

