package ControllerTools;

import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

import ControllerTools.AbstractPaintTool;
import Model.ToolType;

public class LineTool extends AbstractPaintTool {
	
    public LineTool() {
		super(ToolType.LINE, KeyEvent.VK_L);
		
	}

	@Override
    public Shape getShape() {
        return new Line2D.Double(getStartPoint(), getEndPoint());
    }

}

