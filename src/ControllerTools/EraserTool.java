package ControllerTools;

import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

import Model.ToolType;

public class EraserTool extends AbstractPaintTool {
	
    public EraserTool() {
		super(ToolType.ERASER, KeyEvent.VK_A);
		
	}

	@Override
    public Shape getShape() {
        return new Line2D.Double(getStartPoint(), getEndPoint());
    }
}
